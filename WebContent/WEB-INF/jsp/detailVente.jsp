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

				<table class="table">

					<tbody>
						<tr>
							<th scope="row"></th>
							<td>${article.getNom_article()}</td>
						</tr>
						<tr>
							<th scope="row">Description</th>
							<td>${article.getDescription()}</td>
						</tr>
						<tr>
							<th scope="row">Catégorie</th>
							<td>${article.getCategorie()}</td>
						</tr>
						<tr>
							<th scope="row">Meilleure offre</th>
							<td>${enchere.getMontant_enchere()}</td>
						</tr>
						<tr>
							<th scope="row">Mise à prix</th>
							<td>${article.getPrix_initial()}</td>
						</tr>
						<tr>
							<th scope="row">Fin de l'enchère</th>
							<td>${article.getDate_fin_encheres()}</td>
						</tr>
						<tr>
							<th scope="row">Retrait</th>
							<td>${retrait.getRue()},${retrait.getCode_postal()},
								${retrait.getVille()}</td>
						</tr>
						<tr>
							<th scope="row">Vendeur</th>
							<td>${enchere.getNo_utilisateur()}</td>
						</tr>
					</tbody>
				</table>
				<c:if test="${user.getId()==sessionScope.idUser}">
					<a href="#" class="btn btn-block btn-dark">Enchérir</a>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>

	<jsp:include page="foot.jsp" />

</body>
</html>