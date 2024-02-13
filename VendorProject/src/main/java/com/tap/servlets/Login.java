package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Login() {
        super();
        
    }

	
    Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet res = null;
	String url = "jdbc:mysql://localhost:3306/Vendor";
	String un = "root";
	String pwd = "root";
	
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully");
			con = DriverManager.getConnection(url,un,pwd);
			System.out.println("Connection established successfully");
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			String query = "SELECT * FROM VENDORS WHERE USERNAME = ? AND PASSWORD = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			res = pstmt.executeQuery();
			
			
			if(res.next() == true) {
				writer.println("<h3>Welcome "+ res.getString(2)+"!<h3>");
				System.out.println("vendor list");
				
				List<Vendorclass> list = VendorDAO.retrieveVendorListFromDatabase();
				
				request.setAttribute("list", list);
			//  response.sendRedirect(request.getContextPath() + "/VenderList.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/VenderList.jsp");
				rd.forward(request, response);
				
				  
		        for(int i=0;i<list.size();i++){
		            System.out.println(list.get(i));
		        } 
		        		
					
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/invalidlogin.html");
				rd.forward(request, response);	
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
