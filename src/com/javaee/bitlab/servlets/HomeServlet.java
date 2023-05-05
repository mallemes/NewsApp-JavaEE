package com.javaee.bitlab.servlets;

import com.javaee.bitlab.database.DBConnection;
import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.models.Item;
import com.javaee.bitlab.database.models.News;
import com.javaee.bitlab.database.service.CategoryService;
import com.javaee.bitlab.database.service.NewsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<News> news = NewsService.getNewsList();
        request.setAttribute("news_list", news);
        ArrayList<Category> categories = CategoryService.getCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }


}
