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
@WebServlet(name = "ControladorCrearActividades", urlPatterns = {"/usuario/ControladorCrearActividades"})
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
        // Redirigir al jsp /usuario/CrearActividad
        getServletContext().getRequestDispatcher("/usuario/crearActividad.jsp").forward(request, response);
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
        // Obtener los parametros del formulario de CrearActividad
        String tituloAct = request.getParameter("tituloAct");
        String descripcionAct = request.getParameter("descripcionAct");
        // Convierte la fecha de tipo string a tipo fecha para la base de datos.
        Date fechaInicioAct = java.sql.Date.valueOf(request.getParameter("fechaInicioAct"));
        String error = "";
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        // Instanciar el servicio de actividad y experiencia de viaje
        ServicioActividad sa = new ServicioActividad(emf);
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        
        // Crear objeto actividad y asignar los datos del formulario
        Actividad actividad = new Actividad();
        actividad.setTitulo(tituloAct);
        actividad.setDescripcion(descripcionAct);
        actividad.setFecha(fechaInicioAct);
        
        // Guarda en la base de datos la creacion de la actividad
        sa.create(actividad);
        
        /*
        * Obtener el id de la experiencia de viaje y si existe, añadirle la actividad al listado de experiencias de viaje
        * Editar y guardar en la base de datos el cambio de experiencia de viaje
        */
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
        
        // Redirigir al jsp /usuario/crearActividad.jsp
        getServletContext().getRequestDispatcher("/usuario/crearActividad.jsp").forward(request, response);
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
