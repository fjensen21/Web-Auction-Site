<%@ page import="com.auction.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User)session.getAttribute("user");
%>
<html>
<head>
    <title>Messages</title>
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

<h1>Messages</h1>

<table>
    <c:forEach items="${messages}" var="message">
        <tr>
            <td style="padding: 5px">${message.msg_datetime}</td>
            <td style="padding: 5px">${message.message}</td>
        </tr>
    </c:forEach>
</table>
<form action="/messages" method="post">
    <input type="hidden" name="firstrow" value="${firstrow}">
    <input type="hidden" name="rowcount" value="${rowcount}">
    <input type="submit" name="page" value="next">
    <input type="submit" name="page" value="previous">
</form>
</body>
</html>
