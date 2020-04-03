<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <div class="mainContainer" style="padding-bottom: 40px">
            <h3 class="title">Details of the subscription </h3>
            <div class="error"><%@ page errorPage="error.jsp" %></div>
            <table>
                <tr class="trsubs">
                    <td class="tdsubs">id</td>
                    <td class="">${subscription.id}</td>
                </tr>
                <tr class="trsubs">
                    <td class="tdsubs">name</td>
                    <td class="">${subscription.name}</td>
                </tr>
                <tr class="trsubs">
                    <td class="tdsubs">user id</td>
                    <td class="">${subscription.user.id}</td>
                </tr>
                <tr class="trsubs">
                    <td class="tdsubs">category id</td>
                    <td class=""><c:out value="${empty subscription.categoryId ? 'null' : subscription.categoryId}"/></td>
                </tr>
                <tr class="trsubs">
                    <td class="tdsubs">phrase</td>
                    <td class=""><c:out value="${empty subscription.phrase ? 'null' : subscription.phrase}"/></td>
                </tr>
                <tr class="trsubs">
                    <td class="tdsubs">sellerId</td>
                    <td class=""><c:out value="${empty subscription.sellerId ? 'null' : subscription.sellerId}"/></td>
                </tr>
                <tr class="trsubs">
                    <td class="tdsubs">active</td>
                    <td class="">${subscription.active}</td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>