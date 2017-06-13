<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 22.11.2016
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Корзина</title>
</head>
<body>
<c:set var="total" value="${0}"/>
<c:forEach var="p" items="${basketProducts}">
    <c:set var="total" value="${total + p.value}"/>
</c:forEach>
<c:if test="${total>0}">
<b2>
    Количество товаров в корзине: ${total}
</b2>
</c:if>
<c:forEach var="product" items="${basketProducts}">
    <li>
        <b2>Продукт</b2>
        <br/>
        <b2>
            Наименование: ${product.key.name} Количество: ${product.value}
            Суммарная стоимость: ${product.value*product.key.price}
        </b2>
    </li>
</c:forEach>
<c:if test="${total>0}">
    <b2>
        У вас в корзине покупок на сумму: ${totalMoney}
    </b2>
</c:if>
<br/>
<a href="/buyOrder.html">Оплатить заказ</a>
</body>
</html>
