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
import javax.servlet.http.HttpSession;
import modelo.entidades.Actividad;
import modelo.entidades.ExperienciaViaje;
import modelo.servicio.ServicioActividad;
import modelo.servicio.ServicioExperienciaViaje;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorActividad", urlPatterns = {"/usuario/ControladorActividad"})
public class ControladorActividad extends HttpServlet {


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
        /*
        * Obtener el id de actividad y el id de experiencia de viajes
        * Instanciar el servicio de actividad y experiencia de viajes
        * Obtener las actividades de la base de datos en una lista
        * Agregar como atributo el listado de la actividad
        */
        Long id = Long.parseLong(request.getParameter("id"));
        Long idEx = Long.parseLong(request.getParameter("idEx"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioActividad sa = new ServicioActividad(emf);
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        List<Actividad> actividad = sa.findActividadEntities();
        request.setAttribute("actividad", actividad);
        String error = "";
        
        String accionAct = request.getParameter("accionAct");
        
        try {
        // Si el parametro de accionAct es igual a eliminar, busca el id de la experiencia de viaje y el de actividad
            if ("eliminar".equals(accionAct)) {               
                ExperienciaViaje experienciaViaje = sve.findExperienciaViaje(idEx);
                Actividad actividades = sa.findActividad(id);
        // Si existe experiencia de viaje, obtiene la actividad y la elimina para luego actualizar las actividades de esa experiencia
                    if (experienciaViaje != null) {
                        List<Actividad> actividadesExperiencia = experienciaViaje.getActividades();   
                        actividadesExperiencia.remove(actividades);
                        experienciaViaje.setActividades(actividadesExperiencia);
                            try {
        // Edita y guarda los cambios de la experiencia de viaje en la base de datos.
                                sve.edit(experienciaViaje);
                            } catch (Exception e) {
                                request.setAttribute("error","No se puede editar la lista al intentar eliminar la actividad");
                            }
                    }
        // Se elimina la actividad de la base de datos y se actualiza el listado de actividad
            sa.destroy(id);
            actividad = sa.findActividadEntities();
            request.setAttribute("actividad", actividad);  
            }
        } catch (Exception e) {
            request.setAttribute("error","No se puede eliminar la actividad");
        }
        
        emf.close();
        // Redirigir al jsp /usuario/ControladorInicio
        getServletContext().getRequestDispatcher("/usuario/ControladorInicio").forward(request, response);
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
