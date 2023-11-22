/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controladores;

import Modelos.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelosDAO.CategoriaDAO;

@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

    private CategoriaDAO categoriaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            categoriaDAO = new CategoriaDAO();
        } catch (SQLException e) {
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
            out.println("<title>Servlet CategoriaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoriaController at " + request.getContextPath() + "</h1>");
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
        String accion = request.getParameter("accion");

        if ("listarCategorias".equals(accion)) {
            listarCategorias(request, response);
        } else if ("mostrarFormularioAgregar".equals(accion)) {
            mostrarFormularioAgregar(request, response);
        } else if ("mostrarFormularioEditar".equals(accion)) {
            mostrarFormularioEditar(request, response);
        }
        // Puedes agregar más acciones según tus necesidades
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
        String accion = request.getParameter("accion");

        if ("agregarCategoria".equals(accion)) {
            agregarCategoria(request, response);
        } else if ("editarCategoria".equals(accion)) {
            editarCategoria(request, response);
        } else if ("eliminarCategoria".equals(accion)) {
            eliminarCategoria(request, response);
        }
        // Puedes agregar más acciones según tus necesidades
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Categoria> categorias = categoriaDAO.consultarCategorias();
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("/Vistas/Admin/VerCategoria.jsp").forward(request, response);
    }

    private void mostrarFormularioAgregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Vistas/Admin/CategoriaAdd.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        Categoria categoria = categoriaDAO.obtenerCategoriaPorId(idCategoria);
        request.setAttribute("categoria", categoria);
        request.getRequestDispatcher("/Vistas/Admin/CategoriaEdit.jsp").forward(request, response);
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String nombreCategoria = request.getParameter("nombreCategoria");
    Categoria categoria = new Categoria();
    categoria.setNombreCategoria(nombreCategoria);

    if (categoriaDAO.agregarCategoria(categoria)) {
        request.setAttribute("mensaje", "Categoría agregada correctamente");
    } else {
        request.setAttribute("mensaje", "Error al agregar la categoría");
    }

    listarCategorias(request, response);
}


    private void editarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        String nombreCategoria = request.getParameter("nombreCategoria");
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombreCategoria(nombreCategoria);

        if (categoriaDAO.editarCategoria(categoria)) {
            request.setAttribute("mensaje", "Categoría actualizada correctamente");
        } else {
            request.setAttribute("mensaje", "Error al actualizar la categoría");
        }

        listarCategorias(request, response);
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

        if (categoriaDAO.eliminarCategoria(idCategoria)) {
            request.setAttribute("mensaje", "Categoría eliminada correctamente");
        } else {
            request.setAttribute("mensaje", "Error al eliminar la categoría");
        }

        listarCategorias(request, response);
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
