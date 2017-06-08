package src.chm;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoMare;
import src.modelo.Mares;
import src.modelo.Mare_charts;

public class Mare extends HttpServlet {

    DaoMare daomare = null;
    ArrayList<Mares> mareObservada = null;
    ArrayList<Mares> marePrevista = null;
    String dia_temp = "";
    int mes = 0, ano = 0, day = 0;

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int tipo = Integer.parseInt(request.getParameter("type"));
        String dia = request.getParameter("day");
        int estacao = Integer.parseInt(request.getParameter("estacao"));
        System.out.print(tipo);
        System.out.print(dia);
        ArrayList<Mare_charts> l = new ArrayList();
        daomare = new DaoMare();
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(tm));
        String hora;
// verificar data atual, se for anterior utilizar tabela preterita, senao futura.
// criar teste de condicao acima e mandar para o banco
        switch (tipo) {
            case 1:
                dia_temp = pegaData(dia, 0);
                mareObservada = daomare.buscaMare(dia_temp, 1, estacao);//observado
                marePrevista = daomare.buscaMare(dia_temp, 2, estacao);//previsto
                for (int i = 0; i < 24; i++) {
                    Mare_charts a = new Mare_charts();
                    if (i < 10) {
                        hora = "0" + i + ":00";
                    } else {
                        hora = i + ":00";
                    }
                    a.setPeriod(hora);
                    a.setPrevisto(marePrevista.get(i).getValor());
                    if (mareObservada.size() > i) {
                        a.setReal(mareObservada.get(i).getValor());
                    } else {
                        a.setReal();
                    }
                    l.add(a);
                }
                break;
            case 2:
                for (int k = 0; k < 7; k++) {
                    dia_temp = pegaData(dia, k);
                    mareObservada = daomare.buscaMare(dia_temp, 1, estacao);//observado
                    marePrevista = daomare.buscaMare(dia_temp, 2, estacao);//previsto

                    for (int i = 0; i < 24; i++) {
                        Mare_charts a = new Mare_charts();
                        if (i < 10) {
                            hora = "0" + i + ":00/" + day;
                        } else {
                            hora = i + ":00/" + day;
                        }
                        a.setPeriod(hora);
                        a.setPrevisto(marePrevista.get(i).getValor());
                        if (mareObservada.size() > i) {
                            a.setReal(mareObservada.get(i).getValor());
                        } else {
                            a.setReal();
                        }
                        l.add(a);
                    }
                    day++;
                }
                break;
            case 3:
                for (int k = 0; k < 16; k++) {
                    dia_temp = pegaData(dia, k);
                    mareObservada = daomare.buscaMare(dia_temp, 1, estacao);//observado
                    marePrevista = daomare.buscaMare(dia_temp, 2, estacao);//previsto

                    for (int i = 0; i < 24; i++) {
                        Mare_charts a = new Mare_charts();
                        if (i < 10) {
                            hora = "0" + i + ":00/" + day;
                        } else {
                            hora = i + ":00/" + day;
                        }
                        a.setPeriod(hora);
                        a.setPrevisto(marePrevista.get(i).getValor());
                        if (mareObservada.size() > i) {
                            a.setReal(mareObservada.get(i).getValor());
                        } else {
                            a.setReal();
                        }
                        l.add(a);
                    }
                    day++;
                }
                break;
            case 4:
                for (int k = 0; k < 30; k++) {
                    dia_temp = pegaData(dia, k);
                    mareObservada = daomare.buscaMare(dia_temp, 1, estacao);//observado
                    marePrevista = daomare.buscaMare(dia_temp, 2, estacao);//previsto
                    for (int i = 0; i < 24; i++) {
                        Mare_charts a = new Mare_charts();
                        if (i < 10) {
                            hora = "0" + i + ":00/" + day;
                        } else {
                            hora = i + ":00/" + day;
                        }
                        a.setPeriod(hora);
                        a.setPrevisto(marePrevista.get(i).getValor());
                        if (mareObservada.size() > i) {
                            a.setReal(mareObservada.get(i).getValor());
                        } else {
                            a.setReal();
                        }
                        l.add(a);
                    }
                    day++;
                }
                break;
            default:
                break;
        }
        // convert "apps" to "events"		
        String json = new Gson().toJson(l);
        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private String pegaData(String dia, int k) {
        dia_temp = dia;
        if (!dia_temp.equals("")) {
            dia_temp = dia_temp.replaceAll("[^a-zZ-Z0-9 ]", "");
            if (!dia_temp.equals("") && k == 0) {
                day = Integer.parseInt(dia_temp.substring(0, 2));
                mes = Integer.parseInt(dia_temp.substring(2, 4));
                ano = Integer.parseInt(dia_temp.substring(4, 8));
            }
            if (day > 31 && (mes == 1 || mes == 3 || mes == 5
                    || mes == 7 || mes == 8 || mes == 10 || mes == 12)) {
                day = 1;
                mes++;
                if (mes == 13) {
                    mes = 1;
                }
            } else if (day > 30 && (mes == 2 || mes == 4 || mes == 6
                    || mes == 9 || mes == 11)) {
                day = 1;
                mes++;
            }
            if (day < 10) {
                dia_temp = (ano + "-" + mes + "-0" + day);
            } else if (day < 10 && mes < 10) {
                dia_temp = (ano + "-0" + mes + "-0" + day);
            } else if (mes < 10) {
                dia_temp = (ano + "-0" + mes + "-" + day);
            } else {
                dia_temp = (ano + "-" + mes + "-" + day);
            }
        }
        return dia_temp;
    }

}
