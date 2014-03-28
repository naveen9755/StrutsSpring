<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
	</head>
	<body>
		<center>
			<s:actionerror />
			<s:actionmessage />

			<s:form action="Login" method="post">
				<tr>
					<td>
						<s:textfield name="login" label="Login Id" />
						<s:password name="password" label="Password" />
					</td>
				</tr>
				<tr>
					<td>
						<s:submit name="operation" value="Login" theme="simple" />
						<s:reset name="Reset" theme="simple" />
					</td>
				</tr>
			</s:form>
			<a href="d_User">New User</a>
			<a href="d_ForgotPassword">Forgot Password</a>
		</center>
	</body>
</html>