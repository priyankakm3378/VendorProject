package com.tap.servlets;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/VenderServlet")
public class VenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
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
    	 
        List<Vendorclass> vendorList = retrieveVendorListFromDatabase();

        request.setAttribute("vendorList", vendorList);
        System.out.println("hello");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VenderList.jsp");
        dispatcher.forward(request, response);
    }

    private List<Vendorclass> retrieveVendorListFromDatabase() {
        List<Vendorclass> vendorList = new ArrayList<>();

        try{
            String query = "SELECT ACC_NO, NAME, BANK_NAME, ADDRESS1, ADDRESS2, CITY, COUNTRY, ZIPCODE FROM VENDORS";
            PreparedStatement pstmt = con.prepareStatement(query);
                ResultSet res = pstmt.executeQuery();
                    while (res.next()) {
                    	Vendorclass vendor = new Vendorclass();
                                                
//                        vendor.setBankAccountNo(resultSet.getInt("bankAccountNo"));
//                        vendor.setVendorName(resultSet.getString("vendorName"));
//                        vendor.setBankName(resultSet.getString("bankName"));
//                        vendor.setAddressLine1(resultSet.getString("addressLine1"));
//                        vendor.setAddressLine2(resultSet.getString("addressLine2"));
//                        vendor.setCity(resultSet.getString("city"));
//                        vendor.setCountry(resultSet.getString("country"));
//                        vendor.setZipCode(resultSet.getInt("zipCode"));
//                        
                        vendor.setBankAccountNo(res.getInt(1));
                        vendor.setVendorName(res.getString(2));
                        vendor.setBankName(res.getString(3));
                        vendor.setAddressLine1(res.getString(4));
                        vendor.setAddressLine2(res.getString(5));
                        vendor.setCity(res.getString(6));
                        vendor.setCountry(res.getString(7));
                        vendor.setZipCode(res.getInt(8));
                        
                        
                        vendorList.add(vendor);
                    }
                
            
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return vendorList;
    }
}



