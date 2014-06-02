<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %>

<c:if test="${not empty locale}">
    <fmt:setLocale value="${locale}"/>
</c:if>

<c:if test="${empty orderList}">
    <p><spring:message code="order.page.orderListIsEmpty"/></p>
</c:if>

<c:if test="${not empty orderList}">

<table class="table table-hover">
        <thead>
        <tr>
            <th><spring:message code="order.num.label"/></th>
            <th><spring:message code="order.date.label"/></th>
            <th><spring:message code="order.name.label"/></th>
            <th><spring:message code="order.publish.label"/></th>
            <th><spring:message code="order.desc.label"/></th>
            <th><spring:message code="order.scanFile.label"/></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="item" items="${orderList}">
            <tr>
                <td>${item.num}</td>
                <td><fmt:formatDate value="${item.date}" timeStyle="medium"/></td>
                <td>${item.name}</td>
                <td>
                    <c:if test="${item.publish}">
                    <input type="checkbox" checked disabled/>
                    </c:if>
                    <c:if test="${not item.publish}">
                    <input type="checkbox" onclick="publish(this, ${item.id});"/>
                    </c:if>
                <td>${item.desc}</td>
                <td><a href="/letter/download/${item.id}" title="<spring:message code="order.scanFile.download"/>">${item.ext}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>
