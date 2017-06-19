<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 17.06.2017
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>${massage}</h3>
<br/>
<h1>Зареестрироваться</h1>
<br/>
<form action="registration.html" method="post" autocomplete="on">
    <label>
        Login
        <input type="text" name="login" required>
    </label>
    <br>
    <label>
        Password
        <input type="password" name="password" required>
    </label>
    <br>
    <label>
        Email
        <input type="text" name="email" required>
    </label>
    <br>
    <input type="submit" value="Entry">
</form>
</body>
</html>
