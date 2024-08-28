package view;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import controller.dao.TaskDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-task")
public class DeleteTaskServlet extends HttpServlet {
    private static final TaskDAO dao = TaskDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskIdStr = req.getParameter("id");
        if (taskIdStr != null) {
            try {
                int taskId = Integer.parseInt(taskIdStr);
                boolean success = dao.delete(dao.getById(taskId));
                if (success) {
                    resp.sendRedirect(req.getContextPath() + "/tasks-list");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/error.jsp?message=Task%20not%20found");
                }
            } catch (NumberFormatException e) {
                resp.sendRedirect(req.getContextPath() + "/error.jsp?message=Invalid%20task%20ID");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp?message=Task%20ID%20is%20required");
        }
    }
}