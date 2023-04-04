<%--
  Created by IntelliJ IDEA.
  User: Hung
  Date: 04/04/2023
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<div class="alert alert-danger">
    ${ errorMessage }
</div>
<form method="POST" action="/SP23B2_SOF3011_IT17321_war_exploded/login">
    <div class="mt-3">
        <label>Mã</label>
        <input name="ma" class="form-control" type="text" />
    </div>
    <div class="mt-3">
        <label>Mật khẩu</label>
        <input name="matKhau" class="form-control" type="password" />
    </div>
    <div class="mt-3">
        <button class="btn btn-primary">Đăng nhập</button>
    </div>
</form>