/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.crear.actividad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Actividad;
import modelo.entidades.ExperienciaViaje;
import modelo.servicio.ServicioActividad;
import modelo.servicio.ServicioExperienciaViaje;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorCrearActividades", urlPatterns = {"/ControladorCrearActividades"})
public class ControladorCrearActividades extends HttpServlet {


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

        getServletContext().getRequestDispatcher("/usuario/crearActividad.jsp").forward(request, response);
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
        String tituloAct = request.getParameter("tituloAct");
        String descripcionAct = request.getParameter("descripcionAct");
        Date fechaInicioAct = java.sql.Date.valueOf(request.getParameter("fechaInicioAct"));
        String error = "";
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioActividad sa = new ServicioActividad(emf);
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        
        Actividad actividad = new Actividad();
        actividad.setTitulo(tituloAct);
        actividad.setDescripcion(descripcionAct);
        actividad.setFecha(fechaInicioAct);
        
        sa.create(actividad);
        
        Long id = Long.parseLong(request.getParameter("id"));
        ExperienciaViaje experienciaViaje = sve.findExperienciaViaje(id);
        if (experienciaViaje != null) {
            experienciaViaje.getActividades().add(actividad);
            try {
                sve.edit(experienciaViaje);
            } catch (Exception e) {
                error = "No se puede editar";
            }
        }
        emf.close();
        
        getServletContext().getRequestDispatcher("/usuario/crearActividad.jsp").forward(request, response);
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
