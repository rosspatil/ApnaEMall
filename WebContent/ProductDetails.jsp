<%@ page import="com.binarysoftware.burnaby.Product" %> 
<%@ page import="java.sql.*" %> 
<%@ page import="java.util.*" %> 
<jsp:useBean id="dbBean" scope="application" 
class="com.binarysoftware.burnaby.DbBean"/> 
<% 
  String base = (String) application.getAttribute("URL"); 
  String imageUrl = (String) application.getAttribute("image"); 
%> 
<HTML> 
<HEAD> 
<TITLE>Product Details</TITLE> 
</HEAD> 
<BODY> 
<TABLE> 
<TR> 
  <TD COLSPAN=2><jsp:include page="Header.jsp" flush="true"/></TD> 
</TR> 
<TR> 
  <TD><jsp:include page="Menu.jsp" flush="true"/></TD> 
  <TD VALIGN="TOP"> 
<% 
  try {
    int productId = Integer.parseInt(request.getParameter("productId")); 
    Product product = (Product)dbBean.getProductDetails(productId); 
    if (product!=null) {
%> 
<TABLE> 
<TR> 
  <TD><IMG BORDER="0" WIDTH="100" SRC="<%=(imageUrl + product.id)%>.jpg"></TD> 
  <TD rowspan="5"><B><%=product.name%></B><BR> 
      <%=product.description%><BR> 
      Price :$<%=product.price%></TD> 
</TR> 
<TR> 
  <TD COLSPAN="2" ALIGN="RIGHT"> 
    <A HREF="<%=base%>?action=addShoppingItem&productId=<%=product.id%>"> 
      Add To Cart</A> 
  </TD> 
</TR> 
</TABLE> 
<% 
    } 
  } 
  catch (Exception e) {
    out.println("Error: Invalid product identifier."); 
  } 
%> 
  </TD> 
</TR> 
</TABLE> 
</BODY> 
</HTML>