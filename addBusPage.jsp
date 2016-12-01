<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>버스 시간표 등록 하드코딩</h1>
<form action="main.do?action=addBusToAdmin" method="post">
	
	id : <input type="text" name="id"/><br>
	dep : <input type="text" name="dep"/><br>
	dest : <input type="text" name="dest"/><br>
	hour : <input type="text" name="hour"/><br>
	min : <input type="text" name="min"/><br><br><br>
	
	<input type="reset" value="비우기"/>&nbsp;&nbsp;&nbsp;
	<input type="submit" value="다음"/><br><br>
	<a href="AdminMain.jsp">목록으로</a>
</form>
</body>
</html>