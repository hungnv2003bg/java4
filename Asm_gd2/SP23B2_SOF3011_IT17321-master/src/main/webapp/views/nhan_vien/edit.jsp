<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>


<div class="col-8 offset-2">
    <form method="POST"
          action="/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/update?ma=${nv.ma}">
        <div class="row mt-3">
            <div class="col-6">
                <label>Mã</label>
                <input type="text" name="ma" class="form-control" value="${nv.ma}" disabled/>
            </div>
            <div class="col-6">
                <label>Họ</label>
                <input type="text" name="ho" class="form-control" value="${ nv.ho }" required pattern="[a-zA-Z ]+" title="Họ bắt buộc phải là chữ"/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Tên đệm</label>
                <input type="text" name="tenDem" class="form-control" value="${ nv.tenDem }" required pattern="[a-zA-Z ]+" title="Tên đệm bắt buộc phải là chữ" />
            </div>
            <div class="col-6">
                <label>Tên</label>
                <input type="text" name="ten" class="form-control" value="${ nv.ten }" required pattern="[a-zA-Z ]+" title="Tên bắt buộc phải là chữ" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Ngày sinh</label>
                <input type="date" name="ngaySinh" class="form-control" value="${ nv.ngaySinh }" required/>
            </div>
            <div class="col-6">
                <label>SDT</label>
                <input type="tel" name="sdt" class="form-control"  value="${ nv.sdt }" required pattern="[0-9]{10}" title="Số điện thoại phải có 10 số"/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Địa chỉ</label>
                <input type="text" name="diaChi" class="form-control" value="${ nv.diaChi }" required/>
            </div>
            <div class="col-6">
                <label>Giới tính</label>
                <input type="text" name="gioiTinh" class="form-control" value="${ nv.gioiTinh }" required pattern="[a-zA-Z ]+" title="Giới tính phải là chữ"/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6 row">
                <label>Trạng thái</label>
                <input class="form-check col-5" type="radio" name="trangThai" value="true" checked/> <label class="col-1">Làm</label>
                <input class="form-check col-5" type="radio" name="trangThai" value="false"/><label class="col-1">Nghỉ</label>
            </div>
            <div class="col-6">
                <label>Chức vụ</label>
                <select class="form-select" name="idChucVu" >
                    <c:forEach var="cv" items="${ danhSachCV }">
                        <option value="${cv.id}"  ${cv.id == idChucVu ? "selected" : ""}>${cv.ten}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Cửa Hàng</label>
                <select class="form-select" name="idCuaHang" >
                    <c:forEach var="ch" items="${ danhSachCH }">
                        <option value="${ch.id}"  ${ch.id == idCuaHang ? "selected" : ""}>${ch.ten}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-6">
                <label>Mật Khẩu</label>
                <input type="text" name="matKhau" class="form-control" value="${ nv.matKhau }" required />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <button class="btn btn-primary">Sửa</button>
            </div>
            <div class="col-6"></div>
        </div>
    </form>
</div>


