<%@ page import="org.example.project_2.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.project_2.repo.OrderRepo" %>
<%@ page import="org.example.project_2.entity.User" %>
<%@ page import="org.example.project_2.repo.UserRepo" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="static/bootstrap.min.css">
</head>
<body>

<%
    List<Order> orders = OrderRepo.findAll();
    List<User> users = UserRepo.findAll();
    User currentUser = (User) request.getSession().getAttribute("currentUser");
%>

<nav class="navbar bg-body-tertiary bg-dark">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <a class="btn btn-dark text-white" href="open/open.jsp">Open</a>
        <a class="btn btn-dark text-white" href="inprogres/inprogres.jsp">Inprogres</a>
        <a class="btn btn-dark text-white" href="complete/complete.jsp">Complete</a>
        <div>
            <% if (currentUser == null) { %>
            <a class="btn btn-dark text-white" href="auth/login.jsp">Login</a>
            <% } else { %>
            <a class="btn btn-dark text-white" href="../logout">Logout</a>
            <% } %>
        </div>
    </div>
</nav>


<div class="col-9">
    <hr>
    <div class="p-4">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>User</td>
                <th>Order_Id</th>
                <th>Data</th>
                <th>Status</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <% for (Order order : orders) {%>
            <tr>
                <% User user = users.stream().filter(u -> u.getOrders().contains(order)).findFirst().orElse(null);%>
                <% if (user != null) { %>
                <td><%= user.getFirstName() %>
                </td>
                <% } %>
                <td><%= order.getId()%>
                </td>
                <td><%= order.getDateTime()%>
                </td>
                <td><%=order.getStatus()%>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>
</html>