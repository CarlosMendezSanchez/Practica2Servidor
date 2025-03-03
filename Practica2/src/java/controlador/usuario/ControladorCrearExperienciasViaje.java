/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.ExperienciaViaje;
import modelo.servicio.ServicioExperienciaViaje;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorCrearExperienciasViaje", urlPatterns = {"/ControladorCrearExperienciasViaje"})
public class ControladorCrearExperienciasViaje extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/usuario/crearExperienciaViajes.jsp").forward(request, response);
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
        String fechaInicio = request.getParameter("fechaInicio");
        LocalDate fecha = LocalDate.parse(fechaInicio);
        boolean publicada = Boolean.parseBoolean(request.getParameter("publicada"));
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        
        ExperienciaViaje experienciaViaje = new ExperienciaViaje();
        experienciaViaje.setTitulo(titulo);
        experienciaViaje.setDescripcion(descripcion);
        
        
        
        getServletContext().getRequestDispatcher("/usuario/inicio.jsp").forward(request, response);
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
