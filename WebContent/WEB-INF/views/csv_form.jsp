<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name = "content">
        <div class="form-group">
            <!--  fileを投稿したい場合、enctypeをmultipart/form-dataにする。-->
            <form method="POST" action="<c:url value='import' />" enctype="multipart/form-data">
                <!--  fileを投稿したい場合、typeをfileにする。-->
                <input name="csv" type="file"  id="csv-form" placeholder="csvを選択"/>
                <button type="submit">登録</button>
            </form>
        </div>
        <p><a href = "<c:url value = '/index '/>">一覧に戻る</a></p>
        <button class="btn btn-danger" id="delete">削除する</button>
    </c:param>
</c:import>
<script>
</script>