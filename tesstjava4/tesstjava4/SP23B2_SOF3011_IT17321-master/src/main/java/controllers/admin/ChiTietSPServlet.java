package controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.ChiTietSPRepository;
import repositories.KhachHangRepository;
import view_model.ChiTietSP;
import view_model.QLKhachHang;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
    "/ctsp/index",    // GET
    "/ctsp/create",   // GET
    "/ctsp/edit",     // GET
    "/ctsp/delete",   // GET
    "/ctsp/store",    // POST
    "/ctsp/update",   // POST
})
public class ChiTietSPServlet extends HttpServlet {
    private ChiTietSPRepository ctspRepo;
    public ChiTietSPServlet()
    {
        this.ctspRepo = new ChiTietSPRepository();
        this.ctspRepo.insert(new ChiTietSP("Nokis 1280", "Nokia", "Red", "Laptop", 2023, "Còn hàng", 50, "1000000", "150000" ));
        this.ctspRepo.insert(new ChiTietSP("Dell Pre M4", "Dell", "Blue", "Smar", 2024, "Hết hàng", 0, "50000", "70000" ));
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/ctsp/index");
            // 404: Not Found
            // 405: Method Not Allowed
        }

    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/chi_tiet_sp/create.jsp")
            .forward(request, response);
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String sanPham = request.getParameter("sanPham");
        ChiTietSP ctsp = this.ctspRepo.findByMa(sanPham);
        request.setAttribute("ctsp", ctsp);
        request.getRequestDispatcher("/views/chi_tiet_sp/edit.jsp")
                .forward(request, response);
    }
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String sanPham = request.getParameter("sanPham");
        ChiTietSP vm = this.ctspRepo.findByMa(sanPham);
        this.ctspRepo.delete(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/ctsp/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachCTSP", this.ctspRepo.findAll());
        request.getRequestDispatcher("/views/chi_tiet_sp/index.jsp")
                .forward(request, response);
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String sanPham = request.getParameter("sanPham");
        String nsx = request.getParameter("nsx");
        String mauSac = request.getParameter("mauSac");
        String dongSP = request.getParameter("dongSP");
        String namBH = request.getParameter("namBH");
        String moTa = request.getParameter("moTa");
        String soLuongTon = request.getParameter("soLuongTon");
        String giaNhap = request.getParameter("giaNhap");
        String giaBan = request.getParameter("giaBan");
        ChiTietSP vm = new ChiTietSP(sanPham, nsx, mauSac, dongSP, Integer.valueOf(namBH), moTa, Integer.valueOf(namBH), giaNhap, giaBan);

        this.ctspRepo.update(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/ctsp/index");
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            ChiTietSP ctsp = new ChiTietSP();
            BeanUtils.populate(ctsp, request.getParameterMap());
            this.ctspRepo.insert(ctsp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/ctsp/index");
    }
}
