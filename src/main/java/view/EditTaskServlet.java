package view;

import controller.dao.TaskDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;
import model.enums.Priorities;

import java.io.IOException;
@WebServlet("/edit-task")
public class EditTaskServlet extends HttpServlet {
    private static final TaskDAO dao = TaskDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        try {
            rd = req.getRequestDispatcher("/WEB-INF/edit-task.jsp");
            req.setAttribute("task", dao.getById(Integer.parseInt(req.getParameter("id"))));
            rd.forward(req, resp);
        } catch (RuntimeException exception) {
            rd = req.getRequestDispatcher("/WEB-INF/error-page.jsp");
            req.setAttribute("id", req.getParameter("id"));
            System.out.println(req.getParameter("id"));
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskName = req.getParameter("taskName");
        String priorityParam = req.getParameter("priority");
        String taskId = req.getParameter("taskId");

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Task taskToReplace = dao.getById(id);

            if (taskName == null || taskName.isEmpty()) {
                taskName = taskToReplace.getName();
            }
            if (priorityParam == null || priorityParam.isEmpty()) {
                priorityParam = taskToReplace.getPriority().name();
            }
            if (taskId == null || taskId.isEmpty()) {
                taskId = String.valueOf(taskToReplace.getId());
            }
            int updatedId = Integer.parseInt(taskId);

            boolean updated = dao.updateTask(taskToReplace, updatedId, taskName, Priorities.getPriorityFromString(priorityParam));

            if (updated) {
                resp.sendRedirect(req.getContextPath() + "/tasks-list");
            } else {
                req.setAttribute("error", "Failed to update task");
                req.setAttribute("task", taskToReplace);
                req.getRequestDispatcher("/WEB-INF/edit-task.jsp").forward(req, resp);
            }
        } catch (RuntimeException e) {
            req.setAttribute("error", "Invalid data or task ID");
            req.getRequestDispatcher("/WEB-INF/error-page.jsp").forward(req, resp);
        }
    }
}
