<%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 4/19/22
  Time: 10:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auction a car</title>
</head>
<body>
    <nav>
        <a href="/profile">Profile</a>
        <a href="#">Create Auction</a>
        <a href="#">Search</a>
        <a href="#">Customer Service</a>
        <a href="/logout">Logout</a>
    </nav>
    
    <h1>Enter Car Details</h1>
    <% if (request.getAttribute("errorMessage") != null) { %>
    <p style="color: crimson"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
    <form action="/auctioncar" method="post">
        <label for="vin">Vin: </label>
        <input type="text" id="vin" name="vin"> <br>

        <label for="make">Make: </label><br>
        <input type="text" id="make" name="make"><br>

        <label for="model">Model: </label><br>
        <input type="text" id="model" name="model"><br>

        <label for="year">Year: </label><br>
        <input type="text" id="year" name="year"><br>

        <label for="color">Color: </label><br>
        <input type="text" id="color" name="color"><br>

        <label for="numdoors">Number of Doors: </label><br>
        <input type="number" id="numdoors" name="numdoors"><br>

        <h2>Auction Details</h2>

        <label for="secretmin">Secret Minimum Bid: </label><br>
        <input type="number" id="secretmin" name="secretmin"><br>

        <label for="increment">Increment: </label><br>
        <input type="number" id="increment" name="increment"><br>

        <label for="initprice">Initial Price: </label><br>
        <input type="number" id="initprice" name="initprice"><br>

        <label for="enddatetime">End Datetime: </label><br>
        <input type="datetime-local" id="enddatetime" name="enddatetime"><br>

        <button type="submit">Submit</button>
    </form>
</body>
</html>
