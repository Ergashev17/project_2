package org.example.project_2.config;

import jakarta.persistence.EntityManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.project_2.entity.User;

import java.io.IOException;
import java.util.UUID;

import static org.example.project_2.config.DBConfig.entityManagerFactory;

@WebFilter(urlPatterns = "/superuser/*")
public class SuperUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object object = session.getAttribute("currentUser");
        String password = request.getParameter("password");
        if (object != null) {
            User user = (User) object;
            if (user.getPassword().equals(password)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("userId")) {
                UUID userId = UUID.fromString(cookie.getValue());
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                User user = entityManager.find(User.class, userId);
                request.getSession().setAttribute("currentUser", user);
                if (user.getPassword().equals(password)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
            break;
        }

        response.sendRedirect("404");


    }
}
