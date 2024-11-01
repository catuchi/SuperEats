<!DOCTYPE html>
<html>
<head>
    <title>Create Grocery List</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h2>Create a New Grocery List</h2>
    <form action="${pageContext.request.contextPath}/groceryList" method="post">
        <input type="hidden" name="action" value="createList">
        <label>User ID: <input type="text" name="userId"></label><br>
        <button type="submit">Create Grocery List</button>
    </form>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
