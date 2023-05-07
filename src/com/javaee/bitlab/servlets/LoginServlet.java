package com.javaee.bitlab.servlets;

import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.models.User;
import com.javaee.bitlab.database.service.CategoryService;
import com.javaee.bitlab.database.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("authUser") != null) {
            response.sendRedirect("/profile");
            return;
        }
        ArrayList<Category> categories = CategoryService.getCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("authUser") != null) {
            response.sendRedirect("/home");
            return;
        }
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = UserService.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            if (user.isBanned()) {
                request.setAttribute("error", "user is banned");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("authUser", user);
                response.sendRedirect("/profile");
            }
        } else {
            request.setAttribute("error", "wrong password or email");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        }
    }
}