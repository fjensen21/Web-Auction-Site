<%@ page import="com.auction.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 4/5/22
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
<h1>Hello, <%= user.getFirst_name()%></h1>


</body>
</html>
