<%--
  Created by IntelliJ IDEA.
  User: kret3
  Date: 13.09.2021
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
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
        <a class="navbar-brand" href="/">
            <img src="${pageContext.request.contextPath}/images/avatar.jpg" width="40" height="40" class="d-inline-block align-top rounded"
                 alt="idź na start">
            Fresh Food Shop
        </a>

        <div class="collapse navbar-collapse" id="mainNavigation">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/register" >Rejestracja</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Logowanie</a>
                </li>
            </ul>
        </div>
    </nav>
</section>
<section style="width: 99%">
    <div class="row">
        <div class="container p-3 my-3 border shadow p-1 mb-1 bg-white rounded">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="${pageContext.request.contextPath}/images/banana.jpg" class="card-img-top" alt="…" height="650">
                        <div class="card-body" style="padding-bottom: 1px">
                            <h5 class="card-title"><em>Banan – jadalny owoc tropikalny, z botanicznego punktu widzenia – jagoda, wytwarzany przez kilka gatunków roślin zielnych z rodzaju banan.</em></h5>
                            <p class="small"><em></em></p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="${pageContext.request.contextPath}/images/grapes.jpg" class="card-img-top" alt="…" height="650">
                        <div class="card-body" style="padding-bottom: 1px">
                            <h5 class="card-title"><em>Winogrono – owoc roślin z rodziny winoroślowatych, głównie winorośli właściwej. Winogrona można jeść na surowo lub można je przetwarzać.</em></h5>
                            <p class="small"><em></em></p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="${pageContext.request.contextPath}/images/potato.jpeg" class="card-img-top" alt="…" height="650">
                        <div class="card-body" style="padding-bottom: 1px">
                            <h5 class="card-title"><em>Ziemniak (Solanum tuberosum L.) – gatunek rośliny należący do rodziny psiankowatych. Nazwa „ziemniak” odnosi się tak do całej rośliny, jak i do jej jadalnych, bogatych w skrobię bulw pędowych[4], z powodu których ten gatunek uprawia się na skalę masową[5]. Roślina wywodzi się z Ameryki Południowej, gdzie zaczęto ją uprawiać już tysiące lat temu.</em></h5>
                            <p class="small"><em></em></p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="${pageContext.request.contextPath}/images/apple.jpg" class="card-img-top" alt="…" height="650">
                        <div class="card-body" style="padding-bottom: 1px">
                            <h5 class="card-title"><em>Jabłko – jadalny, kulisty owoc drzew z rodzaju jabłoń Malus. Jabłka odmian uprawnych o mieszańcowym pochodzeniu, uznawanych za gatunek jabłoń domowa Malus domestica, są istotnym komercyjnie owocem o soczystym i chrupkim miąższu. Są spożywane na surowo, a także po obróbce kulinarnej.</em></h5>
                            <p class="small"><em></em></p>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</section>
<script src="" type="text/javascript"></script>
</body>
</html>
