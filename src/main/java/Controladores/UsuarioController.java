/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import modelosDAO.UsuarioDAO;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author melan
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            usuarioDAO = new UsuarioDAO();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if ("verPerfil".equals(action)) {
            mostrarPerfil(request, response);
        } else if ("editarPerfil".equals(action)) {
            mostrarEditarPerfil(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "../Vistas/Landing.jsp");
        }
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
        HttpSession session = request.getSession();

        String accion = request.getParameter("accion");

        if ("registrar".equals(accion)) {
            // Registro de nuevo usuario
            registrarUsuario(request, response, session);
        } else if ("ingresar".equals(accion)) {
            // Inicio de sesión
            iniciarSesion(request, response, session);
        } else if ("actualizarPerfil".equals(accion)) {
            actualizarPerfil(request, response);
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        // Código actual de registro de usuario

        // Después de registrar el usuario, redirige a la página de inicio de sesión
        response.sendRedirect("Vistas/Login.jsp");
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("username");
        String contrasena = request.getParameter("userpass");

        Usuario usuario = usuarioDAO.iniciarSesion(nombreUsuario, contrasena);

        if (usuario != null) {
            // Inicio de sesión exitoso
            session.setAttribute("usuarioActual", usuario);
            response.sendRedirect("Vistas/Landing.jsp"); // Redirige a la página principal después del inicio de sesión
        } else {
            // Inicio de sesión fallido
            session.setAttribute("mensaje", "Nombre de usuario o contraseña incorrectos");
            response.sendRedirect("Vistas/Login.jsp");
        }
    }

    private void mostrarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioActual");

        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Vistas/User/verPerfil.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/Vistas/Landing.jsp");
        }
    }

    private void mostrarEditarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");

        if (usuarioActual != null) {
            request.setAttribute("usuario", usuarioActual);
            request.getRequestDispatcher("/Vistas/User/EditarPerfil.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/Vistas/Landing.jsp");
        }
    }

    private void actualizarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");

        if (usuarioActual != null) {
            // Obtener los parámetros del formulario
            String nombreUsuario = request.getParameter("nombreUsuario");
            String correoElectronico = request.getParameter("correoElectronico");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String pais = request.getParameter("pais");
            String rol = request.getParameter("rol");

            // Actualizar el usuario
            usuarioActual.setNombreUsuario(nombreUsuario);
            usuarioActual.setCorreoElectronico(correoElectronico);
            usuarioActual.setNombre(nombre);
            usuarioActual.setApellido(apellido);
            usuarioActual.setFechaNacimiento(fechaNacimiento);
            usuarioActual.setPais(pais);
            usuarioActual.setRol(rol);

            if (usuarioDAO.actualizarUsuario(usuarioActual)) {
                // Actualización exitosa
                usuarioActual = usuarioDAO.obtenerUsuarioPorId(usuarioActual.getIdUsuario()); // Obtener el usuario actualizado de la base de datos
                session.setAttribute("usuarioActual", usuarioActual);
                request.setAttribute("successMessage", "Perfil actualizado correctamente");
                mostrarPerfil(request, response);
            } else {
                // Error al actualizar
                request.setAttribute("errorMessage", "Error al actualizar el perfil");
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/Vistas/Landing.jsp");
        }
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
