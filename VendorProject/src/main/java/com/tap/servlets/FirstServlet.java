package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet res = null;
    String url = "jdbc:mysql://localhost:3306/vendor";
    String un = "root";
    String pwd = "root";
    
    @Override
    public void init() throws ServletException{
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con = DriverManager.getConnection(url,un,pwd);
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
        

        System.out.println("name = "+username);
		System.out.println("password = "+password);
		
		try {
			String query = "SELECT * FROM VENDORS WHERE username = ? AND password = ?";  
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			res = pstmt.executeQuery();
			
			if(res.next() == true) {
				request.getRequestDispatcher("/VenderList.java").forward(request, response);
				//request.getRequestDispatcher("/EditVendor.jsp").forward(request, response);
				
			}	else {
				RequestDispatcher rd = request.getRequestDispatcher("/invalidlogin.html");
				rd.forward(request, response);	
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	      
   }

    public void destroy() {
    	try{
    		res.close();
    		pstmt.close();
    		//con.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
	}
}
















