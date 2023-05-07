package com.javaee.bitlab.servlets;

import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("authUser") == null) {
            response.sendRedirect("/login");
            return;
        }
        ArrayList<Category> categories = CategoryService.getCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
