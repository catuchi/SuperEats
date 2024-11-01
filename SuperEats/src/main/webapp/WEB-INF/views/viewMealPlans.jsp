<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Meal Plans</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
    <h2>Your Meal Plans</h2>

    <c:if test="${not empty mealPlans}">
        <ul>
            <c:forEach var="mealPlan" items="${mealPlans}">
                <li>
                    <h3>${mealPlan.name}</h3>
                    <p>Start Date: ${mealPlan.startDate}</p>
                    <p>End Date: ${mealPlan.endDate}</p>
<%--                     <a href="${pageContext.request.contextPath}/mealPlanDetails?mealPlanId=${mealPlan.mealPlanId}">View Details</a> --%>
                    <a href="${pageContext.request.contextPath}/mealPlan?action=details&mealPlanId=${mealPlan.mealPlanId}">View Details</a>
                    
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty mealPlans}">
        <p>No meal plans found. <a href="${pageContext.request.contextPath}/mealPlan?action=create">Create a new meal plan</a></p>
    </c:if>
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
