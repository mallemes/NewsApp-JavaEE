<%@ page import="java.util.ArrayList" %>
<%@ page import="com.javaee.bitlab.database.models.News" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html>
<head>
    <title>bitlab shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
          crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>

<%@include file="includes/navbar.jsp" %>

<div class="container mt-3">
    <div class="row mt-3">
        <div class="col-12">
            <%
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
                ArrayList<News> news_list = (ArrayList<News>) request.getAttribute("news_list");
                if (news_list != null) {
                    for (News n : news_list) {
            %>
            <div class="p-5 mb-3" style="background-color: #e3f2fd;">
                <a href="/news-details?id=<%=n.getId()%>">
                    <h3><%=n.getTitle()%>
                    </h3>
                </a>
                <p><%=n.getContent()%>
                </p>
                <p>
                    Posted by: <strong><%=n.getUser().getFullName()%><br>
                </strong>
                    At: <strong><%=n.getCreatedAt().toLocalDateTime().format(myFormatObj)%>
                </strong>
                </p>
                <p>Category: <%=n.getCategory().getName()%></p>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>

</body>
</html>