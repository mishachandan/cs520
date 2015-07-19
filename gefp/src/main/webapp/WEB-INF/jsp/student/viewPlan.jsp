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
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="../js/jquery.min.js" ></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title } </title>
	<link href="${tomcatDocUrl}css/tomcat.css" rel="stylesheet" type="text/css" />
	<link href="${tomcatDocUrl}css/menu.css" rel="stylesheet" type="text/css" />
	
	<c:if test="${ empty sessionScope.authenticatedUser }">
		<c:redirect url="${tomcatDocUrl}reloadlogin.html" />
	</c:if>
	<script>
		$(document).ready(function(){
			
			$('input[name="selectCell"]').click( function(){
				
				var operation ;
				   
			   if( $(this).is(':checked') ) {
				//   alert("checked");
				   operation = "add";
			   }
			   else{
				 //  alert("unchecked");
				   operation = "remove";
			   }
			   
			   $.ajax({
	                type: "POST",
	                url:  "editCheckpoint.html",
	                data: {
	                    "planId" : "${student.officialPlan.planId}",
	                    "checkpointId" : $(this).val(),
	                    "operation" : operation 
	                },
	                success:function(data){
	                	
	                	
	                	if(operation == "add"){
	                		alert("checkpoint added !");
	                	}else if(operation == "remove"){
	                		alert("checkpoint removed !");

	                	}
	                },
	               	error: function(data){
	               		alert("Generated ::"+data);
	               	}
	            });
			});
		});
	</script><style>
	table{
	    table-layout: fixed;
	    width: 100%;
	}
	p {
		display:inline;
	}
	li {
		display:inline;
		overflow-x: auto; 
	}
	a {
	display:inline;
	overflow-x: fixed; 
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
                <span id="nav-home" style="border-right: thick single #ff0000;"><a href="#">Plan</a></span> 
                <span id="nav-hosts"><a href="${tomcatDocUrl}student/showDepartment.html">Department</a></span>
               	<span id="nav-config"><a href="${tomcatDocUrl}viewUserProfile.html">User</a></span> 
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
								<security:authorize access="authenticated and principal.isAdvisor()">
								<li style="width:113px;"><a href="<c:url value='/advisor/viewStudents.html'/>" style="width:113px;"> Advisor View</a></li>
								</security:authorize>
						  	</ul>
						  </div>
						</li>
						
						</ul>
						</div>
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
						<h4> Golden Eagle Flight Plan <input type="button" value="Logout" style="float:right;"  onclick="location.href='${tomcatDocUrl}j_spring_security_logout'"/> <!-- logout.html -->  </h4>                     
						   <h3 style="float:center">
                        		${student.major.deptName }   ::  ${student.officialPlan.name }
                        	</h3>
                        	
                    		<c:if test="${not empty student.officialPlan}">
							<table border =1 width='100%'>
								<tr>
									<th></th>
								<c:forEach var="runway" items="${student.officialPlan.runwayList}">	
									<th><center> ${ runway.desc }</center></th>
								</c:forEach>
								</tr>
								
								<c:forEach var="stage1" items="${student.officialPlan.stageList }"><!-- stage loop -->
									<tr> 
										<td> ${stage1.desc }
											
										</td>
									<c:forEach var="runway1" items="${student.officialPlan.runwayList }" ><!--  runway loop -->
										<td>						
										<c:forEach var = "cell" items="${student.officialPlan.cell }">  
											
											<c:if test="${(cell.runway.runwayId eq runway1.runwayId) && (cell.stage.stageId eq stage1.stageId)}">
										      	
										           <c:forEach var="checkpoint" items="${cell.checkpoints}" varStatus="count"> <!-- checkpoint loop -->
										          		<c:choose >
										          			<c:when test="${not empty student.studentCheckpoints  and fn:length(student.studentCheckpoints) > 0 }">
											           			<c:set var="isChecked" value="false"/>
											           			<c:forEach var="studentCheckpoint" items="${student.studentCheckpoints}">		           				
																	
																	  <c:if test="${checkpoint.checkpointId eq studentCheckpoint.checkpointId}">
																	  
											           					<input type="checkbox" name="selectCell" value="${checkpoint.checkpointId }"  checked  > ${checkpoint.value}
											           					<c:set var="isChecked" value="true"/>
																	  </c:if> 
																	
											           			</c:forEach>
											           			<c:if test="${not isChecked }">
											           				<input type="checkbox" name="selectCell" value="${checkpoint.checkpointId }" > ${checkpoint.value}
											           			</c:if>											           	
											           		</c:when>
											           		<c:otherwise> 
											           			<input type="checkbox" name="selectCell" value="${checkpoint.checkpointId }" > ${checkpoint.value}
											           		</c:otherwise>
										           		</c:choose>
										           		
										           		<br/>
										        	</c:forEach>
										    </c:if>	
										</c:forEach>
										</td>
									</c:forEach> 
									</tr>
								</c:forEach>
							</table>
						</c:if>           
                       
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