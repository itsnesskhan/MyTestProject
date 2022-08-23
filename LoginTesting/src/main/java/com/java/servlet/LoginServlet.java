package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dao.DBConnect;
import com.java.dao.UserDao;
import com.java.dao.UserDaoImpl;
import com.java.entity.LoginRequest;
import com.java.entity.User;
import com.java.helper.ValidateModel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = null;
		Map<String, String> ERROR_MAP = new HashMap<String, String>();
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			LoginRequest loginRequest = new LoginRequest(email, password);

			ERROR_MAP = ValidateModel.Validate(loginRequest);
			if (!ERROR_MAP.isEmpty()) {
				req.setAttribute("error_map", ERROR_MAP);
				req.setAttribute("login", loginRequest);
				req.getRequestDispatcher("user/login.jsp").include(req, resp);
				return;

			}

			UserDao userDao = new UserDaoImpl(DBConnect.connect());
			User user = userDao.getUserByEmail(loginRequest);
			if (user == null) {
				req.setAttribute("error", "username or password is incorrect");
				req.getRequestDispatcher("user/login.jsp").forward(req, resp);
			} else {
				session = req.getSession();
				session.setAttribute("user", user);
				Cookie cookie = new Cookie("email", user.getEmail());
				resp.addCookie(cookie);
				String encodeUrl = resp.encodeURL("user/profile.jsp");
				resp.sendRedirect(encodeUrl);
			}

		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("user/login.jsp").forward(req, resp);
		}

	}

}
