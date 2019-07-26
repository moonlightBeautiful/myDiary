<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人日记本主页</title>
    <link href="${pageContext.request.contextPath}/style/diary.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }
    </style>
</head>
<body>
<!-- 导航条 -->
<div class="navbar navbar-inverse navbar-fixed-top">
    <jsp:include page="${pageContext.request.contextPath}/diary/diaryNavbar.jsp"></jsp:include>
</div>
<!-- 日志 -->
<div class="container">
    <div class="row-fluid">
        <!-- 日志列表或者内容 -->
        <div class="span9">
            <jsp:include page="${mainPage }"></jsp:include>
        </div>
        <!-- 个人信息和日志分类 -->
        <div class="span3">
            <jsp:include page="${pageContext.request.contextPath}/diary/diaryRightList.jsp"></jsp:include>
        </div>
    </div>
</div>
</div>
</body>
</html>