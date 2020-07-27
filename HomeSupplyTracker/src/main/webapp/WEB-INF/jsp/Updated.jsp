<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="org.springframework.web.bind.annotation.ModelAttribute"%>
<%@page import="com.SupplyTracker.Pojo.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update</title>
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

<div id="logout">
<span><a href="/HomeSupplyTracker/Dashboard">
Dashboard
</a>
</span>

<span style="float:right"><a href="/HomeSupplyTracker">
Logout
</a>
</span>
</div>


<div class="DivCls1">
<form:form  action="Update"  method="post" modelAttribute="product">
		<table align="center">
		<caption id="tab1cap">Enter the Items In Database</caption>
			<tr>
				<td><label id="Type">Type </label></td>
				<td><form:input type="text" path="Type" id="Type" required="true" /></td>
			</tr>
			<tr>
				<td><label id="Name">Name </label></td>
				<td><form:input type="text" path="Name" id="Name" required="true" /></td>
			</tr>
			<tr>
				<td><label id="Quantity">Quantity </label></td>
				<td><form:input type="text" path="Quantity" id="Quantity" required="true" /></td>
			</tr>
			<tr>
				<td><label id="Price">Price </label></td>
				<td><form:input type="text" path="price" id="Price" required="true" /></td>
			</tr>
			<tr>
				<td><label id="Description">Description</label></td>
				<td><form:input type="text" path="desc" id="Description" required="true" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="left"><input type="submit" id="submit" value="Update Required Items"></td>
			</tr>

			<tr></tr>
		</table><br/>
</form:form>
</div>
<br/>

<div id="divdown" >
	<form:form  action="Update"  method="post" >
	<table align="center" border=border>
	<tr>
			
			<td>Name</td>
			<td>Usual Quantity Purchased</td>
			<td>Price per Quantity</td>
			<td>Description</td>
		</tr>
		
		<c:forEach items="${Map2}" var="map2">
         <c:if test="${map2.key != null}">
         <tr>
         		<td>
         		${map2.key}        		
         		</td>
         		<td>
         		${map2.value.getQuantity()}
         		</td>
         		<td>
         		${ map2.value.getPrice() }
         		</td>
         		<td>
         		${map2.value.getDesc()}
         		</td>
      	</tr>
      	</c:if>
      	</c:forEach>
	</table>
	
	</form:form>
	 </div>
</body>
</html>