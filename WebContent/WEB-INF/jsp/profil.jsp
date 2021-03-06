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
				<h2 class="text-center">Informations</h2>

				<table class="table">

					<tbody>
						<tr>
							<th scope="row">Pseudo</th>
							<td>${user.getPseudo()}</td>
						</tr>
						<tr>
							<th scope="row">Nom</th>
							<td>${user.getNom()}</td>
						</tr>
						<tr>
							<th scope="row">Prénom</th>
							<td>${user.getPrenom()}</td>
						</tr>
						<tr>
							<th scope="row">Email</th>
							<td>${user.getEmail()}</td>
						</tr>
						<tr>
							<th scope="row">Téléphone</th>
							<td>${user.getTelephone()}</td>
						</tr>
						<tr>
							<th scope="row">Adresse</th>
							<td>${user.getRue()},${user.getCode_postal()},
								${user.getVille()}</td>
						</tr>
						<tr>
							<th scope="row">Crédits</th>
							<td>${user.getCredit()}</td>
						</tr>
					</tbody>
				</table>
				<c:if test="${user.getId()==sessionScope.idUser}">
					<a href="${pageContext.servletContext.contextPath}/modifierProfil?id=${sessionScope.idUser}" class="btn btn-block btn-dark">Modifier</a>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>

	<jsp:include page="foot.jsp" />

</body>
</html>