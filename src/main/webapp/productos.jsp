<%@ page import="org.example.lab09.Beans.Usuario" %>
<%@ page import="org.example.lab09.DTOs.ProductoDTO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 30/10/2025
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de productos</title>
</head>
<body>
<%
    Usuario u = (Usuario) session.getAttribute("usuarioSession");
    ArrayList<ProductoDTO> productos= (ArrayList<ProductoDTO>) request.getAttribute("productos");
%>
<nav style="display:flex; gap:12px; align-items:center;">
    <strong>Tienda – <%= u.getNombres() %> <%= u.getApellidos() %></strong>
    <a href="<%=request.getContextPath()%>/ProductoServlet?action=listar">Productos</a>
    <a href="<%=request.getContextPath()%>/CarritoServlet?action=listar">Carrito</a>
    <a href="<%=request.getContextPath()%>/LoginServlet?action=logout">Cerrar sesión</a>
</nav>
<hr/>

<h2>Listado de productos</h2>
<a href="<%=request.getContextPath()%>/ProductoServlet?action=nuevo">
    <button type="button">Nuevo Producto</button>
</a>
<br/><br/>
<table border="1" cellpadding="6" cellspacing="0">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Categoría</th>
        <th>Precio (S/.)</th>
        <th>Stock Disponible</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <%
        for(ProductoDTO p : productos){
    %>
    <tbody>
    <tr>
        <th><%=p.getId()%></th>
        <th><%=p.getNombre()%></th>
        <th><%=p.getCategoriaNombre()%></th>
        <th><%=p.getPrecio()%></th>
        <th><%=p.getStock()%></th>
        <th>
            <form method="post" action="<%=request.getContextPath()%>/CarritoServlet" style="display:inline;">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="idProducto" value="<%= p.getId() %>">
            <button type="submit">Añadir al carrito</button>
            </form>
        </th>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
