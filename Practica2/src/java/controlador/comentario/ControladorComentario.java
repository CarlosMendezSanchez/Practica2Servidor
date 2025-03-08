/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.comentario;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Opinion;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioExperienciaViaje;
import modelo.servicio.ServicioOpinion;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorComentario", urlPatterns = {"/usuario/ControladorComentario"})
public class ControladorComentario extends HttpServlet {


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
        // Redirigir al jsp /usuario/crearcomentario
        getServletContext().getRequestDispatcher("/usuario/crearComentario.jsp").forward(request, response);
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
        // Obtener el parametro de contenido del formulario del jsp de crearcomentario
        String contenido = request.getParameter("contenido");
        String error = "";
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        // Instanciar el servicio de opinion y de experiencia de viaje
        ServicioOpinion opiniones = new ServicioOpinion(emf);
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        
        // Obtener de la sesion el usuario. Obtener el id de la experiencia
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Long id = Long.parseLong(request.getParameter("idEx"));
        ExperienciaViaje experienciaViaje = sve.findExperienciaViaje(id);
        
        // Crear un objeto opinion con los datos de los parametros del formulario
        Opinion opinion = new Opinion();
        opinion.setContenido(contenido);
        opinion.setUsuario(usuario);
        opinion.setExperiencia(experienciaViaje);
        
        // Guardar en la base de datos la opinion
        opiniones.create(opinion);
        emf.close();
        
        // Redirigir a /usuario/crearcomentario.jsp 
        getServletContext().getRequestDispatcher("/usuario/crearComentario.jsp").forward(request, response);
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
