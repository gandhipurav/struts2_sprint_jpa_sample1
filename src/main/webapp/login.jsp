<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Login</title>
</head>

<body>
	<jsp:include page="messages.jsp"/>	
	<form class="login-form" action="j_spring_security_check" method="post">
		<fieldset>
			<legend>Login Here</legend>

			<p>
				<label for="j_username">Username</label>: <input id="j_username"
					name="j_username" size="20" maxlength="50" type="text" />
			</p>

			<p>
				<label for="j_password">Password</label>: <input id="j_password"
					name="j_password" size="20" maxlength="50" type="password" />
			</p>

			<p>
				<input type="submit" value="Login" />
			</p>
		</fieldset>
	</form>
</body>
</html>