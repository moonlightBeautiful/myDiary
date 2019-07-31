package com.java1234.web;

import com.java1234.dao.DiaryTypeDao;
import com.java1234.model.DiaryType;
import com.java1234.util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class DiaryTypeServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    DbUtil dbUtil = new DbUtil();
    DiaryTypeDao diaryTypeDao = new DiaryTypeDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            diaryTypeList(request, response);
        }
    }

    private void diaryTypeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            List<DiaryType> diaryTypeList = diaryTypeDao.diaryTypeList(con);
            request.setAttribute("diaryTypeList", diaryTypeList);
            request.setAttribute("mainPage", "diaryType/diaryTypeList.jsp");
            request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
        } catch (Exception e) {
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
