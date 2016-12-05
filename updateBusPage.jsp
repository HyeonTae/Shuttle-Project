<%@ page import="smu.shuttle.model.Bus" %>
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


/*회원 입력 폼*/


html {
  width: 100%;
  height: 100%;
}

body {
  background: -webkit-linear-gradient(45deg, rgba(66, 183, 245, 0.8) 0%, rgba(66, 245, 189, 0.4) 100%);
  background: linear-gradient(45deg, rgba(66, 183, 245, 0.8) 0%, rgba(66, 245, 189, 0.4) 100%);
  color: rgba(0, 0, 0, 0.6);
  font-family: "Roboto", sans-serif;
  font-size: 14px;
  line-height: 1.6em;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.overlay, .form-panel.one:before {
  position: absolute;
  top: 0;
  left: 0;
  display: none;
  background: rgba(0, 0, 0, 0.8);
  width: 100%;
  height: 100%;
}

.form {
  z-index: 15;
  position: relative;
  background: #FFFFFF;
  width: 600px;
  border-radius: 4px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
  margin: 100px auto 10px;
  overflow: hidden;
}
.form-toggle {
  z-index: 10;
  position: absolute;
  top: 60px;
  right: 60px;
  background: #FFFFFF;
  width: 60px;
  height: 60px;
  border-radius: 100%;
  -webkit-transform-origin: center;
      -ms-transform-origin: center;
          transform-origin: center;
  -webkit-transform: translate(0, -25%) scale(0);
      -ms-transform: translate(0, -25%) scale(0);
          transform: translate(0, -25%) scale(0);
  opacity: 0;
  cursor: pointer;
  -webkit-transition: all 0.3s ease;
          transition: all 0.3s ease;
}
.form-toggle:before, .form-toggle:after {
  content: '';
  display: block;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 30px;
  height: 4px;
  background: #4285F4;
  -webkit-transform: translate(-50%, -50%);
      -ms-transform: translate(-50%, -50%);
          transform: translate(-50%, -50%);
}
.form-toggle:before {
  -webkit-transform: translate(-50%, -50%) rotate(45deg);
      -ms-transform: translate(-50%, -50%) rotate(45deg);
          transform: translate(-50%, -50%) rotate(45deg);
}
.form-toggle:after {
  -webkit-transform: translate(-50%, -50%) rotate(-45deg);
      -ms-transform: translate(-50%, -50%) rotate(-45deg);
          transform: translate(-50%, -50%) rotate(-45deg);
}
.form-toggle.visible {
  -webkit-transform: translate(0, -25%) scale(1);
      -ms-transform: translate(0, -25%) scale(1);
          transform: translate(0, -25%) scale(1);
  opacity: 1;
}
.form-group {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-wrap: wrap;
      -ms-flex-wrap: wrap;
          flex-wrap: wrap;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
      -ms-flex-pack: justify;
          justify-content: space-between;
  margin: 0 0 20px;
}
.form-group:last-child {
  margin: 0;
}
.form-group label {
  display: block;
  margin: 0 0 10px;
  color: rgba(0, 0, 0, 0.6);
  font-size: 12px;
  font-weight: 500;
  line-height: 1;
  text-transform: uppercase;
  letter-spacing: .2em;
}
.two .form-group label {
  color: #FFFFFF;
}
.form-group input {
  outline: none;
  display: block;
  background: rgba(0, 0, 0, 0.1);
  width: 100%;
  border: 0;
  border-radius: 4px;
  box-sizing: border-box;
  padding: 12px 20px;
  color: rgba(0, 0, 0, 0.6);
  font-family: inherit;
  font-size: inherit;
  font-weight: 500;
  line-height: inherit;
  -webkit-transition: 0.3s ease;
          transition: 0.3s ease;
}
.form-group input:focus {
  color: rgba(0, 0, 0, 0.8);
}
.two .form-group input {
  color: #FFFFFF;
}
.two .form-group input:focus {
  color: #FFFFFF;
}
.form-group button {
  outline: none;
  background: #4285F4;
  width: 100%;
  border: 0;
  border-radius: 4px;
  padding: 12px 20px;
  color: #FFFFFF;
  font-family: inherit;
  font-size: inherit;
  font-weight: 500;
  line-height: inherit;
  text-transform: uppercase;
  cursor: pointer;
}
.two .form-group button {
  background: #FFFFFF;
  color: #4285F4;
}
.form-group .form-recovery {
  color: #4285F4;
  font-size: 12px;
  text-decoration: none;
}
.form-panel {
  padding: 60px calc(5% + 60px) 60px 60px;
  box-sizing: border-box;
}
.form-panel.one:before {
  content: '';
  display: block;
  opacity: 0;
  visibility: hidden;
  -webkit-transition: 0.3s ease;
          transition: 0.3s ease;
}
.form-panel.one.hidden:before {
  display: block;
  opacity: 1;
  visibility: visible;
}

.form-header {
  margin: 0 0 40px;
}
.form-header h1 {
  padding: 4px 0;
  color: #4285F4;
  font-size: 24px;
  font-weight: 700;
  text-transform: uppercase;
}
.two .form-header h1 {
  position: relative;
  z-index: 40;
  color: #FFFFFF;
}

.pen-footer {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  -webkit-flex-direction: row;
      -ms-flex-direction: row;
          flex-direction: row;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
      -ms-flex-pack: justify;
          justify-content: space-between;
  width: 600px;
  margin: 20px auto 100px;
}
.pen-footer a {
  color: #FFFFFF;
  font-size: 12px;
  text-decoration: none;
  text-shadow: 1px 2px 0 rgba(0, 0, 0, 0.1);
}
.pen-footer a .material-icons {
  width: 12px;
  margin: 0 5px;
  vertical-align: middle;
  font-size: 12px;
}

.cp-fab {
  background: #FFFFFF !important;
  color: #4285F4 !important;
}


</style>




<script type="text/javascript">
   function init() {
      document.getElementById("myForm").onsubmit = function() {

         var myId = document.getElementById("id");
         var myPass = document.getElementById("pass");

         if(myId.value==null || myId.value=="") {
            alert("id를 입력하시오...");
            myId.focus();
            return false;
         }

         if(myPass.value==null || myPass.value=="") {
            alert("pass를 입력하시오...");
            myPass.focus();
            return false;
         }
      }
   }
   window.onload = init;
</script>

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
           <li><a href="main.do?action=addBus">셔틀버스 추가</a></li>
           <li><a href="main.do?action=updateBusPage">셔틀버스 수정 페이지</a></li>
           <li><a href="main.do?action=searchAllBus">전체 셔틀버스시간표 조회</a></li>
           <li><a href="main.do?action=searchBusFromAsanToSMU">셔틀버스시간표 조회 (아산역 -> 학교)</a></li>
           <li><a href="main.do?action=searchBusFromCheonAnToSMU">셔틀버스시간표 조회 (천안역 -> 학교)</a></li>
           <li><a href="main.do?action=searchBusFromTerminalToSMU">셔틀버스시간표 조회 (터미널 -> 학교)</a></li>
           <li><a href="main.do?action=searchBusForAsan">셔틀버스시간표 조회 (학교 -> 아산역)</a></li>
           <li><a href="main.do?action=searchBusForCheonAn">셔틀버스시간표 조회 (학교 -> 천안역)</a></li>
           <li><a href="main.do?action=searchBusForTerminal">셔틀버스시간표 조회 (학교 -> 터미널)</a></li>
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


<br><br>


<!-- 폼 시작 -->
<br><br><br><br><br><br>
<div class="form">
  <div class="form-toggle"></div>
  <div class="form-panel one">
    <div class="form-header">

    <center>  <h2> 정보수정 </h2><center>
    </div>
    <div class="form-content">
        <form action="main.do?action=searchBusForID" method="post">
        <%
           Bus b = (Bus)request.getAttribute("bus");
           if(b!=null){%>
        <div class="form-group">
          <label for="username">ID</label>
          <input type="text" id="id" name="id" required="required" value="<%=b.getId() %>" readonly="readonly"/>
        </div>
        <div class="form-group">
          <label for="password">출발지</label>
          <input type="text" id="pass" name="dep" required="required"  value="<%=b.getDep() %>"/>
        </div>
        <div class="form-group">
          <label for="password">목적지</label>
          <input  type="text" name="dest" required="required"  value="<%=b.getDest() %>"/>
        </div>

        <div class="form-group">
          <label for="password">시</label>
          <input  type="text"   name="hour" required="required"  value="<%=b.getHour() %>"/>
        </div>
        <div class="form-group">
          <label for="password">분</label>
          <input  type="text"   name="min" required="required" value="<%=b.getMin() %>"/>
        </div>
        <div class="form-group">
          <button  name = "login">정보 수정</button>
        </div>
      </form>
    </div>



    <div class="form-content">

   <form action="main.do?action=deleteBus" method="post">
        <div class="form-group">
          <label for="username">삭제할 ID</label>
          <input type="text" id="id" name="id" required="required" value="<%=b.getId() %>" readonly="readonly"/>
        </div>

        <div class="form-group">
          <button  name = "login">삭제</button>
        </div>
      </form>

    </div>

  </div>
</div>


<br><br><br><br><br>
</body>
</html>
