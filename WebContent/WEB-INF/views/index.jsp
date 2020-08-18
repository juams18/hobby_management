<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
         <h2>Hobbyの一覧</h2>
         <img src="<c:url value='/img/editbutton.png' />" width="50">

        <table id="hobby_list">
            <tbody>
                <tr>
                    <th class="Amazon_picture">画像用将来スペース</th>
                    <th class="kind">分類</th>
                    <th class="title">タイトル</th>
                    <th class="report_date">登録日</th>
                    <th class="control">操作</th>
                </tr>
                <c:forEach var="hobby" items="${hobbies}" varStatus="status">
                    <tr>
                        <td class="Amazon_picture">ここに画像</td>
                        <td class="kind">${hobby.kind}</td>
                        <td class="title">${hobby.title}</td>
                        <td class="report_date">${hobby.report_date}</td>
                        <td class="control">

                            <img src="editbutton.png">
                            <a href="<c:url value='/edit?id=${hobby.id}' />">編集(画像)</a>

                            <img src="deletebutton.png">
                            <a href="#" onclick="confirmDestroy();">削除(画像)</a>
                            <form method="POST" action="${pageContext.request.contextPath}/destroy?id=${hobby.id}">
                                <input type="hidden" name="_token" value="${_token}" />
                            </form>
                            <script>
                            function confirmDestroy() {
                                if(confirm("本当に削除してよろしいですか？")) {
                                    document.forms[<c:out value ="${status.index}"/>].submit();
                                    }
                                }

                            </script>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

         <p><a href="<c:url value='/new' />">新規登録</a></p>

    </c:param>
</c:import>