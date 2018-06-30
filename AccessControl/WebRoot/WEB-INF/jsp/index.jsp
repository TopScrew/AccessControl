<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>目前权限:<%= request.getSession().getAttribute("role") %></h2>
<hr>
---test git--------
<a href="http://127.0.0.1:8080/AccessControl/user/add">用户权限页面</a>
<hr>
<a href="http://127.0.0.1:8080/AccessControl/admin/index">超级管理员页面</a>
</body>
</html>
