<%@ page import="Entities.User" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<spring:url value="/resources/css/styles.css" var="mainCss"/>
<spring:url value="/resources/scripts/angular/ngDialog.min.css" var="dialogCss"/>
<spring:url value="/resources/scripts/angular/ngDialog-theme-default.min.css" var="dialogminCss"/>
<spring:url value="/resources/scripts/angular/angular.min.js" var="angularMain"/>
<spring:url value="/resources/scripts/app.js" var="scriptMain"/>
<spring:url value="/resources/scripts/angular/angular-route.min.js" var="angularRoute"/>
<spring:url value="/resources/scripts/angular/ng-map.min.js" var="map"/>
<spring:url value="/resources/scripts/angular/ngDialog.min.js" var="angularDialog"/>
<html ng-app="NSE">
  <head>
      <base href="/"/>
      <script src="${angularMain}"></script>
      <script src="${angularRoute}"></script>
      <script src="${map}"></script>
      <script src="${angularDialog}"></script>
      <script src="/resources/scripts/angular/angular-cookies.js"></script>
      <script src="/resources/scripts/angular/angular-base64.js"></script>
      <script src="/resources/scripts/angular/jquery-3.2.1/jquery.js"></script>
      <script src="${scriptMain}"></script>


      <script src="/resources/scripts/controllers/index.controller.js"></script>
      <script src="/resources/scripts/controllers/admin.controller.js"></script>
      <script src="/resources/scripts/controllers/news.controller.js"></script>
      <script src="/resources/scripts/controllers/comments.controller.js"></script>
      <script src="/resources/scripts/controllers/login.controller.js"></script>
      <script src="/resources/scripts/controllers/registration.controller.js"></script>
      <script src="/resources/scripts/controllers/teacher.controller.js"></script>
      <script src="/resources/scripts/controllers/student.controller.js"></script>


      <script src = "/resources/scripts/services/index.service.js"></script>
      <script src = "/resources/scripts/services/request.service.js"></script>
      <script src = "/resources/scripts/services/auth.service.js"></script>
      <script src = "/resources/scripts/services/session.service.js"></script>

      <script src = "/resources/scripts/constants/auth.constant.js"></script>
      <script src = "/resources/scripts/constants/roles.constant.js"></script>


      <link rel="stylesheet" href="${mainCss}">
      <link rel="stylesheet" href="${dialogCss}">
      <link rel="stylesheet" href="${dialogminCss}">
      <link rel="shortcut icon" href="/resources/images/logo_icon.png" type="image/png">
    <title>Языковые курсы</title>

  </head>

  <body ng-controller="IndexController">
    <div ng-view>Loading...</div>
  </body>

</html>
