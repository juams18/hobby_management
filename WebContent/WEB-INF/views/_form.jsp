<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<label for="kind">分類</label><br />
<select name="kind">
    <option value="空白"></option>
    <option value="本" <c:out value='${hobby.kind=="本"? "selected":""}' /> >本</option>
    <option value="ゲーム" <c:out value='${hobby.kind=="ゲーム"? "selected":""} '/> >ゲーム</option>
    <option value="映画"<c:out value='${hobby.kind=="映画"? "selected":""}' /> >映画</option>
</select>
<br /><br />
<!--<input type="text" name="kind" value="${hobby.kind}" />   -->


<label for="title">タイトル</label><br />
<input type="text" name="title" value="${hobby.title}" />
<br /><br />

<label for="content">詳細</label><br />
<textarea name="content" rows="10" cols="50">${hobby.content}</textarea>
<br /><br />

<label for="report_date">日付</label><br />
<input type="date" name="report_date" value="<fmt:formatDate value='${hobby.report_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>