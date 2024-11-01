<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin - Manage Recipes</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h2>Manage Recipes</h2>
    <table border="1">
        <tr>
            <th>Recipe ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Approved</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="recipe" items="${recipes}">
            <tr>
                <td>${recipe.recipeId}</td>
                <td>${recipe.title}</td>
                <td>${recipe.description}</td>
                <td>${recipe.isApproved() ? 'Yes' : 'No'}</td>
                <td>
                    <c:if test="${!recipe.isApproved()}">
                        <a href="${pageContext.request.contextPath}/admin?action=approveRecipe&recipeId=${recipe.recipeId}">Approve</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/admin?action=deleteRecipe&recipeId=${recipe.recipeId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
