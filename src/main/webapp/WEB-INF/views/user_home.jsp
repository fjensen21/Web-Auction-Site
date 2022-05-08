<%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 4/18/22
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>
</head>
<body>
<nav>
    <a href="/profile">Profile</a>
    <a href="/choosevehicletype">Create Auction</a>
    <a href="/auctionsearch">Search</a>
    <a href="/customerservice">Customer Service</a>
    <a href="/logout">Logout</a>
</nav>

<h4><a href="/messages">View My Messages</a></h4>

<h3><a href="/myinactiveauctions">View Closed Auctions</a></h3>
</body>
</html>
