<%@ page import="com.auction.model.User" %><%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 4/28/22
  Time: 7:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    User user = (User)session.getAttribute("user");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/profile">Profile</a>
<a href="/choosevehicletype">Create Auction</a>
<a href="/auctionsearch">Search</a>
<a href="/customerservice">Customer Service</a>
<% if (user.isCustomerRep() == true) { %>
<a href="/customerservicerep">Customer Representatives</a>
<% } %>
<a href="/logout">Logout</a>

<h1>Vehicle Details</h1>
<h4>Make: ${vehicle.make}</h4>
<h4>Model: ${vehicle.model}</h4>
<h4>Year: ${vehicle.year}</h4>
<h4>Color: ${vehicle.color}</h4>
<h4>Vin: ${vehicle.vin}</h4>


<h4>Number of Doors: ${vehicle.numdoors}</h4>

<h4>Bed Size (sq ft): ${vehicle.bed_size}</h4>

<h4>Pedal Size: ${vehicle.pedal_size}</h4>

<h1>Bidding Info</h1>
<h6>Closing Bid: ${highestBid.amount} by ${highestBid.username}</h6>
<h4>Auction End Datetime: ${auction.end_datetime}</h4>
</body>
</html>
