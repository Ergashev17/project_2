<%@ page import="org.example.project_2.entity.Order" %>
<%@ page import="org.example.project_2.repo.OrderRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.project_2.entity.Status" %>
<%@ page import="org.example.project_2.repo.StatusRepo" %><%--
  Created by IntelliJ IDEA.
  User: jasur
  Date: 4/19/2024
  Time: 10:43 AM
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
    int orderId = Integer.parseInt(request.getParameter("id"));
    List<Status> statuses = StatusRepo.findAll();
    Order order = OrderRepo.findById(orderId);
%>

<div class="row mt-6">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <h2 class="text-center text-muted">Edit Order</h2>
            <form action="http://localhost:8080/edit/order" method="post" enctype="multipart/form-data">
                <input value="<%=order.getId()%>" id="id" type="hidden">
                <select name="statusId" class="form-control mb-3">
                    <% for (Status status : statuses) {
                        if (status.getId().equals(order.getId())) { %>
                    <option selected value="<%=status.getId()%>"><%=status.getName()%>
                    </option>
                    <% } else %>
                    <option value="<%=status.getId()%>"><%=status.getName()%>
                    </option>
                    <% }%>
                </select>
                <button class="btn btn-dark w-100">Edit</button>
            </form>
        </div>
    </div>
</div>


</body>
</html>
