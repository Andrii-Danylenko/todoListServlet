
<%@ page import="model.Task" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Task</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<%
    Task task = ((Task) request.getAttribute("task"));
%>
<div class="container">
<jsp:include page="header.jsp" />
<h1>Edit existing task</h1>
<br>
<form method="post">
    <label>Task id:
        <input type="text" name="taskId" value="<%=task.getId()%>" disabled>
    </label>
    <br>
    <label>Task name:
        <input type="text" name="taskName" value="<%=task.getName()%>">
    </label>
    <br>
    <label for="priority">Priority:</label>
    <select id="priority" name="priority">
        <option value="low">LOW</option>
        <option value="medium">MEDIUM</option>
        <option value="high">HIGH</option>
    </select>
    <br>
    <%
        if (response.getStatus() == HttpServletResponse.SC_CONFLICT) {
    %>
    <p style='color:red;'>Failed to update task,
        because task with the same name already exists. Please, use another name.</p>
    <%
        }
    %>


    <button name="update" type="submit">UPDATE</button>
    <button name="back" type="submit">GO BACK</button>
</form>
</div>
</body>
</html>
