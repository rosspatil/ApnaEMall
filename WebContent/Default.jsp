
<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
</HEAD>
<BODY>
	<TABLE>
		<TR>
			<TD COLSPAN=2><jsp:include page="Header.jsp" flush="true" /></TD>
		</TR>
		<TR>
			<TD><jsp:include page="Menu.jsp" flush="true" /></TD>
			<TD VALIGN="TOP">

				<H2>
					<font color="red" size="48">Welcome to APNA E-Mall.</font>
				</H2>

			</TD>
		</tr>



	</TABLE>
	<center>
		<table>
			<tr>
				<td><jsp:include page="Product.jsp" flush="true"></jsp:include></td>
			</tr>
		</table>
	</center>
</BODY>
</HTML>