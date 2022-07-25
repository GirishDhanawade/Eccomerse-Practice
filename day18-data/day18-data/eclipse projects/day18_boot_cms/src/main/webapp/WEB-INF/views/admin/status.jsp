<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>In admin's status page...</h5>
	<h5>${requestScope.mesg}</h5>
	<h5>Hello , : ${sessionScope.user_dtls.name}</h5>
	<spring:url var="url" value="/admin/add_new_tut" />
	<form action="${url}" method="get">
		<table style="background-color: lightgrey; margin: auto">

			<tr>
				<td>Choose Topic</td>
				<td><select name="topicId">
						<c:forEach var="topic" items="${requestScope.topic_list}">
							<option value="${topic.id}">${topic.topicName}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td><input type="submit" value="Choose Topic" /></td>
			</tr>
		</table>
	</form>
	<h5>
		<a href="<spring:url value='/user/logout'/>">Log Out</a>
	</h5>

</body>
</html>