package com.java1234.web;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

public class LoginServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        Connection con = null;
        try {
            con = dbUtil.getCon();
            User user = new User(userName, password);
            User currentUser = userDao.login(con, user);
            if (currentUser == null) {
                //登陆失败
                request.setAttribute("user", user);
                request.setAttribute("error", "用户名或密码错误！");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                //登陆成功
                if (remember != null &&remember.equals("remember-me")) {
                    Cookie cookie = new Cookie("user", userName + "-" + password);
                    cookie.setMaxAge(1 * 60 * 60 * 24 * 7);
                    response.addCookie(cookie);
                }
                session.setAttribute("currentUser", currentUser);
                response.sendRedirect("main.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
