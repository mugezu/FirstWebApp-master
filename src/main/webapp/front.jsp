<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Front</title>
</head>
<body>
<h5>${massage}</h5>
<h1>Entry</h1>
<form action="login.do" method="post" autocomplete="on">
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
    <input type="submit" value="Entry">
</form>

<br/>
<a href="/registration.jsp">Зареестироваться</a>
<br/>
<b2/>
Сессия: вы были у нас на сайте ${counterSession} раз
<br/>
<b2/>
Куки: вы были у нас на сайте ${counterCookie} раз
</body>
</html>