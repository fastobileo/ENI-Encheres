<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
	rel="stylesheet" />
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet" />
<!-- MDB -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.3.0/mdb.min.css"
	rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-mdb-toggle="collapse" data-mdb-target="#navbarSupportedContent"
				aria-controls="navbarTogglerDemo01" aria-expanded="false"
				aria-label="Toggle navigation">
				<i class="fas fa-bars"></i>
			</button>
			<a class="navbar-brand" href="${pageContext.servletContext.contextPath}/"><i class="fas fa-home"></i></a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">${param.title }</a></li>
				</ul>
				<c:choose>
					<c:when test="${sessionScope.idUser==null}">
						<ul class="navbar-nav w-auto mb-2 mb-lg-0">
							<li class="nav-item me-3 me-lg-0 dropdown w-auto"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-mdb-toggle="dropdown" aria-expanded="false">
									<i class="fas fa-user text-danger"></i>
							</a>
								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/connection">Connexion</a></li>
								</ul></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="navbar-nav w-auto mb-2 mb-lg-0">
							<li class="nav-item me-3 me-lg-0 dropdown w-auto"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-mdb-toggle="dropdown" aria-expanded="false">
									<i class="fas fa-user text-success"></i>
							</a>
								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/AfficherProfil?id=${sessionScope.idUser}">Voir son profil</a></li>
									<li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/modifierProfil?id=${sessionScope.idUser}">Modifier son
											profil</a></li>
									<li><hr class="dropdown-divider" /></li>
									<li><a class="dropdown-item"
										href="${pageContext.servletContext.contextPath}/Deconnexion">Deconnexion</a></li>
								</ul></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
	<br>
</body>
</html>