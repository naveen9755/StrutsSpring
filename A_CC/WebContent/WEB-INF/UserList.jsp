<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
	<head>
		<title>Registration</title>
		<SCRIPT type="text/javascript">
		function checkall(source){
		checkboxes=document.getElementsByName("ids");
		for(var i=0, n=checkboxes.length;i<n;i++) {
    checkboxes[i].checked = source.checked;
  }
}	
	</SCRIPT>
		<sx:head />
	</head>
	<body>
		<center>
			<s:actionerror />
			<s:form action="UserList" method="post">

				<tr>
					<TH>
						 &nbsp;
						<input name="CheckAll" type="checkbox" onclick="checkall(this)">
					</TH>
					<TH>
						FirstName &nbsp; &nbsp;
					</TH>
					<TH>
						LastName &nbsp; &nbsp;
					</TH>
					<TH>
						@email &nbsp;
					</TH>
					<TH>
						MobileNumber &nbsp; &nbsp;
					</TH>
					<TH>
						Date-Of-Birth &nbsp; &nbsp;
					</TH>
					<TH>
						Gender &nbsp; &nbsp;
					</TH>
					<TH>
						Role &nbsp; &nbsp;
					</TH>
				</tr>
				<s:iterator value="dtoList">
					<tr>
						<td>
							  &nbsp;	<input type="checkbox" name="ids"
								value="<s:property value='id'/>" />
						</td>
						<td>
							<s:property value="firstName" />
						</td>
						<td>
							<s:property value="lastName" />
						</td>
						<td>
							<s:property value="login" />
						</td>
						<td>
							<s:property value="mobileNumber" />
						</td>
						<td>
							<s:property value="dateOfBirth" />
						</td>
						<td>
							<s:property value="gender" />
						</td>
						<td>
							<s:property value="role" />
						</td>
					</tr>
					<tr></tr>
				</s:iterator>
				<tr>
					<td>
						<s:submit name="operation" value="Delete"></s:submit>
					</td>
				</tr>
			</s:form>
		</center>
	</body>
</html>