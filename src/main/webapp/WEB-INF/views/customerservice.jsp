<%@ page import = "java.io.*,java.util.*" %>
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
    <% if (user.isCustomerRep() == true) { %>
    <a href="/customerservicerep">Customer Representatives</a>
    <% } %>
    <a href="/logout">Logout</a>

</nav>
<h1>Hi, ${user.first_name}!</h1>
<h3>Submit a question to one of our representatives.</h3>

<form action="/customerservice" method="post">
    <textarea id="question" name="newquestion" rows="4" cols="50"></textarea>
    <br/>
    <input type="submit" value="submit">
</form>
<form action="/customerservice" method="get">
    <h3>Unanswered Questions</h3>
    <table>
            <c:forEach items="${messages}" var="messages">
                <tr>
                    <td style="padding: 5px">${messages}</td>
                </tr>
            </c:forEach>
    </table>
    <input type="submit" name="refreshUnanswered" value="refresh">
</form>
<form action="/customerservice" method="post">
    <input type="submit" name="clearUnanswered" value="clear">
</form>

<form action="/customerservice" method="get">
    <h3>Answered Questions</h3>
    <table>
        <c:forEach items="${qaPair}" var="qaPair">
            <tr>
                <td style="padding: 5px">${qaPair}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="refreshAnswered" value="refresh">

</form>
<form action="/customerservice" method="post">
    <input type="submit" name="clearAnswered" value="clear">
</form>
</body>
</html>
