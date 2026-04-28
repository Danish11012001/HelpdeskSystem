<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
    <%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    }
    %>  
<html>
<head>
<title>Dashboard</title>
</head>

<style>
body {
    font-family: Arial;
    background: linear-gradient(to right, #4facfe, #00f2fe);
    text-align: center;
    color: white;
}

.container {
    margin-top: 150px;
}

h1 {
    font-size: 40px;
}

a {
    display: inline-block;
    margin: 20px;
    padding: 12px 25px;
    background: white;
    color: #333;
    text-decoration: none;
    border-radius: 8px;
    font-weight: bold;
}

a:hover {
    background: #ddd;
}
</style>

</head>


<body>

<h2>Welcome, <%= session.getAttribute("username") %></h2>

<a href="raiseTicket.jsp">Raise Ticket</a><br><br>
<a href="ViewTicketsServlet">View Tickets</a><br><br>
<a href="LogoutServlet">Logout</a>

</body>
</html>