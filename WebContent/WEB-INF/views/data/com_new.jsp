<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>勤務先会社情報登録ページ</h2>

        <c:if test="${errors != null}">
            <div id="flush_error">
                入力内容にエラーがあります。<br />
                <c:forEach var="error" items="${errors}">
                    ・<c:out value="${error}" /><br />
                </c:forEach>
            </div>
        </c:if>

        <form method="POST" action="<c:url value='/data/create' />">
            <input type="hidden" name="id" value="${id}" />
            <label for="name">会社名</label><br />
            <input type="text" name="name" value="${company.name}" />
            <br /><br />

            <label for="address">住所</label><br />
            <textarea rows="2" cols="40" maxlength="200" name="address"><c:out value="${company.address}" /></textarea>
            <br /><br />

            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">登録</button>
        </form>

        <p><a href="<c:url value='/working/worknew?id=${id}' />">戻る</a></p>
    </c:param>
</c:import>