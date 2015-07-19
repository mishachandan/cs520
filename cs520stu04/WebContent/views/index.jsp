<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="hw1.config.Config,hw1.model.Plan,hw1.model.Runway,hw1.model.Stage"%>
<%
request.setAttribute("tomcatDocUrl", Config.getApplicationURL());
%>

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- <jsp:useBean id="cellToFetch" class="hw1.model.Cell" scope="page" >
	<jsp:setProperty name="runway" property="*"/>
	<jsp:setProperty name="stage" property="*"/>
</jsp:useBean> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CS520 </title>
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
                        <h3>
                        	<a href ="<%=Config.getApplicationURL() %>AddStage" >Add A Stage </a> &nbsp; | &nbsp; <a href ="<%=Config.getApplicationURL() %>AddCheckPoint" >Add A Check Point </a> 
                        </h3>
                        <br/>
						<c:if test="${not empty applicationScope.plan}">
							<table border =1 width='100%'>
								<tr>
									<th></th>
								<c:forEach var="runway" items="${applicationScope.plan.runwayList}">	
									<th><center> ${ runway.desc }</center></th>
								</c:forEach>
								</tr>
								
								<c:forEach var="stage1" items="${applicationScope.plan.stageList }">
									<tr> 
										<td>
											${stage1.desc }
										</td>
									<c:forEach var="runway1" items="${applicationScope.plan.runwayList }" >
																		
										
										<c:forEach var="entry" items="${applicationScope.plan.checkpointMap }">
											
											<c:if test="${entry.key.runway == runway1 && entry.key.stage == stage1}">
										        
										           <td>
										           	<c:forEach var="checkpoint" items="${entry.value}" varStatus="count">
										           		
										           		<input type="checkbox" name="selectCell" value="${entry.key.id }" >
										           	
										           			<c:set var="myVariableRunway" value="${entry.key.runway}"/>
										           			<% 
										           				Runway myVariable = (Runway)pageContext.getAttribute("myVariableRunway");
										           				
										           				int i =((Plan)getServletContext().getAttribute("plan")).getRunwayList().indexOf(myVariable);
										           				pageContext.setAttribute("runObjPageIndex", i);
										           				
										           			%>
										           			<%-- ${runObjPageIndex} --%>
										           		
										           			 <c:set var="myVariableStage" value="${entry.key.stage}"/>
										           			<% 
										           				Stage myVariableS = (Stage)pageContext.getAttribute("myVariableStage");
										           				
										           				int j =((Plan)getServletContext().getAttribute("plan")).getStageList().indexOf(myVariableS);
										           				pageContext.setAttribute("staObjPageIndex", j);
										           				
										           			%>
										           			<%-- ${staObjPageIndex} --%>
										           		
										           		<%-- ${checkpoint.desc } <a href='${tomcatDocUrl}EditCheckpoint?row=${entry.key.stage.desc}&col=${entry.key.runway.desc}&checkpointindex=${count.index }'  > Edit</a> <br/> --%>
										           		${checkpoint.desc } <a href='${tomcatDocUrl}EditCheckpoint?rowId=${staObjPageIndex}&colId=${runObjPageIndex}&checkpointindex=${count.index }'  > Edit</a> <br/>
										           	</c:forEach>
										           </td>
										        
										    </c:if>	
										    
										
										</c:forEach>
									</c:forEach>
									</tr>
								</c:forEach>
							</table>
						</c:if>
						          
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