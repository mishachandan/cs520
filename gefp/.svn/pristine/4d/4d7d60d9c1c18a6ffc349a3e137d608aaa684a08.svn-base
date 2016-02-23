this file is not currently used.
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
request.setAttribute("tomcatDocUrl", "http://localhost:8080/gefp/");
request.setAttribute("title", "CS520 Student");
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

		
		$(document).ready(function(){
			/* $('div[id^="department_"]').hide(); */
			 var selecteddefaultId =$( "#deptSelect option:selected" ).val();
			 $('#department'+selecteddefaultId).show();
			
			$("#deptSelect").change(function(){
				//$('div[id^="department"]').addClass("hide");
				$('div[id^="department"]').hide();
			    var selectedId =$( "#deptSelect option:selected" ).val();
			    $('#department'+selectedId).show();
			    /*select[id^=department]  */
			});
			
		/* 	$('input:radio[name="radioSetDefault"]').change(
				    function(){
				    		alert($(this));
				    	if( $(this).is(":checked") ){ // check if the radio is checked
				            var val = $(this).val(); // retrieve the value
				            alert("vakue: "+val);
				        } 
				        var queryString = {
				        	      "pName" : "bhanu",
				        	      "lName" :"prasad"
				        	   }
				         $.ajax({
				            type: "POST",
				            contentType : 'application/json; charset=utf-8',
				            dataType : 'json',
				            url: "setToDefaultPlan.html",
				            data: JSON.stringify(search), // Note it is important
				            success :function(result) {
				            	alert(result);
				           }
				        });
				    }); */
		}); 
		
	/* 	function setDefaultPlan(URL){
			alert("called  me !");
			return true;
		}
		 */
		function callCreate(url){
			var deptId = $('#deptSelect').val();
			
			 window.location = url+"?deptId="+deptId; 
			
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
                <!-- <span id="nav-examples"><a href="/examples/">Examples</a></span>
                <span id="nav-wiki"><a href="http://wiki.apache.org/tomcat/FrontPage">Wiki</a></span>
                <span id="nav-lists"><a href="http://tomcat.apache.org/lists.html">Mailing Lists</a></span>
                <span id="nav-help"><a href="http://tomcat.apache.org/findhelp.html">Find Help</a></span> -->
                <br class="separator">
            </div>
            <div class="curved container" id="middle">
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
            </div>
            <div id="lower">
                <div id="low-docs">
                    <div class="curved container">
                        <h4> Golden Eagle Flight Plan <input type="button" value="Logout" style="float:right;"  onclick="location.href='j_spring_security_logout'"/>  </h4>
                        <h3 style="float:right">
                        	
                        	
                        	<a href ="#" id="hrefCreate" onclick="callCreate('${tomcatDocUrl}createPlan.html');">Create Plan </a> &nbsp; | &nbsp; 
                        	
                        	
                        </h3>
                    			<!-- tabs for current dept's plan & history -->
		                        <p>
		                        	<table width="40%">
		                        		<tr>
		                        			<td>Department </td>
		                        			<td>
		                        			
		                        				<form:select path="department"  items="${departmentList}"  itemLabel="deptName" itemValue="deptNo" id="deptSelect"/>
				                        			
				                       			<form:select path="department"   id="deptSelect">
				                       				<form:option value="0">-- Select --</form:option>
				                       				<form:options items="${departmentList}"  itemLabel="deptName" itemValue="deptNo" />
				                       			</form:select>
		                        			</td>
		                        			
		                        		</tr>
		                        	</table>
		                        	<table>
		                        	
		                        	</table>
		                        </p>
		                  
		             
		                        	
		                        		<c:forEach items="${departmentList}" var="department" >
		                        			
		                        			<div id="department${department.deptNo}" style="border:0px;background-color:#FFF;border-radius:10px;" class="hide" > 
		                        				
		                        				<table width="80%">
		                        					<tr><th> Plan Name</th>
					                        			<th>Operations</th>
					                        			<th> Assign as default Plan</th>
					                        		</tr>
			                        				
				                        				<c:forEach items="${department.associatedPlans}" var="associatedPlan">
															<tr>
				                        						<td>${associatedPlan.name}	
					                        					</td>
					                        					<td>
					                        						<a href="${tomcatDocUrl}plan/${associatedPlan.planId}"> View</a>
					                        						<a href="${tomcatDocUrl}setDefault/${associatedPlan.planId}"> set Default</a>
					                        						
					                        						<a href="${tomcatDocUrl}viewPlan.html?planId=${associatedPlan.planId}&deptId=${department.deptNo}"> View</a>
					                        						
					                        						
					                        					</td>
					                        					<td>
					                        						<c:if test="${department.currentPlan.planId != associatedPlan.planId}" >
					                        						
					                        						<input type="radio" id="radioSetDefault"  name="planId=${associatedPlan.planId}&deptId=${department.deptNo}" onChange="return setDefaultPlan('planId=${associatedPlan.planId}&deptId=${department.deptNo}')"/> 
					                        						
					                        						<a href="${tomcatDocUrl}setToDefaultPlan.html?planId=${associatedPlan.planId}&deptId=${department.deptNo}"> set </a>
					                        				
					                        						</c:if>
					                        						
					                        					</td>
				                        					</tr>
														</c:forEach>
														
			                        				
			                        			</table>
											</div>
		                        	
		                        		</c:forEach>
		                        	
		                       
                       
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
</html> --%>