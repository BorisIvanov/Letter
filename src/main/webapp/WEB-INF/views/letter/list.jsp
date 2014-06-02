<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title" value="order.page.title"/>

    <tiles:putAttribute name="body">
        <div id="page-error" class="row hidden">
            <p class="bg-danger">
                <spring:message code="page.error"/>
            </p>
        </div>
        <div id="page-success" class="row hidden">
            <p class="bg-success">
                <spring:message code="page.success"/>
            </p>
        </div>
        <div class="row">
            <form id="ajax-form" class="form-horizontal" role="form" method="post" action="/letter/upload"
                  enctype="multipart/form-data">

                <div class="form-group">
                    <label for="num" class="col-sm-2 control-label">
                        <spring:message code="order.num.label"/>
                    </label>

                    <div class="col-sm-6">
                        <input type="text" size="20" class="form-control input-sm" data-field="num" id="num" name="num">

                        <p class="bg-danger hidden"></p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="dateStr" class="col-sm-2 control-label">
                        <spring:message code="order.date.label"/>
                    </label>

                    <div class="col-sm-6">
                        <input type="date" class="form-control input-sm" id="dateStr" name="dateStr"
                               data-field="dateStr">
                        <span class="help-block">${date_pattern}</span>

                        <p class="bg-danger hidden"></p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">
                        <spring:message code="order.name.label"/>
                    </label>

                    <div class="col-sm-6">
                        <input type="text" class="form-control input-sm" size="250" data-field="name" id="name"
                               name="name">

                        <p class="bg-danger hidden"></p>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" id="publish" name="publish" data-field="publish"> <spring:message
                                    code="order.publish.label"/>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="scanFile" class="col-sm-2 control-label">
                        <spring:message code="order.scanFile.label"/> 5Mb (JPG / PDF)
                    </label>

                    <div class="col-sm-6">
                        <input type="file" accept=".pdf,.jpg" id="scanFile" name="scanFile" data-field="scanFile">

                        <p class="bg-danger hidden"></p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="desc" class="col-sm-2 control-label">
                        <spring:message code="order.desc.label"/>
                    </label>

                    <div class="col-sm-6">
                        <textarea data-field="desc" id="desc" name="desc" class="form-control input-sm"
                                  rows="3"></textarea>
                    </div>
                </div>
            </form>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-2">
                    <button value="button" class="btn btn-primary" onclick="save()">Save</button>
                </div>
            </div>

        </div>

        <div id="order-table">
            <jsp:include page="/WEB-INF/views/letter/_tablePartial.jsp"/>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>
