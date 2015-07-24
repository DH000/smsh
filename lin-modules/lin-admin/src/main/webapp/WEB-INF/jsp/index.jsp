<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/pager.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试分页器</title>
</head>
<body>
	<p>分页程序</p>
	<p:pager url="/test/pager.asp?page=#pageNum" pageInfo="${pageInfo}"></p:pager>
	<p>参数</p>
</body>
</html>