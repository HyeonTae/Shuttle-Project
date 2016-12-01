<%@page import="smu.shuttle.model.Bus"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#p{
		background-color: #E4F7BA;
	}
</style>
</head>
<body>
	<%
		ArrayList<Bus> busList = (ArrayList)request.getAttribute("busList");
	%>
	<table width=500>
		<tr>
			<th colspan="4" align="center" id="p"><h2>셔틀버스 조회(천안역->학교)</h2></th>
		</tr>
		<tr>
			<th align="center">출발지</th>
			<th align="center">도착지</th>
			<th align="center">시</th>
			<th align="center">분</th>
		</tr>
		<%
			for(Bus b:busList){%>
				<tr>
					<td><%= b.getDep() %></td>
					<td><%= b.getDest() %></td>
					<td><%= b.getHour() %></td>
					<td><%= b.getMin() %></td>
				</tr>		
			<%}%>			
	</table>
	<br><br><a href="AdminMain.jsp">메뉴 화면으로</a>
</body>
</html>