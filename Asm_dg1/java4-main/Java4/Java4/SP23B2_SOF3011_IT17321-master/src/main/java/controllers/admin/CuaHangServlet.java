package controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.ChucVuRepository;
import repositories.CuaHangRepository;
import view_model.QLChucVu;
import view_model.QLCuaHang;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
    "/cua-hang/index",    // GET
    "/cua-hang/create",   // GET
    "/cua-hang/edit",     // GET
    "/cua-hang/delete",   // GET
    "/cua-hang/store",    // POST
    "/cua-hang/update",   // POST
})
public class CuaHangServlet extends HttpServlet {
    private CuaHangRepository chRepo;
    public CuaHangServlet()
    {
        this.chRepo = new CuaHangRepository();
        this.chRepo.insert(new QLCuaHang("CH1", "WinMart","Cầu Giấy","HN","VN"));
        this.chRepo.insert(new QLCuaHang("CH2", "CircleK","Đống Đa", "HN","VN"));
    }
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/cua-hang/index");
            // 404: Not Found
            // 405: Method Not Allowed
        }
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("view", "/views/cua_hang/create.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachCH", this.chRepo.findAll());
        request.setAttribute("view", "/views/cua_hang/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLCuaHang vm = this.chRepo.findByMa(ma);
        request.setAttribute("ch", vm);
        request.setAttribute("view", "/views/cua_hang/edit.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        QLCuaHang vm = this.chRepo.findByMa(ma);
        this.chRepo.delete(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/cua-hang/index");
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        QLCuaHang qlch = new QLCuaHang(ma, ten, diaChi, thanhPho, quocGia);

        // Tạo ArrayList & thêm vào
        chRepo.update(qlch);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/cua-hang/index");
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            QLCuaHang vm = new QLCuaHang();
            BeanUtils.populate(vm, request.getParameterMap());
            this.chRepo.insert(vm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/cua-hang/index");
    }
}
