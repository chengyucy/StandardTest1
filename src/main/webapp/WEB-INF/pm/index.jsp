<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#mytable").find("tr:even").css("background-color", "#868485");
		//全选 
	});

	function checkBox(name, checked) {
		$("input[name=" + name + "]").prop("checked", checked);
	}

	function optDelete() {
		var chk_value = [];
		$("input[name='ids']:checked").each(function() {
			//将选中额数据存到数组里
			chk_value.push($(this).val());
			alert(chk_value);
		});
		if (chk_value.length == 0) {
			alert("你还没有选择任何内容！");
		}
		if (chk_value.length > 0) {
			location.href = "deleteStan?chk_value=" + chk_value;
		}
	}
</script>

<style type="text/css">
tr {
	height: 40px;
}

table {
	width: 800px;
}
</style>
</head>
<body>
	<h1 style="text-align: center;">标准信息列表</h1>
	<table border="1" cellspacing="0" cellpadding="0" align="center">
		<thead>
			<tr>
				<td colspan="7" style="text-align: right;">
					<form action="index" method="post" style="display: inline;">
						<input type="text" value="${condition}" name="condition" /> <input
							type="submit" value="提交检索" />
					</form> <input type="button" value="新增标准"
					onclick="javascript:window.location.href='toAdd' " /> <input
					type="button" value="删除标准" onclick="optDelete();" />
				</td>
			</tr>
		</thead>
		<tbody id="mytable">
			<tr style="text-align: center;">
				<td><input type="checkbox"
					onclick="checkBox('ids',this.checked)" /></td>
				<td>标准号</td>
				<td>中文名称</td>
				<td>版本</td>
				<td>发布日期</td>
				<td>实施日期</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${pagination.standards }" var="brand">
				<tr>
					<td><input type="checkbox" name="ids" value="${brand.id}" /></td>
					<td>${brand.stdNum }</td>
					<td>${brand.zhname }</td>
					<td>${brand.version }</td>
					<td><fmt:formatDate value="${brand.releaseDate }"
							pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${brand.implDate }"
							pattern="yyyy-MM-dd" /></td>
					<td><a href="download?id=${brand.id }">下载</a> <a
						href="queryById?id=${brand.id }">修改</a></td>
				</tr>
			</c:forEach>
		</tbody>
		<tr>
			<td colspan="7" style="text-align: right;">
				<%-- <c:if test="${not empty condition }"> --%>
						<a href="index?pageNo=1&condition=${condition }">首页</a> | 
						<a href="index?pageNo=${pagination.currPageNo-1 }&condition=${condition }">上一页</a>&nbsp;| 
						<a href="index?pageNo=${pagination.currPageNo+1 }&condition=${condition }">下一页</a>&nbsp;| 
						<a href="index?pageNo=${pagination.totalPageCount }&condition=${condition }">末页</a>&nbsp;&nbsp;
						<span>第${pagination.currPageNo}页/共${pagination.totalPageCount }页</span>
				<%-- </c:if> --%> 
				<%-- <c:if test="${empty condition }">
						<a href="index?pageNo=1">首页</a> | 
						<a href="index?pageNo=${pagination.currPageNo-1 }">上一页</a>&nbsp;| 
						<a href="index?pageNo=${pagination.currPageNo+1 }">下一页</a>&nbsp;| 
						<a href="index?pageNo=${pagination.totalPageCount }">末页</a>&nbsp;&nbsp;
						<span>第${pagination.currPageNo}页/共${pagination.totalPageCount }页</span>
				</c:if> --%>
			</td>
		</tr>
	</table>
</body>
</html>