<%@ page import="org.example.lab09.Beans.Usuario" %>
<%@ page import="org.example.lab09.DTOs.CarritoDTO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 31/10/2025
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carrito</title>
</head>
<body>
<%
    Usuario u = (Usuario) session.getAttribute("usuarioSession");
    ArrayList<CarritoDTO> items = (ArrayList<CarritoDTO>) request.getAttribute("items");
    double total = 0.0;
    for (CarritoDTO it : items) { total += it.getSubtotal(); }
%>
<nav style="display:flex; gap:12px; align-items:center;">
    <strong>Tienda – <%= u.getNombres() %> <%= u.getApellidos() %></strong>
    <a href="<%=request.getContextPath()%>/ProductoServlet?action=listar">Productos</a>
    <a href="<%=request.getContextPath()%>/CarritoServlet?action=listar">Carrito</a>
    <a href="<%=request.getContextPath()%>/LoginServlet?action=logout">Cerrar sesión</a>
</nav>

<h2>Carrito de compras</h2>

<a href="<%=request.getContextPath()%>/ProductoServlet?action=listar">
    <button type="button">Seguir comprando</button>
</a>
<br/><br/>
<table border="1" cellpadding="6" cellspacing="0">
    <thead>
    <tr>
        <th>IdItem</th>
        <th>IdProducto</th>
        <th>Producto</th>
        <th>Usuario</th>
        <th>Precio Unit (S/.)</th>
        <th>Cantidad</th>
        <th>Subtotal (S/.)</th>
    </tr>
    </thead>
    <tbody>
    <% for (CarritoDTO it : items) { %>
    <tr>
        <td><%= it.getIdItem() %></td>
        <td><%= it.getIdProducto() %></td>
        <td><%= it.getNombreProducto() %></td>
        <td><%= it.getNombreUsuario() %></td>
        <td><%= String.format("%.2f", it.getPrecioUnit()) %></td>
        <td><%= it.getCantidad() %></td>
        <td><%= String.format("%.2f", it.getSubtotal()) %></td>
    </tr>
    <% } %>
    </tbody>
</table>
<h3>Total: S/ <%= String.format("%.2f", total) %></h3>
</body>
</html>
