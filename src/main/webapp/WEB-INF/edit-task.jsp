<%@ page import="model.Task" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Task</title>
</head>
<body>
<p><a href="/home">HOME</a> | <a href="/create-task">ADD NEW TASK</a> | <a href="/tasks-list">REVIEW ALL TASKS</a></p>
<h1>Edit existing task</h1>
<br>
<form method="post">
    <label>Task id:
        <input type="text" name="taskId" value="<%=((Task) request.getAttribute("task")).getId()%>">
    </label>
    <br>
    <label>Task name:
        <input type="text" name="taskName" value="<%=((Task) request.getAttribute("task")).getName()%>">
    </label>
    <br>
    <label for="priority">Priority:</label>
    <select id="priority" name="priority">
        <option value="low">LOW</option>
        <option value="medium">MEDIUM</option>
        <option value="high">HIGH</option>
    </select>
    <br>

    <button name="update" type="submit">UPDATE</button>
</form>
</body>
</html>