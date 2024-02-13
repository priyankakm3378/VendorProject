package com.tap.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

public class VendorDAO {
	  Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		public static final String url = "jdbc:mysql://localhost:3306/Vendor";
		public static final String un = "root";
		public static final String pwd = "root";
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		private static final String query1 = "update vendors set NAME = ?, BANK_NAME = ?, ADDRESS1 = ?, ADDRESS2 = ?, CITY = ?, COUNTRY = ?,ZIPCODE = ? WHERE ACC_NO = ?";
		private static final String query2 = "DELETE FROM VENDORS WHERE ACC_NO = ?";
		private static final String query3 = "Select NAME, BANK_NAME, ADDRESS1, ADDRESS2, CITY, COUNTRY, ZIPCODE FROM VENDORS WHERE ACC_NO = ?";
		
		
		protected static Connection getConnection() {
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("Driver loaded successfully");
				con = DriverManager.getConnection(url,un,pwd);
				System.out.println("Connection established successfully");
				System.out.println("-----------------------------------------------------------------------------");

			}
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} 
			return con;
		}
		
		

		
		public static boolean updateVendor(Vendorclass vendor) {
			boolean rowUpdated = false;
			try(Connection con = getConnection()) {
				
				PreparedStatement pstmt1 = con.prepareStatement(query1);
				pstmt1.setString(1, vendor.getVendorName());
				pstmt1.setString(2, vendor.getBankName());
				pstmt1.setString(3, vendor.getAddressLine1());
				pstmt1.setString(4, vendor.getAddressLine2());
				pstmt1.setString(5, vendor.getCity());
				pstmt1.setString(6, vendor.getCountry());
				pstmt1.setInt(7, vendor.getZipCode());
				
				
				rowUpdated = pstmt1.executeUpdate() > 0;

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return rowUpdated;
		}
		

		
		public static boolean deleteVendor(int bankAccountNo) throws SQLException {
			boolean rowDeleted;
			try(Connection con = getConnection()){
				PreparedStatement pstmt2 = con.prepareStatement(query2);
				pstmt2.setInt(1,  bankAccountNo);
				rowDeleted = pstmt2.executeUpdate() > 0;
				
			}
			return rowDeleted;
		}

		
		public VendorDAO(int bankAccountNo) {
			try {
				boolean rowDeleted = deleteVendor(bankAccountNo);
				Vendorclass vend = getVendor(bankAccountNo);
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
				
		public static Vendorclass getVendor(int bankAccountNO) {
			Vendorclass vend = null;
			try(Connection con = getConnection()){
				PreparedStatement pstmt3 = con.prepareStatement(query3);
				pstmt3.setInt(1, vend.bankAccountNo);
				ResultSet res = pstmt3.executeQuery();
				
				while(res.next()) {
					String name = res.getString("vendorName");
					int bankAccountNo = res.getInt(bankAccountNO);
					String bankName = res.getString("bankName");
					
					String addressLine1 = res.getString("addressLine1");
					String addressLine2 = res.getString("addressLine2");
					String city = res.getString("city");
					String country = res.getString("country");
					int zipCode = res.getInt("zipCode");
					
					vend = new Vendorclass( name, bankAccountNo, bankName,addressLine1, addressLine2, city, country, zipCode);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return vend;
		}
		
		
		
	
		
		
		public VendorDAO() {
		
			 try {
				List<Vendorclass> vendorList = retrieveVendorListFromDatabase();
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}


	   public static List<Vendorclass> retrieveVendorListFromDatabase() throws SQLException {
	        List<Vendorclass> vendorList = new ArrayList<>();
	        PreparedStatement pstmt = null;
	        ResultSet res = null;

	           try {
	        		
					Class.forName("com.mysql.cj.jdbc.Driver");
					System.out.println("Driver loaded successfully");
					Connection con = DriverManager.getConnection(url,un,pwd);
					System.out.println("Connection established successfully");
					
					System.out.println("===================================");
	        	   
	        	   String query = "SELECT ACC_NO, NAME, BANK_NAME, ADDRESS1, ADDRESS2, CITY, COUNTRY, ZIPCODE FROM VENDORS";
		             pstmt = con.prepareStatement(query);
		                 res = pstmt.executeQuery();
		                    while (res.next()) {
		                    	Vendorclass vendor = new Vendorclass();
		                                                
//		                        vendor.setBankAccountNo(resultSet.getInt("bankAccountNo"));
//		                        vendor.setVendorName(resultSet.getString("vendorName"));
//		                        vendor.setBankName(resultSet.getString("bankName"));
//		                        vendor.setAddressLine1(resultSet.getString("addressLine1"));
//		                        vendor.setAddressLine2(resultSet.getString("addressLine2"));
//		                        vendor.setCity(resultSet.getString("city"));
//		                        vendor.setCountry(resultSet.getString("country"));
//		                        vendor.setZipCode(resultSet.getInt("zipCode"));
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
		                
	        	   
	           }catch(ClassNotFoundException | SQLException e) {
	        	   e.printStackTrace();
	           }
	           finally {
	        	   try {
	        		   if(res != null) {
	        			   res.close();
	        			   System.out.println("Resource closed");
	        		   }
	        		   if(pstmt != null) {
	        			   pstmt.close();
	        			   System.out.println("PreparedStatement closed");
	        		   }
	        	   }catch(SQLException e) {
	        		   e.printStackTrace();
	        	   }
	           }

	        return vendorList;
	    }
}
