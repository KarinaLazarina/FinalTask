<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<jsp:useBean id="specializationDAO" class="com.epam.FinalTask.db.dao.SpecializationDAO" scope="session"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<c:choose>
    <c:when test="${userName == 'nurse'}">
        <h1>Nurses:</h1>
    </c:when>
    <c:when test="${userName == 'doctor'}">
        <h1>Doctors:</h1>
    </c:when>
</c:choose>
    <c:forEach var="user" items="${users}">
        <div class="card mb-3" style="width: 18rem; display: inline-block;">
                <%--        <img class="card-img-top" src=".../100px180/?text=Image cap" alt="Card image cap">--%>
            <div class="card-body">
                <h5 class="card-title">${user.first_name} ${user.last_name}</h5>
                    <%--            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>--%>
            </div>
            <ul class="list-group list-group-flush">
                <c:if test="${userName == 'doctor'}">
                    <li class="list-group-item">Specialization: ${specializationDAO.getSpecializationById(user.specialization_id)}</li>
                </c:if>
                <li class="list-group-item">Age: ${user.age}</li>
                <li class="list-group-item">Login: ${user.login}</li>
            </ul>
            <div class="card-body">
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
            </div>
        </div>

    </c:forEach>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
