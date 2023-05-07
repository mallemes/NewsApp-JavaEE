<%@ page import="com.javaee.bitlab.database.models.News" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
          crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
    <title>Task details</title>
</head>
<body>

<div>
    <%@include file="includes/navbar.jsp" %>
    <br><br><br><br>
    <%
        News news = (News) request.getAttribute("news");
    %>
    <div class="p-5 container" style="background-color: #e3f2fd;">
        <h3><%=news.getTitle()%>
        </h3>
        <p><%=news.getContent()%>
        </p>
        <p>
            Posted by <strong><%=news.getUser().getFullName()%>
        </strong>
            At <strong><%=news.getCreatedAt()%>
        </strong>
        </p>
        <p>Category: <%=news.getCategory().getName()%>
        </p>
        <% if (currentUser != null && Objects.equals(currentUser.getId(), news.getUser().getId())) { %>
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#exampleModalCenter">
            Edit
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/news-details">
                            <input type="hidden" name="id" value="<%=news.getId()%>">
                            <input class="form-control" type="text" name="title" value="<%=news.getTitle()%>">
                            <textarea class="form-control" name="content"><%=news.getContent()%></textarea>
                            <select class="form-control" name="categoryId">
                                    <%
                                    for (Category category : categories) {
                                %>
                                <option value="<%=category.getId()%>" <%=Objects.equals(category.getId(), news.getCategory().getId()) ? "selected" : ""%>><%=category.getName()%>
                                </option>
                                    <%
                                    }
                                %>
                                <button class="btn btn-success">save</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <% } %>


    </div>
</div>

</body>
</html>
