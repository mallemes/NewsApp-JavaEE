package com.javaee.bitlab.servlets;
import com.javaee.bitlab.database.DBConnection;
import com.javaee.bitlab.database.models.Item;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/details")
public class ItemDetailsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        Item item = DBConnection.getItem(id);
        if (item != null) {
            request.setAttribute("item", item);
            request.getRequestDispatcher("/details.jsp").forward(request, response);
        } else request.getRequestDispatcher("/404.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        DBConnection.updateItem(new Item(id, name, description, price));
        response.sendRedirect("/details?id=" + id);
    }
}
