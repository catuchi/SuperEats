package servlets;

import services.*;
import dao.*;
import supereats.Admin;
import supereats.GroceryList;
import supereats.MealPlan;
import supereats.Recipe;
import supereats.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RecipeService recipeService = new RecipeService();
    private MealPlanService mealPlanService = new MealPlanService();
//    private UserService userService = new UserService();
    private GroceryListService groceryListService = new GroceryListService();
    private UserDAO userDAO = new UserDAOImplementation();
    private AdminDAO adminDAO = new AdminDAOImplementation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recipe> featuredRecipes = recipeService.getFeaturedRecipes();
        request.setAttribute("featuredRecipes", featuredRecipes);

        List<MealPlan> mealPlans = mealPlanService.getAllMealPlans();
        request.setAttribute("mealPlans", mealPlans);

        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);

        List<Admin> admins = adminDAO.getAllAdmins();
        request.setAttribute("admins", admins);

        List<GroceryList> groceryLists = groceryListService.getAllGroceryLists();
        request.setAttribute("groceryLists", groceryLists);

        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }
}
