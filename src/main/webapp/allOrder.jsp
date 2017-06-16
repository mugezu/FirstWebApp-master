<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 14.06.2017
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ваши заказы</title>
</head>
<body>
<c:forEach var="order" items="${orders}">
    <c:if test="${id_order != order.idOrder}">
        <c:if test="${summ>0 }">
            <b2>Сумма заказа: ${summ}</b2>
            <br/>
        </c:if>
        <c:set var="summ" value="${0}"/>
        <b2>================================</b2>
        <br/>
        <b2>Код заказа: ${order.idOrder} Дата: ${order.date}</b2>
        <br/>
        <b2>Список товаров</b2>
        <br/>
    </c:if>
    <b2>${order.productdbByIdProduct.name} Цена: ${order.productdbByIdProduct.price}
        Количество: ${order.countProduct} </b2>
    <c:set var="summ" value="${summ+order.productdbByIdProduct.price*order.countProduct}"/>
    <br/>
    <c:set var="id_order" value="${order.idOrder}"/>
</c:forEach>
<b2>Сумма заказа: ${summ}</b2>
<br/>
<b2>================================</b2>
</body>
</html>
