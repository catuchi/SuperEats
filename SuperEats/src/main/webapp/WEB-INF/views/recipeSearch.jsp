<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recipe Search - SuperEats</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<div class="container">
    <!-- Search Form -->
    <div class="search-container">
        <h2>Find a Recipe</h2>
        <form action="${pageContext.request.contextPath}/recipe" method="get">
            <input type="hidden" name="action" value="search" />

            <div class="form-group">
                <input type="text" name="query" placeholder="Enter keywords or ingredients..." required>
            </div>

            <div class="form-group">
                <label for="filter">Filter by:</label>
                <select name="filter" id="filter">
                    <option value="title">Recipe Title</option>
                    <option value="ingredient">Ingredient</option>
                    <option value="cuisine">Cuisine Type</option>
                </select>
            </div>

            <button type="submit" class="btn-primary">Search</button>
        </form>
    </div>

    <!-- Search Results -->
    <div class="results-container">
        <h3>Search Results</h3>
        <c:choose>
            <c:when test="${not empty recipes}">
                <div class="recipe-card-container">
                    <c:forEach var="recipe" items="${recipes}">
                        <div class="recipe-card">
                            <h4>${recipe.title}</h4>
                            <p><strong>Cuisine:</strong> ${recipe.cuisineType}</p>
                            <p><strong>Prep Time:</strong> ${recipe.prepTime} mins</p>
                            <p>${recipe.description}</p>
                            <a href="${pageContext.request.contextPath}/recipe?action=view&id=${recipe.recipeId}" class="btn-secondary">View Recipe</a>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>No recipes matched your search.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- All Recipes Section -->
    <div class="all-recipes-container">
        <h3>All Recipes</h3>
        <div class="recipe-card-container">
            <c:forEach var="recipe" items="${allRecipes}">
                <div class="recipe-card">
                    <h4>${recipe.title}</h4>
                    <p><strong>Cuisine:</strong> ${recipe.cuisineType}</p>
                    <p><strong>Prep Time:</strong> ${recipe.prepTime} mins</p>
                    <p>${recipe.description}</p>
                    <a href="${pageContext.request.contextPath}/recipe?action=view&id=${recipe.recipeId}" class="btn-secondary">View Recipe</a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
