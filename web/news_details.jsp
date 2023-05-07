<%@ page import="com.javaee.bitlab.database.models.News" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.javaee.bitlab.database.models.Comment" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.Duration" %>
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
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
        News news = (News) request.getAttribute("news");
    %>
    <div class="p-5 container" style="background-color: #e3f2fd;">
        <h3><%=news.getTitle()%>
        </h3>
        <p><%=news.getContent()%>
        </p>
        <p>
            Posted by <strong><%=news.getUser().getFullName()%>
            <br>
        </strong>
            At: <strong><%=news.getCreatedAt().toLocalDateTime().format(myFormatObj)%>
        </strong>
        </p>
        <p>Category: <%=news.getCategory().getName()%>
        </p>
        <% if (currentUser != null && Objects.equals(currentUser.getId(), news.getUser().getId())) { %>
        <div>
            <form action="/delete-news" method="post">
                <input type="hidden" name="newsId" value="<%=news.getId()%>">
                <button class="btn btn-danger">delete</button>
            </form>
        </div>
        <div>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                edit
            </button>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form action="/news-details" method="post">
                            <input type="hidden" name="id" value="<%=news.getId()%>">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit News</h1>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-12">
                                        <label>
                                            TITLE :
                                        </label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                        <input type="text" class="form-control" name="title" required
                                               placeholder="Insert title:" value="<%=news.getTitle()%>">
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <label>
                                            CONTENT :
                                        </label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                                <textarea class="form-control" name="content"
                                                          placeholder="Insert content:" required
                                                          rows="3"><%=news.getContent()%></textarea>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <label>
                                            CATEGORY :
                                        </label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                        <select class="form-control" name="categoryId">
                                            <%
                                                for (Category category : categories) {
                                            %>
                                            <option value="<%=category.getId()%>" <%=Objects.equals(category.getId(), news.getCategory().getId()) ? "selected" : ""%>><%=category.getName()%>
                                            </option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button class="btn btn-success">Update</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        <%
            if (currentUser != null) {
        %>
        <div class="container mt-3">
            <form action="/add-comment" method="post">
                <input type="hidden" name="newsId" value="<%=news.getId()%>">
                <div class="row">
                    <div class="col-10" style="display:flex;">
                        <textarea rows="1" class="form-control" name="comment"></textarea>
                        <button class="btn btn-success btn-sm" style="height: 40px;margin-left: 10px">ADD COMMENT
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <%
            }
        %>


    </div>
    <h1 class="text-center">Comments </h1>
    <div class="container">
        <div class="col-12">
            <%

                ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
                if (comments != null) {
                    for (Comment comment : comments) {
            %>
            <div class="list-group mt-2">
                <a href="JavaScript:void(0)" class="list-group-item list-group-item-action">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1"><%=comment.getUser().getFullName()%>
                        </h5>
                        <%
                            LocalDateTime desiredDateTime = comment.getCreatedAt().toLocalDateTime();
                            LocalDateTime now = LocalDateTime.now();
                            Duration duration = Duration.between(desiredDateTime, now);
                            long minutesAgo = duration.toMinutes();
                        %>
                        <small class="text-body-secondary"><%=minutesAgo%> minutes ago</small>
                    </div>
                    <p class="mb-1"><%=comment.getComment()%>
                    </p>
                </a>
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


