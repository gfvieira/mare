package src.chm;

import src.modelo.ModeloGraficoItem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import src.dao.DaoMare;
import src.modelo.Mares;

public class pdf2 extends HttpServlet {

    int id_estacao = 0;
    int id_tempo = 0;
    String data = "";
    StringBuffer sbFilename = null;
    StringBuffer sbContentDispValue = null;
    Timestamp tm = null;
    DocumentException ex = null;
    ByteArrayOutputStream baosPDF = null;
    int i = 0;

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            id_estacao = Integer.parseInt(req.getParameter("esta"));
            id_tempo = Integer.parseInt(req.getParameter("tempo"));
            data = req.getParameter("campo");
            String dia_temp = data;
            if (!dia_temp.equals("")) {
                String day = "", mes = "", ano = "";
                dia_temp = dia_temp.replaceAll("[^a-zZ-Z0-9 ]", "");
                if (!dia_temp.equals("")) {
                    day = dia_temp.substring(0, 2);
                    mes = dia_temp.substring(2, 4);
                    ano = dia_temp.substring(4, 8);
                }
                dia_temp = (ano + "-" + mes + "-" + day);
            }
            System.out.println(data);
            System.out.println(id_tempo);
            System.out.println(id_estacao);
            baosPDF = generatePDFDocument(this.getServletContext(), data, id_tempo, id_estacao);
            sbFilename = new StringBuffer();
            sbFilename.append("MARE_");
            tm = new Timestamp(System.currentTimeMillis());
            String d = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(tm);
            sbFilename.append(d);
            sbFilename.append(".pdf");
            resp.setHeader("Cache-Control", "max-age=30");
            resp.setContentType("application/pdf");
            sbContentDispValue = new StringBuffer();
            sbContentDispValue.append("inline");
            sbContentDispValue.append("; filename=");
            sbContentDispValue.append(sbFilename);
            resp.setContentType("application/pdf");
            resp.setHeader("Content-disposition", sbContentDispValue.toString());
            resp.setContentLength(baosPDF.size());
            ServletOutputStream sos;
            sos = resp.getOutputStream();
            //resp.getOutputStream().write(baosPDF);
            baosPDF.writeTo(sos);
            sos.flush();
        } catch (DocumentException dex) {
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println(this.getClass().getName() + " caught an exception: "
                    + dex.getClass().getName() + "<br>");
            writer.println("<pr>");
            dex.printStackTrace(writer);
            writer.println("</pr>");
        } finally {
            if (baosPDF != null) {
                baosPDF.reset();
            }
        }
    }

    protected ByteArrayOutputStream generatePDFDocument(
            final ServletContext ctx, String data, int estacao, int tempo)
            throws DocumentException {
        Document doc = new Document();
        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        PdfWriter docWriter = null;
        try {

            docWriter = PdfWriter.getInstance(doc, baosPDF);
            doc.addAuthor("Sample");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("Sample");
            doc.addTitle("MARE");//nome do pdf
            doc.setPageSize(PageSize.A4);
            doc.open();
            String strServerInfo = ctx.getServerInfo();
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 14);
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Font f3 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
            Font f4 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
            Paragraph p1 = new Paragraph("MARINHA DO BRASIL", f);
            p1.setAlignment(Element.ALIGN_CENTER);
            p1.setSpacingAfter(10);
            doc.add(p1);
            Paragraph p2 = new Paragraph("DIRETORIA DE HIDROGRAFIA E NAVEGAÇÃO", f);
            p2.setAlignment(Element.ALIGN_CENTER);
            //p1.setSpacingAfter(10);
            doc.add(p2);
            Paragraph p3 = new Paragraph("CENTRO DE HIDROGRAFIA DA MARINHA", f);
            p3.setAlignment(Element.ALIGN_CENTER);
            p3.setSpacingAfter(6);
            doc.add(p3);
            Paragraph p412 = new Paragraph("SIS-MARE", f);
            p412.setAlignment(Element.ALIGN_CENTER);
            doc.add(p412);
            Paragraph p413 = new Paragraph("Sistema de Maré", f3);
            p413.setAlignment(Element.ALIGN_CENTER);
            p413.setSpacingAfter(10);
            doc.add(p413);
            Paragraph p6 = new Paragraph("INDICATIVO DE MARÉ", f2);
            p6.setAlignment(Element.ALIGN_CENTER);
            p6.setSpacingAfter(14);
            doc.add(p6);
            //fazer um if para informar o tipo de periodo selecionado
            Paragraph p8 = new Paragraph("PERIODO: "+data+" - (24 HORAS)", f3);
            p8.setAlignment(Element.ALIGN_CENTER);
            doc.add(p8);
            tm = new Timestamp(System.currentTimeMillis());
            String d = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(tm);
            Paragraph p7 = new Paragraph("Emitido em: " + d, f4);
            p7.setAlignment(Element.ALIGN_CENTER);
            p7.setSpacingAfter(14);
            doc.add(p7);
            DaoMare daomare = new DaoMare();
            ArrayList<Mares> mareObservada = null;
            ArrayList<Mares> marePrevista = null;
            ArrayList<ModeloGraficoItem> l = new ArrayList();
            //aqui estra o if else para busca do tempo
            mareObservada = daomare.buscaMare(data, 1, estacao);//observado
            marePrevista = daomare.buscaMare(data, 2, estacao);//previsto
            for (int i = 0; i < 24; i++) {
                ModeloGraficoItem a = new ModeloGraficoItem();
                ModeloGraficoItem b = new ModeloGraficoItem();
                a.setHora("" + i);
                b.setHora("" + i);
                a.setValor(marePrevista.get(i).getValor());
                b.setValor(mareObservada.get(i).getValor());
                a.setTipoMare("Prevista");
                b.setTipoMare("Observada");
                l.add(a);
                l.add(b);
            }// testar o grafico com mais de 1 dia
            BufferedImage imagem = GeradorGrafico.gerarGraficoLinha3D("", "Periodo", "Altura em cm", l);
            Image image = Image.getInstance(imagem, null);
            image.setAlignment(Element.ALIGN_CENTER);
            doc.add(image);
            //criar uma classe que retorna a tabela
            PdfPTable teste = new PdfPTable(13);
            teste.setWidthPercentage(100);
            teste.setSpacingAfter(30);
            teste.setSpacingBefore(30);
            PdfPCell cell = new PdfPCell(new Phrase("Altura da maré X Horario do dia: "+data));
            cell.setColspan(13);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBackgroundColor(BaseColor.GRAY);
            teste.addCell(cell);
            teste.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            int a = 12, b = 0;
            for (int k = 0; k < 2; k++) {
                teste.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                teste.addCell(" ");
                for (int i = b; i < a; i++) {
                    String hora = "";
                    if (i < 10) {
                        hora = "0" + i + ":00";
                    } else {
                        hora = i + ":00";
                    }
                    teste.addCell(hora);
                }
                teste.getDefaultCell().setBackgroundColor(BaseColor.RED);
                teste.addCell("Prv");
                for (int i = b; i < a; i++) {
                    teste.addCell("" + marePrevista.get(i).getValor());
                }
                teste.getDefaultCell().setBackgroundColor(BaseColor.BLUE);
                teste.addCell("Obs");
                for (int i = b; i < a; i++) {
                    teste.addCell("" + mareObservada.get(i).getValor());
                }
                b = a;
                a = a * 2;
            }
            doc.add(teste);

            doc.close();
        } catch (DocumentException dex) {
            baosPDF.reset();
            throw dex;
        } catch (Exception ex) {
            Logger.getLogger(pdf2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }

        if (baosPDF.size() < 1) {
            throw new DocumentException("document has " + baosPDF.size()
                    + " bytes");
        }
        return baosPDF;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
