package controllers.admin;

import DomainModels.ChucVu;
import DomainModels.CuaHang;
import DomainModels.NhanVien;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.ChucVuRepository;
import repositories.CuaHangRepository;
import services.ChucVuService;
import services.CuaHangService;
import services.NhanVienService;
import services.impl.ChucVuServiceImpl;
import services.impl.CuaHangServiceimpl;
import services.impl.NhanVienServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@WebServlet({
    "/nhan-vien/index",    // GET
    "/nhan-vien/create",   // GET
    "/nhan-vien/edit",     // GET
    "/nhan-vien/delete",   // GET
    "/nhan-vien/store",    // POST
    "/nhan-vien/update",   // POST
})
public class NhanVienServlet extends HttpServlet {
    NhanVienService nvImpl;
    ChucVuService cvImpl;
    CuaHangService chImpl;

    public NhanVienServlet()
    {
         nvImpl = new NhanVienServiceImpl();
         cvImpl = new ChucVuServiceImpl();
         chImpl = new CuaHangServiceimpl();
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

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        NhanVien nv = nvImpl.findByMa(ma);
        nvImpl.delete(nv);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachNV", this.nvImpl.getAll());
//        request.getRequestDispatcher("/views/khach_hang/index.jsp")
        request.setAttribute("view", "/views/nhan_vien/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp")
                .forward(request, response);
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachCV", cvImpl.getAll());
        request.setAttribute("danhSachCH", chImpl.getAll());
        request.setAttribute("view", "/views/nhan_vien/create.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachCH", chImpl.getAll());
        request.setAttribute("danhSachCV", cvImpl.getAll());
        String ma = request.getParameter("ma");
        NhanVien nv = nvImpl.findByMa(ma);
        request.setAttribute("nv", nv);

        request.setAttribute("idCuaHang", chImpl.findByMa(ma));
        request.setAttribute("idChucVu", cvImpl.findByMa(ma));
        request.setAttribute("view", "/views/nhan_vien/edit.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/index");
            // 404
            // 405
        }
    }


    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            UUID idChucVu = UUID.fromString(request.getParameter("idChucVu"));
            ChucVu cv = new ChucVu();
            cv.setId(idChucVu);

            UUID idCuaHang = UUID.fromString(request.getParameter("idCuaHang"));
            CuaHang ch = new CuaHang();
            ch.setId(idCuaHang);

            NhanVien domainModelNV = new NhanVien();
            domainModelNV.setChucVu(cv);
            domainModelNV.setCuaHang(ch);
            BeanUtils.populate(domainModelNV, request.getParameterMap());
            this.nvImpl.add(domainModelNV);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/index");
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");

            UUID idChucVu = UUID.fromString(request.getParameter("idChucVu"));
            ChucVu cv = new ChucVu();
            cv.setId(idChucVu);


            UUID idCuaHang = UUID.fromString(request.getParameter("idCuaHang"));
            CuaHang ch = new CuaHang();
            ch.setId(idCuaHang);
            /*
             * domainModelKH: Dữ liệu đang có trong DB
             * request.getParameterMap(): Dữ liệu người dùng cập nhật
             */
            NhanVien domainModelNV = this.nvImpl.findByMa(ma);
            domainModelNV.setChucVu(cv);
            domainModelNV.setCuaHang(ch);
            BeanUtils.populate(domainModelNV, request.getParameterMap());
            this.nvImpl.update(domainModelNV);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/nhan-vien/index");
    }
}
