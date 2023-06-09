<%@ page import="com.javaee.bitlab.database.models.User" %>
<%@ page import="com.javaee.bitlab.database.models.Category" %>
<%@ page import="java.util.ArrayList" %>
<%
    User currentUser = (User) session.getAttribute("authUser");
%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<nav class="navbar navbar-expand-lg  navbar-light" style="background-color: #e3f2fd;">
    <a class="navbar-brand" href="/">News</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Categories
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <%
                        ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
                        if (categories != null)
                            for (Category category : categories) {
                    %>
                    <a class="dropdown-item" href="/?category=<%=category.getId()%>"><%=category.getName()%>
                    </a>
                    <%}%>
                </div>
            </li>
            <%
                if (currentUser != null) {
            %>
            <li class="nav-item">
                <a class="nav-link" href="/add/news">Add news</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Profile</a>
            </li>
            <%
                if (currentUser.isAdmin()) {
            %>
            <li class="nav-item">
                <a class="nav-link" href="/admin/users/">Admin</a>
            </li>
            <%}%>
            <%
            } else {
            %>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register">Register</a>
            </li>
            <%}%>
        </ul>
        <%
            String key = request.getParameter("keyword");
            if(key==null) key = "";
        %>
        <form action="/home" class="form-inline my-2 my-lg-0">
            <input value="<%=key%>" class="form-control mr-sm-2" type="search" name="keyword" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>


