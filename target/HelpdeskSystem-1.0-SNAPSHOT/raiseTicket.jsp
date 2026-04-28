<%@ include file="header.jsp" %>
<html>
<head>
<title>Raise Ticket</title>
<%
if (session.getAttribute("username") == null) {
    response.sendRedirect("login.jsp?redirect=raiseTicket.jsp");
}
%>
<a href="login.jsp?redirect=raiseTicket.jsp">Raise Ticket</a>
<style>
body {
    font-family: Arial;
    background: #f4f6f9;
}

.container {
    width: 400px;
    margin: 80px auto;
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px gray;
}

h2 {
    text-align: center;
}

input, textarea, select {
    width: 100%;
    padding: 8px;
    margin: 8px 0;
}

button {
    width: 100%;
    padding: 10px;
    background: #4facfe;
    border: none;
    color: white;
    font-size: 16px;
    border-radius: 5px;
}

button:hover {
    background: #007bff;
}
</style>

</head>
<body>

<div class="container">

<h2>Raise a Ticket</h2>

<form action="TicketServlet" method="post">
    Name:
    <input type="text" name="name" required>

    Title:
    <input type="text" name="title" required>

    Description:
    <textarea name="description" required></textarea>

    Category:
    <select name="category">
        <option>Network</option>
        <option>Software</option>
        <option>Hardware</option>
    </select>

    <button type="submit">Submit Ticket</button>
</form>

</div>

</body>
</html>