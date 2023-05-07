package com.javaee.bitlab.servlets;
import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.models.Comment;
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

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        News news = NewsService.getNews(id);
        if (news == null) {
            response.sendRedirect("/home");
            return;
        }
        request.setAttribute("news", news);
        ArrayList<Category> categories = CategoryService.getCategories();
        request.setAttribute("categories", categories);
        ArrayList<Comment> comments = NewsService.getNewsComment(news.getId());
        request.setAttribute("comments", comments);
        request.getRequestDispatcher("/news_details.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("authUser") == null) {
            response.sendRedirect("/login");
            return;
        }
        Long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Long categoryId = Long.parseLong(request.getParameter("categoryId"));
        Category category = new Category(categoryId, null);
        NewsService.updateNews(new News(id, title, content, category));
        response.sendRedirect("/news-details?id=" + id);
    }
}
