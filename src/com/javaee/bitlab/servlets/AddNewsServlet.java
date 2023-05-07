package com.javaee.bitlab.servlets;

import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.models.News;
import com.javaee.bitlab.database.models.User;
import com.javaee.bitlab.database.service.CategoryService;
import com.javaee.bitlab.database.service.NewsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/add/news")
public class AddNewsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Category> categories = CategoryService.getCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/addItem.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute("authUser") == null) {
            response.sendRedirect("/login");
            return;
        }
        User user = (User) request.getSession().getAttribute("authUser");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Long categoryId = Long.parseLong(request.getParameter("categoryId"));
        News news = new News(
                null, title, content, user, new Category(categoryId, null), null);
        NewsService.createNews(news);
        response.sendRedirect("/?success");
    }
}
