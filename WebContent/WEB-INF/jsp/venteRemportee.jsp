<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="head.jsp">
			<jsp:param name="title" value="Profil" />
		</jsp:include>
		
		<h2 class = "text-center">Détail vente</h2>
		
		<div class = "text-center">
			<p>Vous avez remporté la vente !
			<p>Description :
			<p>Meilleure offre:
			<p>Mise à prix
			<p>Retrait :
			<p>Vendeur :
			<p>Téléphone :
			<p><button>Back</button>
		</div>
		
		<jsp:include page="foot.jsp" />

</body>
</html>