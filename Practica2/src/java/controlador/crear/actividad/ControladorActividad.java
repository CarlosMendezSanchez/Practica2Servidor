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
            if ("eliminar".equals(accionAct)) {               
                ExperienciaViaje experienciaViaje = sve.findExperienciaViaje(idEx);
                Actividad actividades = sa.findActividad(id);
                    if (experienciaViaje != null) {
                        List<Actividad> actividadesExperiencia = experienciaViaje.getActividades();   
                        actividadesExperiencia.remove(actividades);
                        experienciaViaje.setActividades(actividadesExperiencia);
                            try {
                                sve.edit(experienciaViaje);
                            } catch (Exception e) {
                                request.setAttribute("error","No se puede editar la lista al intentar eliminar la actividad");
                            }
                    }
            sa.destroy(id);
            actividad = sa.findActividadEntities();
            request.setAttribute("actividad", actividad);  
            emf.close();
            }
        } catch (Exception e) {
            request.setAttribute("error","No se puede eliminar la actividad");
        }
        
        getServletContext().getRequestDispatcher("/usuario/ControladorInicio").forward(request, response);
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
