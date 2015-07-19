<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="gefpmvc.model.Plan,gefpmvc.model.Runway,gefpmvc.model.Stage"%>
<%
request.setAttribute("tomcatDocUrl", "http://localhost:8080/gefp/");
request.setAttribute("title", "CS520 Admin");
request.setAttribute("UniversityName", "Cal State LA");
request.setAttribute("DepartmentName", "Department of Computer Science");
%>

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${title } </title>
	<link href="${tomcatDocUrl}css/tomcat.css" rel="stylesheet" type="text/css" />
	<script src="<c:url value='/js/jquery-1.11.2.min.js' /> " ></script>
	<script src="<c:url value='/js/jquery-ui-1.11.3/jquery-ui-1.11.3/jquery-ui.js' /> " ></script>
	<!--for column  -->
	<script src="<c:url value='/js/jquery.dragtable.js' /> " ></script>
	<link rel="stylesheet" type="text/css" href="//rawgithub.com/akottr/dragtable/master/dragtable.css" />
	<link href="http://rawgithub.com/akottr/dragtable/master/dragtable.css" rel="stylesheet" type="text/css" />
	 <link href="http://rawgit.com/akottr/dragtable/master/dragtable.css" rel="stylesheet" type="text/css" />
	<link href="http://akottr.github.io/css/akottr.css" rel="stylesheet" type="text/css" />
	<link href="http://akottr.github.io/css/reset.css" rel="stylesheet" type="text/css" />
	<!-- For column -->
	
	<c:if test="${ empty sessionScope.authenticatedUser }">
		<c:redirect url="${tomcatDocUrl}reloadlogin.html" />
	</c:if>
	<style>
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
	<script>
		$(document).ready(function(){
			 $('.defaultTable').dragtable({persistState: function(table) { 
				
					 table.el.find('th').each(function(i) { 
				      if(this.id != '') {
				    	  table.sortOrder[this.id]=i;} 
				    });
					 var newIndex = table.endIndex-2;
					 
					 var newendIndex = table.endIndex-1;
					 var thgot = table.el.find('th');
					 var jqueryTh = $(thgot)[newendIndex];
					 var runwayId = $(jqueryTh).attr("id");
					
		           	  runwayId =  runwayId.substr(3);
				        $.ajax({
				              type: "POST",
				                url:  "reorderRunway.html",
				                data: {
				                    "planId" : "${plan.planId}",
				                    "runwayId": runwayId,
				                    "newIndex" : newIndex
				                }
				              });  
				    } 
				});
			 $("#languages tbody").sortable({
					update: function(event, ui) {			         
			           var stageId =  ui.item.attr("id").substr(3);
			           
			           $.ajax({
			                type: "POST",
			                url:  "reorderStage.html",
			                data: {
			                    "planId" : "${plan.planId}",
			                    "stageId": stageId,
			                    "newIndex" : ui.item.index()
			                }
			            });
			       	}
				});
			$("#languages tbody").disableSelection();
			 $(".cellCheckpoint").sortable({
					update: function(event, ui) {
						var id = ui.item.attr("id").substr(3);
						var arr1 = id.split('col');
						var row = arr1[0];
						var arr2 =arr1[1].split('check');
						var col = arr2[0];
						var check = arr2[1];
			          
			         			           
			            $.ajax({
			                type: "POST",
			                url:  "reorderCheckpoint.html",
			                data: {
			                	"planId" : "${plan.planId}",
			                    "checkpointId" : check,
			                    "runwayId": col,
			                    "stageId": row,
			                    "newIndex" : ui.item.index()
			                }
			            });  
			       	}
				});
				$(".cellCheckpoint").disableSelection();
		});
	</script>
	<style>
		/*  table{border:1px solid #585858;padding:10px;margin:10px}
		table tr#headers{background-color:#585858;color:#fff}
		table tr td{margin:5px;padding:15px;}
		
		
		body { padding:10px; }
.sortable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
.sortable li { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.4em; height: 18px; }
.sortable li span { position: absolute; margin-left: -1.3em; }
.ui-state-highlight { height: 1.5em; line-height: 1.2em; }
.sortable-number { width: 50px;float: right;line-height: 1em;text-align: center;font-weight: bold; } */
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
                <span id="nav-home"><a href="#">Plan</a></span>
                <span id="nav-hosts"><a href="#">Department</a></span>
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
						<h4> Golden Eagle Flight Plan <input type="button" value="Logout" style="float:right;"  onclick="location.href='../j_spring_security_logout '" /> <!-- logout.html -->   </h4>                     
						   <h3 style="float:right">
						   	<br/>
                        	<a href ="${tomcatDocUrl}admin/saveRunway.html" > Runway </a> &nbsp; | &nbsp; 
                        	<a href ="${tomcatDocUrl}admin/saveStage.html" >  Stage</a> &nbsp; | &nbsp; 
                        	<a href ="${tomcatDocUrl}admin/saveCheckpoint.html" >  Checkpoint</a> &nbsp; | &nbsp;  
                        </h3>
                        	<br/><br/><br/>
                    		<c:if test="${not empty plan}">
                    		<div class="demo-content">
								<table border =1 width='100%' class="defaultTable sar-table" id="languages" >
								 	<thead>
										<tr>
											<th></th>
										<c:forEach var="runway" items="${plan.runwayList}">	
											<th id="col${runway.runwayId}"><center> ${ runway.desc }</center></th>
										</c:forEach>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="stage1" items="${plan.stageList }"><!-- stage loop -->
											<tr id="row${stage1.stageId}"> 
												<td> ${stage1.desc }
													
												</td>
											<c:forEach var="runway1" items="${plan.runwayList }" ><!--  runway loop -->
												<td>						
												<c:forEach var = "cell" items="${plan.cell }">  
													
													<c:if test="${(cell.runway.runwayId eq runway1.runwayId) && (cell.stage.stageId eq stage1.stageId)}">
												      		
												      		<ul class="cellCheckpoint">
												          	 <c:forEach var="checkpoint" items="${cell.checkpoints}" varStatus="count"> <!-- checkpoint loop -->
												           		<li id='row${stage1.stageId}col${runway1.runwayId}check${checkpoint.checkpointId}'>
												           			
												           			<input type="checkbox" name="selectCell" value="${checkpoint.checkpointId }"  style="float:left;"> ${checkpoint.value}
												           								
												           			<a href="${tomcatDocUrl}admin/editCheckpoint.html?checkpointId=${checkpoint.checkpointId}&runwayId=${cell.runway.runwayId}&stageId=${cell.stage.stageId}" > <img src="../img/icons/edit.png" alt="edit" width="15px"/></a>
												           			<a href="${tomcatDocUrl}admin/removeCheckpoint.html?checkpointId=${checkpoint.checkpointId}&runwayId=${cell.runway.runwayId}&stageId=${cell.stage.stageId}" > <img src="../img/icons/delete.png" alt="delete" width="15px"/> </a>
												           		
												           			<br/>
												           		</li>
												           		<%-- <li>
												           		
												           		<input type="checkbox" name="selectCell" value="${checkpoint.checkpointId }" > ${checkpoint.value}
												           		
												           		-- <a href='${tomcatDocUrl}EditCheckpoint?row=${entry.key.stage.desc}&col=${entry.key.runway.desc}&checkpointindex=${count.index }'  > Edit</a> <br/> 
												           		</li>
												           		<li>
												           		<a href="${tomcatDocUrl}admin/editCheckpoint.html?checkpointId=${checkpoint.checkpointId}&runwayId=${cell.runway.runwayId}&stageId=${cell.stage.stageId}" style="text-align: right;"> <img src="../img/icons/edit.png" alt="edit" width="15px"/></a><!-- style="float:right;" -->
												           		</li>
												           		<li>
												           		<a href="${tomcatDocUrl}admin/removeCheckpoint.html?checkpointId=${checkpoint.checkpointId}&runwayId=${cell.runway.runwayId}&stageId=${cell.stage.stageId}" style="text-align: right;" > <img src="../img/icons/delete.png" alt="delete" width="15px"/> </a>
												           		</li> --%>
												           		
												           		
												           		
												        	</c:forEach>
												         	 </ul> 
												         
												    </c:if>	
												</c:forEach>
												</td>
											</c:forEach> 
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
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