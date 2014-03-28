<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<title><tiles:getAsString name="title" ignore="true" />
		</title>
	<!-- 	<STYLE type="text/css">
.tbody {
	background-image: url("image/ac5.PNG");
	background-repeat: no-repeat;
	background-position: left;
	background-size: 560px 380px;
}
</STYLE> -->
	</head>
	<body>
		<center>
			<table>
				<tr>
					<td colspan="2" width="1100" height="110" bgcolor="orange">
						<tiles:insertAttribute name="header" />
					</td>
				</tr>
				<tr>
					<td colspan="1" width="300" height="350" bgcolor="white">
						<tiles:insertAttribute name="menu" />
					</td>
					<td class="tbody" colspan="1" width="900" height="350"
						bgcolor="white">
						<tiles:insertAttribute name="body" />
					</td>
				</tr>
				<tr>
					<td bgcolor="green" colspan="2" width="1100" height="110">
						<tiles:insertAttribute name="footer" />
					</td>
				</tr>
			</table>
		</center>
	</body>
</html>