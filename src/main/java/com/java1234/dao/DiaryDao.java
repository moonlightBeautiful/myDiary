package com.java1234.dao;

import com.java1234.model.Diary;
import com.java1234.model.PageBean;
import com.java1234.util.DateUtil;
import com.java1234.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiaryDao {

    public List<Diary> diaryList(Connection con, PageBean pageBean) throws Exception {
        List<Diary> diaryList = new ArrayList<Diary>();
        StringBuffer sb = new StringBuffer("select * from t_diary t1,t_diaryType t2 where t1.typeId=t2.diaryTypeId ");
        sb.append(" order by t1.releaseDate desc");
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Diary diary = new Diary();
            diary.setDiaryId(rs.getInt("diaryId"));
            diary.setTitle(rs.getString("title"));
            diary.setContent(rs.getString("content"));
            diary.setReleaseDate(DateUtil.formatStrToDate(rs.getString("releaseDate"), "yyyy-MM-dd HH:mm:ss"));
            diaryList.add(diary);
        }
        return diaryList;
    }

    public int diaryCount(Connection con) throws Exception {
        StringBuffer sb = new StringBuffer("select count(*) as total from t_diary t1,t_diaryType t2 where t1.typeId=t2.diaryTypeId ");
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    public List<Diary> diaryReleaseDateCountList(Connection con) throws Exception {
        List<Diary> diaryCountList = new ArrayList<Diary>();
        String sql = "SELECT DATE_FORMAT(releaseDate,'%Y年%m月') as releaseDateStr ,COUNT(*) AS diaryCount  FROM t_diary GROUP BY DATE_FORMAT(releaseDate,'%Y年%m月') ORDER BY DATE_FORMAT(releaseDate,'%Y年%m月') DESC;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Diary diary = new Diary();
            diary.setReleaseDateStr(rs.getString("releaseDateStr"));
            diary.setDiaryCount(rs.getInt("diaryCount"));
            diaryCountList.add(diary);
        }
        return diaryCountList;
    }

    public static void main(String[] args) throws Exception {
        Integer i = 2;
        i.toString();

        DbUtil dbUtil = new DbUtil();
        DiaryDao diaryDao = new DiaryDao();
        Connection con = dbUtil.getCon();
        String sql = "select * from t_diary";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Diary diary = new Diary();
            diary.setDiaryId(rs.getInt("diaryId"));
            diary.setTitle(rs.getString("title"));
            diary.setContent(rs.getString("content"));
            diary.setReleaseDate(DateUtil.formatStrToDate(rs.getString("releaseDate"), "yyyy-MM-dd HH:mm:ss"));
            String s1 = rs.getString("releaseDate");
            Date date = rs.getDate("releaseDate");
            String s2 = rs.getString("diaryId");
            String s3 = rs.getString("diaryId");
        }
    }
}
