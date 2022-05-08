<%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 5/8/22
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bid History</title>
</head>
<body>
<h1>Bid History</h1>

<table>
  <c:forEach items="${bids}" var="bid">
    <tr>
      <td style="padding: 5px">${bid.bid_datetime}</td>
      <td style="padding: 5px">${bid.username}</td>
      <td style="padding: 5px">$${bid.amount}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
