package com.quanlynhansu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quanlynhansu.dao.RoleDAO;
import com.quanlynhansu.model.Role;
import com.quanlynhansu.util.UrlConstants;
import com.quanlynhansu.util.PathConstants;

@WebServlet(name = "RoleController", urlPatterns = { UrlConstants.URL_ROLE_LIST, UrlConstants.URL_ROLE_ADD,
		UrlConstants.URL_ROLE_EDIT, UrlConstants.URL_ROLE_DELETE })
public class RoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoleDAO roleDao = null;

	@Override
	public void init() throws ServletException {
		roleDao = new RoleDAO();
	}

	private void getRoleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// gui du lieu ve trang jsp de show len
		req.setAttribute("roles", roleDao.findAll());
		req.getRequestDispatcher(PathConstants.PATH_ROLE_LIST).forward(req, resp);
	}

	private void getRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(PathConstants.PATH_ROLE_ADD).forward(req, resp);
	}

	private void getRoleEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lay id client truyen len url
		int id = Integer.parseInt(req.getParameter("id"));
		Role role = roleDao.findById(id);
		req.setAttribute("role", role);
		req.getRequestDispatcher(PathConstants.PATH_ROLE_EDIT).forward(req, resp);
	}

	private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lay id client truyen le url
		int id = Integer.parseInt(req.getParameter("id"));
		boolean result = roleDao.deleteById(id);
		if (result) {
			// Xuat thong bao xoa thanh cong
		} else {
			// xuat thong bao xoa that bai
		}
		// quay ve trang danh sach
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_ROLE_LIST);
	}

	private void postRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// lay du lieu tu form
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		// Kiem tra du lieu nguoi dung nhap
		if (name == null || name.isEmpty()) {
			req.setAttribute("message", "Vui lòng nhập tên:");
			req.getRequestDispatcher("/WEB-INF/views/role/add.jsp").forward(req, resp);

		}
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);

		boolean result = roleDao.insert(role);

		if (result) {
			// Xuat thong bao them thanh cong

			// Quay ve trang danh sach
			resp.sendRedirect(req.getContextPath() + UrlConstants.URL_ROLE_LIST);
		} else {
			// Xuat thong bao them that bai
			req.setAttribute("message", "Thêm mới thất bại");
			req.getRequestDispatcher("/WEB-INF/views/role/add.jsp");
		}

	}

	private void postRoleEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// lay du lieu tu form
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Role role = new Role(id, name, description);
		roleDao.update(role);
		// quay ve trang danh sach
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_ROLE_LIST);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.URL_ROLE_LIST:
			getRoleList(req, resp);
			break;
		case UrlConstants.URL_ROLE_ADD:
			getRoleAdd(req, resp);
			break;
		case UrlConstants.URL_ROLE_EDIT:
			getRoleEdit(req, resp);
			break;
		case UrlConstants.URL_ROLE_DELETE:
			deleteRole(req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.URL_ROLE_ADD:
			postRoleAdd(req, resp);
			break;
		case UrlConstants.URL_ROLE_EDIT:
			postRoleEdit(req, resp);
			break;

		default:
			break;
		}
	}
}
