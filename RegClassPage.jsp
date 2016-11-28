<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<!-- add link -->


<style>

/*               메인 스타일                         */
@import url(https://fonts.googleapis.com/css?family=Roboto:400,700,500);

html { box-sizing: border-box; }

*, *:before, *:after { box-sizing: inherit; }

body {
background: #fafafa;
font-family: "Roboto", sans-serif;
font-size: 14px;
margin: 0;
}

a { text-decoration: none; }

.container {
width: 1000px;
margin: auto;
}

h1 { text-align:center; margin-top:150px;}

/*                       메뉴스타일                                 */


nav {background : #2ba0db;} /*무슨색을할까*/

nav ul {
font-size : 0;
margin: 0;
padding: 0;
}

nav ul li {
display: inline-block;
position: relative;
}

nav ul li a{
 color : #fff;
 display : block;
 font-size : 17px;
 padding: 16px 13px;
 transition: ?????????;
}

nav ul li :hover {background: #126d9b;}/*메뉴와비슷한색*/

nav ul li ul {
border-bottom: 5px solid #2ba0db; /*메뉴색이랑 같게*/
display : none;
position : absolute;
width: 250px;
}

nav ul li ul li {
border-top : 1px solid #444;
display:block;
}

nav ul li ul li :first-child { border - top : none;}

nav ul li ul li a {
background: #373737;
display:block;
padding : 10px 14px ;
}

nav ul li ul li a :hover { background:  #000000;}

nav .fa.fa-angle-down {margin - left : 6px;}

/*메뉴끝 =========================================*/

</style>


</head>
<body>
<!--메뉴시자아악-->
<nav>
  <div class="container">
    <ul>
      <li><a href="AdminMain.jsp">Home</a></li>
      <li> <a href="#">학생관리<i class='fa fa-angle-down'></i></a>
        <ul>
          <li><a href="main.do?action=regClass">학생추가</a></li>
          <li><a href="main.do?action=searchClass">학생조회</a></li>
          <li><a href="main.do?action=searchAllClass">모든 학생 조회</a></li>
        </ul>
      </li>
      <li class='sub-menu'> <a href="#">셔틀관리<i class='fa fa-angle-down'></i></a>
        <ul>
          <li><a href="#">셔틀 정보 조회</a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$('nav li').hover(
  function() {
    $('ul', this).stop().slideDown(200);
  },
  function() {
    $('ul', this).stop().slideUp(200);
  }
);
</script>
<!-- 둥 -->




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
