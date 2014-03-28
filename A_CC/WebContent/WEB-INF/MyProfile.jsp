<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
	<head>
		<title>Registration</title>
		<sx:head />
	</head>
	<body>
		<center>
			<s:form action="MyProfile" enctype="multipart/form-data"
				method="post">

				<s:hidden name="id" value="%{#session.user.id}"></s:hidden>
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
						<s:textfield name="login" value="%{#session.user.login}"
							label="Email Id" readonly="true" />
					</td>
				</tr>
				<tr>
					<td>
						<s:password name="password" label="Old Password" />
					</td>
				</tr>
				<tr>
					<td>
						<s:password name="newPassword" label="New Password" />
					</td>
				</tr>
				<tr>
					<td>
						<s:password name="confirmPassword" label="Confirm Password" />
					</td>
				</tr>
				<tr>
					<td>
						<s:file name="image" label="Image" />
					</td>
				</tr>
				<tr>
					<td>
						<s:textfield name="mobileNo" label="Mobile Number" />
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
						<s:radio name="gender" list="{'Male','Female'}" label="Gender" />
					</td>
				</tr>

				<tr>
					<td>
						<s:submit name="operation" value="Save" theme="simple" />
					</td>
					<td>
						<s:reset theme="simple" />
					</td>
				</tr>
			</s:form>
		</center>
	</body>
</html>