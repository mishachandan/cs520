<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@page import="hw1.config.Config"%>
<%
request.setAttribute("tomcatDocUrl", Config.getApplicationURL());
%>


<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS 520 </title>
<link href="${tomcatDocUrl}views/tomcat.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div id="wrapper">
            <div id="upper" class="curved container">
                <div id="notice">
                 <img src="${tomcatDocUrl}views/csula.png" alt="[csu la logo]" style="height:20%;width:20%" />
                    <div id="asf-box">
                         <h1>  ${initParam.UniversityName} </h1>
                         <h2>${initParam.DepartmentName} </h2>
                    </div>
                </div>
                <br class="separator" />
            </div>
            <div id="lower">
                <div id="low-docs">
                    <div class="curved container">
                        <h4> Golden Eagle Flight Plan</h4>
                        
                        <br/>
                        <form method="post" action="${tomcatDocUrl}AddStage" >
			
							<table border =1 width='100%'>
								<tr>
									<td>Stage: </td>
									<td> <input type='text' name='stage'/></td>
								</tr>
								<tr>
									<td colspan="2"><center><input type="submit" name="submit" value="Add">  </center></td>
									
								</tr>
							</table>
			
						 </form>         
                        <br/>
                        <ul>
                        	<lh>API</lh>
                        	<li><a href="http://tomcat.apache.org/tomcat-8.0-doc/">Tomcat 8.0 JavaDocs</a></li>
                        </ul>
                     </div>
                </div>
           </div>
	</div>
</body>
</html>