<%@ page import="com.auction.model.User" %><%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 4/6/22
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User)session.getAttribute("user");
%>
<html>
<head>
    <title>Profile</title>
</head>
<body><nav>
    <a href="/profile">Profile</a>
    <a href="/choosevehicletype">Create Auction</a>
    <a href="/auctionsearch">Search</a>
    <a href="/customerservice">Customer Service</a>
    <a href="/logout">Logout</a>
    <% if (user.isCustomerRep() == true) { %>
    <a href="/customerservicerep">Customer Representatives</a>
    <% } %>
</nav>
<h1>Hi, <%= user.getFirst_name()%></h1>
<h3>Personal Information</h3>
<h6><b>Full Name: </b><%= user.getFirst_name()%> <%= user.getLast_name()%></h6>
<h6><b>Username: </b><%= user.getUsername()%></h6>

</body>
</html>
