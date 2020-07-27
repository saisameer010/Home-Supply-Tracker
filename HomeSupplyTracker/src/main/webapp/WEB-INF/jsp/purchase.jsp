<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="org.springframework.web.bind.annotation.ModelAttribute"%>
<%@page import="com.SupplyTracker.Pojo.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Purchased</title>
<style>
body {
	background-color: lightblue;
	 font-family:Helvetica;
}

.DivCls1 {
border-radius: 25px;
	margin: auto;
	padding: 70px;
	height: 250px;
	width: 35%;
	background-color: orange;
	border: 3px solid green;
}

th, td {
	padding: 10px;
}
#tab1cap{
font-size: 18px;
 font-weight: bold;

}
#divdown {
	margin: auto;
	display: flex;
	flex-direction: column;
	min-height: 1vh;
	width: 50%;
	background-color: orange;
}
#logout{

font-size: 18px;
}
</style>

</head>
<body>
<h2><center>Items Purchased .. Mail Sent to Vendor</center></h2><br/>
<table align="center" border=border>
<tr>
			
			<td>Name</td>
			<td>Quantity</td>
			<td>Price</td>
			<td>Description</td>
		</tr>
		<c:forEach items="${Map3}" var="map">
         <c:if test="${map.key != null}">
         <tr>
         		<td>
         		${map.key}        		
         		</td>
         		<td>
         		${map.value.getQuantity()}
         		</td>
         		<td>
         		${map.value.getQuantity() * map.value.getPrice() }
         		</td>
         		<td>
         		${map.value.getDesc()}
         		</td>
      	</tr>
      	</c:if>
      	</c:forEach>
		</table>
		<h3><center>Total is ${Total}</center></h3>

<form  action="Dashboard"  method="post" >
<input type="submit" Value="Return to Dashboard"/>
</form>
</body>
</html>