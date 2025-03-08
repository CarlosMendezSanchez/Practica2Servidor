/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.crear.actividad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Actividad;
import modelo.servicio.ServicioActividad;
import modelo.servicio.ServicioExperienciaViaje;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorEditarEA", urlPatterns = {"/usuario/ControladorEditarEA"})
public class ControladorEditarEA extends HttpServlet {


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
        // Instanciar el servicio de actividad
        ServicioActividad sa = new ServicioActividad(emf);
        // Obtener el id de la actividad
        Long id = Long.parseLong(request.getParameter("id"));
        Actividad actividad = sa.findActividad(id);
        request.setAttribute("actividad", actividad);
        
        emf.close();
        // Redirigir al jsp /usuario/editarActividad.jsp
        getServletContext().getRequestDispatcher("/usuario/editarActividad.jsp").forward(request, response);
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
        // Obtener del formulario de editar actividad los parametros
        String tituloAct = request.getParameter("tituloAct");
        String descripcionAct = request.getParameter("descripcionAct");
        // Convierte la fecha de tipo string a tipo fecha para la base de datos.
        Date fechaInicioAct = java.sql.Date.valueOf(request.getParameter("fechaInicioAct"));
        String error = "";
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        // Instanciar el servicio de actividad y de experiencia viaje
        ServicioActividad sa = new ServicioActividad(emf);
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        
        try {
            // Obtener el id de la actividad
            Long idAct = Long.parseLong(request.getParameter("id"));
            Actividad actividad = sa.findActividad(idAct);
            
            // Si la actividad existe, se actualiza la actividad
            if (actividad != null){
                actividad.setTitulo(tituloAct);
                actividad.setDescripcion(descripcionAct);
                actividad.setFecha(fechaInicioAct);
                
                // Editar y guardar los cambios en la base de datos
                sa.edit(actividad);
            }
        } catch (Exception e) {
            error = "No se puede editar";
        }
        
        emf.close();
        // Redirigir al jsp /usuario/editarActividad.jsp
        getServletContext().getRequestDispatcher("/usuario/editarActividad.jsp").forward(request, response);
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
