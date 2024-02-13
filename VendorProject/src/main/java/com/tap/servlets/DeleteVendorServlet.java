package com.tap.servlets;

//DeleteVendorServlet.java

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteVendorServlet")
public class DeleteVendorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bankAccountNo = Integer.parseInt(request.getParameter("bankAccountNo"));

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Vendor", "root", "root");

			String sql = "DELETE FROM vendors WHERE ACC_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bankAccountNo);

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				// Deletion successful
				// response.sendRedirect(request.getContextPath() + "/VenderList.jsp");
				// response.sendRedirect("VenderList.jsp");
				// response.sendRedirect("Login");

				List<Vendorclass> list = VendorDAO.retrieveVendorListFromDatabase();

				request.setAttribute("list", list);
//				response.sendRedirect(request.getContextPath() + "/VenderList.jsp");

								
				RequestDispatcher rd = request.getRequestDispatcher("/VenderList.jsp");
				rd.forward(request, response);

				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}

			} else {
				// Deletion failed
				response.getWriter().println("Failed to delete vendor record.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("An error occurred while processing your request.");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
