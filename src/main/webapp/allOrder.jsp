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
    <li>
    <b2>Код заказа: ${order.id_order} </b2>
    <br/>
    <b2>Имя получателя: ${order.name_buyer} Цена:</b2>
    <br/>
    <b2>Список товаров</b2>
    <c:forEach var="product" items="${order.list_product}">
        <li>
            <c:set var="key" value="${product.key}"/>
            <b2>Название <c:out${key.name} Цена ${key.price} Количество: ${product.value}</b2>
            <br/>

        </li>
    </c:forEach>
    <b2>Сумма заказа: ${order.summ_order} Цена:</b2>
    </li>
</c:forEach>
</body>
</html>
