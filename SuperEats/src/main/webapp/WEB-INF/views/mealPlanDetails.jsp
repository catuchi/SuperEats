<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Meal Plan Details</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<h2>Meal Plan Details</h2>

	<p>
		<strong>Meal Plan Name:</strong> ${mealPlan.name}
	</p>
	<p>
		<strong>Start Date:</strong> ${mealPlan.startDate}
	</p>
	<p>
		<strong>End Date:</strong> ${mealPlan.endDate}
	</p>

	<h3>Recipes in this Meal Plan</h3>
	<ul>
		<c:forEach var="mealPlanRecipe" items="${mealPlan.mealPlanRecipes}">
			<li><a
				href="${pageContext.request.contextPath}/recipe?action=details&recipeId=${mealPlanRecipe.recipeId}">
					${mealPlanRecipeNames[mealPlanRecipe.mealPlanRecipeId]} </a></li>
		</c:forEach>
	</ul>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>

<div class="container">
	<h1>${mealPlan.name}</h1>
	<p>
		<strong>Start Date:</strong> ${mealPlan.startDate}
	</p>
	<p>
		<strong>End Date:</strong> ${mealPlan.endDate}
	</p>

	<h2>Recipes in Meal Plan</h2>
	<ul>
		<c:forEach var="mealPlanRecipe" items="${mealPlan.mealPlanRecipes}">
			<li>${mealPlanRecipeNames[mealPlanRecipe.mealPlanRecipeId]}</li>
		</c:forEach>
	</ul>

	<a
		href="${pageContext.request.contextPath}/mealPlan?action=edit&id=${mealPlan.mealPlanId}"
		class="btn-secondary">Edit</a> <a
		href="${pageContext.request.contextPath}/mealPlan?action=delete&id=${mealPlan.mealPlanId}"
		class="btn-danger"
		onclick="return confirm('Are you sure you want to delete this meal plan?');">Delete</a>
</div>

<%@ include file="footer.jsp"%>
