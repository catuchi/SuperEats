<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recipe List</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>All Recipes</h1>

    <c:if test="${not empty recipes}">
        <ul>
            <c:forEach var="recipe" items="${recipes}">
                <li>
                    <h3>${recipe.title}</h3>
                    <p><strong>Description:</strong> ${recipe.description}</p>
                    <p><strong>Cuisine Type:</strong> ${recipe.cuisineType}</p>
                    <p><strong>Preparation Time:</strong> ${recipe.prepTime} minutes</p>
                    <p><strong>Calories:</strong> ${recipe.calories}</p>
                    <a href="recipeDetails?recipeId=${recipe.recipeId}">View Details</a>
                    <a href="${pageContext.request.contextPath}/recipeDetails?recipeId=${recipe.recipeId}">View Details</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty recipes}">
        <p>No recipes available.</p>
    </c:if>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
