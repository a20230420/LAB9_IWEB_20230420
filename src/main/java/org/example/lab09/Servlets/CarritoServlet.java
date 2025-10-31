package org.example.lab09.Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab09.Beans.Usuario;
import org.example.lab09.DTOs.CarritoDTO;
import org.example.lab09.Daos.CarritoDao;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CarritoServlet", urlPatterns = {"/CarritoServlet"})
public class CarritoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action")==null?"listar":request.getParameter("action");
        RequestDispatcher view;
        switch(action){
            case "listar":
                Usuario u = (Usuario)request.getSession().getAttribute("usuarioSession");
                int idUsuario = u.getId_usuario();
                ArrayList<CarritoDTO> items = new CarritoDao().listarPorUsuario(idUsuario);
                request.setAttribute("items", items);
                view = request.getRequestDispatcher("carrito.jsp");
                view.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action")==null?"listar":request.getParameter("action");

        switch(action){
            case "add":
                int  idProducto = Integer.parseInt(request.getParameter("idProducto"));
                Usuario u = (Usuario) request.getSession().getAttribute("usuarioSession");
                new CarritoDao().addItem(u.getId_usuario(),idProducto);
                response.sendRedirect(request.getContextPath()+"/ProductoServlet?action=listar");
                break;
            default:
                response.sendRedirect(request.getContextPath()+"/CarritoServlet?action=listar");
        }
    }
}
