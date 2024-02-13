package com.tap.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*; 
import java.sql.*;

@WebServlet("/AddVendorServlet")
public class AddVendorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        
    	String vendorName = request.getParameter("vendorName");
        int bankAccountNo = Integer.parseInt(request.getParameter("bankAccountNo"));
        String bankName = request.getParameter("bankName");
        
        
        Vendorclass ven = new Vendorclass();
        ven.setVendorName(vendorName);
        ven.setBankAccountNo(bankAccountNo);
        ven.setBankName(bankName);
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
           
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/vendor";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            
          
            String sql = "INSERT INTO vendors (vendor_name, bank_account_no, bank_name) VALUES (?, ?, ?)";
            String sql2 = "INSERT INTO VENDORS(ACC_NO, NAME, BANK_NAME, ADDRESS1,"
            		+ " ADDRESS2, CITY, COUNTRY, ZIPCODE, USERNAME, PASSWORD) VALUES (?,?,?,?,?,?,?,?,?,?)";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ven.getVendorName());
            preparedStatement.setInt(2, ven.getBankAccountNo());
            preparedStatement.setString(3, ven.getBankName());
            
          
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
              
                HttpSession session = request.getSession();
                session.setAttribute("newVendor", ven);
                response.sendRedirect("VendorList.html");
            } else {
                
                response.getWriter().println("Failed to save vendor data.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred while processing your request.");
        } finally {
            
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

