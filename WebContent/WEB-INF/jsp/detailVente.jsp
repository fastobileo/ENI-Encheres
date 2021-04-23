<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<div class="container" style="max-width: 600px;">
		<c:choose>
			<c:when test="${errorMessage!=null}">
				<div class="alert alert-danger text-center" role="alert">${errorMessage }</div>
			</c:when>
			<c:otherwise>
				<h2 class="text-center">Détails de la vente</h2>
				<div class="card text-center">
					<div class="card-body">
						<h3 class="card-title">${enchere.getArticle().getNom_article()}</h3>
						<h6>Description :</h6>
						<p>${enchere.getArticle().getDescription()}</p>
					</div>
					<div class="card-footer text-muted">
						<table class="table">
							<tbody>
								<tr>
									<th scope="row">Catégorie</th>
									<td>${enchere.getArticle().getCategorie().getLibelle()}</td>
								</tr>
								<tr>
									<th scope="row">Meilleure offre</th>
									<td>${enchere.getMontant_enchere()}</td>
								</tr>
								<tr>
									<th scope="row">Mise à prix</th>
									<td>${enchere.getArticle().getPrix_initial()}</td>
								</tr>
								<tr>
									<th scope="row">Fin de l'enchère</th>
									<td>${enchere.getArticle().getDate_fin_encheres()}</td>
								</tr>
								<tr>
									<th scope="row">Retrait</th>
									<td>${enchere.getUtilisateur().getRue()},${enchere.getUtilisateur().getVille()},
										${enchere.getUtilisateur().getCode_postal()}</td>
								</tr>
								<tr>
									<th scope="row">Vendeur</th>
									<td>
										<div class="row">
											<div class="col-8">
												<p>${enchere.getUtilisateur().getPseudo()}</p>
											</div>
											<div class="col-4">
												<a class="btn btn-primary btn-lg btn-floating"
													href="${pageContext.servletContext.contextPath}/AfficherProfil?id=${enchere.getUtilisateur().getId()}"><i
													class="fas fa-chevron-right"></i> </a>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<c:if test="${sessionScope.idUser!=null}">
							<form method="POST">
								<div class="container">
									<div class="row">
										<div class="form-outline mb-4 col-8">
											<input required type="number" name="enchere" id="enchere"
												class="form-control" min="${enchere.getMontant_enchere()}"
												max="1548485485484848484"
												value="${enchere.getMontant_enchere()}" /> <label
												class="form-label" for="enchere">Enchere</label>
										</div>
										<div class="col-4">
											<button type="submit" class="btn btn-block btn-dark">Enchérir</button>
										</div>
									</div>
								</div>
								<input hidden name="idEnchere" value="${enchere.getNo_enchere()}"/>
							</form>
						</c:if>
					</div>
				</div>

			</c:otherwise>
		</c:choose>
	</div>

	<jsp:include page="foot.jsp" />

</body>
</html>