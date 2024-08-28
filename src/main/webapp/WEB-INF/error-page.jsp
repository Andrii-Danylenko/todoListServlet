<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<p><a href="/home">HOME</a> | <a href="/create-task">ADD NEW TASK</a> | <a href="/tasks-list">REVIEW ALL TASKS</a></p>

<h1>Task with ID '<%=request.getParameter("id")%>' not found in TODO list!</h1>
</body>
</html>
