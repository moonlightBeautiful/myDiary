package com.java1234.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author gaoxu
 * @date 2019-07-09 17:37
 * @description ... 类
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Object currentUser = session.getAttribute("currentUser");
        String path = request.getServletPath();
        if (currentUser == null && path.indexOf("login") < 0 && path.indexOf("style") < 0&& path.indexOf("bootstrap") < 0 && path.indexOf("images") < 0) {
            //向客户端响应，告诉客户端再发送一个请求去访问login.jsp，客户端受到这个请求后，立刻发出一个新的请求，去请求login.jsp
            response.sendRedirect("login.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}
