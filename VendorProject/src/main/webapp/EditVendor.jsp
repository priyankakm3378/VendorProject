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
    <form action="UpdateVendorServlet" method="post">
        <input type="hidden" name="bankAccountNo" value="<%= request.getParameter("bankAccountNo") %>">
        <div>
            <label for="vendorName">Vendor Name:</label>
            <input type="text" id="vendorName" name="vendorName" value="<%= ((Vendorclass) request.getAttribute("newVendor")).getVendorName() %>">
        </div>
        <div>
            <label for="bankName">Bank Name:</label>
            <input type="text" id="bankName" name="bankName" value="<%= ((Vendorclass) request.getAttribute("newVendor")).getBankName() %>">
        </div>
        <div>
            <label for="address1">Address 1:</label>
            <input type="text" id="address1" name="address1" value="<%= ((Vendorclass) request.getAttribute("newVendor")).getAddressLine1() %>">
        </div>
        <div>
            <label for="address2">Address 2:</label>
            <input type="text" id="address2" name="address2" value="<%= ((Vendorclass) request.getAttribute("newVendor")).getAddressLine2() %>">
        </div>
        <div>
            <label for="city">City:</label>
            <input type="text" id="city" name="city" value="<%= ((Vendorclass) request.getAttribute("newVendor")).getCity() %>">
        </div>
        <div>
            <label for="country">Country:</label>
            <input type="text" id="country" name="country" value="<%= ((Vendorclass) request.getAttribute("newVendor")).getCountry() %>">
        </div>
        <div>
            <label for="zipcode">ZipCode:</label>
            <input type="text" id="zipCode" name="zipCode" value="<%= ((Vendorclass) request.getAttribute("newVendor")).getZipCode() %>">
        </div>
        <button type="submit">Update</button>
    </form>
</div>
</body>
</html>
