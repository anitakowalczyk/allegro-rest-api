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
        <div class="mainContainer">
            <h3>User ${userId} - subscriptions</h3>
            <table>
                <c:forEach items="${subscriptions}" var="subscription">
                    <tr>
                        <td><c:out value="${subscription.key}"/>.</td>
                        <td><c:out value="${subscription.value}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>