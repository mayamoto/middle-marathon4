<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="list" items="${resultList}" varStatus="status">
	<div style="font-weight:bold">球団名</div>${list.teamName}<br><br>
	<div style="font-weight:bold">本拠地</div>${list.headQuarters}<br><br>
	<div style="font-weight:bold">発足</div>${list.inauguration}<br><br>
	<div style="font-weight:bold">歴史</div>${list.history}<br><br>
</c:forEach>
<br>
<input type="button" value="戻る" onclick="history.back()">
</body>
</html>