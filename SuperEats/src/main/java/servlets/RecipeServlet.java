package servlets;

import services.RecipeService;
import supereats.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recipe")
public class RecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RecipeService recipeService;

	@Override
	public void init() throws ServletException {
		recipeService = new RecipeService();
	}

//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String action = request.getParameter("action");
//
//		switch (action != null ? action : "viewAll") {
//		case "view":
//		case "details": // Both "view" and "details" actions will lead to the viewRecipe method
//			viewRecipe(request, response);
//			break;
//		case "search":
//			searchRecipes(request, response);
//			break;
//		default:
//			listAllRecipes(request, response);
//			break;
//		}
//	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");

	    switch (action != null ? action : "viewAll") {
	        case "view":
	            viewRecipe(request, response);
	            break;
	        case "searchForm":
	            List<Recipe> allRecipes = recipeService.getAllRecipes(); // Fetch all recipes
	            request.setAttribute("allRecipes", allRecipes);
	            request.getRequestDispatcher("/WEB-INF/views/recipeSearch.jsp").forward(request, response);
	            break;
	        case "search":
	            searchRecipes(request, response);
	            break;
	        default:
	            listAllRecipes(request, response);
	            break;
	    }
	}


	private void searchRecipes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String query = request.getParameter("query");
	    String filter = request.getParameter("filter");

	    List<Recipe> recipes;
	    if ("ingredient".equalsIgnoreCase(filter)) {
	        recipes = recipeService.searchRecipesByIngredient(query);
	    } else if ("cuisine".equalsIgnoreCase(filter)) {
	        recipes = recipeService.searchRecipesByCuisine(query);
	    } else {
	        recipes = recipeService.searchRecipesByTitle(query);
	    }

	    List<Recipe> featuredRecipes = recipeService.getFeaturedRecipes();
	    request.setAttribute("recipes", recipes);
	    request.setAttribute("featuredRecipes", featuredRecipes);
	    request.setAttribute("query", query);
	    request.setAttribute("filter", filter);
	    request.getRequestDispatcher("/WEB-INF/views/recipeSearchResults.jsp").forward(request, response);
	}

//	private void viewRecipe(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String recipeIdParam = request.getParameter("recipeId") != null ? request.getParameter("recipeId")
//				: request.getParameter("id");
//
//		if (recipeIdParam != null) {
//			int recipeId = Integer.parseInt(recipeIdParam);
//			Recipe recipe = recipeService.getRecipeById(recipeId); // Fetch the recipe
//
//			if (recipe != null) {
//				request.setAttribute("recipe", recipe);
//				request.getRequestDispatcher("/WEB-INF/views/recipeDetails.jsp").forward(request, response);
//				return;
//			}
//		}
//
//		// Redirect to home or show an error message if the recipe is not found
//		response.sendRedirect(request.getContextPath() + "/home");
//	}
	
	private void viewRecipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int recipeId = Integer.parseInt(request.getParameter("id"));
	    Recipe recipe = recipeService.getRecipeById(recipeId);
	    
	    if (recipe != null) {
	        request.setAttribute("recipe", recipe);
	        
	        // Fetch additional data
	        request.setAttribute("recipeIngredients", recipeService.getRecipeIngredients(recipeId));
	        request.setAttribute("averageRating", recipeService.getAverageRating(recipeId));
	        
	        request.getRequestDispatcher("/WEB-INF/views/recipeDetails.jsp").forward(request, response);
	    } else {
	        response.sendRedirect(request.getContextPath() + "/home");
	    }
	}


//	private void searchRecipes(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String query = request.getParameter("query");
//		String filter = request.getParameter("filter");
//
//		List<Recipe> recipes;
//		if ("ingredient".equalsIgnoreCase(filter)) {
//			recipes = recipeService.searchRecipesByIngredient(query);
//		} else if ("cuisine".equalsIgnoreCase(filter)) {
//			recipes = recipeService.searchRecipesByCuisine(query);
//		} else {
//			recipes = recipeService.searchRecipesByTitle(query);
//		}
//
//		request.setAttribute("recipes", recipes);
//		request.setAttribute("query", query);
//		request.setAttribute("filter", filter);
//		request.getRequestDispatcher("/WEB-INF/views/recipeSearchResults.jsp").forward(request, response);
//	}

	private void listAllRecipes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Recipe> recipes = recipeService.getAllRecipes();
		request.setAttribute("recipes", recipes);
		request.getRequestDispatcher("/WEB-INF/views/recipeList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");

	    if ("rate".equals(action)) {
	        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
	        int ratingValue = Integer.parseInt(request.getParameter("rating"));
	        int userId = (int) request.getSession().getAttribute("userId"); // Assuming userId is stored in session
	        
	        recipeService.rateRecipe(recipeId, userId, ratingValue);

	        // Redirect back to the recipe details page to see the updated rating
	        response.sendRedirect(request.getContextPath() + "/recipe?action=view&id=" + recipeId);
	    }
	}


	@Override
	public void destroy() {
		super.destroy();
	}
}
