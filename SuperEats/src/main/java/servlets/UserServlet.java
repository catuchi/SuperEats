package servlets;

import services.*;
import supereats.GroceryList;
import supereats.MealPlan;
import supereats.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private UserService userService;
	private MealPlanService mealPlanService;
	private GroceryListService groceryListService;

    @Override
    public void init() {
        userService = new UserService();
        mealPlanService = new MealPlanService();
        groceryListService = new GroceryListService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action != null ? action : "default") {
            case "profile":
                showProfilePage(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/home");
                break;
        }
    }

//    private void showProfilePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User sessionUser = (User) session.getAttribute("user");
//
//        if (sessionUser != null) {
//            User userProfile = userService.getUserProfile(sessionUser.getUserId());
//            request.setAttribute("userProfile", userProfile);
//            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
//        } else {
//            response.sendRedirect(request.getContextPath() + "/auth?action=login");
//        }
//    }
    
    private void showProfilePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser != null) {
            User userProfile = userService.getUserProfile(sessionUser.getUserId());
            request.setAttribute("userProfile", userProfile);

            // Fetch meal plans and grocery lists associated with the user
            List<MealPlan> mealPlans = mealPlanService.getMealPlansByUserId(sessionUser.getUserId());
            List<GroceryList> groceryLists = groceryListService.getGroceryListsByUserId(sessionUser.getUserId());
            request.setAttribute("mealPlans", mealPlans);
            request.setAttribute("groceryLists", groceryLists);

            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/auth?action=login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("updateProfile".equals(action)) {
            updateProfile(request, response);
        }
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser != null) {
            // Retrieve updated details from the form
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String dietaryPreferences = request.getParameter("dietaryPreferences");
            String profilePicture = request.getParameter("profilePicture");

            // Update only if not null or empty
            User updatedUser = sessionUser;
            if (name != null && !name.isEmpty()) updatedUser.setName(name);
            if (email != null && !email.isEmpty()) updatedUser.setEmail(email);
            if (password != null && !password.isEmpty()) updatedUser.setPassword(password);
            if (dietaryPreferences != null) updatedUser.setDietaryPreferences(dietaryPreferences);
            if (profilePicture != null) updatedUser.setProfilePicture(profilePicture);

            // Update in database
            userService.updateUserProfile(updatedUser);
            session.setAttribute("user", updatedUser); // Update session user

            response.sendRedirect(request.getContextPath() + "/user?action=profile");
        } else {
            response.sendRedirect(request.getContextPath() + "/auth?action=login");
        }
    }
}
