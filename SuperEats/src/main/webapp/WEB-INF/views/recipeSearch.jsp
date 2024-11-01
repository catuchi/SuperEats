<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recipe Search</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Search for Recipes</h1>
    <form action="${pageContext.request.contextPath}/recipe" method="get">
        <input type="hidden" name="action" value="search" />

        <label for="query">Search:</label>
        <input type="text" id="query" name="query" required />

        <label for="filter">Filter by:</label>
        <select id="filter" name="filter">
            <option value="title">Title</option>
            <option value="ingredient">Ingredient</option>
            <option value="cuisine">Cuisine</option>
        </select>
        <button type="submit">Search</button>
    </form>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
