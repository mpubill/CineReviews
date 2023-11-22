<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Perfil de Usuario</title>
    <!-- Agrega tus enlaces a CSS, Bootstrap u otros recursos aquí -->
</head>
<body>
    <header>
        <jsp:include page="../navbar.jsp"></jsp:include>
    </header>
    <div align="center">
        <h2>Perfil de Usuario</h2>

        <% String successMessage = (String) request.getAttribute("successMessage"); %>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>

        <% if (successMessage != null) {%>
        <p style="color: green;"><%= successMessage%></p>
        <% } else if (errorMessage != null) {%>
        <p style="color: red;"><%= errorMessage%></p>
        <% }%>

        <c:if test="${not empty usuario}">
            <c:choose>
                <c:when test="${not empty usuario.imagenPerfil}">
                    <img src="${usuario.imagenPerfil}">
                </c:when>
                <c:otherwise>
                    <img src="https://definicion.de/wp-content/uploads/2019/07/perfil-de-usuario.png" alt="Imagen de Perfil por defecto">
                    <p>Imagen de Perfil por defecto</p>
                </c:otherwise>
            </c:choose>

            <p>Nombre de Usuario: ${usuario.nombreUsuario}</p>
            <p>Correo Electrónico: ${usuario.correoElectronico}</p>
            <p>Nombre: ${usuario.nombre}</p>
            <p>Apellido: ${usuario.apellido}</p>
            <p>Fecha de Nacimiento: ${usuario.fechaNacimiento}</p>
            <p>País: ${usuario.pais}</p>
            <p>Rol: ${usuario.rol}</p>

            <!-- Cambiado a un formulario para dirigir al controlador con la acción editarPerfil -->
            <form action="${pageContext.request.contextPath}/UsuarioController" method="get">
                <input type="hidden" name="action" value="editarPerfil">
                <input type="submit" value="Editar Perfil">
            </form>
        </c:if>
    </div>
</body>
</html>
