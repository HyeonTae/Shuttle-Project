<%@ page import="smu.shuttle.model.Bus" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>셔틀버스 수정 페이지</h1>
   <form action="main.do?action=searchBusForID" method="post">
   셔틀버스 ID : <input type="text" name="id"/>&nbsp;&nbsp;<input type="submit" value="검색">
   </form><br><br>
   <a href="AdminMain.jsp">메뉴 화면으로</a>
   <br><br><br><br>
   <%
      Bus b = (Bus)request.getAttribute("bus");
      if(b!=null){%>
   <h2>정보 수정</h2>
   <form action="main.do?action=updateBus" method="post">
      ID : <input type="text" name="id" value="<%=b.getId() %>" readonly="readonly"><br>
      출발지 : <input type="text" name="dep" value="<%=b.getDep() %>"/><br>
      목적지 : : <input type="text" name="dest" value="<%=b.getDest() %>"/><br>
      시 : <input type="text" name="hour" value="<%=b.getHour() %>"/><br>
      분 : <input type="text" name="min" value="<%=b.getMin() %>"/><br><br>
      <input type="submit" value="정보 수정"><br>
   </form>
   <br><br>
   <h2>정보 삭제</h2>
   <form action="main.do?action=deleteBus" method="post">
      삭제할ID : <input type="text" name="id" value="<%=b.getId() %>" readonly="readonly"><br><br>
      <input type="submit" value="삭제"><br><br>
   </form>
   <%} %>
</body>
</html>
