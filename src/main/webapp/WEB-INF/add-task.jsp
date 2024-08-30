<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Task" %>
<%@ page import="java.util.Set" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Task</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
<jsp:include page="header.jsp" />
<%
    int responseCode = response.getStatus();
    String taskName = (String) request.getAttribute("taskName");
    Integer currentId = (Integer) request.getAttribute("lastId");
%>

<form method="post">
    <label>Task name:
        <input type="text" name="taskName" value="<%= taskName != null ? taskName : "myTask_" + currentId%>" placeholder="myTask_<%=currentId%>">
    </label>
    <br>

    <label for="priority">Priority:</label>
    <select id="priority" name="priority">
        <option value="low">LOW</option>
        <option value="medium">MEDIUM</option>
        <option value="high">HIGH</option>
    </select>
    <br>

    <button name="addTask" type="submit">ADD TASK</button>
        <% if (responseCode == HttpServletResponse.SC_CREATED) { %>
    <p style='color:green;'>Task added successfully!</p>
    <%   }
    else if (responseCode == HttpServletResponse.SC_CONFLICT) { %>
    <button name="clear" type="submit">REMOVE DUPLICATE</button>
    <p style='color:red;'>Task with the given name already exists! Please, use another name or delete the existing task!</p>
    <% } %>
</form>
</div>
</body>
</html>
