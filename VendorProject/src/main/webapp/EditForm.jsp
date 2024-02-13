<!-- EditVendor.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.servlets.Vendorclass" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Vendor</title>
    <link rel="stylesheet" href="editstyle.css">
</head>
<body>
<div class="container">
    <h1>Edit Vendor</h1>
 <!--    <form action="UpdateVendorServlet" method="post"> -->
     <form action="UpdateVendor" method="post">
        <input type="hidden" name="bankAccountNo" value="<%= request.getParameter("bankAccountNo") %>">
        <div>
            <label for="vendorName">Vendor Name:</label>
            <input type="text" id="vendorName" name="vendorName" value="">
        </div>
        <div>
            <label for="bankName">Bank Name:</label>
            <input type="text" id="bankName" name="bankName" value="">
        </div>
        <div>
            <label for="addressLine1">Address 1:</label>
            <input type="text" id="addressLine1" name="addressLine1" value="">
        </div>
        <div>
            <label for="addressLine2">Address 2:</label>
            <input type="text" id="addressLine2" name="addressLine2" value="">
        </div>
        <div>
            <label for="city">City:</label>
            <input type="text" id="city" name="city" value="">
        </div>
        <div>
            <label for="country">Country:</label>
            <input type="text" id="country" name="country" value="">
        </div>
        <div>
            <label for="zipcode">ZipCode:</label>
            <input type="text" id="zipCode" name="zipCode" value="">
        </div>
        <button type="submit" value="Update" >Update</button>
        
    </form>
</div>
</body>
</html>
