<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>

<div class="container">
	<h2>Your Profile</h2>
	<form action="${pageContext.request.contextPath}/user" method="post">
		<input type="hidden" name="action" value="updateProfile" />

		<!-- Profile Details -->
		<div class="form-group">
			<label for="name">Name:</label> <input type="text" name="name"
				id="name" value="${userProfile.name}" required />
		</div>
		<div class="form-group">
			<label for="email">Email:</label> <input type="email" name="email"
				id="email" value="${userProfile.email}" required />
		</div>
		<c:if test="${userProfile.role == 'USER'}">
			<div class="form-group">
				<label for="dietaryPreferences">Dietary Preferences:</label> <input
					type="text" name="dietaryPreferences" id="dietaryPreferences"
					value="${userProfile.dietaryPreferences}" />
			</div>
			<div class="form-group">
				<label for="profilePicture">Profile Picture URL:</label> <input
					type="text" name="profilePicture" id="profilePicture"
					value="${userProfile.profilePicture}" />
			</div>
		</c:if>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password"
				name="password" id="password"
				placeholder="Leave blank to keep current password" />
		</div>
		<button type="submit" class="btn-primary">Save Changes</button>
	</form>

	<!-- User's Meal Plans -->
	<h2>Your Meal Plans</h2>
	<c:choose>
		<c:when test="${not empty mealPlans}">
			<ul>
				<c:forEach var="mealPlan" items="${mealPlans}">
					<li><strong>${mealPlan.name}</strong> (Start:
						${mealPlan.startDate}, End: ${mealPlan.endDate}) <a
						href="${pageContext.request.contextPath}/mealPlan?action=edit&id=${mealPlan.mealPlanId}">Edit</a>
						<a
						href="${pageContext.request.contextPath}/mealPlan?action=delete&id=${mealPlan.mealPlanId}">Delete</a>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No meal plans available.</p>
		</c:otherwise>
	</c:choose>

	<!-- User's Grocery Lists -->
	<h2>Your Grocery Lists</h2>
	<c:choose>
		<c:when test="${not empty groceryLists}">
			<ul>
				<c:forEach var="groceryList" items="${groceryLists}">
					<li><strong>Grocery List ID: ${groceryList.listId}</strong> <a
						href="${pageContext.request.contextPath}/groceryList?action=edit&id=${groceryList.listId}">Edit</a>
						<a
						href="${pageContext.request.contextPath}/groceryList?action=delete&id=${groceryList.listId}">Delete</a>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No grocery lists available.</p>
		</c:otherwise>
	</c:choose>
</div>

<%@ include file="footer.jsp"%>
