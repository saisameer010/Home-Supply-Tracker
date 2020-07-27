<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
  font-family: cursive;
}
.club table,
.club th,
.club td {
  		padding: 5px;
  		text-align: center;
  		border: 1px solid black;
  		font-family: cursive;
	}

	.club tr:nth-child(even) {background-color: #f2f2f2;}
.navbar {
  overflow: hidden;
  background-color: #333;
}

.navbar a {
  float: left;
  font-size: 16px;
  color: black;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

.dropdown {
  float: left;
  overflow: hidden;
}

.dropdown .dropbtn {
  font-size: 16px;  
  border: none;
  outline: none;
  color: black;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.navbar a:hover, .dropdown:hover .dropbtn {
  background-color: #ddd;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}

.header {
  overflow: hidden;
  background-color: #f1f1f1;
  padding: 20px 10px;
}

.header a {
  float: left;
  color: black;
  text-align: center;
  padding: 12px;
  text-decoration: none;
  font-size: 18px; 
  line-height: 25px;
  border-radius: 4px;
}

.header a.logo {
  font-size: 25px;
  font-weight: bold;
}

.header a:hover {
  background-color: #ddd;
  color: black;
}

.header a.active {
  background-color: dodgerblue;
  color: white;
}

.header-right {
  float: right;
}

@media screen and (max-width: 500px) {
  .header a {
    float: none;
    display: block;
    text-align: left;
  }
  
  .header-right {
    float: none;
  }
	
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="header">
	  <a href="/userhome1" class="logo">ANITS</a>
	  <div class="header-right">
	    <a href="/userhome1">Home</a>
	    <div class="dropdown">
    		<button class="dropbtn">Ideas 
      			<i class="fa fa-caret-down"></i>
    		</button>
    		<div class="dropdown-content">
    			<a href="/ideas">Submit An Idea</a>
      			<a href="/viewideas">View Ideas</a>
    		</div>
  		</div> 
	    <div class="dropdown">
    		<button class="dropbtn">Clubs 
      			<i class="fa fa-caret-down"></i>
    		</button>
    		<div class="dropdown-content">
      			<a href="/clubs">Join The Club</a>
      			<a href="/members">Members of the club</a>
      			<a href="/clubevents">Events of the Club</a>
    		</div>
  		</div> 
	    <div class="dropdown">
    		<button class="dropbtn">Events 
      			<i class="fa fa-caret-down"></i>
    		</button>
    		<div class="dropdown-content">
      			<a href="/getevents">Add Events</a>
      			<a href="/allevents">List Events</a>
      			<a href="/searchevent">Search an Event</a>
    		</div>
  		</div> 
  		<div class="dropdown">
    		<button class="dropbtn">Services 
      			<i class="fa fa-caret-down"></i>
    		</button>
    		<div class="dropdown-content">
      			<a href="/services">Add Services</a>
      			<a href="/volunteers">List of Volunteers</a>
    		</div>
  		</div> 
	    <a href="/logout">Logout</a>
	  </div>
	</div>
	
	<div align="center">
		<h1>Explore Clubs</h1>
		<form:form name="volunteerServices"  >
			<table>
			<tr>
				<td>Service Id:</td>
				
			</tr>
			<tr>
				<td>User Id: </td>
				<td></td>
			</tr>
			</table>
			<br><input type="submit" value="Volunteer the Service">
		</form:form>
		
		<br>
	</div>
	<div align="center" class="club">
		<h3>*Successfully Added</h3>
		<h3>*Cannot Volunteer</h3>
	</div>
	<div align="center" class="club">
	<h2>No Services Available</h2>
	
			<h1><i>List of Services</i></h1>
			<table>
				<tr>
					<th>Service Id</th>
					<th>Service Name</th>
					<th>description</th>
					<th>Date</th>
					<th>Volunteers Required</th>
				</tr>

				
			</table>
	
	</div>
</body>
</html>