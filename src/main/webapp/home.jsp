<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 15-Feb-22
  Time: 9:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View Products</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
          integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"
          integrity="sha512-NhSC1YmyruXifcj/KFRWoC561YpHpc5Jtzgvbuzx5VozKpWvQ+4nXhPdFgmx8xqexRcpAglTj9sIBWINXa8x5w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet" href="/home.css">
</head>
<body>
<br>
<table>
    <tr class="title">
        <td class="add-file">
            <button type="button" class="btn btn-outline-primary" style="margin: 20px">
                <a href="/home?action=createGet">
                    <i class="fa-solid fa-plus"></i> Add New Product</a>
            </button>
        </td>
        <td class="search-file">
            <form class="nav-link d-flex" style="margin: 0" action="/home?action=searchByName" method="post">
                <table>
                    <tr>
                        <td>
                            <input class="form-control mr-2" type="text" size="25" placeholder="Search" name="nameSearch">
                        </td>
                        <td>
                            <button class="btn btn-light ml-2" type="submit">
                                <i class="fa-solid fa-magnifying-glass"></i> Search
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>

<br>
<br>
<div class="card">
    <h5 class="card-header font-weight-light">
        <a href="${pageContext.request.contextPath}/home" style="text-decoration: none">
            <span class="fas fa-tasks fa-fw mr-3"></span>
            <span><b>Product List</b></span>
        </a>
    </h5>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Color</th>
                    <th>Category</th>
                    <th colspan="2">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${productCategories}" var="productCategory">
                    <tr>
                        <td>${productCategory.getProduct().getIdProduct()}</td>
                        <td>${productCategory.getProduct().getNameProduct()}</td>
                        <td>${productCategory.getProduct().getPrice()}</td>
                        <td>${productCategory.getProduct().getQuantity()}</td>
                        <td>${productCategory.getProduct().getColor()}</td>
                        <td>${productCategory.getNameCategory()}</td>
                        <td>
                            <button type="button" class="btn btn-info">
                                <a style="color: white; text-decoration: none"
                                   href="/home?action=editGet&idProduct=${productCategory.getProduct().getIdProduct()}"><i
                                        class="fa-solid fa-pencil"></i></a>
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger">
                                <a style="color: white; text-decoration: none"
                                   href="/home?action=delete&idProduct=${productCategory.getProduct().getIdProduct()}"><i
                                        class="fa-solid fa-trash"></i></a>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
</body>
</html>