<%@ page import="model.Task" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>task review</title>
</head>
<p><a href="/home">HOME </a>| <a href="/create-task">ADD NEW TASK </a> | <a href="/tasks-list">REVIEW ALL TASKS </a></p>
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
        <td>
            <form action="/delete-task" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= task.getId() %>">
                <input type="submit" value="DELETE">
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<body>
</body>
</html>