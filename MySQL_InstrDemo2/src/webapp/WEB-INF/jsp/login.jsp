<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search for entries in table Partitions from an author</title>
</head>
<body>

<form action=example-server.com">
      <fieldset>
        <legend>Contact me</legend>
        <div class="form-control">
          <label for="name">Author Name</label>
          <input type="name" id="auth_name" placeholder="Enter the author to search for" required />
        </div>

         <div class="form-control">
          <label for="name">Part title or keywords</label>
          <input type="name" id="part_name" placeholder="Enter part name" required />
        </div>
        <input type="submit" value="Send" class="submit-btn" />


<a href="${pageContext.request.contextPath}/searchparts">Search Parts</a>
</body>
</html>
