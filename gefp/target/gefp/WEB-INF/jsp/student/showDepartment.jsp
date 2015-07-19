<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="gefpmvc.model.Plan,gefpmvc.model.Runway,gefpmvc.model.Stage"%>
<%
request.setAttribute("tomcatDocUrl", "http://localhost:8080/gefp/");
request.setAttribute("title Student", "CS520 Student");
request.setAttribute("UniversityName", "Cal State LA");
request.setAttribute("DepartmentName", "Department of Computer Science");
%>

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="../js/jquery.min.js" ></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title } </title>
	<link href="${tomcatDocUrl}css/tomcat.css" rel="stylesheet" type="text/css" />
	<c:if test="${ empty sessionScope.authenticatedUser }">
		<c:redirect url="${tomcatDocUrl}reloadlogin.html" />
	</c:if>
	<script>
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
	 <div id="wrapper">
            <div id="upper" class="curved container">
                <div id="notice">
                 <img src="${tomcatDocUrl}img/csula.png" alt="[csu la logo]" style="height:20%;width:20%" />
                    <div id="asf-box">
                         <h1>  ${UniversityName} </h1>
                         <h2>${DepartmentName} </h2>
                    </div>
                </div>
                <br class="separator" />
            </div>
            <div class="curved container" id="navigation">
                <span id="nav-home"><a href="${tomcatDocUrl}student/viewPlan.html">Plan</a></span>
                <span id="nav-hosts"><a href="#">Department</a></span>
               	<span id="nav-config"><a href="${tomcatDocUrl}viewUserProfile.html">User</a></span>
                <!-- <span id="nav-examples"><a href="/examples/">Examples</a></span>
                <span id="nav-wiki"><a href="http://wiki.apache.org/tomcat/FrontPage">Wiki</a></span>
                <span id="nav-lists"><a href="http://tomcat.apache.org/lists.html">Mailing Lists</a></span>
                <span id="nav-help"><a href="http://tomcat.apache.org/findhelp.html">Find Help</a></span> -->
                <br class="separator">
            </div>
        <%--     <div class="curved container" id="middle">
                <h3>Plan</h3>
                
         <!--        <div class="col25">
                    <div class="container">
                        <p><a href="/docs/realm-howto.html">Realms &amp; AAA</a></p>
                        <p><a href="/docs/jndi-datasource-examples-howto.html">JDBC DataSources</a></p>
                    </div>
                </div>
                <div class="col25">
                    <div class="container">
                        <p><a href="/examples/">Examples</a></p>
                    </div>
                </div>
                <div class="col25">
                    <div class="container">
                        <p><a href="http://wiki.apache.org/tomcat/Specifications">Servlet Specifications</a></p>
                        <p><a href="http://wiki.apache.org/tomcat/TomcatVersions">Tomcat Versions</a></p>
                    </div>
                </div> -->
                <br class="separator">
            </div> --%>
            <div id="lower">
                <div id="low-docs">
                    <div class="curved container">
						<h4> Golden Eagle Flight Plan <input type="button" value="Logout" style="float:right;"  onclick="location.href='${tomcatDocUrl}j_spring_security_logout'"/>  <!-- logout.html --> </h4>                     
						   <h3 style="float:center">
                        		${fetchedUser.major.deptName }  :: ${fetchedUser.officialPlan.name } 
                        	</h3>
                        	
                        	<form:form modelAttribute="department" method="POST">
                        	
                    		<table width="100%">
                    			<tr>
                    				<td> Change Major: </td>
                    				<td>
                    					<%-- <form:select path="deptNo" items="${departmentList}" itemLabel="deptName" itemValue="deptNo"/> --%>
                    					<form:select path="deptNo" >
                    						<c:forEach items="${departmentList}" var="deptObj" >
                    							
									            <option <c:if test="${deptObj.deptNo eq deptNo}">selected="selected"</c:if>    value="${deptObj.deptNo}">${deptObj.deptName} </option>
									        </c:forEach>
                    					</form:select>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td colspan="2">
                    					<center><input type="submit" value="submit" /></center>
                    				</td>
                    			</tr>
                    		</table>
                    		
                    		<hr>
                    		<center>
                    		<table width="80%">
                    			<tr>
                    				<th> Plan #</th>
                    				<th> Plan name</th>
                    				<th colspan="2">  Period </th>
                    				
                    			</tr>
                    			<c:forEach items="${planHistoryList }"	var="planHistoryObj">
                    				<tr>
	                    				<td>
	                    					${planHistoryObj.plan.planId }
	                    				</td>
	                    				<td>
	                    					${planHistoryObj.plan.name }
	                    				</td>
	                    				<td>
	                    					${planHistoryObj.fromDate} - ${planHistoryObj.toDate} 
	                    				</td>
                    				</tr>
                    			</c:forEach>
                    			
                    		</table>
                    		</center>
                    		</form:form>
                     </div>
                </div>
           </div>
           <div class="curved container" id="footer1">
                <div class="col25">
                    <div class="container">
                        <h4>API</h4>
                        <ul>
                           <li><a href="http://tomcat.apache.org/tomcat-8.0-doc/">Tomcat 8.0 JavaDocs</a></li>
                            
                        </ul>
                    </div>
                </div>
               <!--  <div class="col20">
                    <div class="container">
                        <h4>Other Documentation</h4>
                        <ul>
                            <li><a href="http://tomcat.apache.org/connectors-doc/">Tomcat Connectors</a></li>
                            <li><a href="http://tomcat.apache.org/connectors-doc/">mod_jk Documentation</a></li>
                            <li><a href="http://tomcat.apache.org/native-doc/">Tomcat Native</a></li>
                            <li><a href="/docs/deployer-howto.html">Deployer</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col20">
                    <div class="container">
                        <h4>Get Involved</h4>
                        <ul>
                            <li><a href="http://tomcat.apache.org/getinvolved.html">Overview</a></li>
                            <li><a href="http://tomcat.apache.org/svn.html">SVN Repositories</a></li>
                            <li><a href="http://tomcat.apache.org/lists.html">Mailing Lists</a></li>
                            <li><a href="http://wiki.apache.org/tomcat/FrontPage">Wiki</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col20">
                    <div class="container">
                        <h4>Miscellaneous</h4>
                        <ul>
                            <li><a href="http://tomcat.apache.org/contact.html">Contact</a></li>
                            <li><a href="http://tomcat.apache.org/legal.html">Legal</a></li>
                            <li><a href="http://www.apache.org/foundation/sponsorship.html">Sponsorship</a></li>
                            <li><a href="http://www.apache.org/foundation/thanks.html">Thanks</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col20">
                    <div class="container">
                        <h4>Apache Software Foundation</h4>
                        <ul>
                            <li><a href="http://tomcat.apache.org/whoweare.html">Who We Are</a></li>
                            <li><a href="http://tomcat.apache.org/heritage.html">Heritage</a></li>
                            <li><a href="http://www.apache.org">Apache Home</a></li>
                            <li><a href="http://tomcat.apache.org/resources.html">Resources</a></li>
                        </ul>
                    </div>
                </div> -->
                <br class="separator">
            </div>
            <p class="copyright">Copyright &copy;1999-2015 Apache Software Foundation.  All Rights Reserved</p>
	</div>           
</body>
</html>