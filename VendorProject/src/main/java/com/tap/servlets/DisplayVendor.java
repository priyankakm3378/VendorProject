package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DisplayVendor")
public class DisplayVendor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public DisplayVendor() {
        super();
    }

    Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet res = null;
	String url = "jdbc:mysql://localhost:3306/vendor";
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
	    	
	    	try {
	    		String query2 = "SELECT * FROM DISPLAY";
				Statement stmt = con.createStatement();
				ResultSet res2 = stmt.executeQuery(query2);
				
				writer.println("<table border=\"1\">\r\n"
						+ "\r\n"
						+" <tr>\r\n"
						+" 		<th>ACC_NO</th>\r\n"
						+" 		<th>NAME</th>\r\n"
						+" 		<th>BANK_NAME</th>\r\n"
						+" 		<th>EDIT</th>\r\n"
						+" 		<th>DELETE</th>\r\n"
						+" 	</tr>");
				
				while(res2.next()==true) {
					int ACC_NO = res2.getInt(1);
					String NAME = res2.getString(2);
					String BANK_NAME = res2.getString(3);
					boolean EDIT = res2.getBoolean(4);
					boolean DELETE = res2.getBoolean(5);
					
					writer.println("<tr>\r\n"
							+"\r\n"
							+"		<td>" + ACC_NO + "</td>\r\n"
							+" 		<td>" + NAME + "</td>\r\n"
							+" 		<td>" + BANK_NAME + "</td>\r\n"
							+" 		<td>" + EDIT + "</td>\r\n"
							+" 		<td>" + DELETE + "</td>\r\n"
							+" 	</tr>");
					
				}
				
			writer.println("</table>");
				
			}
	    
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	
}

}
