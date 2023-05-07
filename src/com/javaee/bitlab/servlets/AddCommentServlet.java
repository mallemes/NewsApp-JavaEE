package com.javaee.bitlab.servlets;

import com.javaee.bitlab.database.models.Comment;
import com.javaee.bitlab.database.models.News;
import com.javaee.bitlab.database.models.User;
import com.javaee.bitlab.database.service.CommentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("authUser") == null) {
            response.sendRedirect("/login");
            return;
        }
        User user = (User) request.getSession().getAttribute("authUser");
        Long id = Long.parseLong(request.getParameter("newsId"));
        String comment = request.getParameter("comment");
        News news = new News(id, null, null, null);
        Comment comment1 = new Comment(null, comment, user, news);
        CommentService.addComment(comment1);
        response.sendRedirect("/news-details?id=" + id);
    }
}
