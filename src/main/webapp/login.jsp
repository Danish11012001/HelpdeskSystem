<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Login</title>

<style>
body {
    font-family: Arial;
    background: linear-gradient(to right, #4facfe, #00f2fe);
}

.container {
    width: 300px;
    margin: 100px auto;
    background: white;
    padding: 20px;
    border-radius: 10px;
    text-align: center;
}

input {
    width: 100%;
    padding: 8px;
    margin: 10px 0;
}

button {
    padding: 10px;
    width: 100%;
    background: #4facfe;
    border: none;
    color: white;
}
</style>
</head>

<body>

<div class="container">
<h2>Login</h2>

<form action="LoginServlet" method="post">
    <input type="text" name="username" placeholder="Username" required>
    <input type="password" name="password" placeholder="Password" required>

    <button type="submit">Login</button>
</form>

</div>

</body>
</html>