<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Categoría</title>
</head>
<body>
    <h2>Editar Categoría</h2>
    
    <form action="$/CineReviews/CategoriaController" method="post">
        <input type="hidden" name="accion" value="editarCategoria">
        <input type="hidden" name="idCategoria" value="${categoria.idCategoria}">
        Nombre: <input type="text" name="nombreCategoria" value="${categoria.nombreCategoria}" required>
        <input type="submit" value="Guardar Cambios">
    </form>
    
    <a href="/TuProyecto/CategoriaController?accion=listarCategorias">Volver al Listado de Categorías</a>
</body>
</html>
