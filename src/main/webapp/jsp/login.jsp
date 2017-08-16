<%--
  Created by IntelliJ IDEA.
  User: micro
  Date: 2017/8/12
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<c:if test="${!empty error}">
    <font color="red"> <c:out value="${error}"/> </font>
</c:if>

<form action="userLogin.do"   method="POST">
用户名：
    <br/>
    <input name="username" type="text" >
    <br/>
    密码：
    <br/>
    <input name="password" type="password" >
    <input name="提交" type="submit">
    <input name="重置" type="reset">
</form>


</body>
</html>
