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
    <c:if test="${ f:length(danhSachCTSP) == 0 }">
        <h3>Không có dữ liệu</h3>
    </c:if>
    <c:if test="${ f:length(danhSachCTSP) != 0 }">
        <table class="table table-striped mt-3">
            <thead class="table-primary">
            <tr>
                <th>Sản phẩm</th>
                <th>NSX</th>
                <th>Màu sắc</th>
                <th>Dòng SP</th>
                <th>Năm BH</th>
                <th>Mô tả</th>
                <th>Số LT</th>
                <th>Giá nhập</th>
                <th>Giá bán</th>
                <th colspan="2">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ danhSachCTSP }" var="ctsp">
            <tr>
                <td>${ ctsp.sanPham }</td>
                <td>${ ctsp.nsx }</td>
                <td>${ ctsp.mauSac }</td>
                <td>${ ctsp.dongSP }</td>
                <td>${ ctsp.namBH }</td>
                <td>${ ctsp.moTa }</td>
                <td>${ ctsp.soLuongTon }</td>
                <td>${ ctsp.giaNhap }</td>
                <td>${ ctsp.giaBan }</td>
                <td>
                    <a href="/SP23B2_SOF3011_IT17321_war_exploded/ctsp/edit?sanPham=${ ctsp.sanPham }"
                       class="btn btn-primary">Cập nhật</a>
                </td>
                <td>
                    <a class="btn btn-danger"
                       href="/SP23B2_SOF3011_IT17321_war_exploded/ctsp/delete?sanPham=${ ctsp.sanPham }">
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
