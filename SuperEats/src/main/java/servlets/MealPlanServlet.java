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
	private MealPlanService mealPlanService = new MealPlanService();
    private final RecipeService recipeService = new RecipeService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("view".equals(action)) {
			handleViewMealPlans(request, response);
		} else if ("create".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/createMealPlan.jsp").forward(request, response);
		} else if ("details".equals(action)) {
			handleMealPlanDetails(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("create".equals(action)) {
			handleCreateMealPlan(request, response);
		}
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
