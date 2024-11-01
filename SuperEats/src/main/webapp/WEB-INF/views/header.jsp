<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <title>SuperEats</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<div class="header">
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
           <%--  <li><a href="${pageContext.request.contextPath}/recipe?action=search">Recipe Search</a></li> --%>
             <li><a href="${pageContext.request.contextPath}/recipe?action=searchForm">Recipe Search</a></li>
            
            <c:if test="${not empty sessionScope.user}">
                <li><a href="${pageContext.request.contextPath}/mealPlan">Meal Planning</a></li>
                <li><a href="${pageContext.request.contextPath}/groceryList">Grocery Lists</a></li>
                <li><a href="${pageContext.request.contextPath}/user?action=profile">Profile</a></li>
                
                <!-- Show Admin Dashboard link only if the user is an admin -->
                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <li><a href="${pageContext.request.contextPath}/admin?action=dashboard">Admin Dashboard</a></li>
                </c:if>
                
                <li><a href="${pageContext.request.contextPath}/auth?action=logout">Logout</a></li>
            </c:if>
            <c:if test="${empty sessionScope.user}">
                <li><a href="${pageContext.request.contextPath}/auth?action=login">Login</a></li>
                <li><a href="${pageContext.request.contextPath}/auth?action=register">Register</a></li>
            </c:if>
        </ul>
    </nav>
</div>
