<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
					<h6>Catégories :</h6>
					<select class="form-select" aria-label="Default select example">
						<c:forEach var="l" items="${listeCategorie}">
							<option selected value="${ l.getNoCategorie() }">${ l.getLibelle() }</option>
						</c:forEach>
					</select>
				</div>
				<br>
			</div>

			<form method="get">
				<div class="row">
					<div class="col-md-4 col-sm-12">
						<input type="radio" id="achats" name="filtre1" value="achat"
							checked> <label for="achat">Achats</label>
						<div>
							<input type="checkbox" id="enchere1" name="open"
								value="encheres_ouvertes"> <label
								for="encheres_ouvertes">enchères ouvertes</label>
						</div>
						<div>
							<input type="checkbox" id="enchere2" name="mine"
								value="oui"> <label
								for="mes_encheres">mes enchères</label>
						</div>
						<div>
							<input type="checkbox" id="enchere3" name="win"
								value="oui"> <label
								for="enchere_remportee">mes enchères remportées</label>
						</div>
					</div>
					<div class="col-md-4 col-sm-12">
						<input type="radio" id="ventes" name="filtre1" value="vente">
						<label for="vente">Mes ventes</label>
						<div>
							<input type="checkbox" id="vente1" name="VenteEnCours"
								value="${sessionScope.idUser }"> <label
								for="ventes_encours">mes ventes en cours</label>
						</div>
						<div>
							<input type="checkbox" id="vente2" name="VenteNonDebutees"
								value="${sessionScope.idUser }"> <label
								for="ventes_non_debutees">ventes non débutées</label>
						</div>
						<div>
							<input type="checkbox" id="vente3" name="VentesTerminees"
								value="${sessionScope.idUser }"> <label
								for="ventes_terminees">ventes terminées</label>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-md-4 col-sm-12">
						<button type="submit" class="btn btn-primary btn-block">rechercher</button>
					</div>
					<div class="col-md-4 col-sm-12">
						<a class="btn btn-primary btn-block"
							href="${pageContext.servletContext.contextPath}/Vendre">Vendre
							un article</a>
					</div>
				</div>
			</form>
			<br>
		</div>
		<c:if test="${ ErrorEnchere!=null}">
			<p>${ErrorEnchere }</p>
		</c:if>
		<br>
		<h1 class="">Annonces : </h1>
		<br>
		<div class="row">
		<c:if test="${listeEnchere.size()==0}">RIEN</c:if>
		<!-- Création d'une variable à la date du jour -->
		<c:set var = "now" value = "<%= new java.util.Date()%>"/>
		<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="parsedDate" />
			<c:forEach var="enchere" items="${listeEnchere}">
				<div class="col-md-4 col-sm-12">
					<div class="card mb-3" style="max-width: 540px">
					<h4>${enchere.getArticle().getNom_article()}</h4>
						<div class="row g-0">
							<div class="col-md-4">
								<img
									src="https://mdbootstrap.com/wp-content/uploads/2020/06/vertical.jpg"
									alt="..." class="img-fluid" />
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<p class="card-text">Prix : ${enchere.getMontant_enchere()}</p>
									<p class="card-text">Fin de l'enchère :
										${enchere.getArticle().getDate_fin_encheres()}</p>
									<p class="card-text">Vendeur :
										${enchere.getUtilisateur().getPseudo()}</p>
									<c:choose>
    									<c:when test="${ enchere.getArticle().getDate_fin_encheres() < parsedDate}">
											<div class="text-center">
												<button type="button" class="btn btn-dark">Enchère Terminée</button>
											</div>
   										 </c:when>    
    									<c:otherwise>
											<div class="text-center">
												<a href="${pageContext.servletContext.contextPath}/detailsVente?id_enchere=${enchere.getNo_enchere()}"
												class="btn btn-primary">En savoir plus</a>
											</div>
    									</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<jsp:include page="foot.jsp" />
</body>
</html>