<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
${users.toString()}
Users List:
  <c:forEach var="user" items="${users}">
      <h1>User: ${user.login}</h1>
  </c:forEach>
</body>
</html>
