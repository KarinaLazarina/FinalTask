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

<%--<c:choose>--%>
<%--    <c:when test="${addRole == 'admin' }">--%>
<%--        <h1 class="display-3">Add new admin</h1>--%>
<%--    </c:when>--%>
<%--    <c:when test="${addRole == 'nurse' }">--%>
<%--        <h1 class="display-3">Add new nurse</h1>--%>
<%--    </c:when>--%>
<%--    <c:when test="${addRole == 'doctor' }">--%>
<%--        <h1 class="display-3">Add new doctor</h1>--%>
<%--    </c:when>--%>
<%--</c:choose>--%>

<form action="controller" method="post" class="d-flex align-items-center flex-column justify-content-center">
    <div class="form-group">
        <input type="hidden" name="command" value="editUser"/>
    </div>
    <div class="form-group">
        <input type="hidden" name="id" value="${user.id}"/>
    </div>
    <div class="form-group">
        <label for="first_name">First Name</label>
        <input type="text" class="form-control" id="first_name" value="${user.first_name}" name="first_name">
        <c:if test="${errors.first_name_error != null}">
            <small class="form-text text-muted">${errors.first_name_error}</small>
        </c:if>
    </div>
    <div class="form-group">
        <label for="last_name">Last Name</label>
        <input type="text" class="form-control" id="last_name" value="${user.last_name}" name="last_name">
        <c:if test="${errors.last_name_error != null}">
            <small class="form-text text-muted">${errors.last_name_error}</small>
        </c:if>
    </div>
    <div class="form-group">
        <label for="age">Age</label>
        <input type="text" class="form-control" id="age" value="${user.age}" name="age">
        <c:if test="${errors.age_error != null}">
            <small class="form-text text-muted">${errors.age_error}</small>
        </c:if>
    </div>
    <div class="form-group">
        <label for="exampleInputEmail1">Login</label>
        <input type="text" class="form-control" id="exampleInputEmail1" value="${user.login}" name="login">
        <c:if test="${errors.login_error != null}">
            <small class="form-text text-muted">${errors.login_error}</small>
        </c:if>
    </div>
    <%--    <div class="form-group">--%>
    <%--        <label for="exampleInputPassword1">Password</label>--%>
    <%--        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password">--%>
    <%--        <c:if test="${errors.password_error != null}">--%>
    <%--            <small class="form-text text-muted">${errors.password_error}</small>--%>
    <%--        </c:if>--%>
    <%--    </div>--%>
    <c:choose>
        <c:when test="${editRole == 'admin' }">
            <div class="form-group">
                <input type="hidden" name="role_id" value="1"/>
            </div>
        </c:when>
        <c:when test="${editRole == 'nurse' }">
            <div class="form-group">
                <input type="hidden" name="role_id" value="3"/>
            </div>
            <div class="form-group">
                <input type="hidden" name="specialization_id"/>
            </div>
        </c:when>
        <c:when test="${editRole == 'doctor' }">
            <div class="form-group">
                <input type="hidden" name="role_id" value="2"/>
            </div>
            <div class="form-group">
                <label for="specialization">Specialization</label>
<%--                <select class="form-control" id="specialization" name="specialization">--%>
                    <c:forEach var="specialization" items="${specializations}">
                        <c:choose>
                            <c:when test="${user.specialization_id == specialization.key }">
                                <input disabled type="text" class="form-control" id="specialization" value="${specialization.value}" name="login">
                                <%--                                <option selected> ${specialization.value} </option>--%>
                            </c:when>
<%--                            <c:when test="${user.specialization_id != specialization.key  }">--%>
<%--                                <option>${specialization.value}</option>--%>
<%--                            </c:when>--%>
                        </c:choose>
                    </c:forEach>
<%--                </select>--%>
            </div>
        </c:when>
    </c:choose>
    <button type="submit" class="btn btn-primary">Edit</button>
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
