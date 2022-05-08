<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 4/28/22
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<nav>
    <a href="/profile">Profile</a>
    <a href="/choosevehicletype">Create Auction</a>
    <a href="/auctionsearch">Search</a>
    <a href="/customerservice">Customer Service</a>
    <a href="/logout">Logout</a>
</nav>

<h4><a href="/advancedsearch">Use Advanced Search</a></h4>

<h1>Active Auctions</h1>

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
<form action="/auctionsearch" method="post">
    <input type="hidden" name="firstrow" value="${firstrow}">
    <input type="hidden" name="rowcount" value="${rowcount}">
    <input type="submit" name="page" value="next">
    <input type="submit" name="page" value="previous">
</form>
</body>
</html>
