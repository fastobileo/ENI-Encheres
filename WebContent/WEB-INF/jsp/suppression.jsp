<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Supprimer votre compte</title>
</head>
<body>

	<jsp:include page="head.jsp">
		<jsp:param name="title" value="Modification de votre profil" />
	</jsp:include>
	
	<h6 class="text-center">Souhaitez-vous définitivement supprimer votre compte ?</h6>
	<form method = "POST">
		<div class="text-center">
			<button type="submit" class="btn" id="id">Supprimer</button>
			<a class="btn" type="submit">Annuler</a>
		</div>
	</form>
	<jsp:include page="foot.jsp" />

</body>
</html>