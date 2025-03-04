/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.inicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioExperienciaViaje;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorInicio", urlPatterns = {"/usuario/ControladorInicio"})
public class ControladorInicio extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        List<ExperienciaViaje> experienciaViajes = sve.findExperienciaViajeEntities();
        request.setAttribute("experienciaViajes", experienciaViajes);
        String error = ""; 
        
        String accion = request.getParameter("accion");
        
        if ("crear".equals(accion)) {
            getServletContext().getRequestDispatcher("/usuario/crearExperienciaViajes.jsp").forward(request, response);
            return;
        }
        
        try {
            if ("eliminar".equals(accion)){
                Long id = Long.parseLong(request.getParameter("id"));
                ServicioExperienciaViaje servicioExperienciaViaje = new ServicioExperienciaViaje(Persistence.createEntityManagerFactory("Practica2PU"));
                servicioExperienciaViaje.destroy(id);
                experienciaViajes = servicioExperienciaViaje.findExperienciaViajeEntities();
                request.setAttribute("experienciaViajes", experienciaViajes);
            }
        }  catch (Exception e) {
            request.setAttribute("error", error);
        }
        
        getServletContext().getRequestDispatcher("/usuario/inicio.jsp").forward(request, response);
        return;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        Date fechaInicio = java.sql.Date.valueOf(request.getParameter("fechaInicio"));
        boolean publicada = Boolean.parseBoolean(request.getParameter("publicada"));
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        
        ExperienciaViaje experienciaViaje = new ExperienciaViaje();
        experienciaViaje.setTitulo(titulo);
        experienciaViaje.setDescripcion(descripcion);
        experienciaViaje.setFechaInicio(fechaInicio);
        experienciaViaje.setPublicada(publicada);

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        experienciaViaje.setUsuario(usuario);
        sve.create(experienciaViaje);
        emf.close();
        
        getServletContext().getRequestDispatcher("/usuario/crearExperienciaViajes.jsp").forward(request, response);
        return;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
