<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<div class="navbar">
    <div>
        <strong>BITLAB SHOP</strong>
    </div>
    <div>
        <a class="navbuttons" href="/">Top Sales</a>
        <a class="navbuttons" href="/">New Sales</a>
        <a class="navbuttons" href="/add_item">Add Item</a>
        <a class="navbuttons" href="/profile">profile</a>
    </div>
</div>
<div class="container" style="margin-top: 60px">
    <div  class="login">
        <div class="card bg-light mb-3" style="max-width: 46rem;box-shadow: 2px 2px 2px 2px rgba(0.6, 0.6, 0.6, 0.6);">
            <div class="card-header">
                <h3>ADD ITEM</h3>
            </div>
            <form method="post" action="/add_item" class="card-body">
                <div class="form-group">
                    <label for="exampleInputEmail1">name :</label>
                    <input required type="text" class="form-control" name="name" id="exampleInputEmail1" aria-describedby="emailHelp"
                           placeholder="name">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Description :</label>
                    <input required type="text" name="description" class="form-control" id="exampleInputPassword1" placeholder="Description">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail3">Price :</label>
                    <input required type="number" name="price" class="form-control" id="exampleInputEmail3" aria-describedby="emailHelp"
                           placeholder="Price">
                </div>
                <button class="btn btn-success" type="submit" style="margin-top: 10px">submit</button>
            </form>
        </div>

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