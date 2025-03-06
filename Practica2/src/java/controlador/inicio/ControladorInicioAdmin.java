/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.inicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Usuario;
import modelo.servicio.ServicioUsuario;
import modelo.servicio.exceptions.NonexistentEntityException;
import utilidades.Email;
import utilidades.Utilidades;

/**
 *
 * @author carlos
 */
@WebServlet(name = "ControladorInicioAdmin", urlPatterns = {"/admin/ControladorInicioAdmin"})
public class ControladorInicioAdmin extends HttpServlet {

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
        ServicioUsuario svu = new ServicioUsuario(emf);
        List<Usuario> usuario = svu.findUsuarioEntities();
        request.setAttribute("usuarios", usuario);
        String accion = request.getParameter("accion");
        
        String error = "";
        
        try {  
            if ("editar".equals(accion)){
                Long id = Long.parseLong(request.getParameter("id"));
                Usuario usuarioEditar = svu.findUsuario(id);
                request.setAttribute("usuariosLista", usuarioEditar);
                getServletContext().getRequestDispatcher("/admin/editarUsuario.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.setAttribute("error", "No se puede editar usuarios");
        }
        
        try {
            if ("eliminar".equals(accion)) {  
                Long id = Long.parseLong(request.getParameter("id"));
                svu.destroy(id);
                usuario = svu.findUsuarioEntities();
                request.setAttribute("usuarios", usuario);
                getServletContext().getRequestDispatcher("/admin/inicio.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "No se puede eliminar el usuario porque tiene experiencias");
        }
        
        getServletContext().getRequestDispatcher("/admin/inicio.jsp").forward(request, response);
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
        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String tipo = request.getParameter("tipo");
        String password = request.getParameter("password");
        boolean activo = request.getParameter("activar") != null;
        String error = "";
        
    ServicioUsuario servicioUsuario = new ServicioUsuario(Persistence.createEntityManagerFactory("Practica2PU"));
    Usuario usuario = servicioUsuario.findUsuario(id);

    if (usuario != null) {
        usuario.setEmail(email);
        usuario.setTipo(tipo);
        usuario.setPassword(password);
        usuario.setActivo(activo);

        try {
            servicioUsuario.edit(usuario);
            
            // Si el usuario está activo, enviar correo de activación
            if (activo) {
                String from = "mendez.sanchez.carlos@iescamas.es";
                String emailPassword = "gigh kher umyv erkh";

                Email correo = new Email();
                correo.setTo(usuario.getEmail());
                correo.setFrom(from);
                correo.setSubject("Tu cuenta ha sido activada");
                correo.setText("Hola " + usuario.getEmail() + ",\n\nTu cuenta ha sido activada. ¡Bienvenido!");

                Utilidades util = new Utilidades();
                try {
                    util.enviarEmail(correo, emailPassword);
                } catch (Exception e) {
                    request.setAttribute("error", error);
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", error);
        }
    } else {
        request.setAttribute("error", error);
    }       
        if (usuario != null) {
            request.setAttribute("usuariosLista", usuario);
        }
    getServletContext().getRequestDispatcher("/admin/editarUsuario.jsp").forward(request, response);
    return;
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
