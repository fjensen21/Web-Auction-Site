<%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 5/8/22
  Time: 11:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Search Results</title>
</head>
<body>


<table>
  <c:forEach items="${auctions}" var="auction">
    <tr>
      <td style="padding: 5px">${auction.vehicleDetails}</td>
      <td style="padding: 5px">${auction.userPosted}</td>
      <td style="padding: 5px">
        <form action="/auctionpostdetail" method="post">
          <input type="hidden" name="auctionid" value="${auction.auction_id}">
          <input type="submit" name="view" value="View Details">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
