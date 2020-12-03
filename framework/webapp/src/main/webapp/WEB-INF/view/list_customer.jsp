<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath }" />
<!doctype html>
<html>
<head>
<title>客户管理 - 客户列表</title>
<script>
window.onload = function() {
	document.getElementById("add").onclick = function(){
		location.href = "${BASE}/customer_create";
	}
}
</script>	
</head>
<body>
<h1>客户列表</h1>
<div><input type="button" value="添加" id="add"/></div>
<table>
	<tr>
		<th>客户名称</th>
		<th>联系人</th>
		<th>联系电话</th>
		<th>邮箱地址</th>
		<th>操作</th>
	</tr>
	<c:forEach var="inst" items="${list }">
	<tr>
		<td>${inst.name }</td>
		<td>${inst.contact }</td>
		<td>${inst.telephone }</td>
		<td>${inst.email }</td>
		<td>
			<a href="${BASE }/customer_edit?id=${inst.id}">编辑</a>
			<a href="${BASE }/customer_delete?id=${inst.id}">删除</a>
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>
