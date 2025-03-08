/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.login;

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
import modelo.entidades.Usuario;
import modelo.servicio.ServicioUsuario;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorLogin", urlPatterns = {"/ControladorLogin"})
public class ControladorLogin extends HttpServlet {

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
        // Redirige al jsp de login
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
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
        // Recoger los parametros del formulario de login.
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String error = "";
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            error = "El e-mail y la contraseña son obligatorios";
        } else {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica2PU");
            ServicioUsuario su = new ServicioUsuario(emf);
            // Validacion del usuario mediante el servicio de ServicioUsuario.
            Usuario usu = su.validarUsuario(email, password);
            emf.close();
            if (usu != null) {
                if (!usu.isActivo()){
                    error = "El usuario no esta activo";
                } else {
                // Guardar al usuario en la sesion
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usu);
                
            // Si el tipo de usuario es admin, reenvía al controlador admin/ControladorInicioAdmin, sino a usuario/ControladorInicio en caso de no ser admin.
                if("admin".equals(usu.getTipo())){
                    response.sendRedirect("admin/ControladorInicioAdmin");
                } else {
                    response.sendRedirect("usuario/ControladorInicio");
                }
                
                return;
                }
            } else {
                error = "e-mail o contraseña incorrectos";
            }
        }
        // Si hay un error, se reenvía a login.jsp.
        request.setAttribute("error", error);
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
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
