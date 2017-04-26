<%@ page import="Entities.User" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<spring:url value="/resources/css/styles.css" var="mainCss"/>
<spring:url value="/resources/scripts/angular/ngDialog.min.css" var="dialogCss"/>
<spring:url value="/resources/scripts/angular/ngDialog-theme-default.min.css" var="dialogminCss"/>
<spring:url value="/resources/scripts/angular/angular.min.js" var="angularMain"/>
<spring:url value="/resources/scripts/script.js" var="scriptMain"/>
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
      <script src="http://code.jquery.com/jquery-latest.js"></script>
      <script src="${scriptMain}"></script>
      <link rel="stylesheet" href="${mainCss}">
      <link rel="stylesheet" href="${dialogCss}">
      <link rel="stylesheet" href="${dialogminCss}">
    <title>Angular Main Page</title>
  </head>
  <body ng-controller="IndexController">
    <div ng-view>Loading...</div>
  </body>
</html>
