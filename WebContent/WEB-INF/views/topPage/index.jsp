<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>従業員管理システムへようこそ</h2>
        <p><a href="<c:url value='/employees/index' />">従業員一覧</a></p>
        <p><a href="<c:url value='/employees/new' />">従業員登録</a></p>
    </c:param>
</c:import>
