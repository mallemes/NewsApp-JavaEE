<%@ page import="com.javaee.bitlab.database.models.Item" %>
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
    <div class="navbar">
        <div>
            <strong>BITLAB SHOP</strong>
        </div>
        <div id="navbar">
            <a class="navbuttons" href="/">Top Sales</a>
            <a class="navbuttons" href="/">New Sales</a>
        </div>
    </div>

    <%
        Item item = (Item) request.getAttribute("item");
    %>
    <div style="display: flex; justify-content: center">
        <div style="width: 60%; margin-top: 5px">
            <form action="/details" method="post">
                <div class="form-group" style=" margin-top: 20px">
                    <label> Name:
                        <input style="width: 600px" class="form-control" name="name" type="text"
                               value="<%=item.getName()%>">
                    </label>
                </div>
                <div class="form-group" style=" margin-top: 20px">
                    <label> Description:
                        <textarea class="form-control" name="description" id="" cols="75"
                                  rows="4"><%=item.getDescription()%></textarea>
                    </label>
                </div>
                <div class="form-group" style=" margin-top: 20px">
                    <label> Deadline:
                        <input style="width: 600px" class="form-control" name="price" type="number"
                               value="<%=item.getPrice()%>">
                    </label>
                </div>

                <input type="hidden" name="id" value="<%=item.getId()%>">
                <button class="btn btn-success">save</button>
            </form>
            <form action="/delete" method="post">
                <input type="hidden" name="id" value="<%=item.getId()%>">
                <button class="btn btn-danger">delete</button>
            </form>
        </div>
    </div>
</div>
<script>
    const activeUser = localStorage.getItem("user")
    const navbar = document.getElementById("navbar")
    if (activeUser) {
        let prf = Object.assign(document.createElement('a'), {
            href: "/profile",
            className: "navbuttons",
            innerText: "profile"
        })
        let addI = Object.assign(document.createElement('a'), {
            href: "/add_item",
            className: "navbuttons",
            innerText: "add Item"
        })
        navbar.appendChild(prf)
        navbar.appendChild(addI)
    } else {
        const register = Object.assign(document.createElement('a'), {
            href: "/register",
            className: "navbuttons",
            innerText: "register"
        });
        let login = Object.assign(document.createElement('a'), {
            href: "/login",
            className: "navbuttons",
            innerText: "login"
        });
        navbar.appendChild(register)
        navbar.appendChild(login)
    }
</script>
</body>
</html>
