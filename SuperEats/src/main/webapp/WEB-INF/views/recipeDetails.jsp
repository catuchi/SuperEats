<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recipe Details</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Recipe Details</h1>

    <c:if test="${not empty recipe}">
        <h2>${recipe.title}</h2>
        <p><strong>Description:</strong> ${recipe.description}</p>
        <p><strong>Cuisine Type:</strong> ${recipe.cuisineType}</p>
        <p><strong>Preparation Time:</strong> ${recipe.prepTime} minutes</p>
        <p><strong>Calories:</strong> ${recipe.calories}</p>
        <p><strong>Instructions:</strong> ${recipe.instructions}</p>

        <!-- Ingredients list -->
        <h3>Ingredients:</h3>
        <c:if test="${not empty recipe.recipeIngredients}">
            <ul>
                <c:forEach var="ingredient" items="${recipe.recipeIngredients}">
                    <li>${ingredient.ingredient.name} - ${ingredient.quantity} ${ingredient.unit}</li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty recipe.recipeIngredients}">
            <p>No ingredients listed for this recipe.</p>
        </c:if>

        <!-- Ratings -->
        <h3>Ratings:</h3>
        <c:if test="${not empty recipe.ratings}">
            <ul>
                <c:forEach var="rating" items="${recipe.ratings}">
                    <li>Rating: ${rating.rating} / 5</li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty recipe.ratings}">
            <p>No ratings available for this recipe.</p>
        </c:if>

        <!-- Average Rating -->
        <p><strong>Average Rating:</strong> ${recipeService.getAverageRating(recipe.recipeId)} / 5</p>
    </c:if>

    <c:if test="${empty recipe}">
        <p>Recipe details not available.</p>
    </c:if>

    <a href="home">Back to Home</a>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
