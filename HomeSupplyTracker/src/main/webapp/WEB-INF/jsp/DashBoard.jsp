<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.springframework.web.bind.annotation.ModelAttribute"%>
<%@page import="com.SupplyTracker.Pojo.Product"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>

<style>
body {
	background-color: lightblue;
	font-family:Helvetica;
}

.DivCls1 {
	border-radius: 25px;
	margin: auto;
	padding: 70px;
	height: 150px;
	width: 35%;
	background-color: orange;
	border: 3px solid green;
}

th, td {
	padding: 10px;
}

#tab1cap {
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

#logout {
	float: right;
	font-size: 18px;
}
</style>
</head>
<body>
	<div id="logout">
		<a href="/HomeSupplyTracker"> Logout </a>
	</div>

	<form:form action="Dashboard" method="post" modelAttribute="product">

		<div class="DivCls1">

			<table align="center">
				<caption id="tab1cap">Enter the Items Consumed</caption>
				<tr>
					<td><label id="Type">Type </label></td>
					<td>
					<form:input type="text" path="Type" id="Type" required="true" /> 
							<datalist id="Type">
							<c:forEach var="Value" items="${TypeSet}">
								<option value="${Value}" />
							</c:forEach>
						</datalist>
						</td>
				</tr>
				<tr>
					<td><label id="Name">Name </label></td>
					<td><form:input type="text" path="Name" id="Name"
							required="true" />
							<datalist id="Name">
							<c:forEach var="Value" items="${ItemSet}">
								<option value="${Value}" />
							</c:forEach>
						</datalist>
							</td>
				</tr>
				<tr>
					<td><label id="Quantity">Quantity </label></td>
					<td><form:input type="text" path="Quantity" id="Quantity"
							required="true" /></td>
				</tr>

				<tr>
					<td></td>
					<td align="left"><input type="submit" id="submit"
						value="Consumed"></td>
				</tr>
			</table>
		</div>
		<br />
</form:form>
		<center>
			<c:if test="${Show }">
				<form:form action="Dashboard" method="post" modelAttribute="product">
					<form:input type="hidden" id="Copy" path="" name="Copy"
						value="true" />
					<form:input type="submit" path="" Value="Copy Database" />
				</form:form>
			</c:if>
		</center>
		<table align="center" text-align=center border=border>
			<caption>Shopping List</caption>
			<tr>

				<td>Name</td>
				<td>Quantity</td>
				<td>Price</td>
				<td>Description</td>
			</tr>
			<c:forEach items="${Map}" var="map">
				<c:if test="${map.key != null}">
					<tr>
						<td>${map.key}</td>
						<td>${map.value.getQuantity()}</td>
						<td>${map.value.getQuantity() * map.value.getPrice() }</td>
						<td>${map.value.getDesc()}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>

	
	<center>
		<c:if test="${!Show }">
			<form:form action="Dashboard" method="post" modelAttribute="product">
				<form:input type="hidden" id="Show" name="Show" path="" value="true" />
				<form:input type="submit" path="" Value="Show Database" />
			</form:form>
		</c:if>
	</center>


	<c:if test="${Show }">
		<div id="divdown">

			<center>
				<form:form action="Dashboard" method="post" modelAttribute="product">
					<form:input type="hidden" id="Show" path="" name="Show"
						value="false" />
					<form:input type="submit" path="" Value="Hide Database" />
				</form:form>
	</c:if>
	</center>
	<br />
	<c:if test="${Show }">
		<table align="center" text-align="center" border=border>
			<caption>Previous Buy List</caption>

			<tr>
				<td>Type</td>
				<td>Name</td>
				<td>Usual Quantity Purchased</td>
				<td>Price per Quantity</td>
				<td>Description</td>
			</tr>
			<c:forEach items="${Map2}" var="map2">
				<c:if test="${map2.key != null}">
					<tr>
						<td>${map2.value.getType()}</td>
						<td>${map2.key}</td>
						<td>${map2.value.getQuantity()}</td>
						<td>${ map2.value.getPrice() }</td>
						<td>${map2.value.getDesc() }</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>​

	
	
	</div>
	</c:if>
	<center>
		<button onclick="location.href='Update'" id="myButton"
			class="Change Required Purchases">Change Required Purchases</button>


		<button onclick="location.href='Purchase'" id="myButton"
			class="Change Required Purchases">Payment</button>
	</center>
</body>
</html>