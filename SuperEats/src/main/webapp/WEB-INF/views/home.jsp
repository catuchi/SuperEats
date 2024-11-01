<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home - SuperEats</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>

<jsp:include page="/WEB-INF/views/header.jsp" />

<body>
    <div class="container">
        <h1>Welcome to SuperEats</h1>

        <h2>Featured Recipes</h2>
        <c:choose>
            <c:when test="${not empty featuredRecipes}">
                <div class="card-container">
                    <c:forEach var="recipe" items="${featuredRecipes}">
                        <div class="card">
                            <a href="${pageContext.request.contextPath}/recipe?action=view&id=${recipe.recipeId}">
                                <!-- Placeholder image for recipes without an image attribute -->
                                <img src="${pageContext.request.contextPath}/resources/images/placeholder_recipe_image.jpg" alt="${recipe.title}" class="card-image">
                                <div class="card-content">
                                    <h3>${recipe.title}</h3>
                                    <p>${recipe.description}</p>
                                    <p><strong>Cuisine:</strong> ${recipe.cuisineType}</p>
                                    <p><strong>Prep Time:</strong> ${recipe.prepTime} mins</p>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>No featured recipes available at the moment.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>

<jsp:include page="/WEB-INF/views/footer.jsp" />
</html>
