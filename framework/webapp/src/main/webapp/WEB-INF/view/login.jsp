<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form action="${base }/login" method="post">
	<input type="text" name="username"/><br/>
	<input type="password" name="password"/><br/>
	<input type="submit" value="Submit" />
</form>
</body>
</html>