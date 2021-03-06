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
        <a class="navbar-brand" href="/user/products/">
            <img src="${pageContext.request.contextPath}/images/avatar.jpg" width="40" height="40"
                 class="d-inline-block align-top rounded"
                 alt="idź na start">
            Fresh Food Shop
        </a>

        <div class="collapse navbar-collapse" id="mainNavigation">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <a class="nav-link" href="/user/products/">Produkty</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/cart/"><img class="rounded-circle" width="30" height="25"
                                                           style="padding-right: 5px"
                                                           src="${pageContext.request.contextPath}/images/cart.png">Koszyk
                        <span class="badge badge-pill badge-success">${itemsInCart}</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/user/orders/">Zamówienia</a>
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
                        <a class="dropdown-item" href="#" hidden>Ustawienia</a>
                        <a class="dropdown-item" href="/user/cart/">Koszyk <span
                                class="badge badge-pill badge-success">${itemsInCart}</span></a>
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
            <div class="card text-white bg-secondary ">
                <h5 class="card-header">Opis postępowania:</h5>
                <div class="card-body">
                    <h5 class="card-title">Gratulację twoje zamówienie zostało przekazane do realizacji!! </h5>
                    <br>
                    <h5 class="card-title">By założyć kolejne zamówienie przejdź do sekcji Produkty </h5>
                    <a href="/user/products/" type="button" class="btn btn-primary btn-lg btn-block">Przejdź do sekcji PRODUKTY</a>
                    <br>
                    <h5 class="card-title">By podejrzeć swoje zamówniena i ich status przejdź do sekcji ZAMÓWIENIA </h5>
                    <a href="/user/orders/" type="button" class="btn btn-primary btn-lg btn-block">Przejdź do sekcji ZAMÓWIENIA</a>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/scripts/app.js" type="text/javascript"></script>
</body>
</html>