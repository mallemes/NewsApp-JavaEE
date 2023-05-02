package com.javaee.bitlab.servlets;

import com.javaee.bitlab.database.service.UserService;
import com.javaee.bitlab.database.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = UserService.findUserThroughEmail(email);
          if (user!= null && Objects.equals(user.getPassword(), password)) {
            request.setAttribute("user", user);
              System.out.println(user.getEmail());
              System.out.println(user.getFullName());
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "wrong password or email");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}