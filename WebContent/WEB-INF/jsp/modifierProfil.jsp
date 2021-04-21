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
	
	<h2 class = "text-center">Mon profil </h1>
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
		<button>Enregistrer</button>
	
		<button>Supprimer mon compte</button>
	</div>
	<jsp:include page="foot.jsp" />

</body>
</html>