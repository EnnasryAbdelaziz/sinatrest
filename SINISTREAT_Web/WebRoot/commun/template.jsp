<%@ page import="eai.devass.sinistreat.web.utils.UserContainer"%> 
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.7.3.custom.css" />
<link rel="stylesheet" type="text/css" href="css/calendar-blue.css" />
<link rel="stylesheet" type="text/css" href="css/displaytag.css" />
<link rel="stylesheet" type="text/css" href="css/menuAccordion.css" />
<link rel="stylesheet" type="text/css" href="css/sliderAccordion.css" />
<link rel="stylesheet" type="text/css" href="css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="css/dateInput.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/mission.css" />

<title><tiles:getAsString name="title" /></title>   

<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/AjaxAction.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/grid.locale-fr.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.blockUI.js"></script>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/upload/ajaxfileupload.js"> </script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/scriptAjaxMission.js"> </script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mission.js"></script>		
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/parametrage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/reglement.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jugement.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/recours.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/menu.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar-fr.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar-setup.js"></script>
</head>

<body>

<div id="container">
               
	<tiles:insert name="top" />
		<div style="height:10px">&nbsp;</div>
		<div id="container-content" >
			<table cellpadding="10" cellspacing="0"  border="0" width="100%" >
				  <tr valign="top">
					  <td  style="width:200px;" valign="top" >	
					  		<tiles:insert name="menu" />
					  </td>
					  <td valign="top" >
						  	  <table width="100%" border="0">
					  			<tr><td  align="center" id="ariane"><%=((UserContainer)request.getSession().getAttribute("userContainerActions")).getModuleName()%><%=((UserContainer)request.getSession().getAttribute("userContainerActions")).getFonctionnaliteName()%></td></tr>
					  	  		<tr>
					  	  			<td>
						  				<tiles:insert name="contenu" /> 
						 			</td>
						 		</tr>
							  </table>    
					  <!-- CONTENT END -->
					  </td>
				</tr>
			</table>
		</div>
		<div id="footer-div">
		<tiles:insert name="footer" />
		</div>
</div> <!-- ENDE container -->	
	

</body>
</html>
