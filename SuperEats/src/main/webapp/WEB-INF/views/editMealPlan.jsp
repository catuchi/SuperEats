<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<div class="container">
    <h2>Edit Meal Plan</h2>
    <form action="${pageContext.request.contextPath}/mealPlan" method="post">
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="mealPlanId" value="${mealPlan.mealPlanId}" />

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" name="name" id="name" value="${mealPlan.name}" required />
        </div>
        <div class="form-group">
            <label for="startDate">Start Date:</label>
            <input type="date" name="startDate" id="startDate" value="${mealPlan.startDate}" required />
        </div>
        <div class="form-group">
            <label for="endDate">End Date:</label>
            <input type="date" name="endDate" id="endDate" value="${mealPlan.endDate}" required />
        </div>
        <button type="submit" class="btn-primary">Save Changes</button>
    </form>
</div>

<%@ include file="footer.jsp" %>
