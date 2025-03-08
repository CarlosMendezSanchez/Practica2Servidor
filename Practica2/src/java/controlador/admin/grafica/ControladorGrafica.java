/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin.grafica;

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
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioExperienciaViaje;
import modelo.servicio.ServicioUsuario;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorGrafica", urlPatterns = {"/admin/ControladorGrafica"})
public class ControladorGrafica extends HttpServlet {

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
        
        // Instanciar el servicio de usuario y de experiencia de viaje
        ServicioUsuario su = new ServicioUsuario(emf);
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        
        // Obtener los usuario y experiencias en listas
        List<Usuario> usuario = su.findUsuarioEntities();        
        List<ExperienciaViaje> experiencia = sve.findExperienciaViajeEntities();
        
        // Agregar las listas de usuarios y experiencias de viaje como atributos.
        request.setAttribute("usuarios", usuario);
        request.setAttribute("experiencias", experiencia);

        // Si las fechas recogidas por parametros son nulas devuelve un listado de experiencias de viaje.
        if (request.getParameter("fechaInicio") == null || request.getParameter("fechaFin") == null) {
            List<ExperienciaViaje> experienciaViajeVacia = sve.findExperienciaViajeEntities();
            request.setAttribute("experiencias", experienciaViajeVacia);
        } else {
        // Si no son nulas, convierte las fechas recogidas por parametros de string a fecha en la base de datos
            Date fechaInicio = java.sql.Date.valueOf(request.getParameter("fechaInicio"));
            Date fechaFin = java.sql.Date.valueOf(request.getParameter("fechaFin"));
        // Lista las experiencias de viaje que se encuentren entre esas fechas
            List<ExperienciaViaje> experienciaViaje = sve.findExperienciasByFecha(fechaInicio, fechaFin);
        // Agregar la lista de experiencias como atributo
            request.setAttribute("experiencias", experienciaViaje);
        }
        
        emf.close();
        // Redirigir al jsp /admin/graficaUsuarios.jsp
        getServletContext().getRequestDispatcher("/admin/graficaUsuarios.jsp").forward(request, response);
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
