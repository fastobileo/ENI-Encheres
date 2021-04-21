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
	
	<h2 class = "text-center"> Créer votre profil </h2>
	<div class="container">
		<div class ="row">
			<div class ="col-md-6 col-sm-12">
				<p>Pseudo : 
				<input>
				<p>Prénom : 
				<input>
				<p>Téléphone : 
				<input>
				<p>Code postal : 
				<input>
				<p>Mot de passe : 
				<input>
			</div>
			
			<div class = "col-md-6 col-sm-12">
				<p>Nom : 
				<input>
				<p>Email : 
				<input>
				<p>Rue : 
				<input>
				<p>Ville : 
				<input>
				<p>Confirmation : 
				<input>
			</div>
		</div>	
	</div>
	<div class = "text-center">
		<div class="col-md-4 col-sm-12">
			<a class="btn btn-primary btn-block" href="${pageContext.servletContext.contextPath}/AfficherProfil">Créer</a>
		</div>
	
		<button>Annuler</button>
	</div>
	<jsp:include page="foot.jsp" />

</body>
</html>