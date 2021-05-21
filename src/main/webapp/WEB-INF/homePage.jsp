<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:choose>

    <c:when test="${userRole.getValue() == 'admin' }">
            hello admin
<%--        <a href="controller?command=listOrders">--%>
<%--            --%>
<%--        </a> &nbsp;--%>
    </c:when>


    <c:when test="${userRole.getValue() == 'doctor'}">
        hello doctor
<%--        <a href="controller?command=listMenu">--%>
<%--            <fmt:message key="header_jspf.anchor.menu"/>--%>
<%--        </a> &nbsp;--%>
    </c:when>
</c:choose>
</body>
</html>
