<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Database_layer.User" %>
<html>
<head>
    <title>Hello</title>
    <spring:url value="/resources/css/styles.css" var="mainCss"/>
    <link rel="stylesheet" href="${mainCss}">
</head>
<body>
    <h1>Users List:</h1>
    <table>
        <tr>
            <th>№</th>
            <th>Login</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
      <c:forEach var="user" items="${users}">
          <tr>
              <td><c:out value="${user.user_id}"/></td>
              <td><c:out value="${user.login}"/></td>
              <td><c:out value="${user.email}"/></td>
              <td><c:out value="${user.role}"/></td>
          </tr>
      </c:forEach>
    </table>
</body>
</html>
