<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="controller" method="post">
    <table style="with: 50%">
        <tr>
            <td><input type="hidden" name="command" value="login"/></td>
        </tr>
        <tr>
            <td>UserName</td>
            <td><input type="text" name="login" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
    </table>
    <input type="submit" value="Login" /></form>
<%--<a href="controller?command=doctorCommand">Hello Servlet</a>--%>
</body>
</html>
