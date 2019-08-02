package com.java1234.web;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.util.DateUtil;
import com.java1234.util.DbUtil;
import com.java1234.util.PropertiesUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class UserServlet extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if ("preSave".equals(action)) {
            this.userPreSave(request, response);
        } else if ("save".equals(action)) {
            this.userSave(request, response);
        }

    }

    private void userPreSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mainPage", "user/userSave.jsp");
        request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
    }

    private void userSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        boolean imageChange = false;
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                //通文本表单字段为null，文件表单字段为""或者文件名
                String name = fileItem.getName();
                //获得字段中的name
                String fieldName = fileItem.getFieldName();
                //是一个普通文本表单字段，还是一个文件表单字段
                if (fileItem.isFormField()) {
                    if ("nickName".equals(fieldName)) {
                        user.setNickName(fileItem.getString("utf-8"));
                    }
                    if ("mood".equals(fieldName)) {
                        user.setMood(fileItem.getString("utf-8"));
                    }
                } else if (!"".equals(fileItem.getName())) {
                    try {
                        imageChange = true;
                        String imageName = DateUtil.getCurrentDateStr();
                        user.setImageName(imageName + "." + fileItem.getName().split("\\.")[1]);
                        String filePath = PropertiesUtil.getValue("imagePath") + imageName + "." + fileItem.getName().split("\\.")[1];
                        //write方法用于将FileItem对象中保存的主体内容保存到某个指定的文件中
                        fileItem.write(new File(filePath));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (!imageChange) {
            user.setImageName(user.getImageName().replaceFirst(PropertiesUtil.getValue("imageFolder"), ""));
        }

        Connection con = null;
        try {
            con = dbUtil.getCon();
            int saveNums = userDao.userUpdate(con, user);
            if (saveNums > 0) {
                user.setImageName(PropertiesUtil.getValue("imageFolder") + user.getImageName());
                session.setAttribute("currentUser", user);
                request.getRequestDispatcher("main?all=true").forward(request, response);
            } else {
                request.setAttribute("currentUser", user);
                request.setAttribute("error", "保存失败！");
                request.setAttribute("mainPage", "user/userSave.jsp");
                request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
