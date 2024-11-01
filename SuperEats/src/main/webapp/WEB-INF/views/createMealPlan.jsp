<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Meal Plan</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h2>Create a New Meal Plan</h2>
    <form action="${pageContext.request.contextPath}/mealPlan" method="post">
        <input type="hidden" name="action" value="create"/>
        
        <label for="name">Meal Plan Name:</label>
        <input type="text" id="name" name="name" required/><br><br>

        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" required/><br><br>

        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" required/><br><br>

        <button type="submit">Create Meal Plan</button>
    </form>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
