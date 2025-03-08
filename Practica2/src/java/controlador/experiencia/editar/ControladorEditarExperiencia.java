/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.experiencia.editar;

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
import javax.servlet.http.HttpSession;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioExperienciaViaje;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorEditarExperiencia", urlPatterns = {"/usuario/ControladorEditarExperiencia"})
public class ControladorEditarExperiencia extends HttpServlet {

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
        // Instanciar el servicio de experiencias y obtener la lista de experiencias de la base de datos.
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        // Obtener el id de la experiencia de viaje
        Long id = Long.parseLong(request.getParameter("id"));
        ExperienciaViaje experiencia = sve.findExperienciaViaje(id);
        // Agregar como atributo la experiencia de viaje
        request.setAttribute("experiencia", experiencia);
        
        
        emf.close();
        // Redirigir al jsp /usuario/editarExperiencia
        getServletContext().getRequestDispatcher("/usuario/editarExperiencia.jsp").forward(request, response);
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
        // Obtener los parametros del formulario de editar las experiencias de viaje.
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        // Convierte la fecha de tipo string a tipo fecha para la base de datos.
        Date fechaInicio = java.sql.Date.valueOf(request.getParameter("fechaInicio"));
        // Comprobar que el atributo publicada de tipo checkbox esté marcado, si lo está el booleano se pasa a true.
        boolean publicada = false;
        if (request.getParameter("publicada") != null){
            publicada = true;
        }
        String error = "";
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        // Instanciar el servicio de experiencia de viajes.
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        
        // Obtener el id de la experiencia de viaje.
        Long id = Long.parseLong(request.getParameter("id"));
        ExperienciaViaje experiencia = sve.findExperienciaViaje(id);
        
        // Actualizar los datos de la experiencia de viaje en caso de que la experiencia no se nula.
        if (experiencia != null){
            experiencia.setTitulo(titulo);
            experiencia.setDescripcion(descripcion);
            experiencia.setFechaInicio(fechaInicio);
            experiencia.setPublicada(publicada);
            
            try {
            // Editar y guardar los cambios de la experiencia en la base de datos.
                sve.edit(experiencia);
            } catch (Exception ex) {
                error = "No se ha podido editar";
            }
        }
        
        emf.close();
        // Redirigir al jsp /usuario/editarExperiencia.jsp
        getServletContext().getRequestDispatcher("/usuario/editarExperiencia.jsp").forward(request, response);
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
