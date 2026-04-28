/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

/**
 *
 * @author mahmo
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        String redirectPage = request.getParameter("redirect");

        if ("admin".equals(username) && "admin123".equals(password)) {

            session.setAttribute("role", "admin");
            session.setAttribute("username", username);

        } 
        else if ("user".equals(username) && "user123".equals(password)) {

            session.setAttribute("role", "user");
            session.setAttribute("username", username);

        } 
        else {
            response.getWriter().println("<h3>Invalid Login</h3>");
            return;
        }
        
        if (redirectPage != null && !redirectPage.isEmpty()) {
            response.sendRedirect(redirectPage);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}