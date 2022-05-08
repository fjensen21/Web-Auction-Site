<%--
  Created by IntelliJ IDEA.
  User: finnjensen
  Date: 5/8/22
  Time: 9:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advanced Search</title>
</head>
<body>
<nav>
  <a href="/profile">Profile</a>
  <a href="/choosevehicletype">Create Auction</a>
  <a href="/auctionsearch">Search</a>
  <a href="/customerservice">Customer Service</a>
  <a href="/logout">Logout</a>
</nav>

<form action="/advancedsearch" method="post">
  <h4>Search Advanced</h4>
  <p>Price range:</p>
  <label for="lowest">Lowest</label>
  <input type="number" id="lowest" name="lowest" min="0">
  <label for="highest">Highest</label>
  <input type="number" id="highest" name="highest"><br><br>

  <p>Vehicle type:</p>
  <input type="radio" id="car" name="vehicletype" value="car">
  <label for="car">Car</label><br>
  <input type="radio" id="truck" name="vehicletype" value="truck">
  <label for="truck">Truck</label><br>
  <input type="radio" id="motorcycle" name="vehicletype" value="motorcycle">
  <label for="motorcycle">Motorcycle</label>
  
  <p>Color keyword:</p>
  <label for="color">Color:</label><br>
  <input type="text" id="color" name="color"><br>
  <input type="submit">
</form>


</body>
</html>
