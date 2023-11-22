<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Listado de Categorías</title>
</head>
<body>
    <h2>Listado de Categorías</h2>
    
    <c:if test="${not empty categorias}">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Acciones</th>
            </tr>
            <c:forEach var="categoria" items="${categorias}">
                <tr>
                    <td>${categoria.idCategoria}</td>
                    <td>${categoria.nombreCategoria}</td>
                    <td>
                        <a href="/CineReviews/CategoriaController?accion=mostrarFormularioEditar&idCategoria=${categoria.idCategoria}">Editar</a>
                        <a href="/CineReviews/CategoriaController?accion=eliminarCategoria&idCategoria=${categoria.idCategoria}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    
    <c:if test="${empty categorias}">
        <p>No hay categorías disponibles.</p>
    </c:if>
    
    <a href="${pageContext.request.contextPath}/CategoriaController?accion=mostrarFormularioAgregar">Agregar Nueva Categoría</a>
</body>
</html>
