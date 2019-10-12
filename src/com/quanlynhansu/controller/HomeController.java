package com.quanlynhansu.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quanlynhansu.connection.JDBCConnection;
import com.quanlynhansu.util.PathConstants;
import com.quanlynhansu.util.UrlConstants;

@WebServlet(name = "HomeServlet", urlPatterns = {UrlConstants.URL_HOME})
public class HomeController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		req.getRequestDispatcher(PathConstants.PATH_HOME).forward(req, resp);
	}
}
