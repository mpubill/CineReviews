<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>

    <style>
        body {
            font-family: 'Poppins';
            font-size: 24px;
        }

        body {
            background-color: #f0f0f0;
        }

        .login-box {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff; /* Color de fondo del cuadro de inicio de sesión */
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 mx-auto mt-5">
            <div class="login-box">
                <h2 class="text-center">Aqui poner un logo porfis jej</h2>
                <form method="post" action="${pageContext.request.contextPath}/UsuarioController">
                    <div class="mb-3">
                        <label for="username" class="form-label">Nombre de Usuario</label>
                        <input type="text" class="form-control" name="username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" name="userpass" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn" name="accion" value="ingresar" style="background-color: #8d4cca; color: white; font-size: 24px;">Iniciar Sesión</button>
                    </div>
                </form> <br>
                <div class="text-center">
                    <a href="Registro.jsp" style="color:  #8d4cca;">¿No tienes cuenta?</a>
                </div>

                <!-- Aquí se muestra el mensaje si existe -->
                <c:if test="${not empty mensaje}">
                    <div class="alert alert-danger mt-3" role="alert">
                        ${mensaje}
                    </div>
                </c:if>
                <!-- Fin de la sección de mensaje -->

            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.5.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
