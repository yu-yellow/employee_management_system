<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>現場　新規登録ページ</h2>

        <form method="POST" action="<c:url value='/working/workcreate' />">
            <c:import url="_formW.jsp" />
            <input type="hidden" name="id" value="${ id }" />
            <button type="submit">登録</button>
        </form>

        <p><a href="<c:url value='/working/workindex?id=${ id }' />">戻る</a></p>
    </c:param>
</c:import>