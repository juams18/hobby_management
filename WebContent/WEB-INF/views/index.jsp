<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>Hobbyの一覧</h2>
        <table id="hobby_list">
            <tbody>
                <tr>
                    <th class="Amazon_picture">画像用将来スペース</th>
                    <th class="kind">分類</th>
                    <th class="title">タイトル</th>
                    <th class="report_date">登録日</th>
                    <th class="delete">削除</th>
                </tr>
                <c:forEach var="hobby" items="${hobbies}" varStatus="status">
                    <tr>
                        <td class="Amazon_picture">ここに画像</td>
                        <td class="kind">${hobby.kind}</td>
                        <td class="title">${hobby.title}</td>
                        <td class="report_date">${hobby.created_at}</td>
                        <td class="delete">削除(ポップアップあとから付ける)</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- 以下のリンク違うかも -->
        <p><a href="<c:url value='/new' />">新規登録</a></p>

    </c:param>
</c:import>