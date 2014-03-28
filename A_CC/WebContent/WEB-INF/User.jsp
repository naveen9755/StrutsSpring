<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
	<head>
		<title>Registration</title>
		<sx:head />
	</head>
	<body>
		<center>
			<s:actionerror />
			<s:form action="User" enctype="multipart/form-data" method="post">
				<tr>
					<td>
						<s:textfield name="firstName" label="First Name" />
					</td>
				</tr>
				<tr>
					<td>
						<s:textfield name="lastName" label="Last Name" />
					</td>
				</tr>
				<tr>
					<td>
						<s:textfield name="login" label="Email Id" />
					</td>
				</tr>
				<tr>
					<td>
						<s:password name="password" label="Password" />
					</td>
				</tr>
				<tr>
					<td>
						<s:file name="image" label="Image" />
					</td>
				</tr>
				<tr>
					<td>
						<s:textfield name="mobileNumber" label="Mobile Number" />
					</td>
				</tr>
				<tr>
					<td>
						<sx:datetimepicker name="dateOfBirth" label="Date Of Birth"
							displayFormat="dd-MMM-yyyy" value="%{'2013-11-01'}" />
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
						<s:radio name="gender" list="{'Male','Female'}" label="Gender" />
					</td>
				</tr>
				<tr>
					<td>
						<s:radio name="role" list="{'Developer','Admin'}" label="Role" />
					</td>
				</tr>
				<tr>
					<td>
						<s:submit name="operation" value="SignUp" theme="simple" />
					</td>
					<td>
						<s:reset theme="simple" />
					</td>
				</tr>
			</s:form>
		</center>
	</body>
</html>