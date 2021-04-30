<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vendre un Article</title>
</head>
<body>
	<jsp:include page="head.jsp">
		<jsp:param name="title" value="Vendre un article" />
	</jsp:include>
	<!-- Mon image -->

	<div class="container" style="max-width: 600px;">
		<c:choose>
			<c:when test="${errorMessage!=null}">
				<div class="alert alert-danger text-center" role="alert">${errorMessage }</div>
			</c:when>
			<c:otherwise>
				<form method="POST"
					action="${pageContext.servletContext.contextPath}/VendreArticle">
					<!-- title input -->
					<div class="form-outline mb-4">
						<input type="text" id="form7Example1" class="form-control" /> <label
							class="form-label" for="form7Example1">Titre de l'article</label>
					</div>
					<!-- Message input -->
					<div class="form-outline mb-4">
						<textarea class="form-control" id="form6Example7" rows="4"></textarea>
						<label class="form-label" for="form6Example7">Description</label>
					</div>
					<div class="dropdown">
						<h6>catégories :</h6>
						<select class="form-select" aria-label="Default select example">
							<c:forEach var="l" items="${listeCategorie}">
								<option selected value="${ l.getNoCategorie() }">${ l.getLibelle() }</option>
							</c:forEach>
						</select>
					</div>
					<br>
					<!-- <div class="form-outline mb-4">
						<h6>Ajouter une image :</h6>
						<input type="file" id="file" accept="image/png, image/jpeg">
					</div>
					<div class="float-right" style="max-width: 100px;">
						<img src="https://mdbootstrap.com/img/new/standard/city/043.jpg"
							class="img-fluid hover-shadow" alt="" />
					</div> -->
					<h6>Mise à prix :</h6>
					<div class="form-outline mb-4">
						<input type="number" min="0" id="form7Example1"
							class="form-control" />
					</div>

					<h6>Début de l'enchère :</h6>
					<div class="form-outline mb-4">
						<input type="date" id="form7Example1" class="form-control" />
					</div>

					<h6>Fin de l'enchère :</h6>
					<div class="form-outline mb-4">
						<input type="date" id="form7Example1" class="form-control" />
					</div>
					<h6>Retrait :</h6>
					<section class="border p-4 mb-4">
						<div class="form-outline">
							<input type="text" id="formControlDefault" class="form-control"
								value="${user.getRue()}" /> <label class="form-label"
								for="formControlDefault">Rue :</label>
						</div>
						<br>
						<div class="form-outline">
							<input type="text" id="formControlDefault" class="form-control"
								value="${user.getCode_postal()}" /> <label class="form-label"
								for="formControlDefault">Code postal :</label>
						</div>
						<br>
						<div class="form-outline">
							<input type="text" value="${user.getVille()}"
								id="formControlDefault" class="form-control" /> <label
								class="form-label" for="formControlDefault">Ville :</label>
						</div>
					</section>
					<br>
					<div class="d-grid gap-2 d-md-block">
						<button class="btn-block btn-primary" type="submit">Enregistrer</button>
						<button class="btn-block btn-dark" type="button">Annuler</button>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</body>
<jsp:include page="foot.jsp" />
</body>
</html>