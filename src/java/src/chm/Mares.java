/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Mares extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Estacao> list;
            DaoMare daomare = new DaoMare();
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
