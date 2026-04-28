package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;

import com.project.model.Ticket;

public class TicketDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/helpdesk";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "";

    public void insertTicket(Ticket ticket) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            

            String sql = "INSERT INTO tickets (name, title, description, category, priority, status, username, solution) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ticket.getName());
            ps.setString(2, ticket.getTitle());
            ps.setString(3, ticket.getDescription());
            ps.setString(4, ticket.getCategory());
            ps.setString(5, ticket.getPriority());
            ps.setString(6, ticket.getStatus());
            ps.setString(7,ticket.getUsername());
            ps.setString(8,ticket.getSolution());
        

            ps.executeUpdate();
            
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Ticket> getAllTickets() {
    List<Ticket> list = new ArrayList<>();

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        String sql = "SELECT * FROM tickets";
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Ticket t = new Ticket();

            t.setName(rs.getString("name"));
            t.setTitle(rs.getString("title"));
            t.setDescription(rs.getString("description"));
            t.setCategory(rs.getString("category"));
            t.setPriority(rs.getString("priority"));
            t.setStatus(rs.getString("status"));
            t.setId(rs.getInt("id"));
            t.setSolution(rs.getString("solution"));
            t.setTechnician(rs.getString("technician"));

            list.add(t);
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
    public void updateStatus(int id, String status) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        String sql = "UPDATE tickets SET status=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1,status);
        ps.setInt(2, id);

        ps.executeUpdate();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public List<Ticket> searchTickets(String keyword, String category, String status) {
    List<Ticket> list = new ArrayList<>();

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        String sql = "SELECT * FROM tickets WHERE 1=1";

        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND (name LIKE ? OR title LIKE ?)";
        }
        if (category != null && !category.isEmpty()) {
            sql += " AND category=?";
        }
        if (status != null && !status.isEmpty()) {
            sql += " AND status=?";
        }

        PreparedStatement ps = con.prepareStatement(sql);

        int i = 1;

        if (keyword != null && !keyword.isEmpty()) {
            ps.setString(i++, "%" + keyword + "%");
            ps.setString(i++, "%" + keyword + "%");
        }
        if (category != null && !category.isEmpty()) {
            ps.setString(i++, category);
        }
        if (status != null && !status.isEmpty()) {
            ps.setString(i++, status);
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Ticket t = new Ticket();
            t.setId(rs.getInt("id"));
            t.setName(rs.getString("name"));
            t.setTitle(rs.getString("title"));
            t.setDescription(rs.getString("description"));
            t.setCategory(rs.getString("category"));
            t.setPriority(rs.getString("priority"));
            t.setStatus(rs.getString("status"));
            t.setSolution(rs.getString("solution"));
            t.setTechnician(rs.getString("technician"));

            list.add(t);
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
    public List<Ticket> getTicketsByUser(String username) {
    List<Ticket> list = new ArrayList<>();

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        String sql = "SELECT * FROM tickets WHERE username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Ticket t = new Ticket();
            t.setId(rs.getInt("id"));
            t.setName(rs.getString("name"));
            t.setTitle(rs.getString("title"));
            t.setDescription(rs.getString("description"));
            t.setCategory(rs.getString("category"));
            t.setPriority(rs.getString("priority"));
            t.setStatus(rs.getString("status"));
            t.setSolution(rs.getString("solution"));
            t.setTechnician(rs.getString("technician"));
            list.add(t);
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
    public void deleteTicket(int id) {

    String query = "DELETE FROM tickets WHERE id=?";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
   public void assignTechnician(int id, String tech) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        String query = "UPDATE tickets SET technician=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, tech);
        ps.setInt(2, id);

        ps.executeUpdate();

    } catch(Exception e) {
        e.printStackTrace();
    }
}
}
