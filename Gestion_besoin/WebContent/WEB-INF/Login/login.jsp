<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login | GESTION DE BESOIN</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<!-- <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
		<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
		<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="css/util.css">
		<link rel="stylesheet" type="text/css" href="css/main.css">
	 -->
<link href="<c:url value="style/css_login/images/icons/favicon.ico"/>"
	rel="icon">
<!--===============================================================================================-->

<link
	href="<c:url value="style/css_login/vendor/bootstrap/css/bootstrap.min.css"/>"
	rel="stylesheet">
<!--===============================================================================================-->

<link
	href="<c:url value="style/css_login/fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>"
	rel="stylesheet">
<!--===============================================================================================-->

<link href="<c:url value="style/css_login/vendor/animate/animate.css"/>"
	rel="stylesheet">
<!--===============================================================================================-->

<link
	href="<c:url value="style/css_login/vendor/css-hamburgers/hamburgers.min.css"/>"
	rel="stylesheet">
<!--===============================================================================================-->

<link
	href="<c:url value="style/css_login/vendor/select2/select2.min.css"/>"
	rel="stylesheet">
<!--===============================================================================================-->

<link href="<c:url value="style/css_login/css/util.css"/>"
	rel="stylesheet">

<link href="<c:url value="style/css_login/css/main.css"/>"
	rel="stylesheet">
<!--===============================================================================================-->

</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img alt="IMG"
						src="<c:url value="style/css_login/images/cem.png"/>">
				</div>

				<form class="login100-form validate-form" action="ControleUser"
					method="post">
					<span class="login100-form-title"> Authentification </span>

					<div class="wrap-input100 validate-input"
						data-validate="Veuillez completer le login">
						<!-- data-validate = "Valid email is required: ex@abc.xyz -->
						<input class="input100" type="text" name="login"
							placeholder="Login"> <span class="focus-input100"></span>
						<span class="symbol-input100"> <i class="fa fa-user"
							aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Veuillez completer le mot de passe">
						<input class="input100" type="password" name="pass"
							placeholder="Mot de passe"> <span class="focus-input100"></span>
						<span class="symbol-input100"> <i class="fa fa-lock"
							aria-hidden="true"></i>
						</span>
					</div>

					<div style="color: red; padding: 10px;">
						<%
							String var= (String) request.getAttribute("trouve");
							if (var!=null)
							out.println(var);
						%>
					</div>

					<input type="hidden" name="ajout" value="login">
					<div class="container-login100-form-btn">
						<button class="login100-form-btn">Se connecter</button>
					</div>

					<div class="text-center p-t-12">
						<span class="txt1">  </span> <a class="txt2" href="#"></a>
					</div>

					<div class="text-center p-t-136"></div>
				</form>
			</div>
		</div>
	</div>




	<!--===============================================================================================-->

	<script
		src="<c:url value="style/css_login/vendor/jquery/jquery-3.2.1.min.js" />"
		type="text/javascript"></script>
	<!--===============================================================================================-->

	<script
		src="<c:url value="style/css_login/vendor/bootstrap/js/popper.js" />"
		type="text/javascript"></script>

	<script
		src="<c:url value="style/css_login/vendor/bootstrap/js/bootstrap.min.js" />"
		type="text/javascript"></script>
	<!--===============================================================================================-->

	<script
		src="<c:url value="style/css_login/vendor/select2/select2.min.js" />"
		type="text/javascript"></script>
	<!--===============================================================================================-->

	<script
		src="<c:url value="style/css_login/vendor/tilt/tilt.jquery.min.js" />"
		type="text/javascript"></script>
	<script>
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
	<!--===============================================================================================-->

	<script src="<c:url value="style/css_login/js/main.js" />"
		type="text/javascript"></script>

</body>
</html>