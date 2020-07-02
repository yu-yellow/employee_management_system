<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${working != null}">
                <h2>【${working.company.name}】の現場情報　編集ページ</h2>
                <form method="POST" action="<c:url value='/working/workupdate?wid=${working.id}' />">
                    <c:import url="_formW.jsp" />
                    <button type="submit">更新</button>
                </form>

                <p><a href="#" onclick="confirmDestroy();">削除する</a></p>
                <form method="POST" action="<c:url value='/working/workdestroy?wid=${working.id}' />">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>

                <script>
                    function confirmDestroy() {
                        if(confirm("本当に削除してよろしいですか？")) {
                            document.forms[1].submit();
                        }
                    }

                </script>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/working/workindex?id=${ working.employee.id }' />">戻る</a></p>
    </c:param>
</c:import>