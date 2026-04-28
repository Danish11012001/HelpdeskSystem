package com.project.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.project.dao.TicketDAO;

@WebServlet("/DeleteTicketServlet")
public class DeleteTicketServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            TicketDAO dao = new TicketDAO();
            dao.deleteTicket(id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("ViewTicketsServlet");
    }
}