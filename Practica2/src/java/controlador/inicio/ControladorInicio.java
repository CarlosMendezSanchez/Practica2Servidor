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
import modelo.entidades.Opinion;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioExperienciaViaje;
import modelo.servicio.ServicioOpinion;

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
        // Instanciar el servicio de ExperienciaViaje y de Opinion.
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        ServicioOpinion opiniones = new ServicioOpinion(emf);
        // Obtener en forma de lista las experiencias y las opiniones de la base de datos.
        List<ExperienciaViaje> experienciaViajes = sve.findExperienciaViajeEntities();
        List<Opinion> listaOpiniones = opiniones.findOpinionEntities();
        // Agregar las listas mediante atributos para el archivo jsp.
        request.setAttribute("experienciaViajes", experienciaViajes);
        request.setAttribute("listaOpiniones", listaOpiniones);
        String error = ""; 
        
        String accion = request.getParameter("accion");
        
        // Si el parametro de accion es igual a crear, reenvia a /usuario/crearExperienciaViajes.jsp
        if ("crear".equals(accion)) {
            getServletContext().getRequestDispatcher("/usuario/crearExperienciaViajes.jsp").forward(request, response);
            return;
        }
        
        try {
        /*
        * Si el parametro de accion es igual a eliminar, recoge el id de la experiencia que se va a eliminar
        * Elimina la experiencia de viaje y luego actualiza la lista de experiencias.
        */
            if ("eliminar".equals(accion)){
                Long id = Long.parseLong(request.getParameter("id"));                
                sve.destroy(id);
                experienciaViajes = sve.findExperienciaViajeEntities();
                request.setAttribute("experienciaViajes", experienciaViajes);
            }
        }  catch (Exception e) {
            // Si no se puede eliminar la experiencia se envia un mensaje de error
            request.setAttribute("error","No se puede eliminar porque la experiencia tiene una actividad asociada");
        }
        
        emf.close();
        // Redirigir a /usuario/inicio.jsp
        getServletContext().getRequestDispatcher("/usuario/inicio.jsp").forward(request, response);
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
        // Obtener los parametros del formulario de creacion de experiencias de viajes.
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        Date fechaInicio = java.sql.Date.valueOf(request.getParameter("fechaInicio"));
        boolean publicada = Boolean.parseBoolean(request.getParameter("publicada"));
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        // Instanciar el servicio de ServicioExperienciaViaje
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        
        // Crear objeto experienciaViaje con los datos proporcionados por el formulario
        ExperienciaViaje experienciaViaje = new ExperienciaViaje();
        experienciaViaje.setTitulo(titulo);
        experienciaViaje.setDescripcion(descripcion);
        experienciaViaje.setFechaInicio(fechaInicio);
        experienciaViaje.setPublicada(publicada);

        // Obtener al usuario de la sesion y asociarlo con la experiencia de viaje.
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        experienciaViaje.setUsuario(usuario);
        // Crear la experiencia de viaje en la base de datos.
        sve.create(experienciaViaje);
        emf.close();
        
        getServletContext().getRequestDispatcher("/usuario/crearExperienciaViajes.jsp").forward(request, response);
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
