<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath }" />
<!doctype html>
<html>
<head>
	<title>客户管理 - 创建客户</title>
</head>
<body>
	<form action="${BASE }/customer_create" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>客户名称</td>
				<td>
					<input type="text" name="name" />
				</td>
			</tr>
			<tr>
				<td>联系人</td>
				<td>
					<input type="text" name="contact" />
				</td>
			</tr>
			<tr>
				<td>联系电话</td>
				<td>
					<input type="text" name="telephone" />
				</td>
			</tr>
			<tr>
				<td>电子邮件</td>
				<td>
					<input type="email" name="email" />
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td>
					<input type="text" name="remark" />
				</td>
			</tr>
			<tr>
				<td>照片</td>
				<td>
					<input type="file" name="photo" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Submit"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
