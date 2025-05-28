
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Simulation des flux : Administration ORG</title>
    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/xml; charset=UTF-8">
    
  </head>
	<form name="myform" action="<%=request.getContextPath()%>/adminTestServlet" method="post" > 
	<body >
	<TABLE width="800" height="400">
	<TR><TD><LABEL>Simulation des flux :</LABEL></TD></TR>
	<TR height="100%"><TD width="100%">
	  	<TEXTAREA rows="40" cols="150" name="flux"></TEXTAREA> 
	</TD>
	</TR>
	</TABLE>

	<A href="javascript: submitform()">Appeler le service</A>
</body>
  </form>
  	<SCRIPT type="text/javascript"><!--
		function submitform()
		{
		  	document.myform.submit();
		}
		
	--></SCRIPT>    
  
</html>
