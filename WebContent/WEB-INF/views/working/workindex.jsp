<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>現場履歴</h2>

        <table id="employee_list">
            <tbody>
                <tr>
                    <th>勤務先</th>
                    <th>契約期間</th>
                    <th>契約状態</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="working" items="${working}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${working.company.name}" /></td>
                        <td><fmt:formatDate value="${working.start_at}" pattern="yyyy/MM/dd" /> ～ <fmt:formatDate value="${working.end_at}" pattern="yyyy/MM/dd" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${working.end_flag == 0}">待機中</c:when>
                                <c:when test="${working.end_flag == 1}">継続中</c:when>
                                <c:when test="${working.end_flag == 2}">終了</c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${working.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/working/workshow?wid=${working.id}' />">詳細を表示</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${working_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((woring_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/working/workindex?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/working/worknew?id=${ id }' />">現場登録</a></p>
        <p><a href="<c:url value='/employees/show?id=${ id }' />">戻る</a></p>

    </c:param>
</c:import>