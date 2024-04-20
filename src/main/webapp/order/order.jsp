<%@ page import="org.example.project_2.repo.OrderRepo" %>
<%@ page import="org.example.project_2.entity.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jasur
  Date: 4/19/2024
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../static/bootstrap.min.css">
</head>
<body>

<%
    List<Order> orders = OrderRepo.findAll();
%>

<div class="col-3 border right p-4">
    <ul>
        <a href="../order/order.jsp">
            <li class="list-group-item bg-dark text-white ">Order</li>
        </a>
        <a href="../user/user.jsp">
            <li class="list-group-item ">User</li>
        </a>
    </ul>
</div>

<div class="col-9">
    <hr>
    <div class="p-4">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Order_Id</th>
                <th>Data</th>
                <th>Status</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <% for (Order order : orders) {%>
            <tr>
                <td><%= order.getId()%>
                </td>
                <td><%= order.getDateTime()%>
                </td>
                <td><%=order.getStatus()%>
                </td>
                <td>
                    <a href="editOrder.jsp?id=<%=order.getId()%>" class="btn btn-info">Edit</a>
                    <a class="btn btn-dark" href="http://localhost:8080/order/delete?ID=<%= order.getId()%>">delete</a>
                </td>
            </tr>

            <% } %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
