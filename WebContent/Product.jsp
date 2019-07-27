<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.binarysoftware.burnaby.Database"%>
<%@ page import="com.binarysoftware.burnaby.Product"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="dbBean" scope="application"
	class="com.binarysoftware.burnaby.DbBean" />
<%
	String base = (String) application.getAttribute("URL");
	String imageUrl = (String) application.getAttribute("image");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="=1">
		<%
			try {
				Connection con = Database.getConnection();
				Statement st = con.createStatement();
				String sql = "Select * from product;";
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					Product product = (Product) dbBean.getProductDetails(rs
							.getInt("productId"));
					if (product != null) {
		%>

		<tr>
			<TD><IMG BORDER="0" WIDTH="100"
				SRC="<%=(imageUrl + product.id)%>.jpg"></TD>
			<TD ><B><%=product.name%></B><BR> <%=product.description%><BR>
				Price :$<%=product.price%></TD>

			<TD COLSPAN="2" ALIGN="RIGHT"><A
				HREF="<%=base%>?action=addShoppingItem&productId=<%=product.id%>">
					Add To Cart</A></TD>
		</TR>
		<%
			}
				}
			} catch (Exception e) {
				out.println("Error: Invalid product identifier.");
			}
		%>
	</table>
</body>
</html>