<% 
  String base = (String) application.getAttribute("URL"); 
  String imageUrl = (String) application.getAttribute("image"); 
%> 
<TABLE WIDTH="1340" CELLPADDING="0" 
  HEIGHT="75" CELLSPACING="0" BORDER="0"> 
<TR> 
  <TD ALIGN="left" BGCOLOR="F6F6F6"><a href=<%=base %> >
    <FONT FACE="Verdana" SIZE="4">Apna E-Mall</FONT></a> 
  </TD> 
  <TD ALIGN="RIGHT" BGCOLOR="F6F6F6"> 
    <A HREF="<%=base%>?action=login"><IMG 
      BORDER="0" SRC="<%=imageUrl+ "User.JPG" %>" title="Login" ></A> 
    &nbsp;&nbsp;&nbsp; 
  </TD> 
  <TD  BGCOLOR="F6F6F6"> 
    <A HREF="<%=base%>?action=displayShoppingCart"><IMG 
      BORDER="0" SRC="<%=imageUrl+ "Cart.jpg" %>"></A> 
    &nbsp;&nbsp;&nbsp; 
  </TD> 
</TR> 
</TABLE>