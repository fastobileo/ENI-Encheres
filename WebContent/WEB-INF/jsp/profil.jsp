<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
	<div class="container" style="max-width: 600px;">
	<h2 class = "text-center">Mon profil</h2>
		<div>
	          	<p>Pseudo :
	          	<p>Nom : 
	          	<p>Prénom : 
	          	<p>Email :
	          	<p>Téléphone :
	          	<p>Adresse :
	          	<ul>
	          		<li>Rue :</li>
	          		<li>Code postal :</li>
	          		<li>Ville :</li>
	          	</ul>
	          	<div class = "text-center">
	          		<button>Modifier</button>
	          	</div>
	          	
	    </div>      	
    </div>   
	
	<jsp:include page="foot.jsp" />

</body>
</html>