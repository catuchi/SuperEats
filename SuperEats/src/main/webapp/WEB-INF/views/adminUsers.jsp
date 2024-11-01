<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin - Manage Users</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h2>Manage Users</h2>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>
                    <!-- Link for viewing user details could be added here -->
                    <!-- <a href="#">View</a> -->
                    <a href="${pageContext.request.contextPath}/admin?action=deleteUser&userId=${user.userId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
