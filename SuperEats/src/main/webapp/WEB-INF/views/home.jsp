<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Home - SuperEats</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/header.jsp" />

	<!-- Sidebar -->
	<%-- <jsp:include page="/WEB-INF/views/sidebar.jsp" /> --%>

	<!-- Main Content -->
	<main>
		<h1>Welcome to SuperEats</h1>

		<h2>Featured Recipes</h2>
		<c:choose>
			<c:when test="${not empty featuredRecipes}">
				<ul>
					<c:forEach var="recipe" items="${featuredRecipes}">
						<li>
							<h3>${recipe.title}</h3>
							<p>${recipe.description}</p>
							<p>
								<strong>Cuisine:</strong> ${recipe.cuisineType}
							</p>
							<p>
								<strong>Preparation Time:</strong> ${recipe.prepTime} mins
							</p>
							<p>
								<strong>Calories:</strong> ${recipe.calories}
							</p>
							<p>
								<strong>Instructions:</strong> ${recipe.instructions}
							</p>
						</li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
				<p>No featured recipes available at the moment.</p>
			</c:otherwise>
		</c:choose>

		<!-- Repeat similar structure for Meal Plans, Users, Admins, Grocery Lists -->
	</main>

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
