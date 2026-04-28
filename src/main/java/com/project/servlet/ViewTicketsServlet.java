package com.project.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.project.dao.TicketDAO;
import com.project.model.Ticket;

@WebServlet("/ViewTicketsServlet")
public class ViewTicketsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");
        String username = (String) session.getAttribute("username");
        
        
        TicketDAO dao = new TicketDAO();
        List<Ticket> tickets;

        if ("admin".equals(role)) {
            tickets = dao.getAllTickets();
        } else {
            tickets = dao.getTicketsByUser(username);
        }

        request.setAttribute("tickets", tickets);
        RequestDispatcher rd = request.getRequestDispatcher("viewTickets.jsp");
        rd.forward(request, response);
    }
}