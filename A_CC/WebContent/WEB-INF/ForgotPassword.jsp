<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>Change Password</title>
	</head>
	<body>
		<center>
			<s:form action="ForgotPassword" method="post">
				<s:actionerror />
				<s:actionmessage />
				<tr>
					<td>
						<s:textfield name="Login" label="Enter you Login Id" />
					</td>
				</tr>
				<tr>
					<td>
						<s:select list="securityList" name="securityQuestion"
							label="SecurityQuestion"></s:select>
					</td>
				</tr>
				<tr>
					<td>
						<s:textfield name="answer" label="Answer"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						<s:submit name="operation" value="ForgotPassword" theme="simple" />
					</td>
					<td>
						<a href="d_Login">Login</a>
					</td>
				</tr>
			</s:form>
		</center>
	</body>
</html>