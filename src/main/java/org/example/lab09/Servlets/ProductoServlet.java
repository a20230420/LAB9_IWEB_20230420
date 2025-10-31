package org.example.lab09.Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab09.Beans.Categoria;
import org.example.lab09.Beans.Usuario;
import org.example.lab09.DTOs.ProductoDTO;
import org.example.lab09.Daos.CategoriaDao;
import org.example.lab09.Daos.ProductoDao;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action")==null?"listar":request.getParameter("action");
        RequestDispatcher view;
        switch (action) {
            case "nuevo":
                CategoriaDao categoriaDao = new CategoriaDao();
                ArrayList<Categoria> categorias = categoriaDao.listar();
                request.setAttribute("categorias", categorias);

                view = request.getRequestDispatcher("/productoForm.jsp");
                view.forward(request, response);
                break;
            case "listar":
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSession");
                if (usuario == null) {
                    response.sendRedirect(request.getContextPath() + "/LoginServlet");
                    return;
                }
                int idUsuario = usuario.getId_usuario();

                ProductoDao productoDao = new ProductoDao();
                ArrayList<ProductoDTO> productos = productoDao.listarParaUsuario(idUsuario);
                request.setAttribute("productos", productos);

                view = request.getRequestDispatcher("/productos.jsp");
                view.forward(request, response);
                break;
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        ProductoDao productoDao = new ProductoDao();
        productoDao.crear(idCategoria, nombre, descripcion, precio, stock);

        response.sendRedirect(request.getContextPath()+"/ProductoServlet?action=listar");
    }
}
