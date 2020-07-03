<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>

<c:if test="${flush != null}">
    <div id="flush_success">
        <c:out value="${flush}"></c:out>
    </div>
</c:if>

<label for="company_id">勤務先会社名</label><br />
<select name="company_id">
    <c:forEach var="company" items="${ company }" varStatus="status">
        <option value="${ company.id }" <c:if test="${ working.company.id == company.id}">selected</c:if>><c:out value="${ company.name }" /></option>
    </c:forEach>
</select>&nbsp;&nbsp;&nbsp;
<a href="<c:url value='/data/new?id=${id}' />">会社名登録</a>
<br /><br />

<label for="field_manager">現場責任者</label><br />
<input type="text" name="field_manager" value="${ working.field_manager }" />
<br /><br />

<label>勤務時間</label><br />
<input name="open" type="time" value="<fmt:formatDate value='${working.open}' pattern='HH:mm' />"/>
～
<input name="close" type="time" value="<fmt:formatDate value='${working.close}' pattern='HH:mm' />" />
<br /><br/>

<label for="breaktime">休憩時間</label><br />
<input type="text" size= "3" maxlength="5" name="breaktime" value="${working.breaktime}" />&nbsp;分
<br /><br />

<label>契約期間</label><br />
<input name="start_at" type="date" value="${ working.start_at }" />
～
<input name="end_at" type="date" value="${ working.end_at }" />
<br /><br/>

<label for="content">業務内容</label><br />
<textarea rows="3" cols="40" maxlength="100" name="content"><c:out value="${working.content}" /></textarea>
<br /><br />

<label for="end_frag">契約状態</label><br />
<select name="end_flag">
    <option value="0"<c:if test="${working.end_flag == 0}"> selected</c:if>>待機中</option>
    <option value="1"<c:if test="${working.end_flag == 1}"> selected</c:if>>継続中</option>
    <option value="2"<c:if test="${working.end_flag == 2}"> selected</c:if>>終了</option>
</select>
<br /><br />

<label for="note">備考</label><br />
<textarea rows="5" cols="40" maxlength="200" name="note"><c:out value="${working.note}" /></textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
