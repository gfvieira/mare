package src.chm;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import src.dao.DaoMare;
import src.modelo.Mares;

public class Upload extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private final int maxFileSize = 10000 * 1024;
    private final int maxMemSize = 10000 * 1024;
    //private File file;
    private int type = 0, idEstacao = 0;
    private String nome = "", ip = "", user = "";

    @Override
    public void init() {
        //Get the file location where it would be stored.
        //filePath = getServletContext().getInitParameter("file-upload");
//        filePath = "/opt/tomcat/apache-tomcat-8.0.30/webapps/docs_sigbase/secom";
        //filePath = "/home/gustavo/Área de Trabalho/SIG-BASE/sigbase/web/secom/docs/";
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {
        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("/opt/tomcat/apache-tomcat-8.0.30/webapps/temp"));
        //factory.setRepository(new File("/home/gustavo/Área de Trabalho/SIG-BASE/sigbase/web/secom/docs/"));
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);
            // Process the uploaded file items
            Iterator i = fileItems.iterator();
            File file = null;
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
//                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    filePath = "/opt/tomcat/apache-tomcat-8.0.30/webapps/temp/";
                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                } else {
                    if (fi.getFieldName().equals("type")) {
                        type = Integer.parseInt(fi.getString());
                    }
                    if (fi.getFieldName().equals("idEstacao")) {
                        idEstacao = Integer.parseInt(fi.getString());
                    }
                }
            }
            ip = request.getRemoteAddr();
            boolean check = insereDados(file, type, idEstacao, ip);
            if (check == true) {
                request.setAttribute("mensagem", "OK");
            } else {
                request.setAttribute("mensagem", "NO");
            }
            RequestDispatcher rd = request.getRequestDispatcher("enviar.jsp");
            rd.forward(request, response);
        } catch (FileUploadException | IOException | NumberFormatException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }

    private boolean insereDados(File file, int type, int idEstacao, String ip) {
        FileReader arq;
        boolean check = false;
        String linha = "";
        try {
            arq = new FileReader(file);
            BufferedReader lerarq = new BufferedReader(arq);
            switch (type) {
                case 1://mare observada
                    linha = lerarq.readLine();
                    while (linha != null) {
                        String data = linha.substring(0, linha.indexOf(' '));
                        data = converteData(data);
                        System.out.println("data: " + data);
                        String hora = linha.substring(linha.indexOf(' '), linha.indexOf(':'));
                        hora = hora.trim();
                        int time;
                        if (hora.substring(0, 1).equals("0")) {
                            time = Integer.parseInt(hora.substring(1, 2));
                        } else {
                            time = Integer.parseInt(hora);
                        }
                        System.out.println("hora: " + time);
                        String valor = linha.substring(linha.indexOf(';') + 1);
                        valor = valor.replaceAll("[^a-zZ-Z0-9 ]", "");
                        System.out.println("valor: " + valor);
                        System.out.println(linha);
                        linha = lerarq.readLine();
                        DaoMare daomare = new DaoMare();
                        Mares mare = daomare.buscaMareObservada(data, idEstacao, time);
                        if (mare == null) {
                            check = daomare.insereMareObservada(idEstacao, valor, data, time, "09096418", ip);
                        } else {
                            return false;
                        }
                    }
                    break;
                case 2:
                    linha = lerarq.readLine();
                    int count = 0;
                    while (linha != null) {
                        linha = retirarEspaco(linha);
                        String data = linha.substring(0, linha.indexOf('@'));
                        count +=data.length();
                        data = converteData(data);
                        System.out.println("data: " + data);
                        for (int i = 0; i < 24; i++) {
                            int time = i;
                            System.out.println("hora: " + time);
                            System.out.println(count);
                            System.out.println(linha.indexOf('@')+1);
                            String valor = pegarValor(linha, count++);
                            count+=valor.length();
                            valor = valor.replaceAll("[^a-zZ-Z0-9 ]", "");
                            System.out.println("valor: " + valor);
                            System.out.println(linha);
                            DaoMare daomare = new DaoMare();
                            Mares mare = daomare.buscaMarePreterita(data, idEstacao, time);
                            if (mare == null) {
                                check = daomare.insereMarePreterita(idEstacao, valor, data, time, "09096418", ip);
                            } else {
                                check = false;
                            }
                        }                            
                        linha = lerarq.readLine();
                        count = 0;
                    }
                    break;
                case 3:
                    break;

                default:
                    System.out.print("default");
                    break;

            }
            arq.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    private String converteData(String data) {
        String dia_temp = data;
        String day = "", mes = "", ano = "";
        dia_temp = dia_temp.replaceAll("[^a-zZ-Z0-9 ]", "");
        if (!dia_temp.equals("")) {
            day = dia_temp.substring(0, 2);
            mes = dia_temp.substring(2, 4);
            ano = dia_temp.substring(4, 8);
        }
        return dia_temp = (ano + "-" + mes + "-" + day);
    }

    private String retirarEspaco(String linha) {
        String saida = "";
        char one;
        int encontrou = 0;
        for (int i = 0; i < linha.length(); i++) {
            one = linha.charAt(i);
            if (one == ' ' && encontrou == 0) {
                saida = saida + "@";
                encontrou = 1;
            } else if (one == ' ' && encontrou == 1) {
            } else {
                saida = saida + one;
                encontrou = 0;
            }
        }
        return saida;
    }

    private String pegarValor(String linha, int count) {
        String saida = "";
        char one;
        int encontrou = 0;
        for (int i = count; i < linha.length(); i++) {
            one = linha.charAt(i);
            if (one == '@' && (encontrou == 0 || encontrou == 1)) {
                encontrou++;
            } else if (one != '@' && encontrou == 1) {
                saida=saida+one;
            } else if(encontrou == 2){
                return saida;
            }
        }
        return saida;
    }
}
