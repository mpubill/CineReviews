<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Editar Perfil</title>
        <!-- Agrega tus enlaces a CSS, Bootstrap u otros recursos aquí -->
    </head>
    <body>

        <header>
            <jsp:include page="../navbar.jsp"></jsp:include>
            </header>

            <div align="center">
                <h2>Editar Perfil</h2>

            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
            <% if (errorMessage != null) {%>
            <p style="color: red;"><%= errorMessage%></p>
            <% }%>

            <form action="${pageContext.request.contextPath}/UsuarioController" method="post">
                <label for="nombreUsuario">Nombre de Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" value="${usuario.nombreUsuario}" required><br>

                <label for="correoElectronico">Correo Electrónico:</label>
                <input type="email" id="correoElectronico" name="correoElectronico" value="${usuario.correoElectronico}" required><br>

                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required><br>

                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" value="${usuario.apellido}" required><br>

                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="${usuario.fechaNacimiento}" required><br>

                <label for="pais">País:</label>
                <input type="text" id="pais" name="pais" value="${usuario.pais}" required><br>

                <label for="rol">Rol:</label>
                <input type="text" id="rol" name="rol" value="${usuario.rol}" required><br>

                <button type="submit" name="accion" value="actualizarPerfil">Guardar Cambios</button>
            </form>
        </div>

    </body>
</html>
