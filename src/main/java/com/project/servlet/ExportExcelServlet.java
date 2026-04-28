/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.dao.TicketDAO;
import com.project.model.Ticket;

@WebServlet("/ExportExcelServlet")
public class ExportExcelServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        TicketDAO dao = new TicketDAO();
        List<Ticket> list = dao.getAllTickets(); // later we can filter monthly

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tickets");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Name");
        header.createCell(1).setCellValue("Title");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Category");
        header.createCell(4).setCellValue("Priority");
        header.createCell(5).setCellValue("Status");

        int rowCount = 1;
        for (Ticket t : list) {
            Row row = sheet.createRow(rowCount++);

            row.createCell(0).setCellValue(t.getName());
            row.createCell(1).setCellValue(t.getTitle());
            row.createCell(2).setCellValue(t.getDescription());
            row.createCell(3).setCellValue(t.getCategory());
            row.createCell(4).setCellValue(t.getPriority());
            row.createCell(5).setCellValue(t.getStatus());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=tickets.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}