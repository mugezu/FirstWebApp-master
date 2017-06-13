<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21.11.2016
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Все товары</title>
</head>
<body>
<c:set var="total" value="${0}"/>
<c:forEach var="p" items="${basketProducts}">
    <c:set var="total" value="${total + p.value}"/>
</c:forEach>
<b2>
    Количество товаров в корзине: ${total} на сумму ${totalMoney}
</b2>
<c:forEach var="product" items="${products}">
    <li>
        <b2>Продукт</b2>
        <br/>
        <b2>Наименование: ${product.name} Цена: ${product.price}
            <a href="./buyProduct.html?id=${product.id}">Купить товар</a></b2>
    </li>
</c:forEach>
<a href="./basket.html"> Перейти в корзину</a>
</body>
</html>
