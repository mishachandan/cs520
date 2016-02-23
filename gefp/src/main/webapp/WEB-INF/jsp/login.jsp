<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
request.setAttribute("tomcatDocUrl", "http://localhost:8080/gefp/");
request.setAttribute("title", "CS520");
request.setAttribute("UniversityName", "Cal State LA");
request.setAttribute("DepartmentName", "Department of Computer Science");
%>

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="js/jquery.min.js" ></script>	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title } </title>
	<link href="${tomcatDocUrl}css/tomcat.css" rel="stylesheet" type="text/css" />
	<script>
		function validate(){
			
	 var username = $('#usernameId').val();
			var password = $('#passwordId').val();
			
			if(username=='' || password==''){
				alert(" Username and password cannot be blank !");
				return false;
			} 
				
			
			return true;
		}
	</script>
	
	<style>
		.error{
				color:red;
		}
	</style>
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
            <div class="curved container" id="middle1">
                <h3>Login</h3>
                	
                <div class="col100">
                    <div class="container">
                        <p>
                        	<form name="form" action="j_spring_security_check"  method="post"> 
                        		<c:if  test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}">
                        			<font color="red"> ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</font>
                        		</c:if>
                        	<table> 
                        		<tr>	
                        			<td>User name: </td>
                        			<td><input  type="text" id="usernameId" name="j_username"/>
                        				<br />
                        				
                        				</td>
                        		</tr>
                        		<tr>
                        			<td> Password: </td>
                        			<td><input  type="password" id="passwordId" name="j_password" />
                        				<br />
                        				
                        			 </td>                        			
                        		</tr>
                        		<!-- <tr>
                        			<td colspan="2">Remember me <input type="checkbox" name="_spring_security_remember_me" /></td>
                        		</tr> -->
                        		<tr>
                        			<td colspan="2"> <input  type="submit" value="Login" onclick="return validate();"/></td>
                        		</tr>
                        	</table>
                        	</form>
                        	<%-- this is working wihth j security  --%> 
                        		<%-- <form:form modelAttribute="user" action="j_spring_security_check"  method="post"> 
                        		<c:if  test="${not empty param.message}">
                        			<font color="red"> ${param.message }</font>
                        		</c:if>
                        	<table> 
                        		<tr>	
                        			<td>User name: </td>
                        			<td><form:input path="username" type="text" id="usernameId"/>
                        				<form:errors path="username" class="error"/><br />
                        				
                        				</td>
                        		</tr>
                        		<tr>
                        			<td> Password: </td>
                        			<td><form:input path="password" type="password" id="passwordId"/>
                        				<form:errors path="password"  class="error"/><br />
                        				
                        			 </td>                        			
                        		</tr>
                        		<tr>
                        			<td colspan="2"> <input  type="submit" value="submit" onclick="return validate();"/></td>
                        		</tr>
                        	</table>
                        	</form:form>  --%>
                        </p>
                    </div>
                </div>
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
            </div>
           <%--  <div id="lower">
                <div id="low-docs">
                    <div class="curved container">
                        <h4> Golden Eagle Flight Plan</h4>
                        <h3>
                        	<a href ="${tomcatDocUrl}AddStage" >Add A Stage </a> &nbsp; | &nbsp; <a href ="${tomcatDocUrl}AddCheckPoint" >Add A Check Point </a> 
                        	Misha
                        </h3>
                        <br/>
							Chandan
						          
                        <br/>
                        <ul>
                        	<lh>API</lh>
                        	<li><a href="http://tomcat.apache.org/tomcat-8.0-doc/">Tomcat 8.0 JavaDocs</a></li>
                        </ul>
                     </div>
                </div>
           </div> --%>
           <div class="curved container" id="footer">
                <!-- <div class="col20">
                    <div class="container">
                        <h4>Other Downloads</h4>
                        <ul>
                            <li><a href="http://tomcat.apache.org/download-connectors.cgi">Tomcat Connectors</a></li>
                            <li><a href="http://tomcat.apache.org/download-native.cgi">Tomcat Native</a></li>
                            <li><a href="http://tomcat.apache.org/taglibs/">Taglibs</a></li>
                            <li><a href="/docs/deployer-howto.html">Deployer</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col20">
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