<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/images/user_icon.png"/>
        个人中心
    </div>
</div>

<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/images/byType_icon.png"/>
        按日志类别
    </div>
    <div class="data_list">
        <ul>
            <c:forEach var="diaryTypeCount" items="${diaryTypeCountList }">
                <li><span><a href="#">${diaryTypeCount.typeName }(${diaryTypeCount.diaryCount })</a></span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/images/byDate_icon.png"/>
        按日志日期
    </div>
    <div class="datas">
        <ul>
            <c:forEach var="diaryCount" items="${diaryReleaseDateCountList }">
                <li><span><a href="#">${diaryCount.releaseDateStr }(${diaryCount.diaryCount })</a></span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
