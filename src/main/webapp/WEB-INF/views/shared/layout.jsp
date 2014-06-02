<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:set var="titleKey">
        <tiles:insertAttribute name="title" ignore="true" />
    </c:set>
    <title><spring:message code="${titleKey}" /></title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/"><spring:message code="app.brand" /></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/?lang=en">En</a></li>
                <li><a href="/?lang=ru">Ru</a></li>
            </ul>
        </div>
    </div>
</nav>

    <div class="container">
        <tiles:insertAttribute name="body" />
    </div>
    <script src="http://yandex.st/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://yandex.st/jquery/form/3.14/jquery.form.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="/resources/js/app.js"></script>
</body>
</html>