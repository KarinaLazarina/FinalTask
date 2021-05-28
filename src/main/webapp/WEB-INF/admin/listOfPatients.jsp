<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<jsp:useBean id="userDAO" class="com.epam.FinalTask.db.dao.UserDao" scope="session"/>

<h1>Patients:</h1>
<c:forEach var="patient" items="${patients}">
    <div class="card mb-3" style="width: 18rem; display: inline-block;">
            <%--        <img class="card-img-top" src=".../100px180/?text=Image cap" alt="Card image cap">--%>
        <div class="card-body">
            <h5 class="card-title">${patient.first_name} ${patient.last_name}</h5>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Date of birth: ${patient.date_of_birth}</li>
            <li class="list-group-item">Doctor: ${userDAO.findUserNameById(patient.doctor_id)}</li>
            <li class="list-group-item">Status: ${patient.status}</li>
        </ul>
        <div class="card-body">
            <a href="/controller?command=getEditForm&editRole=patient&userId=${patient.id}" class="card-link">Edit</a>
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
