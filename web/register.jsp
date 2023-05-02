<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tasks</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
          crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<div class="navbar">
    <div>
        <strong>BITLAB SHOP</strong>
    </div>
    <div>
        <a class="navbuttons" href="/">Top Sales</a>
        <a class="navbuttons" href="/">New Sales</a>
        <a class="navbuttons" href="/register">register</a>
        <a class="navbuttons" href="/login">login</a>
    </div>
</div>
<div class="container" style="margin-top: 60px">
    <%
        String error = (String) request.getAttribute("error");
        String email = (String) request.getAttribute("email");
        String fullName = (String) request.getAttribute("fullName");
        if (error != null){
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>error</strong>  <%=error%>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <%}%>
    <form method="post" action="/register" class="login">
        <div class="card bg-light mb-3" style="max-width: 46rem;box-shadow: 2px 2px 2px 2px rgba(0.6, 0.6, 0.6, 0.6);">
            <div class="card-header">
                <h3>REGISTER PAGE</h3>
            </div>
            <div class="card-body">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email :</label>
                    <input  <%if (email != null){%>value="<%=email%>"<%}%> name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                           placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password :</label>
                    <input name="password" type="password" class="form-control" id="exampleInputPassword1"
                           placeholder="enter Password">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword5">Re-enter password :</label>
                    <input name="password2" type="password" class="form-control" id="exampleInputPassword5"
                           placeholder="repeat Password">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail3">Full Name :</label>
                    <input  <%if (fullName != null){%>value="<%=fullName%>" <%}%> name="fullName" type="text" class="form-control" id="exampleInputEmail3" aria-describedby="emailHelp"
                           placeholder="Enter full name">
                </div>
                <button class="btn btn-success" style="margin-top: 10px">register</button>
            </div>
        </div>

    </form>
</div>
</div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
