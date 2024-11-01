package servlets;

import services.UserService;
import supereats.Role;
import supereats.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("login".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		} else if ("register".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
		} else if ("logout".equals(action)) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("login".equals(action)) {
			handleLogin(request, response);
		} else if ("register".equals(action)) {
			handleRegistration(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

//	private void handleLogin(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//
//		boolean loginSuccessful = userService.loginUser(email, password);
//
//		if (loginSuccessful) {
//			// Fetch the user object after a successful login
//			User user = userService.getUserByEmail(email);
//
//			// Start session and set user attribute
//			HttpSession session = request.getSession();
//			session.setAttribute("user", user);
//
//			// Redirect to home
//			response.sendRedirect(request.getContextPath() + "/home");
//		} else {
//			// Login failed, send back to login page with error
//			request.setAttribute("error", "Invalid email or password");
//			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
//		}
//	}
	
	private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    User user = userService.loginUser(email, password);

	    if (user != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("user", user);

	        // Redirect based on role
	        if (user.getRole().equals(Role.ADMIN)) {
	            response.sendRedirect(request.getContextPath() + "/admin?action=dashboard");
	        } else {
	            response.sendRedirect(request.getContextPath() + "/home");
	        }
	    } else {
	        request.setAttribute("error", "Invalid email or password");
	        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	    }
	}


	private void handleRegistration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String dietaryPreferences = request.getParameter("dietaryPreferences");
		String profilePicture = request.getParameter("profilePicture");

		// Create new User object
		User newUser = new User(name, email, password, dietaryPreferences, profilePicture);

		// Register user and check if successful
		if (userService.registerUser(newUser)) {
			// Registration successful, fetch user and start session
			User registeredUser = userService.getUserByEmail(email);
			HttpSession session = request.getSession();
			session.setAttribute("user", registeredUser);

			// Redirect to home page after logging in
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			// Registration failed, forward back to register page with error message
			request.setAttribute("error", "Registration failed. Email may already be in use.");
			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
		}
	}

}
