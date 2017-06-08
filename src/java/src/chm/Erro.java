package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import src.dao.ConnectDataBase;

public class Erro {

    private ConnectDataBase connectDataBase = null;
    private Statement statement;
    private Erro log = null;
    HttpServletResponse response;

    public Erro() {
        connectDataBase = new ConnectDataBase();
    }

    public void Gravar(String texto) {
        String conteudo = texto;
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String d = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(tm);
        String d1 = new SimpleDateFormat("MM").format(tm);
        String d2 = new SimpleDateFormat("yyyy").format(tm);
        int d3 = Integer.parseInt(d1);
        try {
            // o true significa q o arquivo será constante  
            //FileWriter x = new FileWriter(new File("C:\\Documents and Settings\\Administrador\\Desktop\\"+d+".txt"));
            FileWriter x = new FileWriter(new File("//opt//tomcat//apache-tomcat-8.0.30//webapps//docs_sigbase//log_sigbase//" + d2 + "//" + d3 + "//" + d + ".txt"));
            //FileWriter x = new FileWriter(new File("C:\\Users\\Carina Esthela\\Desktop\\"+d+".txt"));
            //"C:\\Users\\oficina\\Desktop",true

            conteudo += "\n\r"; // criando nova linha e recuo no arquivo
            x.write(conteudo); // armazena o texto no objeto x, que aponta para o arquivo
            x.close(); // cria o arquivo
        } // em caso de erro apreenta mensagem abaixo
        catch (Exception e) {
            java.util.logging.Logger.getLogger(Erro.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void Gravar(String message, String lasteAccess) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insere2TableSQL = "INSERT INTO erro(metodo, mensagem, data, hora)"
                + " VALUES ('" + lasteAccess + "', '" + message + "', '" + date + "', '" + thisSec + "');";
        try {
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insere2TableSQL);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out;
            try {
                out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>ERRO</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>ERRO: " + message + "</h1>");
                out.println("</body>");
                out.println("</html>");
            } catch (IOException ex) {
                Logger.getLogger(Erro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out;
            try {
                out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>ERRO</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>ERRO!!!!</h1>");
                out.println("</body>");
                out.println("</html>");
            } catch (IOException ex) {
                Logger.getLogger(Erro.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public void Gravar(String msg, String classe, String metodo) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO erro(msg, classe, metodo, data, hora)"
                + " VALUES('" + msg + "', '" + classe + "', '" + metodo + "',"
                + " '" + date + "', '" + thisSec + "');";
        try {
            System.out.println(insereTableSQL);
            System.out.println(msg);
            System.out.println(classe);
            System.out.println(metodo);
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            System.out.println(msg);
            System.out.println(classe);
            System.out.println(metodo);
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            connectDataBase.closeConnection();
        }
    }

}
