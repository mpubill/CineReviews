<%-- 
    Document   : ListaPelicula
    Created on : 6 nov. 2023, 10:46:26
    Author     : melan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Películas</title>
</head>
<body>
    <h1>Listado de Películas</h1>
    
    <!-- Botones de filtro -->
    <form method="get" action="${pageContext.request.contextPath}/EncuestaController">
        <label for="filtroFecha">Filtrar por Fecha:</label>
        <input type="date" id="filtroFecha" name="filtroFecha">
        
        <label for="filtroNombre">Filtrar por Nombre:</label>
        <input type="text" id="filtroNombre" name="filtroNombre">
        
        <label for="filtroCategoria">Filtrar por Categoría:</label>
        <select id="filtroCategoria" name="filtroCategoria">
            <option value="">Todas las categorías</option>
            <option value="Accion">Acción</option>
            <option value="Comedia">Comedia</option>
            <!-- Agrega más opciones de categorías aquí -->
        </select>
        
        <input type="submit" name="accion" value="Filtrar">
    </form>
    
    <!-- Listado de películas -->
    <table>
        <tr>
            <th>Título</th>
            <th>Año de Lanzamiento</th>
            <th>Director</th>
            <th>Categoría</th>
        </tr>
        <c:forEach items="${listaPeliculas}" var="pelicula">
            <tr>
                <td>${pelicula.titulo}</td>
                <td>${pelicula.anioLanzamiento}</td>
                <td>${pelicula.director}</td>
                <td>${pelicula.categoria.nombreCategoria}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>