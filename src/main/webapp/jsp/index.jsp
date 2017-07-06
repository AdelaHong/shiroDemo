<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
success   WELCOME<shiro:principal></shiro:principal>
<br/>
<shiro:hasRole name="admin">
    <a href="admin.do">admin</a></br>
</shiro:hasRole>

<shiro:hasAnyRoles  name="admin,user">
<a href="user.do">user</a></br>
</shiro:hasAnyRoles>

<a href="/logout">login out</a>

</body>
</html>
