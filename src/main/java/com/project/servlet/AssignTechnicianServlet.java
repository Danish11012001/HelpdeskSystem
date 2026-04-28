/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.project.servlet;

import com.project.dao.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AssignTechnicianServlet")
public class AssignTechnicianServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("ticketId"));
        
        String name = request.getParameter("technician");
        String contact = request.getParameter("contact");

        // Combine into single string
        String technicianData = name + " (" + contact + ")";

        TicketDAO dao = new TicketDAO();
        dao.assignTechnician(id, technicianData);

        response.sendRedirect("ViewTicketsServlet");
    }
}