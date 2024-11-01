<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<div class="container">
    <h2>Search Results</h2>

    <c:choose>
        <c:when test="${not empty recipes}">
            <div class="recipe-result-cards">
                <c:forEach var="recipe" items="${recipes}">
                    <div class="recipe-result-card">
                        <a href="${pageContext.request.contextPath}/recipe?action=view&id=${recipe.recipeId}">
                            <img src="${pageContext.request.contextPath}/resources/images/placeholder_recipe_image_5.jpg" alt="${recipe.title}">
                            <div class="recipe-content">
                                <h3>${recipe.title}</h3>
                                <p>${recipe.description}</p>
                                <p><strong>Cuisine:</strong> ${recipe.cuisineType}</p>
                                <p><strong>Prep Time:</strong> ${recipe.prepTime} mins</p>
                                <p><strong>Average Rating:</strong> ${recipe.averageRating}</p>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <p>No recipes matched your search.</p>
            <h3>Popular Recipes</h3>
            <!-- Include popular recipes as a fallback -->
        </c:otherwise>
    </c:choose>
</div>


<%@ include file="footer.jsp" %>
