package org.example.lab09.Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.lab09.Beans.Usuario;
import org.example.lab09.Daos.UsuarioDao;

import java.io.IOException;
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "loginform" : request.getParameter("action");
        RequestDispatcher view;
        switch (action) {
            case "loginform":
                view = request.getRequestDispatcher("/loginForm.jsp");
                view.forward(request, response);
                break;
            case "logout":
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect(request.getContextPath()+ "/LoginServlet?action=loginform");
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDao usuarioDao = new UsuarioDao();
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        Usuario usuario = usuarioDao.validarUsuarioPassword(email,password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioSession", usuario);

            response.sendRedirect(request.getContextPath()+"/ProductoServlet?action=listar");
        }else{
            response.sendRedirect(request.getContextPath() + "/LoginServlet?error");
        }
    }
}
