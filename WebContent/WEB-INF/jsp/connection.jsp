<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<c:if test="${errorMessage!=null}">
			<div class="alert alert-danger text-center" role="alert">${errorMessage }</div>
		</c:if>
		<form action="${pageContext.servletContext.contextPath}/connection"
			method="post">
			<div class="form-outline mb-4">
				<input required type="text" name="id" id="id" class="form-control" value="${ id }" /> <label
					class="form-label" for="form1Example1">Identifiant</label>
			</div>
			<div class="form-outline mb-4">
				<input required type="password" name="password" id="form1Example2"
					class="form-control" /> <label class="form-label"
					for="form1Example2">Mot de passe</label>
			</div>
			<div class="row mb-4">
				<div class="col d-flex justify-content-center">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="seSouvenirDeMoi"
							id="form1Example3"/> <label class="form-check-label"
							for="form1Example3"> Se souvenir de moi </label>
					</div>
				</div>

				<!-- <div class="col">
					<a href="#!">Mot de passe oublié</a>
				</div>-->
			</div>
			<button type="submit" class="btn btn-primary btn-block">Se
				connecter</button>
		</form>
		<br> <a class="btn btn-dark btn-block" href ="${pageContext.servletContext.contextPath}/creationCompte">Créer un compte</a>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>