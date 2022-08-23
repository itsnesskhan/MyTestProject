package com.java.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dao.DBConnect;
import com.java.dao.UserDao;
import com.java.dao.UserDaoImpl;
import com.java.entity.User;
import com.java.helper.ValidateModel;

/**
 * Servlet implementation class SignupSevlet
 */
@WebServlet("/SignupSevlet")
public class SignupSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.service(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.service(request, response);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ERROR = "";
		HttpSession session = null;
		Map<String, String> ERROR_MAP = new HashMap<String, String>();

		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			String password = request.getParameter("password");
			boolean tc = Boolean.parseBoolean(request.getParameter("tc"));

			User user = new User(name, email, gender, password);
			user.setProfile("default.jpg");

			ERROR_MAP = ValidateModel.Validate(user);
			if (!ERROR_MAP.isEmpty()) {
				request.setAttribute("error_map", ERROR_MAP);
				request.setAttribute("user", user);
				request.getRequestDispatcher("user/index.jsp").include(request, response);
				return;
			} else if (!tc) {
				request.setAttribute("error", "Please agree to the terms and conditions!");
				request.setAttribute("user", user);
				request.getRequestDispatcher("user/index.jsp").forward(request, response);
				return;
			}
			UserDao userDao = new UserDaoImpl(DBConnect.connect());
			boolean flag = userDao.signUpUser(user);
			if (!flag) {
				request.setAttribute("error", "User already exist with username");
				request.setAttribute("user", user);
				request.getRequestDispatcher("user/index.jsp").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("user/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("user/index.jsp").forward(request, response);
		}

	}

}
