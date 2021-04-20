<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>
	<jsp:include page="head.jsp">
		<jsp:param name="title" value="Connexion" />
	</jsp:include>
	<div class="container" style="max-width: 600px;">
		<form action="${pageContext.servletContext.contextPath}/connection" method="post">
			<div class="form-outline mb-4">
				<input type="text" name="id" id="id" class="form-control" /> <label
					class="form-label" for="form1Example1">Identifiant</label>
			</div>
			<div class="form-outline mb-4">
				<input type="password" name="password" id="form1Example2" class="form-control" /> <label
					class="form-label" for="form1Example2">Mot de passe</label>
			</div>
			<div class="row mb-4">
				<div class="col d-flex justify-content-center">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value=""
							id="form1Example3" checked /> <label class="form-check-label"
							for="form1Example3"> Se souvenir de moi </label>
					</div>
				</div>

				<div class="col">
					<!-- Simple link -->
					<a href="#!">Mot de passe oublié</a>
				</div>
			</div>
			<button type="submit" class="btn btn-primary btn-block">Se connecter</button>
		</form>
		<br>
		<a class="btn btn-dark btn-block"/>Créer un compte</a>
	</div>
	<h1>${errorMessage }</h1>
	<h1>${user.getId() }</h1>
	<h1>${sessionScope.idUser}</h1>
	
	
	<jsp:include page="foot.jsp" />

</body>
</html>