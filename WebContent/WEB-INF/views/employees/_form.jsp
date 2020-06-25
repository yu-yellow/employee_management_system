<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for="code">従業員番号</label><br />
<c:choose>
    <c:when test="${ employees.id!=null }">
        <input type="text" name="code" value="${employees.code}" disabled />
        <input type="hidden" name="code" value="${employees.code}" />
    </c:when>
    <c:otherwise>
        <input type="text" name="code" value="${employees.code}" />
    </c:otherwise>
</c:choose>
    <br /><br />

<label for="name">氏名</label><br />
<input type="text" name="name_kanzi" value="${employees.name_kanzi}" />
<br /><br />

<label for="hurigana">ふりがな</label><br />
<input type="text" name="name_kana" value="${ employees.name_kana }" />
<br /><br />

<label for="password">パスワード</label><br />
<input type="password" name="password" value="${ password.password }" />
<br /><br />

<label for="belongs_num">所属</label><br />
<select name="belongs_num">
    <c:forEach var="belongsnum" items="${ belongsnum }" varStatus="status">
        <option value="${ belongsnum.belongs_id }" <c:if test="${ employees.belongs.belongs_id == belongsnum.belongs_id}">selected</c:if>><c:out value="${ belongsnum.belongs_name }" /></option>
    </c:forEach>
</select>
<br /><br />

<label for="birthday_at">生年月日</label><br />
<input name="birthday_at" type="date" value="${employees.birthday_at}" />
<br /><br/>

<label for="join_at">入社日</label><br />
<input name="join_at" type="date" value="${employees.join_at}" />
<br /><br/>

<label for="leave_at">退社日</label><br />
<input name="leave_at" type="date" value="${employees.leave_at}" />
<br /><br/>
<label for="admin_flag">権限</label><br />
<select name="admin_flag">
    <option value="0"<c:if test="${employees.admin_flag == 0}"> selected</c:if>>一般</option>
    <option value="1"<c:if test="${employees.admin_flag == 1}"> selected</c:if>>管理者</option>
</select>
<br /><br />
<input type="hidden" name="_token" value="${_token}" />
