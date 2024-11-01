<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Grocery Lists</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h2>Grocery Lists</h2>
    <ul>
        <c:forEach var="groceryList" items="${groceryLists}">
            <li>
                <a href="${pageContext.request.contextPath}/groceryList?action=view&listId=${groceryList.listId}">
                    Grocery List for User ID: ${groceryList.userId}
                </a>
            </li>
        </c:forEach>
    </ul>
    <a href="${pageContext.request.contextPath}/groceryList?action=add">Create New Grocery List</a>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
