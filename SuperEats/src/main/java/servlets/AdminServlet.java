package servlets;

import services.AdminService;
import services.RecipeService;
import services.UserService;
import supereats.Recipe;
import supereats.Role;
import supereats.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private AdminService adminService;
    private RecipeService recipeService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminService();
        recipeService = new RecipeService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        // Check if the user is logged in and has an admin role
        if (currentUser == null || !currentUser.getRole().equals(Role.ADMIN)) {
            response.sendRedirect(request.getContextPath() + "/auth?action=login");
            return;
        }

        String action = request.getParameter("action");
        switch (action != null ? action : "dashboard") {
            case "viewRecipes":
                viewRecipes(request, response);
                break;
            case "viewUsers":
                viewUsers(request, response);
                break;
            case "approveRecipe":
                approveRecipe(request, response);
                break;
            case "deleteRecipe":
                deleteRecipe(request, response);
                break;
            default:
            	viewDashboard(request, response);
                break;
        }
    }


    private void viewDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/adminDashboard.jsp").forward(request, response);
    }

    private void viewUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/adminUsers.jsp").forward(request, response);
    }

    private void viewRecipes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recipe> recipes = recipeService.getAllRecipes();
        request.setAttribute("recipes", recipes);
        request.getRequestDispatcher("/WEB-INF/views/adminRecipes.jsp").forward(request, response);
    }

    private void approveRecipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        adminService.approveRecipe(recipeId);
        response.sendRedirect(request.getContextPath() + "/admin?action=viewRecipes");
    }

    private void deleteRecipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        recipeService.deleteRecipe(recipeId);
        response.sendRedirect(request.getContextPath() + "/admin?action=viewRecipes");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
