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


@WebServlet("/create-task")
public class AddTaskServlet extends HttpServlet {
    private static final TaskDAO dao = TaskDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/add-task.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskName = req.getParameter("taskName");
        String priorityParam = req.getParameter("priority");
        String clearButton = req.getParameter("clear");
        if (taskName != null && !taskName.isEmpty()) {
            if (clearButton != null) {
                dao.deleteByName(taskName);
                resp.sendRedirect(req.getContextPath() + "/create-task");
                return;
            } else if (priorityParam != null) {
                Task task = new Task(taskName, Priorities.getPriorityFromString(priorityParam));
                if (dao.tryAdd(task)) {
                    req.setAttribute("success", "true");
                } else {
                    req.setAttribute("error", "duplicate");
                    req.setAttribute("taskName", taskName);
                }
            }
        } else {
            req.setAttribute("error", "invalid");
        }
        doGet(req, resp);
    }
}