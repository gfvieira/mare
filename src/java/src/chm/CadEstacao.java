package src.chm;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoMare;
import src.modelo.Estacao;

/**
 *
 * @author gustavo
 */
public class CadEstacao extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String estacao = request.getParameter("estacao").toUpperCase();
        String ip = request.getRemoteAddr();
        ArrayList<Estacao> list;
        DaoMare daomare = new DaoMare();
        daomare.insereEstacao(estacao, ip);
        list = daomare.buscaEstacao();
        if (list.isEmpty() || list == null) {
        } else {
            String json = new Gson().toJson(list);

            // Write JSON string.
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }

    }

}
