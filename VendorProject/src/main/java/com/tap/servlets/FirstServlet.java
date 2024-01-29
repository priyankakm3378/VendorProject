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
				request.getRequestDispatcher("/DisplayVendor.java").forward(request, response);
				
			}
//				writer.println("<h3>Welcome "+ res.getString(2)+"!<h3>");
//				String query2 = "SELECT * FROM DISPLAY";
//				Statement stmt = con.createStatement();
//				ResultSet res2 = stmt.executeQuery(query2);
//				
//				writer.println("<table border=1>\r\n"
//						+ "\r\n"
//						+" <tr>\r\n"
//						+" 		<th>ACC_NO</th>\r\n"
//						+" 		<th>NAME</th>\r\n"
//						+" 		<th>BANK_NAME</th>\r\n"
//						+" 		<th>EDIT</th>\r\n"
//						+" 		<th>DELETE</th>\r\n"
//						+" 	</tr>");
//				
//				while(res2.next()==true) {
//					int ACC_NO = res2.getInt(1);
//					String NAME = res2.getString(2);
//					String BANK_NAME = res2.getString(3);
//					boolean EDIT = res2.getBoolean(4);
//					boolean DELETE = res2.getBoolean(5);
//					
//					writer.println("<tr>\r\n"
//							+"\r\n"
//							+"		<td>" + ACC_NO + "</td>\r\n"
//							+" 		<td>" + NAME + "</td>\r\n"
//							+" 		<td>" + BANK_NAME + "</td>\r\n"
//							+" 		<td>" + EDIT + "</td>\r\n"
//							+" 		<td>" + DELETE + "</td>\r\n"
//							+" 	</tr>");
//					
//				}
//				
//			writer.println("</table>");
//				
//			}
//			else {
//				RequestDispatcher rd = request.getRequestDispatcher("/invalidlogin.html");
//				rd.forward(request, response);	
//				
//				writer.println("<h3>Invalid login details. Please try again! <h3>");
//			}
//			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
//        boolean isValidLogin = validateLogin(username, password);
//
//        
//        response.getWriter().println("<html><body>");
//        response.getWriter().println("<h2>Login Result:</h2>");
//
//        if (isValidLogin) {
//            response.getWriter().println("<p>Welcome, " + username + "!</p>");
//        } else {
//            response.getWriter().println("<p>Invalid username or password.</p>");
//        }
//
//        response.getWriter().println("</body></html>");
//    }
//
//    private boolean validateLogin(String username, String password) {
//        // Dummy login validation logic (replace with your actual authentication logic)
//        return "demo".equals(username) && "password".equals(password);
//          
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



















//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Enumeration;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@WebServlet("/FirstServlet")
//public class FirstServlet extends HttpServlet {
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		
//		String Uname = request.getParameter("Uname");
//		String pswd = request.getParameter("pswd");
//		
//		response.setContentType("text/html");
//		
////		PrintWriter writer = response.getWriter();
////		writer.println("<h3>doGet() service method called by priyanka</h3>");
////		
//		
//		System.out.println("name = "+Uname);
//		System.out.println("password = "+pswd);
//		
////		Enumeration<String> pn = request.getParameterNames();
////		while(pn.hasMoreElements()) {
////			System.out.println(pn.nextElement());
////		}
////
////		RequestDispatcher rd = request.getRequestDispatcher("/staticresp.html");
////		rd.forward(request, response);
////		
//	}
//
//	public void destroy() {
//		System.out.println("destroy() method called");
//	}
//
//}
