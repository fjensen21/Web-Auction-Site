--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Customer Representative Home</title>
</head>
<body>
<h1>Hi, ${user.first_name}!</h1>
<nav>
  <a href="/profile">Profile</a>
  <a href="/choosevehicletype">Create Auction</a>
  <a href="/auctionsearch">Search</a>
  <a href="/customerservice">Customer Service</a>
  <a href="/logout">Logout</a>
</nav>


<form action="/customerservicerep" method="get">
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
<form action="/customerservicerep" method="post">
  <textarea id="answer" name="newanswer" rows="4" cols="50"></textarea>
  <br/>
  <h3>Username:</h3>
  <input type="text" name="username">
  <br/>
  <h3>Message ID:</h3>
  <input type="text" name="messagePairId">
  <br/>
  <input type="submit" value="submit">
</form>
</body>
</html>