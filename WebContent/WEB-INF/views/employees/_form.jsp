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
<%-- <label for="code">社員番号</label><br />
<input type="text" name="code" value="${employees.code}" disabled /> --%>
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
    <option value="0" <c:if test="${ employees.belongs_num == 0}">selected</c:if>>大阪第1</option>
    <option value="1" <c:if test="${ employees.belongs_num == 1}">selected</c:if>>大阪第2</option>
    <option value="2" <c:if test="${ employees.belongs_num == 2}">selected</c:if>>大阪第3</option>
</select>
<br /><br />

<label for="birthday_at">生年月日</label><br />
<input name="birthday_at" type="date" value="${ employees.birthday_at }" />
<br /><br/>

<label for="join_at">入社日</label><br />
<input name="join_at" type="date" value="${ employees.join_at }" />
<br /><br/>

<label for="leave_at">退職日</label><br />
<input name="leave_at" type="date" value="${ employees.leave_at }" />
<br /><br/>
<label for="admin_flag">権限</label><br />
<select name="admin_flag">
    <option value="0"<c:if test="${employee.admin_flag == 0}"> selected</c:if>>一般</option>
    <option value="1"<c:if test="${employee.admin_flag == 1}"> selected</c:if>>管理者</option>
</select>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>