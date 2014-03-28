<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<body>
	<table align="right">
	<tr><td>
		<s:if test="%{#session.user.login!=null}">
			Welcome:&nbsp;&nbsp;&nbsp;
				<s:property value="%{#session.user.login}" /><br>
			<p align="right">
			<a href="d_UserList"><abbr title="All User List">List</abbr> </a>
			<a href="d_Login?operation=Logout">Logout</a>
			<a href="d_ChangePassword">Resetpassword</a></p>
			<!-- <p align="left"><img src="image/rr.jpg" height="45" width="75"></img> 
			</p> -->
		</s:if>
		</td></tr>
</table>
	</body>
</html>