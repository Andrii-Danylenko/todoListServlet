<%@ page import="model.Task" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>task review</title>
</head>
<jsp:include page="header.jsp" />
<table border="1">
    <thead>
    <tr>
        <th>№</th>
        <th>Name</th>
        <th>Priority</th>
        <th colspan="3">Operation</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Task task : (Set<Task>) request.getAttribute("tasks")) {

        %>
    <tr>
        <td><%=task.getId()%></td>
        <td><%=task.getName()%></td>
        <td><%=task.getPriority()%></td>
        <td><a href="/read-task?id=<%=task.getId()%>">READ</a></td>
        <td><a href="/edit-task?id=<%=task.getId()%>">EDIT</a></td>
        <td><a href="/delete-task?id=<%=task.getId()%>">DELETE</a></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<body>
</body>
</html>