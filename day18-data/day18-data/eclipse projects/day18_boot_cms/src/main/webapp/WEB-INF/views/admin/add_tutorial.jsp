<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--import spring supplied form tags lib , to support 2 way data binding --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5 style="color: red;">${requestScope.err_mesg}</h5>
	<%--model to view layer data binding --%>
	<form:form method="post" modelAttribute="tutorial">
		<table style="background-color: lightgrey; margin: auto">

			<tr>
				<td>Enter Tutorial Name</td>
				<td><form:input path="tutName" /></td>
			</tr>
			<tr>
				<td>Enter Author Name</td>
				<td><form:input path="author" /></td>
			</tr>
			<tr>
				<td>Publish Date</td>
				<td><form:input type="date" path="publishDate" /></td>
			</tr>
			<tr>
				<td>Contents</td>
				<td><form:textarea rows="10" cols="40" path="contents" />
			</tr>
			<tr>
				<td><input type="submit" value="Add New Tutorial" /></td>

			</tr>
		</table>
	</form:form>
</body>
</html>