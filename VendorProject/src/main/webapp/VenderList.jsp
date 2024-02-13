<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<%@ page import="com.tap.servlets.Vendorclass" %>

<!DOCTYPE html>
<html>
<head>
<title>List of Vendors</title>
<link rel="stylesheet" type="text/css" href="VendorListStyle.css">
</head>
<body>
	<h1>List of Vendors</h1>
	<table>
		<thead>
			<tr>
				<th>Bank Account No.</th>
				<th>Vendor Name</th>
				<th>Bank Name</th>
				<th>Address Line 1</th>
				<th>Address Line 2</th>
				<th>City</th>
				<th>Country</th>
				<th>ZipCode</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="vendor">
				<tr>
					<td>${vendor.bankAccountNo}</td>
					<td>${vendor.vendorName}</td>
					<td>${vendor.bankName}</td>
					<td>${vendor.addressLine1}</td>
					<td>${vendor.addressLine2}</td>
					<td>${vendor.city}</td>
					<td>${vendor.country}</td>
					<td>${vendor.zipCode}</td>
					<!--  <td><a href="EditDelete?bankAccountNo=${vendor.bankAccountNo}">Edit</a></td>-->
					 <td><a href="EditForm.jsp?bankAccountNo=${vendor.bankAccountNo}">Edit</a></td> 
					<%-- <td><a href="delete_vendor.jsp?bankAccountNo=${vendor.bankAccountNo}">Delete</a></td> --%>
					<td><a href="delete?bankAccountNo=<c:out value='${vendor.bankAccountNo}'/>">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		
		
		
    

	<p>
	 <button type="submit" style="text- Decoration = none;"><a href="NewVendor.jsp">Register New Vendor</a></button>
	<!-- 	<a href="NewVendor.jsp">Register New Vendor</a> -->
	</p>
</body>
</html>
