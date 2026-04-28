<%@ include file="header.jsp" %>
<%@ page import="java.util.*, com.project.model.Ticket" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
String role = (String) session.getAttribute("role");
%>
<%
if (session.getAttribute("username") == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
}
%>
<html>
<head>
<title>All Tickets</title>

<style>
body {
    font-family: Arial;
    background-color: #f4f6f9;
}

h2 {
    text-align: center;
}

.top-bar {
    width: 90%;
    margin: auto;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
}

form {
    text-align: center;
    margin-bottom: 20px;
}

table {
    width: 90%;
    margin: auto;
    border-collapse: collapse;
    background: white;
}

th, td {
    padding: 10px;
    text-align: center;
}

th {
    background-color: #333;
    color: white;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}

/* Priority colors */
.high { color: red; font-weight: bold; }
.medium { color: orange; font-weight: bold; }
.low { color: green; font-weight: bold; }

/* Status colors */
.open { color: blue; font-weight: bold; }
.closed { color: gray; font-weight: bold; }

/* Buttons */
button {
    padding: 6px 12px;
    border: none;
    color: white;
    cursor: pointer;
    border-radius: 5px;
    margin: 2px;
}

.close-btn {
    background: red;
}

.delete-btn {
    background: darkred;
}

.excel-btn {
    background: green;
}
</style>

</head>
<body>

<h2>All Tickets</h2>

<!-- 🔝 TOP BAR -->
<div class="top-bar">

    <span>Welcome, <%= session.getAttribute("username") %></span>

    <div>
        <a href="ExportExcelServlet">
            <button class="excel-btn">⬇ Download Excel</button>
        </a>

        <a href="LogoutServlet">
            <button style="background:black;">Logout</button>
        </a>
    </div>

</div>

<!-- 🔍 FILTER -->
<form action="ViewTicketsServlet" method="get">
    <input type="text" name="keyword" placeholder="Search...">

    <select name="category">
        <option value="">All Categories</option>
        <option>Network</option>
        <option>Software</option>
        <option>Hardware</option>
    </select>

    <select name="status">
        <option value="">All Status</option>
        <option>OPEN</option>
        <option>CLOSED</option>
    </select>

    <button type="submit" style="background:black;">Filter</button>
</form>

<!-- 📊 TABLE -->
<table>
<tr>
    <th>Name</th>
    <th>Title</th>
    <th>Description</th>
    <th>Category</th> 
    <th>Priority</th>
    <th>Status</th>
    <th>Solution</th>
    <th>Technician</th>
    <% if ("user".equals(role)) { %>
        <th>Action</th>
    <% } %>
    

    <% if ("admin".equals(role)) { %>
        <th>Assign</th>
        <th>Action</th>
        <th>Delete</th>
    <% } %>
</tr>

<%
List<Ticket> list = (List<Ticket>) request.getAttribute("tickets");

if (list != null) {
    for (Ticket t : list) {
%>

<tr>
    <td><%= t.getName() %></td>
    <td><%= t.getTitle() %></td>
    <td><%= t.getDescription() %></td>
    <td><%= t.getCategory() %></td>

    <!-- Priority -->
    <td class="<%= t.getPriority().toLowerCase() %>">
        <%= t.getPriority() %>
    </td>

    <!-- Status -->
    <td class="<%= t.getStatus().toLowerCase() %>">
        <%= t.getStatus() %>
    </td>
    <td>
    <%
        String sol = t.getSolution();
        if (sol != null) {
            String[] parts = sol.split("\\|");
            for (String s : parts) {
    %>
                <div>💡 <%= s.trim() %></div>
    <%
            }
        }
    %>
    </td>
    
    <td>
    <% 
    if(t.getTechnician() == null || t.getTechnician().isEmpty()) { %>
        <span style="color:orange;">In Process</span>
    <% } else { %>
        <span style="color:green;"><%= t.getTechnician() %></span>
    <% } %>
</td>
    
    <td>
    <!-- ADMIN VIEW -->
    <% if(role.equals("admin")) { %>

        <% if(t.getTechnician() == null || t.getTechnician().isEmpty()) { %>

            <form action="AssignTechnicianServlet" method="post">
                <input type="hidden" name="ticketId" value="<%= t.getId() %>">

                <input type="text" name="technician" placeholder="Name" required style="width:90px;">
                <input type="text" name="contact" placeholder="Contact" required style="width:90px;">

                <button type="submit" style="background: #185fa5; color: #fff; border: none; border-radius: 8px; padding: 7px 18px; font-size: 14px; font-weight: 500; cursor: pointer; display: inline-flex; align-items: center; gap: 8px;">
                <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="#fff" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round">
                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                <circle cx="12" cy="7" r="4"/>
                </svg>
                    Assign
                </button>
            </form>

        <% } else { %>
            <span style="color:green;">Assigned</span>
        <% } %>

    <!-- USER VIEW -->
    <% } else { %>

        <% if(!t.getStatus().equals("Resolved")) { %>

            <form action="UpdateStatusServlet" method="post">
                <input type="hidden" name="ticketId" value="<%= t.getId() %>">
                <input type="hidden" name="status" value="Resolved">

                <button type="submit" style="background:blue; color:white;">
                    Mark as Resolved
                </button>
            </form>

        <% } else { %>

            <span style="color:green; font-weight:bold;">Resolved</span>

        <% } %>

    <% } %>
    </td>
    <% if(role.equals("admin")) { %>
    <!-- Close Button -->
    <td>
        <form action="UpdateStatusServlet" method="post">
            <input type="hidden" name="ticketId" value="<%= t.getId() %>">
            <button class="close-btn">Close</button>
        </form>
    </td>

    <!-- Delete Button -->
    <td>
        <form action="DeleteTicketServlet" method="get">
            <input type="hidden" name="id" value="<%= t.getId() %>">
            <button class="delete-btn" onclick="return confirm('Delete this ticket?')">
                Delete
            </button>
        </form>
    </td>

    <% } %>

</tr>

<%
    }
}
%>

</table>

</body>
</html>