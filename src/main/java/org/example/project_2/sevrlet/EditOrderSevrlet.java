package org.example.project_2.sevrlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.project_2.entity.Order;
import org.example.project_2.entity.Status;
import org.example.project_2.repo.OrderRepo;
import org.example.project_2.repo.StatusRepo;

import java.io.IOException;

@WebServlet(name = "editOrder", value = "/edit/order")
public class EditOrderSevrlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int statusId = Integer.parseInt(req.getParameter("statusId"));
        Status status = StatusRepo.findById(statusId);

        Order order = OrderRepo.findById(id);
        order.setId(id);
        order.setStatus(status);
        resp.sendRedirect("/order/order.jsp");
    }

}
