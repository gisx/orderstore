<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Service Test</title>
</head>
<body>
DOCUMENTATION:<br>

<p/>
<b>TOOLS</b><br>
IDE eclipse-jee-luna-SR2<br>
Web Container apache-tomcat-7.0.69<br>
RDBMS mysql-8.0.13<br>
ORM hibernate-release-5.4.0.Final<br>
Jersey RESTful Web Services JAX-RS API jaxrs-ri-2.25.1<br>
JUnit 4.12<br>

<hr>
<p/>
GET ALL ORDERS - XML<br>
URL: <b>http://localhost:8080/orderstore/rest/OrderService/orders</b>
<br>

<hr>
<p/>
GET ALL ORDERS - JSON - open Postman extension in: chrome://apps/
<br>
METHOD: GET
<br>
URL: http://localhost:8080/orderstore/rest/OrderService/orders
<br>
Headers:<br>
 Content-Type application/json
<br> 
 
<hr>
<p/>
GET 1 ORDER - XML<br>
METHOD: GET<br>
URL: http://localhost:8080/orderstore/rest/OrderService/orders/00000010
<br> 
 
<hr>
<p/>
POST FORM 1 ORDER - open Postman extension in: chrome://apps/<br>
URL: http://localhost:8080/orderstore/rest/OrderService/orders
<br>
METHOD: POST
<br>
Headers:<br>
 Content-Type application/x-www-form-urlencoded
<br> 
Body:<br>
[x] x-www-form-urlencoded<br>
PARAMETERS:<br>
codcontrolorder=00000011
<br>
creationdatetime=2018-12-31 12:00:00.0
<br>
nameitem=air
<br>
valueunititem=100.0
<br>
qtitems=1
<br>
codclient=11


<br>
<hr>
<p/>
<img src="img/mysqlserver.png"/><br>
<img src="img/dataset.png"/><br>
<img src="img/postman.png"/><br>




</body>
</html>