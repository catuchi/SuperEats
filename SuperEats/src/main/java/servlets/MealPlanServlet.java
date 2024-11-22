package servlets;

import services.MealPlanService;
import services.RecipeService;
import supereats.MealPlan;
import supereats.MealPlanRecipe;
import supereats.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/mealPlan")
public class MealPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MealPlanService mealPlanService = new MealPlanService();
    private final RecipeService recipeService = new RecipeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action != null ? action : "view") {
            case "view":
                handleViewMealPlans(request, response);
                break;
            case "create":
                request.getRequestDispatcher("/WEB-INF/views/createMealPlan.jsp").forward(request, response);
                break;
            case "details":
                handleMealPlanDetails(request, response);
                break;
            case "edit":
                handleEditMealPlan(request, response);
                break;
            case "delete":
                handleDeleteMealPlan(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/home");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action != null ? action : "") {
            case "create":
                handleCreateMealPlan(request, response);
                break;
            case "update":
                handleUpdateMealPlan(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/home");
                break;
        }
    }
    
    private void handleEditMealPlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int mealPlanId = Integer.parseInt(request.getParameter("id"));
        MealPlan mealPlan = mealPlanService.getMealPlanById(mealPlanId);

        if (mealPlan != null) {
            request.setAttribute("mealPlan", mealPlan);
            request.getRequestDispatcher("/WEB-INF/views/editMealPlan.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/mealPlan?action=view");
        }
    }
    
    private void handleDeleteMealPlan(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int mealPlanId = Integer.parseInt(request.getParameter("id"));
        mealPlanService.deleteMealPlan(mealPlanId);
        response.sendRedirect(request.getContextPath() + "/mealPlan?action=view");
    }
    
    private void handleUpdateMealPlan(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int mealPlanId = Integer.parseInt(request.getParameter("mealPlanId"));
        String name = request.getParameter("name");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

        MealPlan mealPlan = new MealPlan(mealPlanId, 0, name, startDate, endDate); 
        mealPlanService.updateMealPlan(mealPlan);

        response.sendRedirect(request.getContextPath() + "/mealPlan?action=view");
    }

	private void handleViewMealPlans(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			List<MealPlan> mealPlans = mealPlanService.getMealPlansByUserId(user.getUserId());
			request.setAttribute("mealPlans", mealPlans);
			request.getRequestDispatcher("/WEB-INF/views/viewMealPlans.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/auth?action=login");
		}
	}

	private void handleCreateMealPlan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			String name = request.getParameter("name");
			LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
			LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

			MealPlan newMealPlan = new MealPlan(user.getUserId(), name, startDate, endDate);
			mealPlanService.createMealPlan(newMealPlan);

			response.sendRedirect(request.getContextPath() + "/mealPlan?action=view");
		} else {
			response.sendRedirect(request.getContextPath() + "/auth?action=login");
		}
	}

//    private void handleMealPlanDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String mealPlanIdParam = request.getParameter("mealPlanId");
//        if (mealPlanIdParam != null) {
//            int mealPlanId = Integer.parseInt(mealPlanIdParam);
//            MealPlan mealPlan = mealPlanService.getMealPlanById(mealPlanId); // Ensure this method is implemented in MealPlanService
//            request.setAttribute("mealPlan", mealPlan);
//            request.getRequestDispatcher("/WEB-INF/views/mealPlanDetails.jsp").forward(request, response);
//        } else {
//            response.sendRedirect(request.getContextPath() + "/mealPlan?action=view");
//        }
//    }

	private void handleMealPlanDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int mealPlanId = Integer.parseInt(request.getParameter("mealPlanId"));
		MealPlan mealPlan = mealPlanService.getMealPlanById(mealPlanId);

		// Map to store MealPlanRecipe ID and Recipe name
		Map<Integer, String> mealPlanRecipeNames = new HashMap<>();

		// Populate the map with recipe names
		for (MealPlanRecipe mealPlanRecipe : mealPlan.getMealPlanRecipes()) {
			int recipeId = mealPlanRecipe.getRecipeId();
			String recipeName = recipeService.getRecipeById(recipeId).getTitle(); // Get recipe name
			mealPlanRecipeNames.put(mealPlanRecipe.getMealPlanRecipeId(), recipeName);
		}

		// Set mealPlan and mealPlanRecipeNames as attributes for the JSP
		request.setAttribute("mealPlan", mealPlan);
		request.setAttribute("mealPlanRecipeNames", mealPlanRecipeNames);

		request.getRequestDispatcher("/WEB-INF/views/mealPlanDetails.jsp").forward(request, response);
	}
}
