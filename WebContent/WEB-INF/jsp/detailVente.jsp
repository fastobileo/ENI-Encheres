<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="head.jsp">
		<jsp:param name="title" value="Profil" />
	</jsp:include>
	
	<h2 class = "text-center">Détail vente</h2>
	
	<div class = "text-center">
		<p>NOM DE LA VENTE
		<p>Description :
		<p>Catégorie :
		<p>Meilleure offre:
		<p>Mise à prix
		<p>Fin de l'enchère:
		<p>Retrait :
		<p>Vendeur :
		<div>
			<p>Ma proposition :
			<input>
			<button>Encherir</button>
		</div>
		
		
	</div>
	
	<jsp:include page="foot.jsp" />

</body>
</html>