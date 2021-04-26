<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
	<jsp:include page="head.jsp">
		<jsp:param name="title" value="Accueil" />
	</jsp:include>
	<div class="container">
		<div class="row">
			<h3>Filtres :</h3>
			<div class="col-md-4 col-sm-12">
				<div class="form-outline">
					<input type="text" id="form1" class="form-control" /> <label
						class="form-label" for="form1">Rechercher</label>
				</div>
				<br>
				<div class="dropdown">
					<h6>catégories :</h6>
					<select class="form-select" aria-label="Default select example">
						<option selected value="1">Toutes</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select>
				</div>
				<br>
			</div>

			<div class="col-md-4 col-sm-12">
				<a class="btn btn-primary btn-block">rechercher</a>
			</div>
			<div class="col-md-4 col-sm-12">
				<a class="btn btn-primary btn-block" href="${pageContext.servletContext.contextPath}/Vendre">Vendre un article</a>
			</div>
			<br>
		</div>
		
		<c:forEach var= "enchere" items= "${listeEnchere}">
		
		<div class="row">
			<h4>${enchere.getArticle().getNom_article()}</h4>
			<div class="card mb-3" style="max-width: 540px">
				<div class="row g-0">
					<div class="col-md-4">
						<img
							src="https://mdbootstrap.com/wp-content/uploads/2020/06/vertical.jpg"
							alt="..." class="img-fluid" />
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<p class="card-text">Prix : ${enchere.getMontant_enchere()}</p>
							<p class="card-text">Fin de l'enchère : ${enchere.getArticle().getDate_fin_encheres()}</p>
							<p class="card-text">Vendeur : ${enchere.getUtilisateur().getPseudo()}</p>
							<div class="text-center">
								<a href= "${pageContext.servletContext.contextPath}/detailsVente?id=${enchere.getNo_enchere()}" class="btn btn-primary">En savoir plus</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</c:forEach>

	</div>

	<jsp:include page="foot.jsp" />
</body>
</html>