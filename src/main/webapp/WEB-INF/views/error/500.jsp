<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title" value="error.page.title"/>
    <tiles:putAttribute name="body">
        <h2><spring:message code="error.page.500"/></h2>
        <h2><a href="/"><spring:message code="error.page.toMain"/></a></h2>
    </tiles:putAttribute>
</tiles:insertDefinition>