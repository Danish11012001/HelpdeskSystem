
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<title>Success</title>

<style>
body {
    font-family: Arial;
    background: linear-gradient(to right, #00c6ff, #0072ff);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.card {
    background: white;
    padding: 30px;
    border-radius: 10px;
    text-align: center;
    box-shadow: 0 0 15px rgba(0,0,0,0.2);
}

h2 {
    color: green;
}

a {
    display: inline-block;
    margin-top: 15px;
    padding: 10px 20px;
    background: #0072ff;
    color: white;
    text-decoration: none;
    border-radius: 5px;
}

a:hover {
    background: #0056cc;
}
</style>

</head>
<body>

<div class="card">
    <h2>✅ Ticket Created Successfully</h2>
    <p>Your issue has been recorded.</p>

    <a href="ViewTicketsServlet">View Tickets</a>
    <br><br>
    <a href="raiseTicket.jsp">Raise Another Ticket</a>
</div>

</body>
</html>