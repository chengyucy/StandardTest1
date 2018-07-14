<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
		/* $(document).ready(function(){
			$("#mytable tr").each(function(){
			    $(this).find("td:first").css("textAlign","center");

			})
		}); */
	</script>
<style type="text/css">
tr {
	height: 40px;
}

td {
	text-align: center;
}

table {
	width: 600px;
}
</style>
</head>
<body>
	<h1 style="text-align: center;">标准信息列表</h1>
	<form action="addStan" method="post" enctype="multipart/form-data">
		<table border="1" cellspacing="0" cellpadding="0" align="center"
			id="mytable">
			<tr>
				<td>*&nbsp;标准号</td>
				<td><input type="text" name="stdNum" /></td>
			</tr>
			<tr>
				<td>*&nbsp;中文名称</td>
				<td><input type="text" name="zhname" /></td>
			</tr>
			<tr>
				<td>*&nbsp;版本号</td>
				<td><input type="text" name="version" /></td>
			</tr>
			<tr>
				<td>*&nbsp;关键字/词</td>
				<td><input type="text" name="keys" /></td>
			</tr>
			<tr>
				<td>*&nbsp;发布日期(yyyy-MM-dd)</td>
				<td><input type="text" name="releaseDate" /></td>
			</tr>
			<tr>
				<td>*&nbsp;实施日期(yyyy-MM-dd)</td>
				<td><input type="text" name="implDate" /></td>
			</tr>
			<tr>
				<td>*&nbsp;附件</td>
				<td><input type="file" name="fuJian" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="保存">
					<input type="reset" value="取消">
				</td>
			</tr>

		</table>
	</form>

</body>
</html>