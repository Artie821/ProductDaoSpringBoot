<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
            integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Fresh Food Shop</title>
</head>
<body>
<section>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#mainNavigation" aria-controls="mainNavigation" aria-expanded="false"
                aria-label="Pokaż lub ukryj nawigację">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="/admin/dashboard/">
            <img src="${pageContext.request.contextPath}/images/avatar.jpg" width="40" height="40"
                 class="d-inline-block align-top rounded"
                 alt="idź na start">
            Fresh Food Shop
        </a>

        <div class="collapse navbar-collapse" id="mainNavigation">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                </li>
            </ul>
            <ul class="navbar-nav ml-sm-5 mt-2 mt-md-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="userMenu" data-toggle="dropdown"
                       aria-haspopup="true"
                       aria-expanded="false">
                        <img class="rounded-circle" width="20" height="20"
                             src="${pageContext.request.contextPath}/images/user.png" alt="USER"> ${username}</a>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userMenu">
                        <a class="dropdown-item" href="#" >Ustawienia</a>
                        <div class="dropdown-divider"></div>
                        <sec:authorize access="isAuthenticated()">
                            <form action="<c:url value="/perform_logout"/>" method="post">
                                <input type="submit" class="dropdown-item" value="Wyloguj">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </sec:authorize>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</section>
<section style="width: 100%">
    <div class="row">
        <div class="container p-3 my-3 border shadow p-1 mb-1 bg-white rounded">
            <h3 style="float:left">
                <a href="/admin/dashboard/" class="btn btn-outline-success  btn-large" >Zarządzaj kategoriami</a>
                <a href="/admin/productsAdmin/" class="btn btn-outline-success active btn--large">Zarządzaj produktami</a>
                <a href="/admin/ordersAdmin/" class="btn btn-outline-success  btn-large">Zarządzaj zamówieniami</a>
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="container p-3 my-3 border shadow p-1 mb-1 bg-white rounded">
            <table id="table" class="table table-striped">
                <thead class="thead-dark">
                <tr>
                    <th>Nazwa</th>
                    <th>Cena (PLN)</th>
                    <th>Kategoria</th>
                    <th>Opis</th>
                    <th>Status</th>
                    <th>Akcja</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product" >
                    <tr>
                        <td><c:out value="${product.name}"/></td>
                        <td><c:out value="${product.price}"/></td>
                        <td><c:out value="${product.productCategory.categoryName}"/></td>
                        <td><c:out value="${product.description}"/></td>
                        <td>
                            <c:if test="${product.active}">
                                <span class="badge badge-pill badge-success">AKTYWNE</span>
                            </c:if>
                            <c:if test="${!product.active}">
                                <span class="badge badge-pill badge-warning">NIE AKTYWNE</span>
                            </c:if>
                        </td>
                        <td><a href="/admin/editProductAdmin/${product.id}" class="btn btn-outline-success btn-sm" value="">EDYTUJ</a>
                            <a href="/admin/removeProductAdmin/${product.id}" class="btn btn-outline-dark btn-sm" value="">ZMIEŃ STATUS</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="container p-3 my-3 border shadow p-1 mb-1 bg-white rounded">
            <a href="/admin/addProductAdmin/"  class="btn btn-outline-success btn-lg" >DODAJ PRODUKT</a>
        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/scripts/app.js" type="text/javascript"></script>
</body>
</html>