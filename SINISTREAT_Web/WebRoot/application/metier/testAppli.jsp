<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="../../servlet/testAppli.mim" method="post">
<table>
	<tr>
		<td><textarea name="sqlReq" rows="4" cols="100"></textarea> </td>
		
	</tr>
	<tr>
		<td>	 </td>
			
	</tr>
	<tr>
		<td>Action name : <input type="text" name="actionPW">
		<button type="submit">[Faire gaffe, clause where ...]</button> </td>		
	</tr>
</table>



</form>

</body>
</html>