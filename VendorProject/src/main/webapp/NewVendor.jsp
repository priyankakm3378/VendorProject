<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create Vendor</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Create Vendor</h2>
    <form action="NewVendorServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" name="password" required><br>

        <label for="vendorName">Vendor Name:</label>
        <input type="text" name="vendorName" required><br>

        <label for="bankAccountNo">Bank Account No:</label>
        <input type="number" name="bankAccountNo" required><br>

        <label for="bankName">Bank Name:</label>
        <input type="text" name="bankName" required><br>

        <label for="addressLine1">Address Line 1:</label>
        <input type="text" name="addressLine1" required><br>

        <label for="addressLine2">Address Line 2:</label>
        <input type="text" name="addressLine2"><br>

        <label for="city">City:</label>
        <input type="text" name="city" required><br>

        <label for="country">Country:</label>
        <input type="text" name="country" required><br>

        <label for="zipCode">ZipCode:</label>
        <input type="number" name="zipCode" required><br>

        <input type="submit" value="Register Vendor">
    </form>
</body>
</html>
