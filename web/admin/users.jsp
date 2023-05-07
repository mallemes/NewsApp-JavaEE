<%@ page import="java.util.Objects" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../includes/navbar.jsp" %>
<div class="container">
    <table class="table table-striped container mt-5">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">Full name</th>
            <th scope="col">is admin</th>
            <th scope="col">edit</th>
        </tr>
        </thead>
        <tbody>
        <% ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
            if (users != null) {
                for (User user : users) {
        %>
        <%if (!Objects.equals(currentUser.getId(), user.getId())) {%>
        <tr>
            <th scope="row"><%=user.getId()%>
            </th>
            <td><%=user.getFullName()%>
            </td>
            <td><%=user.isAdmin()%>
            </td>
            <td>
                <form action="/admin/users/" method="post">
                    <input type="hidden" name="userId" value="<%=user.getId()%>">
                    <input type="hidden" name="action" value="<%=user.isBanned()%>">
                    <button style="width: 70px" class="btn btn-<%if (!user.isBanned()) {%>danger<%}else{%>success<%}%>">
                        <%if (!user.isBanned()) {%>ban<%} else%>unban
                    </button>
                </form>

            </td>
        </tr>
        <%}%>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
