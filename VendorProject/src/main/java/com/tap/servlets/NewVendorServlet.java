package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NewVendorServlet")
public class NewVendorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewVendorServlet() {
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
			con = DriverManager.getConnection(url, un, pwd);
			System.out.println("Connection established successfully");
			System.out.println("-----------------------------------------------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException {

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String vendorName = request.getParameter("vendorName");
		int bankAccountNo = Integer.parseInt(request.getParameter("bankAccountNo"));
		//String AccountNo = request.getParameter("AccountNo");
		String bankName = request.getParameter("bankName");
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		int zipCode = Integer.parseInt(request.getParameter("zipCode"));
		//String zipCode = request.getParameter("zipCode");

		try {
//			if (bankAccountNo != null && zipCode != null) {

//				Integer bankAccountNo = Integer.parseInt(AccountNo);
//				Integer zipcode = Integer.parseInt(zipCode);

				String query = "INSERT INTO VENDORS(`ACC_NO`,`NAME`,`BANK_NAME`,`ADDRESS1`,`ADDRESS2`,`CITY`,`COUNTRY`,`ZIPCODE`,`USERNAME`,`PASSWORD`) VALUES (?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, bankAccountNo);
				pstmt.setString(2, vendorName);
				pstmt.setString(3, bankName);
				pstmt.setString(4, addressLine1);
				pstmt.setString(5, addressLine2);
				pstmt.setString(6, city);
				pstmt.setString(7, country);
				pstmt.setInt(8, zipCode);
				pstmt.setString(10, password);
				pstmt.setString(9, username);

				// res = pstmt.executeQuery();
				int k = pstmt.executeUpdate();

				System.out.println("Vendor UserName: " + username);
				System.out.println("Password: " + password);
				System.out.println("Vendor Name: " + vendorName);
				System.out.println("Bank Account No: " + bankAccountNo);
				System.out.println("Bank Name: " + bankName);
				System.out.println("Address Line 1: " + addressLine1);
				System.out.println("Address Line 2: " + addressLine2);
				System.out.println("City: " + city);
				System.out.println("Country: " + country);
				System.out.println("ZipCode: " + zipCode);
				
				writer.println(k + "Records are inserted");
				System.out.println(k + " Records are inserted.");

//			} else {
//				writer.println("There was an error processing your request.\n");
//				writer.println("Please check the provided data and try again.");
//				System.out.println("There was an error processing your request.");
//
//			}
		} catch (NumberFormatException | SQLException e) {

			e.printStackTrace();
			writer.println("There was an error processing your request.\n");
			writer.println("Please check the provided data and try again.");
			System.out.println("There was an error processing your request.");

		}

	}

}
