package com.quanlynhansu.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quanlynhansu.connection.JDBCConnection;

@WebServlet("/home")
public class HomeController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(Connection conn = JDBCConnection.getConnection()){
			if(conn != null) {
				System.out.println("Connect thanh cong");
			}
			else {
				System.out.println("Connect that bai");
			}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		
		req.getRequestDispatcher("views/home/index.jsp").forward(req, resp);
	}
}
