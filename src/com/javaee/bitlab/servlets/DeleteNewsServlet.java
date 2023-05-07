package com.javaee.bitlab.servlets;

import com.javaee.bitlab.database.models.News;
import com.javaee.bitlab.database.models.User;
import com.javaee.bitlab.database.service.NewsService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-news")
public class DeleteNewsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("authUser") == null) {
            response.sendRedirect("/login");
            return;
        }
        User user = (User) request.getSession().getAttribute("authUser");
        News news = NewsService.getNews(Long.parseLong(request.getParameter("news_id")));
        if (news.getUser().getId().equals(user.getId())) {
            NewsService.deleteNews(news);
        }
        response.sendRedirect("/home");
    }

}
