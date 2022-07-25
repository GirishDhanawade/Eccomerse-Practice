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
	<%-- <h5>${requestScope.tut_names}</h5> --%>
	<table style="background-color: lightgrey; margin: auto">
		<caption>Available Tutorials under Topic ID :
			${param.topicId}</caption>
		<c:forEach var="tut_name" items="${requestScope.tut_names}">
			<tr>
			<spring:url var="url" value="/customer/tutorial_detail?tutName=${tut_name}"/>
				<td><a href="${url}">${tut_name}</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>