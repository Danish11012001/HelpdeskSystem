/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.project.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.project.dao.TicketDAO;

@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        int id = Integer.parseInt(request.getParameter("ticketId"));
        String status = request.getParameter("status");

        TicketDAO dao = new TicketDAO();
        if ("admin".equals(role)) {
            dao.updateStatus(id, "CLOSED");
        } 
        else if ("user".equals(role)) {
            if ("Resolved".equals(status)) {
                dao.updateStatus(id, status);
            } else {
                response.getWriter().println("Access Denied");
                return;
            }
        }

        response.sendRedirect("ViewTicketsServlet");
    }
}