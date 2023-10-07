<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.sql.*" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>List from all musician of a particular instrument</title>
</head>
<body>
<p>
<c:out value="${msg}">
</p>
<p>
<ul>
<!-- a verifier l acces au map -->
  <c:forEach var"j" begin="1" end="${listmusic.size()}">
    <li>Name <c:out value="${listmusic.get(j).get("musician")}"> 
    </li>
  </c:forEach>
</ul>
</p>
<p>
Click on below to show and modify the partitions associated with this particular instrument
<a href="${pageContext.request.contextPath}/updatepart.jsp">Partitions</a>
</p>
</body>
</html>
 
