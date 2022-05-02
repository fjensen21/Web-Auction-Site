<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.auction.model.User" %><%--
<%--
  Created by IntelliJ IDEA.
  User: smati
  Date: 5/1/22
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User)session.getAttribute("user");
%>

<html>
<head>
    <title>Customer Service!</title>
</head>
<body>
<nav>
    <a href="/profile">Profile</a>
    <a href="/choosevehicletype">Create Auction</a>
    <a href="/auctionsearch">Search</a>
    <a href="/customerservice">Customer Service</a>
    <a href="/logout">Logout</a>
</nav>
<h1>Hi, ${user.first_name}!</h1>
<h3>Submit a question to one of our representatives.</h3>

<form action="/customerservice" method="post">
    <textarea id="question" name="newquestion" rows="4" cols="50"></textarea>
    <br/>
    <input type="submit" value="submit">
</form>

</body>
</html>
