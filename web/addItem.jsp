<!DOCTYPE html>
<html>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container " style="margin-top: 60px; margin-left: 14rem">
    <div class="login">
        <div class="card bg-light mb-3" style="max-width: 46rem;box-shadow: 2px 2px 2px 2px rgba(0.6, 0.6, 0.6, 0.6);">
            <div class="card-header">
                <h3>ADD NEWS</h3>
            </div>
            <form action="/add/news" method="post">
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
                                   placeholder="Insert title:">
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
                                      rows="3"></textarea>
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
                                <option value="<%=category.getId()%>"><%=category.getName()%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-success">Create</button>
                </div>
            </form>
        </div>

    </div>
</div>

</body>
</html>