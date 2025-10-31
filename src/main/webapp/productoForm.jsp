<%@ page import="org.example.lab09.Beans.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.lab09.Beans.Categoria" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 30/10/2025
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario u = (Usuario) session.getAttribute("usuarioSession");
    ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
%>
<html>
<head>
    <title>Nuevo Producto</title>
</head>
<body>
<nav style="display:flex; gap:12px; align-items:center;">
    <strong>Tienda – <%= u.getNombres() %> <%= u.getApellidos() %></strong>
    <a href="<%=request.getContextPath()%>/ProductoServlet?action=listar">Productos</a>
    <a href="<%=request.getContextPath()%>/CarritoServlet?action=listar">Carrito</a>
    <a href="<%=request.getContextPath()%>/LoginServlet?action=logout">Cerrar sesión</a>
</nav>
<hr/>
<div class = 'container'>
    <h1 class='mb-3'>Crear nuevo producto</h1>
    <form method="POST" action="<%=request.getContextPath()%>/ProductoServlet">
        <div>
            <label>Categoria</label>
            <select name="idCategoria" required>
                <option value="">--Selecciona--</option>
                <%
                    for(Categoria categoria : categorias){
                %>
                <option value="<%= categoria.getId()%>"><%= categoria.getNombre()%></option>
                <%
                    }
                %>
            </select>
        </div>
        <div>
            <label>Nombre</label>
            <input type="text" name="nombre" required>
        </div>
        <div>
            <label>Descripcion</label>
            <input type="text" name="descripcion" required>
        </div>
        <div>
            <label>Precio</label>
            <input type="number" name="precio" required>
        </div>
        <div>
            <label>Stock</label>
            <input type="number" name="stock" required>
        </div>

        <button type="submit" class="btn btn-primary">Crear</button>
        <a href="<%=request.getContextPath()%>/ProductoServlet?action=listar">Cancelar</a>
    </form>
</div>
</body>
</html>
