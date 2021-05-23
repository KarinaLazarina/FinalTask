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

<jsp:useBean id="specializationDAO" class="com.epam.FinalTask.db.dao.SpecializationDAO" scope="session"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<c:choose>
    <c:when test="${addRole == 'admin' }">
        <h1 class="display-3">Add new admin</h1>
    </c:when>
    <c:when test="${addRole == 'nurse' }">
        <h1 class="display-3">Add new nurse</h1>
    </c:when>
    <c:when test="${addRole == 'doctor' }">
        <h1 class="display-3">Add new doctor</h1>
    </c:when>
</c:choose>


<form action="controller" method="post" class="d-flex align-items-center flex-column justify-content-center">
    <div class="form-group">
        <input type="hidden" name="command" value="addUser"/>
    </div>
    <div class="form-group">
        <label for="first_name">First Name</label>
        <input type="text" class="form-control" id="first_name" placeholder="First Name" name="first_name">
    </div>
    <div class="form-group">
        <label for="last_name">Last Name</label>
        <input type="text" class="form-control" id="last_name" placeholder="Last Name" name="last_name">
    </div>
    <div class="form-group">
        <label for="age">Age</label>
        <input type="text" class="form-control" id="age" placeholder="Age" name="age">
    </div>
    <div class="form-group">
        <label for="exampleInputEmail1">Login</label>
        <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Enter login" name="login">
        <%--            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password">
    </div>
    <c:choose>
        <c:when test="${addRole == 'admin' }">
            <div class="form-group">
                <input type="hidden" name="role_id" value="1"/>
            </div>
        </c:when>
        <c:when test="${addRole == 'nurse' }">
            <div class="form-group">
                <input type="hidden" name="role_id" value="3"/>
            </div>
            <div class="form-group">
                <input type="hidden" name="specialization_id"/>
            </div>
        </c:when>
        <c:when test="${addRole == 'doctor' }">
            <div class="form-group">
                <input type="hidden" name="role_id" value="2"/>
            </div>
            <%--            <div class="form-group">--%>
            <%--                <label for="specialization">Specialization</label>--%>
            <%--                <input type="text" class="form-control" id="specialization" placeholder="Specialization"--%>
            <%--                       name="specialization">--%>
            <%--            </div>--%>
            <div class="form-group">
                <label for="specialization">Specialization</label>
                <select class="form-control" id="specialization" name="specialization">
                    <c:forEach var="specialization" items="${specializationDAO.specializations}">
                        <option>${specialization.value}</option>
                    </c:forEach>
                </select>
            </div>
        </c:when>
    </c:choose>
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
