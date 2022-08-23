package com.java.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.java.dao.DBConnect;
import com.java.dao.UserDao;
import com.java.dao.UserDaoImpl;
import com.java.entity.User;
import com.java.helper.Helper;
import com.java.helper.ValidateModel;

/**
 * Servlet implementation class UpdateServlet
 */
@MultipartConfig
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ERROR = "";
		HttpSession session = null;
		String file_path = "";
		boolean profile_flag = false;
		Map<String, String> ERROR_MAP = new HashMap<String, String>();

		try {
			int userid = Integer.parseInt(request.getParameter("userid"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			String password = request.getParameter("password");
			boolean tc = Boolean.parseBoolean(request.getParameter("tc"));
			Part part = request.getPart("profile");
			String profile= part.getSubmittedFileName();
			
			
			if (profile.isBlank()) {
				session = request.getSession(false);
				User user = (User)session.getAttribute("user");
				if (user.getProfile()!="default.jpg") {
					profile = user.getProfile();
					profile_flag = true;
				}
				else {
					profile ="default.jpg";					
				}
			}
			
			User user = new User(userid, name, email, gender, password, profile);
			
			ERROR_MAP = ValidateModel.Validate(user);
			if (!ERROR_MAP.isEmpty()) {
				request.setAttribute("error_map", ERROR_MAP);
				request.setAttribute("user", user);
				request.getRequestDispatcher("user/edit-profile.jsp").include(request, response);
				return;
			}
			UserDao userDao = new UserDaoImpl(DBConnect.connect());
			boolean flag = userDao.updateUser(user);
			if (!flag) {
//				System.out.println(user);
				request.setAttribute("error", "User already exist with username");
				request.setAttribute("user", user);
				request.getRequestDispatcher("user/edit-profile.jsp").forward(request, response);
				return;
			} else {
				if (user.getProfile()!="default.jpg" && !profile_flag) {
					file_path = request.getRealPath("/") + "images" + File.separator + user.getProfile();
					Helper.saveProfile(part.getInputStream(), file_path);	
				}
				
				request.setAttribute("msg", "profile updated successfully");
				System.out.println(user);
				session = request.getSession(false);
				session.setAttribute("user", user);
				response.sendRedirect("user/profile.jsp");
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("user/edit-profile.jsp").forward(request, response);
		}

	}

}
