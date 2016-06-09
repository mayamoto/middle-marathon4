<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- スクリプトの定義 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
<script language="javascript" type="text/javascript">
	//チーム名をクリックしたとき
	function onClick(id){
		//リクエストURLにパラメータを付加する
		window.location.href="/middle-marathon4/DetailServlet?id=" + id;

		//リクエスト先に遷移する
		return false;
	}



</script>

<title>プロ野球セントラル・リーグ一覧</title>
</head>
<body>
	<div align="center" style="border:#000000 solid 1px;">プロ野球<br>セントラル・リーグ一覧</div><br>
		<c:forEach var="list" items="${resultList}" varStatus="status">
			<a href="#" id="${status.count}" onClick="onClick(this.id);return false;">${list.teamName}</a><br><br>
		</c:forEach>
	</body>
</html>