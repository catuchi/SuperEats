<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h2>Admin Dashboard</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/admin?action=viewUsers">Manage Users</a></li>
        <li><a href="${pageContext.request.contextPath}/admin?action=viewRecipes">Manage Recipes</a></li>
    </ul>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
