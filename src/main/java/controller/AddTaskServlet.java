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


@WebServlet("/create-task")
public class AddTaskServlet extends HttpServlet {
    private static TaskDAO dao;

    @Override
    public void init(ServletConfig config) {
        dao = TaskDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/add-task.jsp");
        req.setAttribute("lastId", Task.getIncrementator().getCurrentId());
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
                Task task = new Task(taskName, Priority.getPriorityFromString(priorityParam));
                if (dao.tryAdd(task)) {
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                } else {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                    req.setAttribute("taskName", taskName);
                }
            }
        } else {
           resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        doGet(req, resp);
    }
}