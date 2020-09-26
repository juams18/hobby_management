<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.4/css/all.css">
<script src="https://www.w3schools.com/lib/w3.js"></script>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
         <h2>Hobbyの一覧</h2>

        <table class="sorttbl"id="myTable">
            <tbody>
                <tr>
                    <th class="Amazon_picture">イメージ</th>
                    <th class="kind" onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(2)')">分類<i class="fa fa-sort"></i></th>
                    <th class="title" onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(3)')">タイトル<i class="fa fa-sort"></i></th>
                    <th class="report_date" onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(4)')">登録日<i class="fa fa-sort"></i></th>
                    <th class="control">操作</th>
                </tr>
                <c:forEach var="hobby" items="${hobbies}" varStatus="status">
                    <tr class="item">
                        <td class="Amazon_picture"><img src = "${hobby.rakuten_picture}"></td>
                        <td class="kind">${hobby.kind}</td>
                        <td class="title">${hobby.title}</td>
                        <td class="report_date">${hobby.report_date}</td>
                        <td class="control">

                            <a href="<c:url value='/edit?id=${hobby.id}' />"><img src="<c:url value='/img/editbutton.png' />" width="50"></a>

                            <a href="#" onclick="confirmDestroy(<c:out value ="${status.index}"/>);"><img src="<c:url value='/img/deletebutton.png' />" width="50"></a>
                            <form method="POST" action="${pageContext.request.contextPath}/destroy?id=${hobby.id}">

                                <input type="hidden" name="_token" value="${_token}" />
                            </form>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

         <p><a href="<c:url value='/new' />">新規登録</a></p>
         <p><a href="<c:url value='/import' />">インポート</a></p>
         <p><a href="<c:url value='/export' />" download = "hobby.csv">エクスポート</a></p>

       <script>
        function confirmDestroy(destroyid) {
            if(confirm("本当に削除してよろしいですか？")) {
                document.forms[destroyid].submit();
            }
        }
        </script>
    </c:param>
</c:import>


