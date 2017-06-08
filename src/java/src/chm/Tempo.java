/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.chm;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gustavo
 */
public class Tempo extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> lista = new ArrayList<>();
//        Timestamp tm = new Timestamp(System.currentTimeMillis());
//        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(tm));
//        lista.add(Integer.toString(year-3));
//        lista.add(Integer.toString(year-2));
        lista.add("Previsto");
        lista.add("Observado");

        // convert "apps" to "events"		
        String json = new Gson().toJson(lista);

        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
