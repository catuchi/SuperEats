<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register - SuperEats</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<div class="card-container">
    <div class="register-card">
        <h2>Register on SuperEats</h2>
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/auth" method="post">
            <input type="hidden" name="action" value="register" />

            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" required placeholder="Enter your name">
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required placeholder="Enter your email">
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required placeholder="Create a password">
            </div>

            <div class="form-group">
                <label for="dietaryPreferences">Dietary Preferences (optional)</label>
                <input type="text" id="dietaryPreferences" name="dietaryPreferences" placeholder="E.g., Vegetarian, Gluten-Free">
            </div>

            <div class="form-group">
                <label for="profilePicture">Profile Picture URL (optional)</label>
                <input type="url" id="profilePicture" name="profilePicture" placeholder="Link to your profile picture">
            </div>

            <button type="submit" class="btn-primary">Register</button>
        </form>
        <p>Already have an account? <a href="${pageContext.request.contextPath}/auth?action=login">Login here</a></p>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
