<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>

<div class="col-8 offset-2">
    <form method="POST"
          action="/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/store">
        <div class="row mt-3">
            <div class="col-6">
                <label>Mã</label>
                <input type="text" name="ma" class="form-control"  required/>
            </div>
            <div class="col-6">
                <label>Họ</label>
                <input type="text" name="ho" class="form-control"  required pattern="[a-zA-Z ]+" title="Họ bắt buộc phải là chữ" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Tên đệm</label>
                <input type="text" name="tenDem" class="form-control" required pattern="[a-zA-Z ]+" title="Tên đệm bắt buộc phải là chữ" />
            </div>
            <div class="col-6">
                <label>Tên</label>
                <input type="text" name="ten" class="form-control" required pattern="[a-zA-Z ]+" title="Tên bắt buộc phải là chữ" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Ngày sinh</label>
                <input type="date" name="ngaySinh" class="form-control"  required/>
            </div>
            <div class="col-6">
                <label>SDT</label>
                <input type="tel" name="sdt" class="form-control"  required pattern="[0-9]{10}" title="Số điện thoại phải có 10 số" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Địa chỉ</label>
                <input type="text" name="diaChi" class="form-control" required />
            </div>
            <div class="col-6">
                <label>Giới tính</label>
                <input type="text" name="gioiTinh" class="form-control" required pattern="[a-zA-Z ]+" title="Giới tính bắt buộc phải là chữ" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Trạng thái</label>
                <div class="row">
                    <input class="form-check col" type="radio" name="trangThai" value="0" checked/> <label class="col">Làm</label>
                    <input class="form-check col" type="radio" name="trangThai" value="1"/><label class="col">Nghỉ</label>
                </div>

            </div>
            <div class="col-6">
                <label>Chức vụ</label>
                <select class="form-select" name="idChucVu" id="" >
                    <c:forEach var="cv" items="${ danhSachCV }">
                        <option value="${cv.id}">${cv.ten}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Cửa Hàng</label>
                <select class="form-select" name="idCuaHang" >
                    <c:forEach var="ch" items="${ danhSachCH }">
                        <option value="${ch.id}" >${ch.ten}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-6">
                <label>Mật Khẩu</label>
                <input type="text" name="matKhau" class="form-control" required />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <button class="btn btn-primary">Thêm mới</button>
            </div>
            <div class="col-6"></div>
        </div>
    </form>
</div>

