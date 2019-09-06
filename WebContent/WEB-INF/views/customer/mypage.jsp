<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
</head>
<body>
마이페이지
<h2>환영합니다 ${customer.name}님</h2>

 	<h3>	아이디 ${customer.id} <br/></h3>
	<h3>	비밀번호  ${customer.pw} <br/></h3>
	<h3>	주민번호  ${customer.ssn} <br/></h3>
	<h3>	이름  ${customer.name} <br/></h3>
	<h3>	신용도  ${customer.credit}> <br/></h3>
</body>
</html>