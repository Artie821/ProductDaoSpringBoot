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
                <li class="nav-item active">
                    <a class="nav-link" href="/user/cart/"><img class="rounded-circle" width="30" height="25"
                                                           style="padding-right: 5px"
                                                           src="${pageContext.request.contextPath}/images/cart.png">Koszyk
                        <span class="badge badge-pill badge-success">${itemsInCart}</span></a>
                </li>
                <li class="nav-item ">
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
            <h3 style="float:left"> Produkty w twoim koszyku: </h3>
        </div>
    </div>
    <div class="row">
        <div class="container ">
            <c:if test="${emptyCart}">
                <div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    Twój koszyk jest pusty przejdź do sekcji <a class="btn btn-outline-success" href="/user/products/">Produkty</a>
                    aby dodać produkty do koszyka i złożyć zamówienie.
                </div>
            </c:if>
            <c:if test="${threeOrMore}">
                <div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    Gratulacje masz w koszyku przynajmniej 3 produkty róznego rodzaju! Naliczamy bonus -5%!!
                </div>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="container p-3 my-3 border shadow p-1 mb-1 bg-white rounded">
            <table id="table" class="table table-striped">
                <thead class="thead-dark">
                <tr>
                    <th>Nazwa</th>
                    <th>Cena jednostkowa (PLN)</th>
                    <th>Ilość</th>
                    <th>Wartość</th>
                    <th>Akcja</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cart}" var="cart">
                    <tr>
                        <td><c:out value="${cart.product.name}"/></td>
                        <td><c:out value="${cart.product.price}"/></td>
                        <td><c:out value="${cart.quantity}"/></td>
                        <td>${cart.product.price * cart.quantity}</td>
                        <td>
                            <form:form action="/user/cart/" method="post" modelAttribute="product">
                                <input hidden name="product" value="${cart.product.id}">
                                <input hidden name="itemsNumber" value="+1">
                                <input type="submit" class="btn btn-outline-success btn-sm" value="↑"></a>
                            </form:form>
                        </td>
                        <td>
                            <form:form action="/user/cart/" method="post" modelAttribute="product">
                                <input hidden name="product" value="${cart.product.id}">
                                <input hidden name="itemsNumber" value="-1">
                                <input type="submit" class="btn btn-outline-danger btn-sm" value="↓"></a>
                            </form:form>
                        </td>
                        <td>
                            <form:form action="/user/cart/removeAll" method="post" modelAttribute="product">
                                <input hidden name="product" value="${cart.product.id}">
                                <input type="submit" class="btn btn-outline-danger btn-sm" value="USUŃ WSZYTSKIE"></a>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <c:if test="${!emptyCart}">
            <div class="container p-3 my-3 border shadow p-1 mb-1 bg-white rounded">
                <h3 style="float:left"> Całkowita wartość koszyka:</h3>
                <h3 style="float: right"><c:if test="${threeOrMore}"><span
                        class="badge badge-pill badge-success">BONUS -5%</span></c:if> ${totalCartValue} PLN </h3>
            </div>
        </c:if>
    </div>
    <div class="row">
        <c:if test="${!emptyCart}">
            <div class="container p-3 my-3 border shadow p-1 mb-1 bg-white rounded">
                <button class="btn btn-success btn-block" data-toggle="modal"
                        data-target="#exampleModal" data-value="${totalCartValue}" data-object="${cart}" >Potwierdź</button>
            </div>
        </c:if>
    </div>
    <form id="formSubmitId" method="post" action="/user/orders/">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="totalValue" value="${totalCartValue}">
        <input type="hidden" value="${cart}">
    </form>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Potwierdzenie zamówienia</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Zamknij">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p> Uwaga! </p>
                    <p> Po zatwierdzeniu zamówienia trafi ono do realizacji!</p>
                    <p> Swoje zamówienia oraz ich status zanjdziesz w sekcji Zamówienia</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Anuluj</button>

                    <a href="" type="button" class="btn btn-outline-success" id="btnname" >Potwierdź</a>

                </div>
            </div>
        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/scripts/app.js" type="text/javascript"></script>
</body>
</html>

