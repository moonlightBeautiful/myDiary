package com.java1234.dao;

import com.java1234.model.DiaryType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DiaryTypeDao {

    /**
     * 日志按照类别统计
     *
     * @param con
     * @return
     * @throws Exception
     */
    public List<DiaryType> diaryTypeCountList(Connection con) throws Exception {
        List<DiaryType> diaryTypeCountList = new ArrayList<DiaryType>();
        String sql = "SELECT diaryTypeId,typeName,COUNT(diaryId) as diaryCount FROM t_diary RIGHT JOIN t_diaryType ON t_diary.typeId=t_diaryType.diaryTypeId GROUP BY typeName;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            DiaryType diaryType = new DiaryType();
            diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
            diaryType.setTypeName(rs.getString("typeName"));
            diaryType.setDiaryCount(rs.getInt("diaryCount"));
            diaryTypeCountList.add(diaryType);
        }
        return diaryTypeCountList;
    }
}
