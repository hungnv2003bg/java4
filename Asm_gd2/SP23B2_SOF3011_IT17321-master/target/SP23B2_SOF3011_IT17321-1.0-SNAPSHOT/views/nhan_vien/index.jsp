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
            <a href="/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/create" class="btn btn-success">Thêm mới nhân viên</a>
        </div>
    </div>
    <c:if test="${ f:length(danhSachNV) == 0 }">
        <h3>Không có dữ liệu</h3>
    </c:if>
    <c:if test="${ f:length(danhSachNV) != 0 }">
        <table class="table table-striped mt-3">
            <thead class="table-primary">
            <tr>
                <th>Mã</th>
                <th>Họ</th>
                <th>Tên đệm</th>
                <th>Tên</th>
                <th>Ngày Sinh</th>
                <th>Giới tính</th>
                <th>SĐT</th>
                <th>Địa chỉ</th>
                <th>Trạng thái</th>
                <th>Chức vụ</th>
                <th>Cửa hàng</th>
                <th colspan="2">Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ danhSachNV }" var="nv">
            <tr>
                <td>${ nv.ma }</td>
                <td>${ nv.ho }</td>
                <td>${ nv.tenDem}</td>
                <td>${ nv.ten }</td>
                <td>${ nv.ngaySinh }</td>
                <td>${ nv.gioiTinh }</td>
                <td>${ nv.sdt }</td>
                <td>${ nv.diaChi }</td>
                <td>
                    <c:if test="${nv.trangThai == '0'}">Làm</c:if>
                    <c:if test="${nv.trangThai == '1'}">Nghỉ</c:if>
                </td>
                <td>${ nv.chucVu.ten}</td>
                <td>${ nv.cuaHang.ten}</td>
                <td>
                    <a href="/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/edit?ma=${ nv.ma }"
                       class="btn btn-primary">Cập nhật</a>
                </td>
                <td>
                    <a class="btn btn-danger"
                       href="/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/delete?ma=${ nv.ma }">
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
