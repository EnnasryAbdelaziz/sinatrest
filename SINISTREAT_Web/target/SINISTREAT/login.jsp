<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>login.jsp</TITLE>
</HEAD>
<BODY>

<script language="javascript"> 
function onSubmit(action){	
	var a = submit();
	setTimeout(login(a),3000);		
}

function login(a){
	 var b =a;
	document.forms[1].action='habLogonActionByLDAP.do';	
	document.forms[1].submit();	
			
}
function submit(){	
	document.forms[0].submit();	
	return '';		
}
</script>
<P>Application de test qui implémente SSO sur un server websphere 6.1 .</P>
	
	
	<%
	String user = (String) request.getSession().getAttribute("userSession");
	if(user== null || "".equals(user)){ 
		%>
	
	<form method="post" action="<%=request.getContextPath()%>/j_security_check">
	<input type="text" name="j_username">
	<input type="password" name="j_password">
	<input type=button value="Login" onclick="onSubmit('j_security_check');">	
	</form>
		<%
	}
	
	if(user != null && !"".equals(user) ){
		
		%>
		
		L'utilisateur <%=user %>  n'a pas  un compte dans cette application 
		<form name="logout" action="<%=request.getContextPath()%>/ibm_security_logout?logoutExitPage=/" method="post">
			<input type=submit name="Logout" value="change user">
		</form>
		
		<%		
		} else {
		%>
		L'utilisateur  <%=user %> est déjà connecté :
		<form name="logout" action="<%=request.getContextPath()%>/ibm_security_logout?logoutExitPage=/" method="post">
			<input type=submit name="Logout" value="logout">
		</form>
		
		<%		
		}	
	%>
	
	
	
</BODY>
</HTML>

<%
	request.getSession().removeAttribute("userSession");
		%>
