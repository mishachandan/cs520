<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Golden Eagle Flight Plan </title>
</head>
<body>
<center>
<%-- <tiles:insertDefinition name="login" >
</tiles:insertDefinition> --%>
<%-- <tiles:putAttribute name="content">  --%> 

<a href="http://localhost:8080/gefp/home.html " >Home</a> <br/>

<a href="http://localhost:8080/gefp/users.html" > Users</a> <br/>

<a href="http://localhost:8080/gefp/addDept.html">Add Department</a> <br/>


<a href="http://localhost:8080/gefp/displayPlan.html?planid=1" >DisplayPlan</a> <br/>

	
<a href="http://localhost:8080/gefp/addPlan.html" >Add Plan</a> <br/>

<!-- <a href="http://localhost:8080/gefp/addPlanAll.html" >Add All Plan</a> <br/> -->

<a href="http://localhost:8080/gefp/addPlan.html" >Add Runway</a> <br/>

<a href="http://localhost:8080/gefp/addPlan.html" >Add Stage</a> <br/>

<a href="http://localhost:8080/gefp/addPlan.html" >Add Checkpoint</a> <br/>u

</center>
<%-- </tiles:putAttribute> --%>


<c:redirect url="http://localhost:8080/gefp/login.html"/>

</body>
</html>

