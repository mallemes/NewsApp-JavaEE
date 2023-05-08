package com.javaee.bitlab.servlets.admin;

import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.models.User;
import com.javaee.bitlab.database.service.AdminService;
import com.javaee.bitlab.database.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/users/")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("authUser") == null && !((User) req.getSession().getAttribute("authUser")).isAdmin()) {
            resp.sendRedirect("/login");
            return;
        }
        ArrayList<User> users = AdminService.getUserList();
        req.setAttribute("users", users);
        ArrayList<Category> categories = CategoryService.getCategories();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("authUser") == null && !((User) req.getSession().getAttribute("authUser")).isAdmin()) {
            resp.sendRedirect("/login");
            return;
        }
        Long UserId = Long.parseLong(req.getParameter("userId"));
        if (req.getParameter("action") != null) {
            if (req.getParameter("action").equals("true"))
                AdminService.toggleBanUser(UserId, false);
            else if (req.getParameter("action").equals("false"))
                AdminService.toggleBanUser(UserId, true);
        }
        resp.sendRedirect("/admin/users/");
    }
}
