<%@ page import="Database_layer.User" %><%--
  Created by IntelliJ IDEA.
  User: alexb
  Date: 14-Mar-17
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url value="/resources/css/styles.css" var="mainCss"/>
<spring:url value="/resources/scripts/angular/angular.min.js" var="angularMain"/>
<spring:url value="/resources/scripts/script.js" var="scriptMain"/>
<spring:url value="/resources/scripts/angular/angular-route.min.js" var="angularRoute"/>
<html>
  <head>
      <base href="/"/>
      <script src="${angularMain}"></script>
      <script src="${angularRoute}"></script>
      <script src="http://code.jquery.com/jquery-latest.js"></script>
      <script src="${scriptMain}"></script>
      <link rel="stylesheet" href="${mainCss}">
    <title>Angular Main Page</title>
  </head>
  <body ng-controller="IndexController">
    <div ng-view>Loading...</div>
  </body>
</html>