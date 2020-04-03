<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h3 class="title">Update subscription</h3>
            <div class="error">
                <c:if test="${not empty error}">
                    <c:out value="${error}"/>
                </c:if>
            </div>
            <div class="form">
                <form:form method="POST" action="/subscriptions/updated" modelAttribute="subscription">
                    <table>
                        <tr>
                            <td class="label"><form:label path="id">id</form:label></td>
                            <td><form:input class="input" path="id" value="${oldSubscription.id}" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td class="label"><form:label path="name">name</form:label></td>
                            <td><form:input class="input" path="name" value="${oldSubscription.name}"/></td>
                        </tr>
                        <tr>
                            <td class="label"><form:label path="categoryId">categoryId</form:label></td>
                            <td><form:input class="input" path="categoryId" id="categoryId" value="${oldSubscription.categoryId}"/></td>
                        </tr>
                        <tr>
                            <td class="label"><form:label path="phrase">phrase</form:label></td>
                            <td><form:input class="input" path="phrase" id="phrase" value="${oldSubscription.phrase}"/></td>
                        </tr>
                        <tr>
                            <td class="label"><form:label path="sellerId">sellerId</form:label></td>
                            <td><form:input class="input" path="sellerId" id="sellerId" value="${oldSubscription.sellerId}"/></td>
                        </tr>
                        <tr>
                            <td class="label"><form:label path="active">active</form:label></td>
                            <td><form:input class="input" path="active"
                                            value="${previousSubscription != null ? previousSubscription.active : oldSubscription.active}"/></td>
                        </tr>
                        <tr style="height: 15px"></tr>
                        <tr>
                            <td></td>
                            <td style="float: right; margin-right: 20px"><input type="submit" class="button"  value="Submit"/></td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>