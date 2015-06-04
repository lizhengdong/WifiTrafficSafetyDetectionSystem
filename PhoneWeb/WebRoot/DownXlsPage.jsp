<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导出excel</title>
</head>
<body>
<%-- <s:a action="exportXsl" class="export">导出excel</s:a>
<br/> --%>
<%
   String macAdd = request.getParameter("macAdd");
%>
<form name="form1" action="exportXsl" >
<input type="text" name="macAdd" value=<%=macAdd%> hidden="true">
<a href="javascript:form1.submit()" id="form1Submit">点击链接下载excel表</a>
</form>
<!--<script> document.getElementById("form1Submit").click(); </script>-->
</body>
</html>