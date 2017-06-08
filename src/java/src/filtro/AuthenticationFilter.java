package src.filtro;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

//        HttpSession session = req.getSession(false);
//        if (session != null) {
//            usuario = (Usuario) session.getAttribute("usuario"); ///verificar excessao 
//        }

        String uri = req.getRequestURI();
        this.context.log("Requested Resource:: " + uri);

        String url = "/chm/";

        boolean check;
        if (uri.equals(url) //pagina
                || uri.equals(url + "mare.jsp")//pagina 
                || uri.equals(url + "pdf.jsp")//pagina 
                || uri.equals(url + "pdf2.jsp")//pagina 
                || uri.equals(url + "Mare.jsp")
                || uri.equals(url + "Mares.jsp")
                || uri.equals(url + "Upload.jsp")
                || uri.equals(url + "enviar.jsp")
                || uri.equals(url + "Troca.jsp")
                || uri.equals(url + "Tempo.jsp")
                || uri.equals(url + "CadEstacao.jsp")
                ) {
            check = true;
        } 
//        else if (usuario != null) {
//            if ((uri.equals(url + "admin/admin_home.jsp"))
//                    && usuario.getPri().equals("ADMIN")) {
//                check = true;
//            } else if (uri.equals(url + "LogOff.jsp")) {
//                session.invalidate();
//                check = false;
//            } else {
//                check = false;
//            }
//        } 
        else {
            check = false;
        }
        if (check == false) {// implementar log de recusa de acesso
            Timestamp tm = new Timestamp(System.currentTimeMillis());
            String d = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(tm);
            this.context.log("====== ACESSO NAO AUTORIZADO PARA IP: " + request.getRemoteAddr() + " "
                    + "AS " + d);
            res.sendRedirect(url + "loginSigbase.jsp");
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        //close any resources here
    }

}
