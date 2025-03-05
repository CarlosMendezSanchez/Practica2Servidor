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
@WebServlet(name = "ControladorEditarEA", urlPatterns = {"/ControladorEditarEA"})
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
        ServicioActividad sa = new ServicioActividad(emf);
        Long id = Long.parseLong(request.getParameter("id"));
        Actividad actividad = sa.findActividad(id);
        request.setAttribute("actividad", actividad);
        getServletContext().getRequestDispatcher("/usuario/editarActividad.jsp").forward(request, response);
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
        
        try {
            Long idAct = Long.parseLong(request.getParameter("id"));
            Actividad actividad = sa.findActividad(idAct);
            
            if (actividad != null){
                actividad.setTitulo(tituloAct);
                actividad.setDescripcion(descripcionAct);
                actividad.setFecha(fechaInicioAct);
                
                sa.edit(actividad);
                emf.close();
            }
        } catch (Exception e) {
            error = "No se puede editar";
        }
        
        getServletContext().getRequestDispatcher("/usuario/editarActividad.jsp").forward(request, response);
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
