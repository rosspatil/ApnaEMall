<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="grey" background="images\\Tulips.jpg">

	<center>
		<h1>Personal login</h1>
		<form >
			<table border="1" bgcolor="white">
				<tr>
					<td><b>Name</b></td>
					<td><input type="text" name="login"></td>
				</tr>

				<tr>
					<td><b>Password</b></td>
					<td><input type="password" name="pswd"></td>
				</tr>

			</table>
			<input type="hidden" name="action" value="loggedin">
			<input type="submit" value="Submit"> <input type="reset"
				value="reset">

		</form>
	</center>
</body>
</html>