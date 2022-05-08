<%@ page import="com.auction.model.User" %><%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 4/19/22
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    User user = (User)session.getAttribute("user");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose Vehicle Type</title>
</head>
<body>

<nav>
    <a href="/profile">Profile</a>
    <a href="/choosevehicletype">Create Auction</a>
    <a href="/auctionsearch">Search</a>
    <a href="/customerservice">Customer Service</a>
    <% if (user.isCustomerRep() == true) { %>
    <a href="/customerservicerep">Customer Representatives</a>
    <% } %>
    <a href="/logout">Logout</a>
</nav>

<h1>What type of Vehicle would you like to list?</h1>

<a href="/auctioncar">Car</a><br>
<a href="/auctiontruck">Truck</a><br>
<a href="/auctionmotorcycle">Motorcycle</a><br>
</body>
</html>
