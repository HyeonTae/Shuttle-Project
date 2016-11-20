<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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


nav {background : #000000;} /*무슨색을할까*/

nav ul {
font-size : 0;
margin: 0;
padding: 0;
}
/*nav ul li !!!!!!!*/
nav ul li a{
   color : #fff;
   display : blue;
   font-size : 17px;
   padding: 16px 13px;
   /*transition: ?????????;*/
}

nav ul li :hover {background: #000000;}/*이쁜색을 찾아보자*/

nav ul li ul {
  border-bottom: 5px solid #000000;
  display : none;
  position : absolute;
  width: 250px;
}

nav ul li ul li {
border-top : 1px solid #444;
display:block;
}

nav ul li ul li : first-child { border - top : none;}

nav ul li ul li a{
  background: #000000;
  display:block;
  padding : 10px 14px ;
}

nav ul li ul li a : hover { background:  #000000;}

nav .fa.fa-angle-down {margin - left : 5px;}



</style>


<script type="text/javascript">
//draft
</script>

<title>Insert title here</title>
</head>
<body>
  <nav>
    <div class="container">
      <ul>
        <li><a href="#">Home</a></li>
        <li> <a href="#">학생관리<i class='fa fa-angle-down'></i></a>
          <ul>
            <li><a href="#">학생추가</a></li>
            <li><a href="#">학생조회</a></li>
            <li><a href="#">모든 학생 조회</a></li>
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
</body>
</html>
