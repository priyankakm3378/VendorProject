// EditVendorServlet.java

package com.tap.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditVendorServlet")
public class EditVendorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("delete")) {
            deleteVendor(request, response);
        } else {
            int bankAccountNo = Integer.parseInt(request.getParameter("bankAccountNo"));
            Vendorclass newVendor = retrieveDetailsByAccNo(bankAccountNo);
            if(newVendor != null) {
                request.setAttribute("newVendor", newVendor);
                RequestDispatcher dispatcher = request.getRequestDispatcher("EditVendor.jsp");
                dispatcher.forward(request, response);
            } else {
                System.out.println("Something error happened and new Vendor object is null");
            }
        }
    }
    
    private void deleteVendor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bankAccountNo = Integer.parseInt(request.getParameter("bankAccountNo"));
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendor", "root", "root");
            String sql = "DELETE FROM vendors WHERE ACC_NO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bankAccountNo);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("VendorList.jsp");
            } else {
                response.getWriter().println("Failed to delete vendor data.");
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
    
 // EditVendorServlet.java

    private static Vendorclass retrieveDetailsByAccNo(int bankAccountNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vendorclass newVendor = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendor", "root", "root");

            String sql = "SELECT * FROM vendors WHERE ACC_NO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bankAccountNo);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                newVendor = new Vendorclass();
                newVendor.setVendorName(rs.getString("vendor_name"));
                newVendor.setBankAccountNo(rs.getInt("bank_account_no"));
                newVendor.setBankName(rs.getString("bank_name"));
                newVendor.setAddressLine1(rs.getString("addressLine1"));
                newVendor.setAddressLine2(rs.getString("addressLine2"));
                newVendor.setCity(rs.getString("city"));
                newVendor.setCountry(rs.getString("country"));
                newVendor.setZipCode(rs.getInt("zipCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

        return newVendor;
    }

}
