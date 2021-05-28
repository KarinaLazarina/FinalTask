<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="/WEB-INF/jspf/header.jspf" %>
<jsp:useBean id="userDAO" class="com.epam.FinalTask.db.dao.UserDao" scope="session"/>

<h1 class="display-3">Add new patient</h1>


<c:if test="${userAdded != null}">
    <p>${userAdded}</p>
</c:if>

<form action="controller" method="post" class="d-flex align-items-center flex-column justify-content-center">
    <div class="form-group">
        <input type="hidden" name="command" value="addUser"/>
    </div>
    <div class="form-group">
        <input type="hidden" name="status" value="awaiting appointment"/>
    </div>
    <div class="form-group">
        <label for="first_name">First Name</label>
        <input type="text" class="form-control" id="first_name" placeholder="First Name" name="first_name">
        <c:if test="${errors.first_name_error != null}">
            <small class="form-text text-muted">${errors.first_name_error}</small>
        </c:if>
    </div>
    <div class="form-group">
        <label for="last_name">Last Name</label>
        <input type="text" class="form-control" id="last_name" placeholder="Last Name" name="last_name">
        <c:if test="${errors.last_name_error != null}">
            <small class="form-text text-muted">${errors.last_name_error}</small>
        </c:if>
    </div>
    <div class="form-group">
        <label for="date-input">Date of birth</label>
        <input class="form-control" type="date" id="date-input" name="date_of_birth">
        <c:if test="${errors.date_error != null}">
            <small class="form-text text-muted">${errors.date_error}</small>
        </c:if>
    </div>
    <div class="form-group">
        <label for="doctor">Doctor</label>
        <select class="form-control" id="doctor" name="doctor_id">
            <c:forEach var="doctor" items="${userDAO.doctors}">
                <option value="${doctor.id}">${doctor.first_name} ${doctor.last_name}(${specializations[doctor.specialization_id]})</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <input type="hidden" name="role_id" value="patient"/>
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
</form>
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
