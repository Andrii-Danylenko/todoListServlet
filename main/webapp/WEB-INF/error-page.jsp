<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<jsp:include page="header.jsp" />

<h1><%=request.getAttribute("error_type")%></h1>
</body>
</html>
