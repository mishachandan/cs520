<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
request.setAttribute("tomcatDocUrl", "http://localhost:8080/gefp/");
request.setAttribute("title", "CS520 Advisor");
request.setAttribute("UniversityName", "Cal State LA");
request.setAttribute("DepartmentName", "Department of Computer Science");
%>

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="../js/jquery.min.js" ></script>
	<script src="../js/jquery-ui.js" ></script>
	<script src="../js/jquery-ui.min.js" ></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title } </title>
	<link href="${tomcatDocUrl}css/tomcat.css" rel="stylesheet" type="text/css" />
	<link href="${tomcatDocUrl}css/jquery-ui.css" rel="stylesheet" type="text/css" />
		<link href="${tomcatDocUrl}css/menu.css" rel="stylesheet" type="text/css" />
	
	<style>
		.fixed-height {
			padding: 1px;
			max-height: 200px;
			overflow: auto;
		}
	</style>
	<script>

		
		$(document).ready(function(){
			 $("#generate_cin").click(function(event){
			        event.preventDefault();
			        var cin = "G" + (100000000 + Math.floor(Math.random()*100000000));
			        $("#cin").val( cin );
			    });
			 $("#search").autocomplete({
		        source:  "<c:url value='/autocomplete/user.html' />",
		        select: function(event, ui) {
		        	 /* alert(ui.item.id +"\t"+ui.item.cin);
		        	 $(this).val(ui.item.cin);
		        	 return false; */
		        	 
		        	if( ui.item )
		                window.location.href = "viewPlan.html?studentId=" + ui.item.userid;
		            
		        }
		    });
		      
		}); 
	
		function search(){
			
			$.ajax({
				type: "POST",
				url: "<c:url value='/user/search.html' />?term="+$('#search').val(),
				/* data:{
					term: $('#term').val()
				}, */
				error: function (xhr, textStatus, errorThrown) {
                    
                    if( xhr.responseText=='empty'){
                    	$('#addStudent').removeClass('hide');
                    	$('#displayStudent').addClass('hide');
                    }else{
                    	alert('Error: ' + xhr.responseText);
                    }
                }, success: function (data) {
                	
                	
                	$('#displayStudent').removeClass('hide');
                	$('#addStudent').addClass('hide');
                	
                	var data = $.parseJSON(data);
                	var tableContent = "<br/><center><table border='1'><tr><th>CIN</th><th>Username</th><th>Name</th><th>Department</th><th>Plan Name</th><th>Operation</th></tr>";
                	$.each(data, function(i, item) {
                		tableContent = tableContent+"<tr><td>"+item.cin+"<td>"+item.username+"</td>"+"<td>"+item.name+"</td>"+"<td>"+item.departmentName+"</td>"+"<td>"+item.officialPlan+"<td> <a href='viewPlan.html?studentId="+item.id+" '> View Plan</a></td>"+"</td></tr>";
                	});
                	
                	tableContent = tableContent+"</table></center>";
                	$('#displayStudent').html(tableContent);
                }
			});
		}
		
	</script>
	<style>
		.hide{
		display:none;
		}
	</style>
	
	<c:if test="${ empty sessionScope.authenticatedUser }">
		<c:redirect url="${tomcatDocUrl}reloadlogin.html" />
	</c:if>
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
                <span id="nav-home"><a href="#">Plan</a></span>
                <span id="nav-hosts"><a href="#">Department</a></span>
               	<span id="nav-config"><a href="#">User</a></span>
               	<span id="nav-config"><a href="viewStudents.html">Enrollment Students</a></span>
               	<center>
               	<div id="menu">
						<ul class="menu">
						<li><a href="#">Home</a>
						  <div>
						  	<ul style="width:113px;">
						  		<security:authorize access="authenticated and principal.isAdmin()">
						  		<li style="width:113px;">
								<a href="<c:url value='/admin/welcome-file-admin.html'/>" style="width:113px;">Admin View</a></li>
								</security:authorize>
								<security:authorize access="authenticated and principal.isStudent()">
								<li style="width:113px;"><a href="<c:url value='/student/viewPlan.html'/>" style="width:113px;"> Student View</a></li>
								</security:authorize>
						  	</ul>
						  </div>
						</li>
						
						</ul>
						</div>
               			<%-- <security:authorize access="authenticated and principal.isAdmin()">
								<a href="<c:url value='/admin/welcome-file-admin.html'/>">Admin View</a></li>
								</security:authorize>
				
								<security:authorize access="authenticated and principal.isStudent()">
								<a href="<c:url value='/advisor/welcome.html'/>"> Student View</a></li>
								</security:authorize> --%>
               	</center>
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
                        <h4> Golden Eagle Flight Plan 
                        	
                        <input type="button" value="Logout" style="float:right;"  onclick="location.href='../j_spring_security_logout'" /> <!-- logout.html --> </h4>
                        <h3 style="float:right">
                        	
                        	
						<%--        <a href ="#" id="hrefCreate" onclick="callCreate('${tomcatDocUrl}createPlan.html');">Create Plan </a> &nbsp; | &nbsp; 
 							--%>	
                        </h3>	
                        		
                        		
                        		<label for="search">Student : </label>
  								<input id="search" name="term" type="text">
                    			
                        		<input type="button" value="search" onclick="javascript:search()"></input>
                        		<br/>	
		                        <span id="addStudent" class="hide" >
		                        <hr> <br>
		                        	<center>
		                        	<form action="<c:url value='/user/add.html' />" method="POST">
		                        		<table width="40%">
		                        			<tr>
		                        				<td>Firstname</td>
		                        				<td><input type="text" name="firstname" ></td>
		                        			</tr>
		                        			<tr>
		                        				<td>Lastname</td>
		                        				<td><input type="text" name="lastname" ></td>
		                        			</tr>
		                        			<tr>
		                        				<td>CIN</td>
		                        				<td><input type="text" name="cin" id="cin"> <button id="generate_cin" >Generate</button> </td>
		                        			</tr>
		                        			<tr>
		                        				<td>Major</td>
		                        				<td><select name="major" >
		                        					<c:forEach items="${department }"  var="deptObj">
		                        						<option value="${deptObj.deptNo }"> ${deptObj.deptName} </option>
		                        					</c:forEach>
		                        				</select></td>
		                        			</tr>
		                        			<tr>
		                        				<td colspan="2"> <center> <input type="submit" value="add"/></center></td>
		                        			</tr>
		                        		</table>
		                        	</form>
		                        	</center>
		                        </span>
		                        <span id="displayStudent" class="hide">
		                        </span>
		                       
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