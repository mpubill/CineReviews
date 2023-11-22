<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registro de Usuario</title>
        <!-- Agrega tus enlaces a CSS, Bootstrap u otros recursos aquí -->
    </head>
    <body>

        <div align="center">
            <h2>Registro de Usuario</h2>

            <%-- Muestra mensajes de éxito o error --%>
            <% String successMessage = (String) session.getAttribute("successMessage"); %>
            <% String errorMessage = (String) session.getAttribute("errorMessage"); %>

            <% if (successMessage != null) {%>
            <p style="color: green;"><%= successMessage%></p>
            <% } else if (errorMessage != null) {%>
            <p style="color: red;"><%= errorMessage%></p>
            <% }%>

            <form method="post" action="${pageContext.request.contextPath}/UsuarioController?accion=registrar">
                <label for="nombreUsuario">Nombre de Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" required><br>

                <label for="correoElectronico">Correo Electrónico:</label>
                <input type="email" id="correoElectronico" name="correoElectronico" required><br>

                <label for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" required><br>

                <label for="imagenPerfil">URL de la Imagen de Perfil:</label>
                <input type="text" id="imagenPerfil" name="imagenPerfil"><br>

                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre"><br>

                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido"><br>

                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento"><br>

                <label for="pais">País:</label>
                <input type="text" id="pais" name="pais"><br>

                <label for="rol">Rol:</label>
                <select id="rol" name="rol">
                    <option value="usuario">Usuario</option>
                    <option value="admin">Administrador</option>
                </select><br>

                <button type="submit">Registrar Usuario</button>
            </form>
        </div>

    </body>
</html>
