<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - SuperEats</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<div class="card-container">
    <div class="login-card">
        <h2>Login to SuperEats</h2>
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/auth" method="post">
            <input type="hidden" name="action" value="login" />
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required placeholder="Enter your email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required placeholder="Enter your password">
            </div>
            <button type="submit" class="btn-primary">Login</button>
        </form>
        <p>Don't have an account? <a href="${pageContext.request.contextPath}/auth?action=register">Register here</a></p>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
