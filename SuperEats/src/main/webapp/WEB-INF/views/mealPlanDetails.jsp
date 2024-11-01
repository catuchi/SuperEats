<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
