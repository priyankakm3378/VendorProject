package com.tap.servlets;
import com.tap.servlets.VendorDAO;
import com.tap.servlets.Vendorclass;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditDeleteServlet")
public class EditDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
   	 
    private VendorDAO vendorDAO;
    public EditDeleteServlet() {
        super();
        this.vendorDAO = new VendorDAO(); 
    }

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getServletPath();
			
			switch(action) {
			case "/EditDelete":
				try {
					showEditForm(request, response);
				} catch (SQLException | ServletException | IOException e) {
					e.printStackTrace();
				}
				
				break;
				
			case "/update":
				try {
					updateVendor(request, response);
				}catch(SQLException | IOException e) {
					e.printStackTrace();
				}
				break;
				
			case "/delete":
				try{
					deleteVendor(request, response);
				}catch(SQLException | IOException e){
					
				}
				break;
			
			}
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private void updateVendor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int bankAccountNo = Integer.parseInt(request.getParameter("bankAccountNo"));
		String vendorName = request.getParameter("vendorName");
		String bankName = request.getParameter("bankName");
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		int zipCode = Integer.parseInt(request.getParameter("zipCode"));
				
		Vendorclass vendorObj = new Vendorclass(vendorName , bankName, addressLine1, addressLine2,
				city, country, zipCode);
		
		vendorDAO.updateVendor(vendorObj);
		response.sendRedirect("list");

	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int bankAccountNo = Integer.parseInt(request.getParameter("bankAccountNo"));
		Vendorclass existingVendor = null;
		try {
			existingVendor = VendorDAO.getVendor(bankAccountNo);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/EditForm.jsp");
		request.setAttribute("vend", existingVendor);
		dispatcher.forward(request, response);

	}
	
	
	
	private void deleteVendor(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int bankAccountNo = Integer.parseInt(request.getParameter("bankAccountNo"));
		vendorDAO.deleteVendor(bankAccountNo);
		response.sendRedirect("list");
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}


}
