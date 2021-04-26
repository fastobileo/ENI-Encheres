<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creation de votre compte</title>
</head>
<body>

	<jsp:include page="head.jsp">
		<jsp:param name="title" value="Création de votre compte" />
	</jsp:include>
	
	<form action="${pageContext.servletContext.contextPath}/creationCompte"
			method="post">
	
	<h2 class = "text-center"> Créer votre profil </h2>
	<div class="container" style="max-width: 600px;">
		<div class ="row">
			<div class ="col-md-6 col-sm-12">
				<div class="form-outline mb-4">
					<input required type="text" name="pseudo" id="pseudo" class="form-control" /> <label
					class="form-label" for="form1Example1">Pseudo</label>
				</div>
				<div class="form-outline mb-4">
					<input required type="text" name="prenom" id="prenom" class="form-control" /> <label
					class="form-label" for="form1Example1">Prenom</label>
				</div>
				<div class="form-outline mb-4">
					<input required type="text" name="telephone" id="telephone" class="form-control" /> <label
					class="form-label" for="form1Example1">Telephone</label>
				</div>
				<div class="form-outline mb-4">
					<input required type="text" name="code_postal" id="code_postal" class="form-control" /> <label
					class="form-label" for="form1Example1">Code postal</label>
				</div>
				<div class="form-outline mb-4">
					<input required type="password" name="mot_de_passe" id="mot_de_passe" class="form-control" /> <label
					class="form-label" for="form1Example1">Mot de passe</label>
				</div>
			</div>			
			
			<div class ="col-md-6 col-sm-12">
				<div class="form-outline mb-4">
					<input required type="text" name="nom" id="nom" class="form-control" /> <label
					class="form-label" for="form1Example1">Nom</label>
				</div>
				<div class="form-outline mb-4">
					<input required type="text" name="email" id="email" class="form-control" /> <label
					class="form-label" for="form1Example1">Email</label>
				</div>
				<div class="form-outline mb-4">
					<input required type="text" name="rue" id="rue" class="form-control" /> <label
					class="form-label" for="form1Example1">Rue</label>
				</div>
				<div class="form-outline mb-4">
					<input required type="text" name="ville" id="ville" class="form-control" /> <label
					class="form-label" for="form1Example1">Ville</label>
				</div>
				<div class="form-outline mb-4">
					<input required type="password" name="mot_de_passe_confirme" id="mot_de_passe_confirme" class="form-control" /> <label
					class="form-label" for="form1Example1">Confirmation</label>
				</div>
			</div>
		</div>	
		
		<div class = "text-center">
		<div class="col-md-12 col-sm-12">
			<button type="submit" class="btn btn-primary btn-block">Créer</button>
			<a type="submit" class="btn btn-dark btn-block" href ="${pageContext.servletContext.contextPath}/">Annuler</a>
		</div>
		
	</div>
	</div>
	
	</form>
	<jsp:include page="foot.jsp" />

</body>
</html>