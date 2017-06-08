package src.filtro;
 
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
 
/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {
 
    private ServletContext context;
     
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> params = req.getParameterNames();
        while(params.hasMoreElements()){
            String name = params.nextElement();
            String value = request.getParameter(name);
            Timestamp tm = new Timestamp(System.currentTimeMillis());
            String d = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(tm);
            this.context.log(req.getRemoteAddr() + " " +d+ " :: Request Params::{"+name+"="+value+"}");
        }
         
//        Cookie[] cookies = req.getCookies();
//        if(cookies != null){
//            for(Cookie cookie : cookies){
//                this.context.log(req.getRemoteAddr() + "::Cookie::{"+cookie.getName()+","+cookie.getValue()+"}");
//            }
//        }
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }
 
    @Override
    public void destroy() {
        //we can close resources here
    }
 
}