package servlets;

import services.GroceryListService;
import supereats.GroceryList;
import supereats.GroceryListIngredient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/groceryList")
public class GroceryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroceryListService groceryListService;

	@Override
	public void init() throws ServletException {
		groceryListService = new GroceryListService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action != null ? action : "viewAll") {
		case "view":
			viewGroceryList(request, response);
			break;
		case "add":
			showAddGroceryListForm(request, response);
			break;
		case "editIngredient":
			showEditIngredientForm(request, response);
			break;
		case "deleteIngredient":
			deleteIngredientFromGroceryList(request, response);
			break;
		default:
			listAllGroceryLists(request, response);
			break;
		}
	}

	private void viewGroceryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int listId = Integer.parseInt(request.getParameter("listId"));
		GroceryList groceryList = groceryListService.getGroceryListById(listId);

		if (groceryList != null) {
			request.setAttribute("groceryList", groceryList);
			request.getRequestDispatcher("/WEB-INF/views/groceryListDetails.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/groceryList");
		}
	}

	private void listAllGroceryLists(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<GroceryList> groceryLists = groceryListService.getAllGroceryLists();
		request.setAttribute("groceryLists", groceryLists);
		request.getRequestDispatcher("/WEB-INF/views/groceryList.jsp").forward(request, response);
	}

	private void showAddGroceryListForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/addGroceryList.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("addIngredient".equals(action)) {
			addIngredientToGroceryList(request, response);
		} else if ("updateIngredient".equals(action)) {
			updateIngredient(request, response);
		} else if ("createList".equals(action)) {
			createGroceryList(request, response);
		} else if ("deleteIngredient".equals(action)) {
			deleteIngredientFromGroceryList(request, response);
		}
	}

	private void updateIngredient(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int listId = Integer.parseInt(request.getParameter("listId"));
		int ingredientId = Integer.parseInt(request.getParameter("ingredientId"));
		double quantity = Double.parseDouble(request.getParameter("quantity"));
		String unit = request.getParameter("unit");

		groceryListService.updateIngredient(listId, ingredientId, quantity, unit);
		response.sendRedirect(request.getContextPath() + "/groceryList?action=view&listId=" + listId);
	}

	private void addIngredientToGroceryList(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int listId = Integer.parseInt(request.getParameter("listId"));
		int ingredientId = Integer.parseInt(request.getParameter("ingredientId"));
		double quantity = Double.parseDouble(request.getParameter("quantity"));
		String unit = request.getParameter("unit");

		groceryListService.addIngredient(listId, ingredientId, quantity, unit);
		response.sendRedirect(request.getContextPath() + "/groceryList?action=view&listId=" + listId);
	}

	private void createGroceryList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		GroceryList newGroceryList = new GroceryList(userId);

		groceryListService.createGroceryList(newGroceryList);
		response.sendRedirect(request.getContextPath() + "/groceryList");
	}

	private void showEditIngredientForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int listId = Integer.parseInt(request.getParameter("listId"));
		int ingredientId = Integer.parseInt(request.getParameter("ingredientId"));

		GroceryList groceryList = groceryListService.getGroceryListById(listId);
		if (groceryList != null) {
			// Find the specific ingredient in the list
			GroceryListIngredient ingredient = groceryList.getIngredients().stream()
					.filter(i -> i.getIngredientId() == ingredientId).findFirst().orElse(null);

			if (ingredient != null) {
				request.setAttribute("ingredient", ingredient);
				request.setAttribute("listId", listId);
				request.getRequestDispatcher("/WEB-INF/views/editIngredient.jsp").forward(request, response);
				return;
			}
		}
		response.sendRedirect(request.getContextPath() + "/groceryList?action=view&listId=" + listId);
	}

	private void deleteIngredientFromGroceryList(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int listId = Integer.parseInt(request.getParameter("listId"));
		int ingredientId = Integer.parseInt(request.getParameter("ingredientId"));

		groceryListService.deleteIngredient(listId, ingredientId);

		response.sendRedirect(request.getContextPath() + "/groceryList?action=view&listId=" + listId);
	}

}
