<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agregar Categoría</title>
</head>
<body>
    <h2>Agregar Categoría</h2>
    
    <form action="/CineReviews/CategoriaController" method="post">
        <input type="hidden" name="accion" value="agregarCategoria">
        Nombre: <input type="text" name="nombreCategoria" required>
        <input type="submit" value="Agregar Categoría">
    </form>
    
    <a href="${pageContext.request.contextPath}/CategoriaController?accion=listarCategorias">Volver al Listado de Categorías</a>
</body>
</html>
