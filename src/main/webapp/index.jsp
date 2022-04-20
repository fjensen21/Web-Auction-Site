<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Welcome to Auction</h1>
<h2>Users must login or create an account to access the auction</h2>
<br/>
<div id="login-form">
    <h3>Login</h3>
    <form action="/login" method="post">
        <input type="text" name="username">
        <br/>
        <input type="password" name="password">
        <br/>
        <input type="submit" value="Login">
    </form>
    <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color: crimson"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
</div>
<div id="account-create">
    <a href="/createaccount">Don't have an account? Create one.</a>
</div>
</body>
</html>