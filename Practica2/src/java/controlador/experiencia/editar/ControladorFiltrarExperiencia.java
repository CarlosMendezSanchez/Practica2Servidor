/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.experiencia.editar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Opinion;
import modelo.servicio.ServicioExperienciaViaje;
import modelo.servicio.ServicioOpinion;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorFiltrarExperiencia", urlPatterns = {"/ControladorFiltrarExperiencia"})
public class ControladorFiltrarExperiencia extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String filtro = request.getParameter("filtro");
        if (filtro == null) {
            filtro = "";
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
        ServicioExperienciaViaje sve = new ServicioExperienciaViaje(emf);
        ServicioOpinion opiniones = new ServicioOpinion(emf);
        List<ExperienciaViaje> exViaje = sve.findExperienciaViajeEntities();
        List<Opinion> listaOpiniones = opiniones.findOpinionEntities();
        request.setAttribute("listaOpiniones", listaOpiniones);
        emf.close();
        filtro = filtro.toLowerCase().trim();
        List<ExperienciaViaje> filtrados = new ArrayList();
        for (ExperienciaViaje exV: exViaje) {
            if (exV.getTitulo().toLowerCase().contains(filtro) || 
                    exV.getDescripcion().toLowerCase().contains(filtro)) {
                filtrados.add(exV);
            }
        }
        request.setAttribute("experienciaViajes", filtrados);
        getServletContext().getRequestDispatcher("/usuario/filtrarExperiencia.jsp").forward(request, response);
    }
    

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
        processRequest(request, response);
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
        processRequest(request, response);
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
