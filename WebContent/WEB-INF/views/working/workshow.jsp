<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${ working != null }">
                <h2> 【${ working.company.name }】の現場　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>勤務先会社名</th>
                            <td><c:out value="${ working.company.name }" /></td>
                        </tr>
                        <tr>
                            <th>勤務先所在地</th>
                            <td><c:out value="${ working.company.address }" /></td>
                        </tr>
                        <tr>
                            <th>現場責任者</th>
                            <td><c:out value="${ working.field_manager }" /></td>
                        </tr>
                        <tr>
                            <th>勤務時間</th>
                            <td>
                                <fmt:formatDate value="${ working.open }" pattern="HH:mm" />
                                ～
                                <fmt:formatDate value="${ working.close }" pattern="HH:mm" />
                            </td>
                        </tr>
                        <tr>
                            <th>休憩時間</th>
                            <td><c:out value="${ working.breaktime }" />分</td>
                        <tr>
                            <th>契約期間</th>
                            <td>
                                <fmt:formatDate value="${working.start_at}" pattern="yyyy/MM/dd" />
                                 ～
                                <fmt:formatDate value="${working.end_at}" pattern="yyyy/MM/dd" />
                            </td>
                        </tr>
                        <tr>
                            <th>契約状況</th>
                            <td>
                                <c:choose>
                                    <c:when test="${working.end_flag == 0}">待機中</c:when>
                                    <c:when test="${working.end_flag == 1}">継続中</c:when>
                                    <c:when test="${working.end_flag == 2}">終了</c:when>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>業務内容</th>
                            <td><c:out value="${working.content}" /></td>
                        </tr>
                        <tr>
                            <th>備考</th>
                            <td><c:out value="${working.note}" /></td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${working.end_flag != 2}">
                <p><a href="<c:url value='/working/workedit?wid=${working.id}' />">詳細内容を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/working/workindex?id=${working.employee.id}' />">戻る</a></p>
    </c:param>
</c:import>