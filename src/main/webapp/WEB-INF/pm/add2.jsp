<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add2</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
						var stdNum1 = $("#stdNum");
						var zhname1 = $("#zhname");
						var version1 = $("#version");
						var keys1 = $("#keys");
						var releaseDate1 = $("#releaseDate");
						var implDate1 = $("#implDate");
						var btn = $("#btn");
						
						stdNum1.bind("blur",function(){
							var stdNum = $("#stdNum").val();
							$.ajax({
								type : "post",
								url : "isExists",
								data : 	"stdNum="+stdNum,
								dataType : "text",
                                success : function(data) {
									if (data == "ok") {
										alert("标准号可以使用!");
										$("#stdNum").attr("result", "succ");
									} else {
										alert("标准号重复!");
										$("#stdNum").attr("result", "fail");
									}
								},
								error : function(xhr,msg) {
									alert(msg);
									$("#stdNum").attr("result", "fail");
								}

							});
						});
						

						zhname1.onblur=function () {
                            if($("#zhname").val() == "" || $.trim($("#zhname").val()).length == 0){
                                 		alert("中文名不能为空!");
                                 		// $("#zhname").focus();
                                 		$("#zhname").attr("result", "fail");
                                 	}else{
                                 		$("#zhname").attr("result", "succ");
							}
                        };
						
						version1.bind("blur",function(){
							if ($("#version").val() == "" || $.trim($("#version").val()).length == 0) {
								alert("版本号不能为空!");
								$("#version").attr("result", "fail");
								// $("#version").focus();
							} else {
								$("#version").attr("result", "succ");
							}
						});
						
						keys1.bind("blur",function(){
							var keys = $("#keys").text();
							if ($("#keys").val() == "" || $.trim($("#keys").val()).length == 0) {
								alert("关键词不能为空!");
								$("#keys").attr("result", "fail");
								// $("#keys").focus();
							} else {
								$("#keys").attr("result", "succ");
							}
						});
						
						releaseDate1.bind("blur",function(){
							var releaseDate = $("#releaseDate").val();
							var reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
							var regExp = new RegExp(reg);
							if (!regExp.test(releaseDate)) {
								alert("日期格式不正确，正确格式为：yyyy-MM-dd");
								$("#releaseDate").attr("result", "fail");
								// $("#releaseDate").focus();
							} else {
								$("#releaseDate").attr("result", "succ");
							}
						});
						
						implDate1.bind("blur",function(){
							var implDate = $("#implDate").val();
							var reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
							var regExp = new RegExp(reg);
							if (!regExp.test(implDate)) {
								alert("日期格式不正确，正确格式为：yyyy-MM-dd");
								$("#implDate").attr("result", "fail");
								// $("#implDate").focus();
							} else {
								$("#implDate").attr("result", "succ");
							}
						});

						btn.bind("click",function(){
							if (stdNum1.attr("result") != "succ") {
								stdNum1.blur();
							} else if (zhname1.attr("result") != "succ") {
								zhname1.onblur();
							} else if (version1.attr("result") != "succ") {
								version1.blur();
							} else if (keys1.attr("result") != "succ") {
								keys1.blur();
							} else if (releaseDate1.attr("result") != "succ") {
								releaseDate1.blur();
							} else if (implDate1.attr("result") != "succ") {
								implDate1.blur();
							} else {
								if (confirm("是否确认提交数据?")) {
									$("#myForm").submit();
								}
							}
						});

					});
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
	<h1 style="text-align: center;">添加标准信息</h1>
	<form action="addStan" method="post" id="myForm" enctype="multipart/form-data">
		<table border="1" cellspacing="0" cellpadding="0" align="center"
			id="mytable">
			<tr>
				<td>*&nbsp;标准号</td>
				<td><input id="stdNum" type="text" name="stdNum"/></td>
			</tr>
			<tr>
				<td>*&nbsp;中文名称</td>
				<td><input type="text" id="zhname" name="zhname"/></td>
			</tr>
			<tr>
				<td>*&nbsp;版本号</td>
				<td><input type="text" id="version" name="version"/></td>
			</tr>
			<tr>
				<td>*&nbsp;关键字/词</td>
				<td><input type="text" id="keys" name="keys"/></td>
			</tr>
			<tr>
				<td>*&nbsp;发布日期(yyyy-MM-dd)</td>
				<td><input type="text" id="releaseDate" name="releaseDate"/></td>
			</tr>
			<tr>
				<td>*&nbsp;实施日期(yyyy-MM-dd)</td>
				<td><input type="text" id="implDate" name="implDate"/></td>
			</tr>
			<tr>
				<td>*&nbsp;附件</td>
				<td><input type="file" id="fuJian" name="fuJian"/>
				<span>${msg }</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="btn" value="保存" />
					<input type="reset" value="取消" />
				</td>
			</tr>

		</table>
	</form>
</body>
</html>