<%--
  Created by IntelliJ IDEA.
  User: shivjoshi
  Date: 5/4/22
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reports</title>
</head>
<body>
<nav>
    <a href="/profile">Profile</a>
    <a href="/choosevehicletype">Create Auction</a>
    <a href="/auctionsearch">Search</a>
    <a href="/customerservice">Customer Service</a>
    <a href="/logout">Logout</a>
</nav>
<form action="/reports" method="get">
    <h3>Best Buyer</h3>

    <table>
        <c:forEach items="${bestBuyer}" var="bestBuyer">
            <tr>
                <td style="padding: 5px">${bestBuyer}</td>
            </tr>
        </c:forEach>
    </table>

    <h3>Total Earnings</h3>
    <table>
        <c:forEach items="${totalEarnings}" var="totalEarnings">
            <tr>
                <td style="padding: 5px">${totalEarnings}</td>
            </tr>
        </c:forEach>
    </table>

    <h3>Earnings Per User</h3>
    <table>
        <c:forEach items="${earningsPerUser}" var="earningsPerUser">
            <tr>
                <td style="padding: 5px">${earningsPerUser}</td>
            </tr>
        </c:forEach>
    </table>

</form>
</body>
</html>
