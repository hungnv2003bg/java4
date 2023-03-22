<%--
  Created by IntelliJ IDEA.
  User: Hung
  Date: 2023-03-16
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/SP23B2_SOF3011_IT17321_war_exploded/css/bootstrap.min.css" />
</head>
<body>
<div class="col-8 offset-2">
    <form method="POST"
          action="/SP23B2_SOF3011_IT17321_war_exploded/ctsp/update?sanPham=${ ctsp.sanPham }">
        <div class="row mt-3">
            <div class="col-6">
                <label>Sản phẩm</label>
                <select name="sanPham" class="form-select">
                    <option value="Asus Gaming">Asus Gaming</option>
                    <option value="Dell M4800">Dell M4800</option>
                    <option value="Nokia 1280">Nokia 1280</option>
                </select>
            </div>
            <div class="col-6">
                <label>Nhà SX</label>
                <select name="nsx" class="form-select">
                    <option value="Dell">Dell</option>
                    <option value="Asus">Asus</option>
                    <option value="Nokia">Nokia</option>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Màu sắc</label>
                <select name="mauSac" class="form-select">
                    <option value="Red">Red</option>
                    <option value="Blue">Blue</option>
                    <option value="Gray">Gray</option>
                </select>
            </div>
            <div class="col-6">
                <label>Dòng SP</label>
                <select name="dongSP" class="form-select">
                    <option value="PC">PC</option>
                    <option value="Laptop">Laptop</option>
                    <option value="Smart">Smart</option>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">

                <label >Năm BH</label>
                <select class="form-select" name="namBH">
                    <option>2018</option>
                    <option>2019</option>
                    <option>2020</option>
                    <option>2021</option>
                    <option>2022</option>
                    <option>2023</option>
                </select>
            </div>
            <div class="col-6">
                <label>Số lượng tồn</label>
                <input type="text" name="soLuongTon" class="form-control" value="${ ctsp.soLuongTon }" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Mô tả </label>
                <input type="radio" name="moTa" value="1" checked/>Còn hàng
                <input type="radio" name="moTa" value="0"/>Hết hàng
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Giá nhập</label>
                <input type="text" name="giaNhap" class="form-control" value="${ ctsp.giaNhap }" />
            </div>
            <div class="col-6">
                <label>Giá bán</label>
                <input type="text" name="giaBan" class="form-control value="${ ctsp.giaBan }" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <button class="btn btn-primary">Cập nhật</button>
            </div>
            <div class="col-6"></div>
        </div>
    </form>
</div>
<script src="/SP23B2_SOF3011_IT17321_war_exploded/js/bootstrap.min.js"></script>
</body>
</html>
