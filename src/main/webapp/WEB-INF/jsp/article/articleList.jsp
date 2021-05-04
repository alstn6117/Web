<%@ page import="kr.mjc.minsu.web.dao.Article" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<body>
<h3>기사 목록</h3>
<p><a href="./loginForm">로그인</a> <a href="./articleForm">회원가입</a></p>
<%
    List<Article> articleList = (List<Article>) request.getAttribute("articleList");
    for (Article article : articleList) {%>
<p><%= article %>
</p>
<%
    }
%>
</body>
</html>