package controllers.admin;

import DomainModels.DongSP;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import repositories.DongSPRepository;
import services.DongSPService;
import services.impl.DongSPServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
    "/dsp/index",    // GET
    "/dsp/create",   // GET
    "/dsp/edit",     // GET
    "/dsp/delete",   // GET
    "/dsp/store",    // POST
    "/dsp/update",   // POST
})
public class DongSPServlet extends HttpServlet {
    private DongSPService dspImpl;

    public DongSPServlet()
    {
        dspImpl = new DongSPServiceImpl();
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
        DongSP domainModelDSP = this.dspImpl.findByMa(ma);
        this.dspImpl.delete(domainModelDSP);

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/dsp/index");
    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSachDSP", this.dspImpl.getAll());
//        request.getRequestDispatcher("/views/khach_hang/index.jsp")
        request.setAttribute("view", "/views/dongsp/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp")
                .forward(request, response);
    }

    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/dongsp/create.jsp")
                .forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        DongSP domainModelDSP = this.dspImpl.findByMa(ma);
        request.setAttribute("dsp", domainModelDSP);
        request.getRequestDispatcher("/views/dongsp/edit.jsp")
                .forward(request, response);
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
            // 404
            // 405
        }
    }


    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            DongSP domainModelDSP = new DongSP();
            BeanUtils.populate(domainModelDSP, request.getParameterMap());
            System.out.println(domainModelDSP.getMa());
            this.dspImpl.add(domainModelDSP);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/dsp/index");
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            /*
             * domainModelKH: Dữ liệu đang có trong DB
             * request.getParameterMap(): Dữ liệu người dùng cập nhật
             */
            DongSP domainModelDSP = this.dspImpl.findByMa(ma);
            BeanUtils.populate(domainModelDSP, request.getParameterMap());
            this.dspImpl.update(domainModelDSP);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/SP23B2_SOF3011_IT17321_war_exploded/dsp/index");
    }
}
