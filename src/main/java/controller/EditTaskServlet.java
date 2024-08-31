package controller;

import repository.TaskDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Task;
import model.enums.Priority;

import java.io.IOException;
@WebServlet("/edit-task")
public class EditTaskServlet extends HttpServlet {
    private static TaskDAO dao;

    @Override
    public void init(ServletConfig config) {
        dao = TaskDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        try {
            rd = req.getRequestDispatcher("/WEB-INF/edit-task.jsp");
            req.setAttribute("task", dao.getById(Integer.parseInt(req.getParameter("id"))));
            rd.forward(req, resp);
        } catch (NumberFormatException exception) {
            rd = req.getRequestDispatcher("/WEB-INF/error-page.jsp");
            req.setAttribute("error_type", "'%s' is not a valid number!".formatted(req.getParameter("id")));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            rd.forward(req, resp);
        } catch (RuntimeException exception) {
            req.setAttribute("error_type", "Task with ID '%s' not found in TODO list!".formatted(req.getParameter("id")));
            rd = req.getRequestDispatcher("/WEB-INF/error-page.jsp");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskName = req.getParameter("taskName");
        String priorityParam = req.getParameter("priority");
        if (req.getParameter("back") != null) {
            resp.sendRedirect(req.getContextPath() + "/tasks-list");
            return;
        }
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Task taskToReplace = dao.getById(id);

            if (taskName == null || taskName.isEmpty()) {
                taskName = taskToReplace.getName();
            }
            if (priorityParam == null || priorityParam.isEmpty()) {
                priorityParam = taskToReplace.getPriority().name();
            }
            boolean updated = dao.updateTask(taskToReplace, taskName, Priority.getPriorityFromString(priorityParam));
            if (updated) {
                resp.sendRedirect(req.getContextPath() + "/tasks-list");
            } else {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                req.setAttribute("task", taskToReplace);
                req.getRequestDispatcher("/WEB-INF/edit-task.jsp").forward(req, resp);
            }
        } catch (NumberFormatException exception) {
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/error-page.jsp");
            req.setAttribute("error_type", "'%s' is not a valid number!".formatted(req.getParameter("id")));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            rd.forward(req, resp);
        } catch (RuntimeException exception) {
            req.setAttribute("error_type", "Task with ID '%s' not found in TODO list!".formatted(req.getParameter("id")));
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/error-page.jsp");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            rd.forward(req, resp);
        }
    }
}
