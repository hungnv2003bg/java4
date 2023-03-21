<%--
  Created by IntelliJ IDEA.
  User: Hung
  Date: 2023-03-16
  Time: 00:59
  To change this template use File | Settings | File Templates.
--%>
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
            <a href="#" class="btn btn-success">Thêm mới</a>
        </div>
    </div>
    <c:if test="${ f:length(danhSachKH) == 0 }">
        <h3>Không có dữ liệu</h3>
    </c:if>
    <c:if test="${ f:length(danhSachKH) != 0 }">
        <table class="table table-striped mt-3">
            <thead class="table-primary">
            <tr>
                <th>Mã</th>
                <th>Họ</th>
                <th>Đệm</th>
                <th>Tên</th>
                <th>Ngày sinh</th>
                <th>SDT</th>
                <th>Địa chỉ</th>
                <th>Thành phố</th>
                <th>Quốc gia</th>
                <th colspan="2">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ danhSachKH }" var="kh">
            <tr>
                <td>${ kh.ma }</td>
                <td>${ kh.ho }</td>
                <td>${ kh.ten_dem }</td>
                <td>${ kh.ten }</td>
                <td>${ kh.ngay_sinh }</td>
                <td>${ kh.sdt }</td>
                <td>${ kh.dia_chi }</td>
                <td>${ kh.thanh_pho }</td>
                <td>${ kh.quoc_gia }</td>
                <td>
                    <a href="/SP23B2_SOF3011_IT17321_war_exploded/khach-hang/edit?ma=${ kh.ma }"
                       class="btn btn-primary">Cập nhật</a>
                </td>
                <td>
                    <a class="btn btn-danger"
                       href="/SP23B2_SOF3011_IT17321_war_exploded/khach-hang/delete?ma=${ kh.ma }">
                        Xóa</a>
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
