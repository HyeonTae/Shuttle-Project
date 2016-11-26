<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>학생 추가</h1>
<form action="main.do?action=regClassToAdmin" method="post">
   ID : <input type="text" name="id"/><br>
   PW : <input type="password" name="pass"/><br>
   이름 : <input type="text" name="name"/><br>
   학과 : <input type="text" name="dept"/><br>
   지역 : <input type="text" name="area"/><br>
   <br>
   <input type="submit" value="가입하기">&nbsp;
   <input type="reset" value="지우기"><br>
   <a href="AdminMain.jsp">메뉴 화면으로</a>
</form>
</body>
</html>
