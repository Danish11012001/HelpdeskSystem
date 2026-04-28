package com.project.servlet;
import com.project.ai.AISuggester;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.project.dao.TicketDAO;
import com.project.model.Ticket;

@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String name = request.getParameter("name");
    String title = request.getParameter("title");
    String description = request.getParameter("description");
    String category = request.getParameter("category");

    HttpSession session = request.getSession();
    String username = (String) session.getAttribute("username");

    String priority;

    if (description.toLowerCase().contains("urgent") ||
        description.toLowerCase().contains("not working")) {
        priority = "HIGH";
    } else if (description.toLowerCase().contains("slow")) {
        priority = "MEDIUM";
    } else {
        priority = "LOW";
    }
    List<String> solution = AISuggester.getTopSolutions(description);
    String combined = String.join(" | ", solution);
    Ticket ticket = new Ticket();
    ticket.setName(name);
    ticket.setTitle(title);
    ticket.setDescription(description);
    ticket.setCategory(category);
    ticket.setPriority(priority);
    ticket.setStatus("OPEN");
    ticket.setUsername(username);
    ticket.setSolution(combined);

    TicketDAO dao = new TicketDAO();
    dao.insertTicket(ticket);

    response.sendRedirect("success.jsp");
}
}