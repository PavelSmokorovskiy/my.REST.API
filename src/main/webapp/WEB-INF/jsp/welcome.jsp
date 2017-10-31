<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta foodName="viewport" content="width=device-width, initial-scale=1">
    <meta foodName="description" content="">
    <meta foodName="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.foodName != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" foodName="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <br>
        <h3>Welcome user: <b>${username}</b> | with roles: <b>${roles}</b>
            <br>
            <br>
            <br>
            <a href="logout">logout</a></h3>

    </c:if>

</div>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>