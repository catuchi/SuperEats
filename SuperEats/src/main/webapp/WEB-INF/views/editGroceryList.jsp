<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<div class="container">
    <h2>Edit Grocery List</h2>
    <form action="${pageContext.request.contextPath}/groceryList" method="post">
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="listId" value="${groceryList.listId}" />

        <!-- Optionally add fields for the grocery list name or other attributes -->
        <button type="submit" class="btn-primary">Save Changes</button>
    </form>
</div>

<%@ include file="footer.jsp" %>
