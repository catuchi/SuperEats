<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Grocery List Details</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h2>Grocery List Details</h2>
    <h3>Ingredients</h3>
    <ul>
        <c:forEach var="ingredient" items="${groceryList.ingredients}">
            <li>${ingredient.name} - ${ingredient.quantity} ${ingredient.unit}</li>
        </c:forEach>
    </ul>

    <h3>Add Ingredient</h3>
    <form action="${pageContext.request.contextPath}/groceryList" method="post">
        <input type="hidden" name="action" value="addIngredient">
        <input type="hidden" name="listId" value="${groceryList.listId}">
        <label>Ingredient ID: <input type="text" name="ingredientId"></label><br>
        <label>Quantity: <input type="text" name="quantity"></label><br>
        <label>Unit: <input type="text" name="unit"></label><br>
        <button type="submit">Add Ingredient</button>
    </form>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
