
<%@page import="com.dto.UserDTO"%>
<%@page import="com.opensymphony.xwork2.ActionSupport"%><%@ taglib
	prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<!--<script type="text/javascript">

  function checkForm(form)
  {
   var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
    if(form.newPassword.value != "" && form.oldPassword.value != form.newPassword.value ) {
     if( form.newPassword.value == form.newPassword2.value){
      if(form.newPassword.value.length < 6) {
        alert("Error: Password must contain at least six characters AlphaNumeric Value!");
        form.pwd1.focus();
        return false;
      }
      re = /[~,!,@,#,$,%,^,&,*]/;
     if(!re.test(form.newPassword.value)) {
        alert("Error: Password must be Contain Special Charater!");
        form.newPassword.focus();
        return false;
      } 
      re = /[0-9]/;
      if(!re.test(form.newPassword.value)) {
        alert("Error: password must contain at least one number (0-9)!");
        form.newPassword.focus();
        return false;
      }
      re = /[a-z]/;
      if(!re.test(form.newPassword.value)) {
        alert("Error: password must contain at least one lowercase letter (a-z)!");
        form.newPassword.focus();
        return false;
      }
      re = /[A-Z]/;
      if(!re.test(form.newPassword.value)) {
        alert("Error: password must contain at least one uppercase letter (A-Z)!");
        form.newPassword.focus();
        return false;
      }
      } else {
      alert("Error: Please check New Password Value must be Same...!");
      form.newPassword.keyup();
      return false;
    }
      
    } else {
      alert("Error: Please check Old and New Password must be diffrent...!");
      form.newPassword.focus();
      return false;
    }

    alert("Thanks You entered a valid password: ");
    return true;
  }

</script>-->
		<title>Change Password</title>
	</head>
	<body>

		<s:actionerror />
		<s:actionmessage />
		<s:form name="changePassword" action="ChangePassword"
			onsubmit="return checkForm()">
			<s:hidden name="id" value="%{#session.user.id}"></s:hidden>
			<tr>
				<td>
					<s:textfield name="Login" value="%{#session.user.login}"
						readonly="true" />
					<s:password name="oldPassword" label="Old-Password" />
					<s:password name="newPassword" label="New Password"/>
					<s:password name="confrmPassword" label="Re-enter New Password" />
				</td>
			</tr>
			<tr>
				<td>
					<s:submit name="operation" value="ChangePassword"/>
				</td>
			</tr>
		</s:form>
	</body>

</html>