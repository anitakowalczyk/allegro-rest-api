<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>allegro rest api</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h3 class="allegroTitle">allegro rest api</h3>
        </div>
        <div class="mainContainer">
            <h3 class="title">Add new subscription</h3>
            <div class="error">
                <c:if test="${not empty error}">
                    <c:out value="${error}"/>
                </c:if>
            </div>
            <div class="form">
                <form:form method="POST" action="/subscriptions/added" modelAttribute="subscription">
                    <table>
                        <tr>
                            <td class="label"><form:label  path="name">name</form:label></td>
                            <td><form:input class="input" path="name" value="${oldSubscription != null ? oldSubscription.name : ''}"/></td>
                        </tr>
                        <tr>
                            <td class="label"><form:label  path="categoryId">categoryId</form:label></td>
                            <td><form:input class="input" path="categoryId" id="categoryId"
                                            value="${oldSubscription != null ? oldSubscription.categoryId : ''}"/></td>
                        </tr>
                        <tr>
                            <td class="label"><form:label path="phrase">phrase</form:label></td>
                            <td><form:input class="input" path="phrase" id="phrase"
                                            value="${oldSubscription != null ? oldSubscription.phrase : ''}"/></td>
                        </tr>
                        <tr>
                            <td class="label"><form:label  path="sellerId">sellerId</form:label></td>
                            <td><form:input class="input" path="sellerId" id="sellerId"
                                            value="${oldSubscription != null ? oldSubscription.sellerId : ''}"/></td>
                        <tr style="height: 15px"></tr>
                        <tr>
                            <td></td>
                            <td style="float: right; margin-right: 20px"><input class="button" type="submit" value="Submit"/></td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>