<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.sql.*" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>List Page</title>
</head>
<body>

<p>List of partitions by instrument
  <table>
  <c:forEach item="${requestScope.parts}" var="parts">
      <tr>
        <td><c:out value="${parts.id}"/>  </td>
        <td><c:out value="${parts.name}"/>  </td>
        <td><c:out value="${parts.muse}"/>  </td>
        <td><c:out value="${parts.instr}"/>  </td>
      </tr>
  <c:forEach>
  </table>
</p> 

<p>Enter update of partition
  <form action="${pageContext.request.contextPath}/updateform">
    <table>
       <tr>
         <td>Enter part id:</td>
         <td><input type="text" name="id" /></td>
       </tr>
       <tr>
         <td>Enter part name:</td>
         <td><input type="text" name="name" /></td>
       </tr>
       <tr>
         <td>Enter part musician name:</td>
         <td><input type="text" name="muse_name" /></td>
       </tr>
       <tr>
         <td colspan="2">
            <input type="submit" name="Enter"/>
         </td>
       </tr>
    </table>
  </form>
</p>
</body>
</html>
