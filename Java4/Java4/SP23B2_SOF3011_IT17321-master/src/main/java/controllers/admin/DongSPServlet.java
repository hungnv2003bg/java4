package controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.ChucVuRepository;
import repositories.DongSPRepository;
import view_model.DongSP;
import view_model.QLChucVu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@WebServlet({
    "/dsp/index",    // GET
    "/dsp/create",   // GET
    "/dsp/edit",     // GET
    "/dsp/delete",   // GET
    "/dsp/store",    // POST
    "/dsp/update",   // POST
})
public class DongSPServlet extends HttpServlet {
    private DongSPRepository dspRepo;
    public DongSPServlet()
    {
        this.dspRepo = new DongSPRepository();
        this.dspRepo.insert(new DongSP("DSP1", "Điện thoại"));
        this.dspRepo.insert(new DongSP("DSP2", "Laptop"));
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
            response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/dsp/index");
            // 404: Not Found
            // 405: Method Not Allowed
        }
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/dongsp/create.jsp")
            .forward(request, response);
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachDSP", this.dspRepo.findAll());
        request.getRequestDispatcher("/views/dongsp/index.jsp")
                .forward(request, response);
    }
    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        DongSP vm = this.dspRepo.findByMa(ma);
        request.setAttribute("dsp", vm);
        request.getRequestDispatcher("/views/dongsp/edit.jsp")
                .forward(request, response);
    }
    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        DongSP vm = this.dspRepo.findByMa(ma);
        this.dspRepo.delete(vm);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/dsp/index");
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        DongSP dsp = new DongSP(ma, ten);
        // Tạo ArrayList & thêm vào
        dspRepo.update(dsp);
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/dsp/index");
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            DongSP vm = new DongSP();
            BeanUtils.populate(vm, request.getParameterMap());
            this.dspRepo.insert(vm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/dsp/index");
    }
}
