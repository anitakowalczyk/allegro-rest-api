<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>allegro rest api - email</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h3 class="allegroTitle">allegro rest api</h3>
        </div>
        <div class="mainContainer">
            <h3 class="title">Send email</h3>
            <div class="form">
                <form:form method="POST" action="/email/sent" modelAttribute="email">
                    <table>
                        <tr>
                            <td class="label"><form:label path="to">to</form:label></td>
                            <td><form:input class="input" path="to"/></td>
                        </tr>
                        <tr>
                            <td class="label"><form:label path="subject">subject</form:label></td>
                            <td><form:input class="input" path="subject"/></td>
                        </tr>
                        <tr>
                            <td class="label"><form:label path="content">content</form:label></td>
                            <td><form:input  class="input" path="content"/></td>
                        </tr>
                        <tr style="height: 15px"></tr>
                        <tr>
                            <td></td>
                            <td style="float: right; margin-right: 20px"><input type="submit" class="button" value="Send"/></td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>