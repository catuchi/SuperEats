package servlets;

import services.RecipeService;
import supereats.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipeDetails")
public class RecipeDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private RecipeService recipeService = new RecipeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get recipe ID from the request parameter
        String recipeIdParam = request.getParameter("recipeId");
        if (recipeIdParam != null) {
            try {
                int recipeId = Integer.parseInt(recipeIdParam);

                // Fetch the recipe by ID
                Recipe recipe = recipeService.getRecipeById(recipeId);

                if (recipe != null) {
                    // Set recipe as a request attribute
                    request.setAttribute("recipe", recipe);

                    // Forward to recipeDetails.jsp
                    request.getRequestDispatcher("/WEB-INF/views/recipeDetails.jsp").forward(request, response);
                } else {
                    // If no recipe found, redirect to home with an error message
                    response.sendRedirect(request.getContextPath() + "/home?error=RecipeNotFound");
                }
            } catch (NumberFormatException e) {
                // Invalid recipe ID format, redirect to home with an error
                response.sendRedirect(request.getContextPath() + "/home?error=InvalidRecipeId");
            }
        } else {
            // No recipe ID provided, redirect to home with an error
            response.sendRedirect(request.getContextPath() + "/home?error=NoRecipeId");
        }
    }
}
