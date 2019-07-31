<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/images/list_icon.png"/>
        日记类别列表
        <span class="diaryType_add">
			<button class="btn btn-mini btn-success" type="button">添加日志类别</button>
		</span>
    </div>
    <div>
        <table class="table table-hover table-striped">
            <tr>
                <th>编号</th>
                <th>类别名称</th>
                <th>操作</th>
            </tr>
            <c:forEach var="diaryType" items="${diaryTypeList }">
                <tr>
                    <td>${diaryType.diaryTypeId }</td>
                    <td>${diaryType.typeName }</td>
                    <td>
                        <button class="btn btn-mini btn-info" type="button">修改</button>&nbsp;<button
                            class="btn btn-mini btn-danger" type="button">删除
                    </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>