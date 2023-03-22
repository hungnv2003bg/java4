
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/SP23B2_SOF3011_IT17321_war_exploded/css/bootstrap.min.css" />
</head>
<body>
<div class="col-8 offset-2 mt-5 table-responsive">
    <div class="row">
        <div class="col-6">
            <a href="/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/create" class="btn btn-success">Thêm mới chức vụ</a>
        </div>
    </div>
    <c:if test="${ f:length(danhSachCV) == 0 }">
        <h3>Không có dữ liệu</h3>
    </c:if>
    <c:if test="${ f:length(danhSachCV) != 0 }">
        <table class="table table-striped mt-3">
            <thead class="table-primary">
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th colspan="2">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ danhSachCV }" var="cv">
            <tr>
                <td>${ cv.ma }</td>
                <td>${ cv.ten }</td>
                <td>
                    <a href="/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/edit?ma=${ cv.ma }"
                       class="btn btn-primary">Cập nhật</a>
                </td>
                <td>
                    <a href="/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/delete?ma=${ cv.ma }"
                    class="btn btn-danger">Xóa</a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<script src="/SP23B2_SOF3011_IT17321_war_exploded/js/bootstrap.min.js"></script>
</body>
</html>
