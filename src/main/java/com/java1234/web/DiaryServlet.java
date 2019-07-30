package com.java1234.web;

import com.java1234.dao.DiaryDao;
import com.java1234.model.Diary;
import com.java1234.util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class DiaryServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    DbUtil dbUtil = new DbUtil();
    DiaryDao diaryDao = new DiaryDao();

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
        /*
            show：根据日志id显示日志内容
         */
        if ("show".equals(action)) {
            this.diaryShow(request, response);
        }
    }

    private void diaryShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String diaryId = request.getParameter("diaryId");
        Connection con = null;
        try {
            con = dbUtil.getCon();
            Diary diary = diaryDao.diaryShow(con, diaryId);
            request.setAttribute("diary", diary);
            request.setAttribute("mainPage", "diary/diaryShow.jsp");
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
