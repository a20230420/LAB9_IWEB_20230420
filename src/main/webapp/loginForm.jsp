<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 30/10/2025
  Time: 04:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form class ="form-singin" method="POST" action ="<%=request.getContextPath()%>/LoginServlet">
        <h1 class="h3 mb-3 font-weight-normal"> Ingreso al sistema </h1>
        <input type="text" name="inputEmail" class="form-control" placeholder="Correo" autofocus="">
        <input type="password" name="inputPassword" class="form-control" placeholder="Password">
        <% if(request.getParameter("error") != null) {%>
        <div class="text-danger mb-2">Error en usuario o contraseña</div>
        <% }%>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</body>
</html>
