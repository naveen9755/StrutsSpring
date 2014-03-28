<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>

		<title>FoundTransfer</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>

		<s:form action="Fundtransfer">
			<tr>
				<td>
					<s:actionmessage />
				</td>
			</tr>
			<tr>
				<td>
					<s:textfield name="depositerId" label="Enter Depositer Account No" />
				</td>
			</tr>
			<tr>
				<td>
					<s:textfield name="amount" label="Enter Amount" />
				</td>
			</tr>
			<tr>
				<td>
					<s:textfield name="reciverId" label="Enter Reciver Account No" />
				</td>
			</tr>
			<tr>
				<td>
					<s:submit name="operation" value="FundTransfer"
						label="Transfer Money" theme="simple" />
				</td>
				<td>
					<s:reset theme="simple" />
				</td>
			</tr>
		</s:form>
	</body>
</html>
