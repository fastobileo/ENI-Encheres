<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier votre profil</title>
</head>
<body>

	<jsp:include page="head.jsp">
		<jsp:param name="title" value="Modification de votre profil" />
	</jsp:include>

	<h2 class="text-center">Mon profil</h2>
	<form method="POST"
		action="${pageContext.servletContext.contextPath}/modifierProfil">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<div class="form-outline mb-4">
						<input required type="text" name="Pseudo" id="Pseudo"
							class="form-control" value="${user.getPseudo()}" /> <label
							class="form-label" for="Pseudo">Pseudo</label>
					</div>
					<div class="form-outline mb-4">
						<input required type="text" name="Prenom" id="Prenom"
							class="form-control" value="${user.getPrenom()}" /> <label
							class="form-label" for="Prenom">Prénom</label>
					</div>
					<div class="form-outline mb-4">
						<input required type="text" name="tel" id="tel"
							class="form-control" value="${user.getTelephone()}" /> <label
							class="form-label" for="tel">Téléphone</label>
					</div>
					<div class="form-outline mb-4">
						<input required type="text" name="cp" id="cp" class="form-control"
							value="${user.getCode_postal()}" /> <label class="form-label"
							for="cp">Code postal</label>
					</div>
					<div class="form-outline mb-4">
						<input required type="text" name="mdp" id="mdp"
							class="form-control" value="${user.getMot_de_passe()}" /> <label
							class="form-label" for="mdp">Mot de passe</label>
					</div>
				</div>

				<div class="col-md-6 col-sm-12">
					<div class="form-outline mb-4">
						<input required type="text" name="Nom" id="Nom"
							class="form-control" value="${user.getNom()}" /> <label
							class="form-label" for="Nom">Nom</label>
					</div>
					<div class="form-outline mb-4">
						<input required type="text" name="Email" id="Email"
							class="form-control" value="${user.getEmail()}" /> <label
							class="form-label" for="Email">Email</label>
					</div>
					<div class="form-outline mb-4">
						<input required type="text" name="Rue" id="Rue"
							class="form-control" value="${user.getRue()}" /> <label
							class="form-label" for="Rue">Rue</label>
					</div>
					<div class="form-outline mb-4">
						<input required type="text" name="Ville" id="Ville"
							class="form-control" value="${user.getVille()}" /> <label
							class="form-label" for="Ville">Ville</label>
					</div>
					<div class="form-outline mb-4">
						<input required type="text" name="Confirmation" id="Confirmation"
							class="form-control" value="${user.getMot_de_passe()}" /> <label
							class="form-label" for="Confirmation">Confirmation</label>
					</div>
				</div>
			</div>
		</div>
		<input name="id" value="${user.getId()}" hidden>
		<div class="text-center">
			<button type="submit" class="btn">Enregistrer</button>
			<a class="btn">Supprimer mon compte</a>
		</div>
	</form>
	<jsp:include page="foot.jsp" />
</body>
</html>