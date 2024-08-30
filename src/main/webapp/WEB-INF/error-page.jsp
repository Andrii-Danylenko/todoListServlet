
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
<jsp:include page="header.jsp" />
<h1 style='color:red'> <%=request.getAttribute("error_type")%></h1>
</div>
</body>
</html>
