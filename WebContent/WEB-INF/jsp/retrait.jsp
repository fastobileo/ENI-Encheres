<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Retrait</title>
</head>
<body>

	<jsp:include page="head.jsp">
		<jsp:param name="title" value="Profil" />
	</jsp:include>
	
	<div class = "text-center">
		<h2>.... a remporté l'enchère !</h2>
		<p> NOM DU PRODUIT
		<p>Description :
		<p>Meilleure offre :
		<p>Mise à prix :
		<p>Fin de l'enchère :
		<p>Retrait :
		<p>Vendeur :
		<p><button>Retrait effectué</button>
	</div>
	
	<jsp:include page="foot.jsp" />

</body>
</html>