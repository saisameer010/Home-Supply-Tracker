
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Yahoo!!</title>
<style>
body {
	background-color: lightblue;
}

span{
color:darkred;
}
div {
font-family:Helvetica;
border-radius: 25px;
	margin: auto;
	padding: 70px;
	height: 100px;
	width: 35%;
	background-color: orange;
	border: 3px solid green;
}
th, td {
  padding: 10px;
  }
</style>
</head>
<body>
	<center>
		<h1>Hello There..!!<br/> Welcome to Home Inventory Tracker</h1>
	</center>
	<div id="login">
		<form action="Dashboard" method="post">
			<table align="center">
				<tr>
					<td><label id="username">Username: </label></td>
					<td><input name="username" id="username" required /></td>
				</tr>
				
				<tr>
					<td><label id="password">Password:</label></td>
					<td><input type="password" name="password" id="password"
						required /></td>
				</tr>
				
				<tr>
					<td></td>
					<td align="left"><button id="login" name="showprev"
							value="true">Login</button></td>
				</tr>

				<tr></tr>
			</table>
			<c:if test="${Log }"><span>INVALID CREDENTIALS. TRY AGAIN</span> </c:if>
		</form>
	</div>
</body>
</html>