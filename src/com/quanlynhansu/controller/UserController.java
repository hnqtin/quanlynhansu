package com.quanlynhansu.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.quanlynhansu.connection.JDBCConnection;
import com.quanlynhansu.dao.RoleDAO;
import com.quanlynhansu.dao.UserDAO;
import com.quanlynhansu.dto.UserDTO;
import com.quanlynhansu.model.User;
import com.quanlynhansu.util.PathConstants;
import com.quanlynhansu.util.UrlConstants;

@WebServlet(name = "UserController", urlPatterns = { UrlConstants.URL_USER_LIST, UrlConstants.URL_USER_ADD,
		UrlConstants.URL_USER_EDIT, UrlConstants.URL_USER_DETAILS, UrlConstants.URL_USER_DELETE })
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDAO userDao = null;
	private RoleDAO roleDao = null;

	@Override
	public void init() throws ServletException {
		userDao = new UserDAO();
		roleDao = new RoleDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.URL_USER_LIST:

			break;
		case UrlConstants.URL_USER_EDIT:
			break;
		case UrlConstants.URL_USER_DETAILS:
			break;
		case UrlConstants.URL_USER_ADD:
			break;
		case UrlConstants.URL_USER_DELETE:
			break;

		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.URL_USER_ADD:
			break;
		case UrlConstants.URL_USER_EDIT:
			break;

		default:
			break;
		}
	}

	private void getUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<UserDTO> users = userDao.findAllUserWithRoleName();

		req.setAttribute("users", users);
		req.getRequestDispatcher(PathConstants.PATH_USER_LIST).forward(req, resp);
	}

	private void getUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("roles", roleDao.findAll());
		req.getRequestDispatcher(PathConstants.PATH_ROLE_ADD).forward(req, resp);
	}

	private void getUserDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher(PathConstants.PATH_USER_DETAILS).forward(req, resp);
	}

	private void getUserEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		User user = userDao.findById(id);

		req.setAttribute("user", user);
		req.setAttribute("roles", roleDao.findAll());
		req.getRequestDispatcher(PathConstants.PATH_USER_EDIT).forward(req, resp);
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = "DELETE FROM users WHERE id = ?";
		int id = Integer.parseInt(req.getParameter("id"));

		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);

			// thuc thi truy van du lieu
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// quay ve trang ca nhan
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_USER_LIST);
	}

	private void postUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// lay du lieu tu form
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String avatar = req.getParameter("avatar");
		int roleId = Integer.parseInt(req.getParameter("roleId"));

		// ma hoa mat khau
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

		User user = new User();
		user.setEmail(email);
		user.setPassword(hashed);
		user.setFullname(fullname);
		user.setAvatar(avatar);
		user.setRoleId(roleId);

		userDao.insert(user);

		// Quay ve trang danh sach
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_USER_LIST);

	}

	private void postUserEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// lay du lieu tu form
		int id = Integer.parseInt(req.getParameter("id"));
		// Lay du lieu tu form
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String avatar = req.getParameter("avatar");
		int roleId = Integer.parseInt(req.getParameter("roleId"));

		User user = new User(id,email,password,fullname,avatar,roleId);
		userDao.update(user);
		
		//quay ve trang danh sach
		
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_USER_LIST);
	}
}
