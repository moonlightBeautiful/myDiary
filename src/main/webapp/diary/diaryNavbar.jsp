<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar-inner">
    <div class="container">
        <a class="brand" href="#">屌丝的日记本</a>
        <div class="nav-collapse collapse">
            <ul class="nav">
                <li class="active"><a href="main?all=true"><i class="icon-home"></i>&nbsp;主页</a></li>
                <li class="active"><a href="diary?action=preSave"><i class="icon-pencil"></i>&nbsp;写日记</a></li>
                <li class="active"><a href="#"><i class="icon-book"></i>&nbsp;日记分类管理</a></li>
                <li class="active"><a href="#"><i class="icon-user"></i>&nbsp;个人中心</a></li>
            </ul>
        </div>
        <form name="myForm" class="navbar-form pull-right" method="post" action="main?all=true">
            <input class="span2" id="s_title" name="s_title" type="text" style="margin-top:5px;height:30px;"
                   placeholder="往事如烟...">
            <button type="submit" class="btn" onkeydown="if(event.keyCode==13) myForm.submit()"><i
                    class="icon-search"></i>&nbsp;搜索日志
            </button>
        </form>
    </div>
</div>

