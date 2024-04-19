package org.example.project_2.sevrlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.project_2.entity.User;
import org.example.project_2.repo.UserRepo;

import java.io.IOException;

@WebServlet(name = "auth", value = "/auth/login")
public class AuthSevrlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = UserRepo.findByEmailAndPassword(email, password);
        req.getSession().setAttribute("currentUser", user);
        Cookie cookie = new Cookie(
                "userId", user.getId().toString()
        );
        cookie.setSecure(false);
        cookie.setMaxAge(60 * 60 * 3);
        cookie.setPath("/");
        resp.addCookie(cookie);

        if (user.getPassword().equals(password)) {
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/auth/login.jsp");
        }
    }
}
