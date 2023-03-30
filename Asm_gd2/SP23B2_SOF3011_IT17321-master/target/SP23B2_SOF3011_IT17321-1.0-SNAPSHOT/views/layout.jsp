<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý</title>
    <link rel="stylesheet"
          href="/SP23B2_SOF3011_IT17321_war_exploded/css/bootstrap.min.css" />
</head>
<body>
<div class="mt-3">
    <h1 class="text-center">Quản lý</h1>
</div>

<nav class="nav" style="margin-left: 250px">
    <a class="nav-link" href="/SP23B2_SOF3011_IT17321_war_exploded/chuc-vu/index">Chức vụ</a>
    <a class="nav-link" href="/SP23B2_SOF3011_IT17321_war_exploded/cua-hang/index">Cửa hàng</a>
    <a class="nav-link" href="/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/index">Nhân viên</a>
    <a class="nav-link" href="/SP23B2_SOF3011_IT17321_war_exploded/khach-hang/index">Khách hàng</a>
    <a class="nav-link" href="/SP23B2_SOF3011_IT17321_war_exploded/san-pham/index">Sản phẩm</a>
    <a class="nav-link" href="/SP23B2_SOF3011_IT17321_war_exploded/ctsp/index">Chi tiết SP</a>
    <a class="nav-link" href="/SP23B2_SOF3011_IT17321_war_exploded/nsx/index">NSX</a>
    <a class="nav-link" href="/SP23B2_SOF3011_IT17321_war_exploded/mau-sac/index">Màu sắc</a>
    <a class="nav-link" href="/SP23B2_SOF3011_IT17321_war_exploded/dsp/index">Dòng SP</a>
</nav>
<div class="row bg-dark text-white position-relative overflow-hidden" style="min-height: 250px; width: 1300px; margin-left: 150px">
    <img class="img-fluid"  style="position: absolute; top: 0; left: 0; width: 100%; height: 100%;" src="/SP23B2_SOF3011_IT17321_war_exploded/img/banner.png" alt="" >
</div>
<div class="row mt-4" style="min-height: 500px;">
    <div class="col-3">
        Left content
    </div>
    <div class="col-9">
        <jsp:include page="${ view }" />
    </div>
</div>


<script src="/SP23B2_SOF3011_IT17321_war_exploded/js/bootstrap.min.js"></script>
</body>
</html>
