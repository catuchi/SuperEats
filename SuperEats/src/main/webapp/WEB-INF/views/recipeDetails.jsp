<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${recipe.title} - Recipe Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>

<jsp:include page="/WEB-INF/views/header.jsp" />

<body>
    <div class="container">
        <div class="recipe-details-card">
            <!-- Recipe Image -->
            <img src="${pageContext.request.contextPath}/resources/images/placeholder_recipe_image_2.jpg" alt="${recipe.title}" class="recipe-image">

            <!-- Recipe Content -->
            <div class="recipe-content">
                <h1>${recipe.title}</h1>
                <p class="recipe-description">${recipe.description}</p>

                <!-- Recipe Information -->
                <div class="recipe-info">
                    <p><strong>Cuisine:</strong> ${recipe.cuisineType}</p>
                    <p><strong>Preparation Time:</strong> ${recipe.prepTime} minutes</p>
                    <p><strong>Calories:</strong> ${recipe.calories} kcal</p>
                </div>

                <!-- Ingredients List -->
                <h2>Ingredients</h2>
                <ul class="ingredient-list">
                    <c:forEach var="ingredient" items="${recipeIngredients}">
                        <li>${ingredient.ingredient.name} - ${ingredient.quantity} ${ingredient.unit}</li>
                    </c:forEach>
                </ul>

                <!-- Instructions -->
                <h2>Instructions</h2>
                <p>${recipe.instructions}</p>

                <!-- Average Rating Display -->
                <h3>Average Rating: ${averageRating}</h3>

                <!-- Rating Form -->
                <c:if test="${not empty sessionScope.user}">
                    <h3>Rate this Recipe</h3>
                    <form action="${pageContext.request.contextPath}/recipe?action=rate" method="post">
                        <input type="hidden" name="recipeId" value="${recipe.recipeId}" />
                        <label for="rating">Your Rating (1-5):</label>
                        <select name="rating" id="rating" required>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                        <button type="submit">Submit Rating</button>
                    </form>
                </c:if>
                <c:if test="${empty sessionScope.user}">
                    <p><a href="${pageContext.request.contextPath}/auth?action=login">Log in</a> to rate this recipe.</p>
                </c:if>
            </div>
        </div>
    </div>
</body>

<jsp:include page="/WEB-INF/views/footer.jsp" />
</html>
