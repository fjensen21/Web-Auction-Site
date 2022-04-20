<%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 4/7/22
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create an Account</title>
</head>
<body>
<h1>Create an Account</h1>
<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color: crimson"><%= request.getAttribute("errorMessage") %></p>
<% } %>
<form action="/createaccount" method="post">
    <input type="text" name="username" placeholder="Username"><br/>
    <input type="password" name="password" placeholder="Password"><br>
    <input type="text" name="firstname" placeholder="First Name"><br/>
    <input type="text" name="lastname" placeholder="Last Name"><br/>
    <input type="email" name="email" placeholder="Email"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
