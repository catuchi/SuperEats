<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Search Results</h1>
    <c:choose>
        <c:when test="${not empty recipes}">
            <ul>
                <c:forEach var="recipe" items="${recipes}">
                    <li>
                        <h3><a href="${pageContext.request.contextPath}/recipe?action=view&id=${recipe.recipeId}">
                            ${recipe.title}</a></h3>
                        <p>${recipe.description}</p>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No recipes matched your search.</p>
            <p>Try adjusting your search or check out some popular recipes below:</p>
            <ul>
                <!-- Optionally, add featured or popular recipes here -->
                <c:forEach var="recipe" items="${featuredRecipes}">
                    <li>
                        <h3><a href="${pageContext.request.contextPath}/recipe?action=view&id=${recipe.recipeId}">
                            ${recipe.title}</a></h3>
                        <p>${recipe.description}</p>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
