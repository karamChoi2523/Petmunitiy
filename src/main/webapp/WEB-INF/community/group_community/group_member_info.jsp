<%@page contentType="text/html; charset=utf-8" import="java.util.*" import="model.*" %> <!-- 변수 바꾸기 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	UserInfo user = (UserInfo)request.getAttribute("user");
	Pet pet = (Pet)request.getAttribute("pet");
%>
<html>
<head>
<title>사용자 관리 - 그룹원 상세정보 보기</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/btn.css' />" type="text/css">

</head>
<body>   

<!-- registration form  -->

<!--<h2>회원가입</h2>  -->
	<h3>그룹원 상세정보 보기</h3>
	<table style="background-color: #848484; width: 100%">
	
  	  <tbody>
  	  	<tr height="40">
		      <td align="center" bgcolor="#E6E6E6">사용자 ID</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
		      	${user.loginId}  		
		      </td>
		</tr>
		<tr height="40">
		      <td align="center" bgcolor="#E6E6E6">이름</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
			${user.userNickname}
		      </td>
		</tr>
  	  	<tr height="40">
		      <td align="center" bgcolor="#E6E6E6">이메일 주소</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
				${user.email}
		      </td>
		</tr>
	  <tr height="40">
		      <td align="center" bgcolor="#E6E6E6">전화번호</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
				${user.phoneNumber}
		      </td>
		</tr>
		<tr height="40">
		      <td align="center" bgcolor="#E6E6E6">그룹 이름</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
				${user.joinGroup} 
		      </td>
		</tr>
 	</tbody>
</table>
<%if(pet == null){%>
	펫이 등록되지 않았습니다.
<%}else{ %>
<table style="width:100%">
   <tr>
   <td style="width:200px">
   		<%if(pet!=null && pet.getFilename() == null){ %>
			<img src="<c:url value='/images/linkedin_profile_image.png' />" style="width:200px; height:200px"/>	
		<%}else{ %>
			<img src="<c:url value='/upload/${pet.filename}'/>" style="width:200px; height:200px" />	
		<%} %>	 
   </td>
   <td style="width:1200px">
   <br>
   	<table style="background-color: #848484; width: 100%">
    <!--<center>-->
    		<tr height="40">
		      <td align="center" bgcolor="#E6E6E6">이름</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
		      	${pet.name}  		
		      </td>
		   </tr>
		    <tr height="40">
		      <td align="center" bgcolor="#E6E6E6">나이</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
				${pet.age}
		      </td>
		    </tr>
		    <tr height="40">
		      <td align="center" bgcolor="#E6E6E6">성별</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
		         ${pet.gender}
		      </td>
		    </tr>
		    <tr height="40">
		      <td align="center" bgcolor="#E6E6E6">건강상태</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
		         ${pet.health}
		      </td>
		    </tr>
		    <tr height="40">
		      <td align="center" bgcolor="#E6E6E6">접종여부</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
		         ${pet.vaccination}
		      </td>
		    </tr>
		    <tr height="40">
		      <td align="center" bgcolor="#E6E6E6">종</td>
		      <td bgcolor="ffffff" style="padding-left: 10">
		         ${pet.kind}
		      </td>
		    </tr>
   	</table>
   <%} %>
</table>

</body>
</html>