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
	<script src="${tomcatDocUrl}js/jquery.min.js" ></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title } </title>
	<link href="${tomcatDocUrl}css/tomcat.css" rel="stylesheet" type="text/css" />
	<c:if test="${ empty sessionScope.authenticatedUser }">
		<c:redirect url="${tomcatDocUrl}reloadlogin.html" />
	</c:if>
	<script>
		$(document).ready(function(){
			/* $('#password').val();
			$('#confirmPassword').val();
			 */
			$('#submit').click(function(){
				return validate();
			});
			
		});
		
		function validate(){
			
			var password = $('#password').val();
			var confirmPassword = $('#confirmPassword').val();
			var fname = $('#fname').val();
			var lname = $('#lname').val();
			var emailid = $('#emailid').val();
			var address = $('#address').val();
			
			if(password.length==0 && confirmPassword.length==0){
			}else{
				if(password.length<4 ){
					alert("Password should be greater than 4");
					return false;
				}
				if(confirmPassword.length<4){
					alert("Confirm password should be greater than 4");
					return false;	
				}
				if(!password.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{4,}$/)){
					alert("Password should be have a digit , a letter , a small letter and a capital letter.");
					return false;
				}
				if(!confirmPassword.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{4,}$/)){
					alert("Confirm Password should be have a digit , a letter , a small letter and a capital letter.");
					return false;
				}
				if(password!=confirmPassword){
					alert("Password and confirm password should be same.")
					return false;
				}
			}
			if(fname.length==0 ){
				alert("First name should not be blank.");
				return false;
			}
			if(lname.length==0 ){
				alert("Last name should not be blank.");
				return false;
			}
			if(emailid.length==0 ){
				alert("Email id should not be blank.");
				return false;
			}
			
			var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			
			if(!regex.test(emailid)){
				alert("Email should be a valid format.");
				return false;
			}
			
			if(address.length==0 ){
				alert("Address should not be blank.");
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
            <div class="curved container" id="navigation">
                <span id="nav-home"><a href="${tomcatDocUrl}student/viewPlan.html">Plan</a></span>
                <span id="nav-hosts"><a href="${tomcatDocUrl}student/showDepartment.html">Department</a></span>
               	<span id="nav-config"><a href="#">User</a></span>
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
						<h4> Profile <input type="button" value="Logout" style="float:right;"  onclick="location.href='${tomcatDocUrl}j_spring_security_logout'"/>  <!-- logout.html --> </h4>                     
						   <h3 style="float:center">
                        		
                        	</h3>
                        	
                    		<form:form modelAttribute="userUIModel" method="POST" >
                    		
                    		<c:if test="${status eq 'success'}">
                    			<center><font color="red"> User data updated successfully.</font></center>
                    		</c:if>
							<table border =1 width='100%'>
								<tr>
									<td>CIN</td>
									<td><form:input type="text" path="cin" disabled="true"/>
										<form:errors path="cin" class="error"/><br />
									</td>
								</tr>
								<tr>
									<td>Username</td>
									<td><form:input type="text" path="username" disabled="true"/>
										<br />
									</td>
								</tr>
								<tr>
									<td>Password</td>
									<td><form:input type="password" path="password" id="password" />
										<form:errors path="password" class="error"/><br />
									</td>
								</tr>
								<tr>
									<td>Confirm Password</td>
									<td><form:input type="password" path="confirmPassword" id="confirmPassword"/>
										<form:errors path="confirmPassword" class="error"/><br />
									</td>
								</tr>
								<tr>
									<td>First Name</td>
									<td><form:input type="text" path="fname" id="fname"/>
										<form:errors path="fname" class="error"/><br />
									</td>
								</tr>
								<tr>
									<td>Last Name</td>
									<td><form:input type="text" path="lname" id="lname"/>
										<form:errors path="lname" class="error"/><br />
									</td>
								</tr>
								<tr>
									<td>Email ID</td>
									<td><form:input type="text" path="emailid" id="emailid"/>
										<form:errors path="emailid" class="error"/><br />
									</td>
								</tr>
								<tr>
									<td>Address </td>
									<td><form:textarea path="address" rows="5" cols="30"  id="address"/>
										<form:errors path="address" class="error"/><br />
									</td>
								</tr>
								<tr>
									<td colspan="2"> <center><input type="submit" value="submit" id="submit"/></center></td>
									
								</tr>
							</table>
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