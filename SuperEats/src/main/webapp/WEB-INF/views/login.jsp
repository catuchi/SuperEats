<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<h2>Login</h2>
	<form action="${pageContext.request.contextPath}/auth?action=login"
		method="post">
		<label>Email:</label> <input type="email" name="email" required>
		<label>Password:</label> <input type="password" name="password"
			required>
		<button type="submit">Login</button>
		<c:if test="${not empty error}">
			<p style="color: red;">${error}</p>
		</c:if>
	</form>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>