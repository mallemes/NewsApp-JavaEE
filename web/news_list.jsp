<%@ page import="java.util.ArrayList" %>
<%@ page import="com.javaee.bitlab.database.models.News" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<%
    ArrayList<News> news_list = (ArrayList<News>) request.getAttribute("news_list");
%>
<div>
    <%
        for (News n: news_list) {
    %>
    <div><h1 style="color: red"><%=n.getTitle()%></h1></div>
    <%}%>
</div>

</body>
</html>
