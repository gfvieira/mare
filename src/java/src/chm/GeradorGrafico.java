package src.chm;

import src.modelo.ModeloGraficoItem;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Color;
import src.modelo.Indicador_model;
import src.modelo.Mare_charts;

public class GeradorGrafico {
    int x,y,z;

    public GeradorGrafico() {
    }

    /**
     * Gera um gráfico de barras 3D Vertical
     * @param tituloGrafico String, o titulo do gráfico
     * @param tituloEixoX String, o titulo do eixo X
     * @param tituloEixoY String, o titulo do eixo Y
     * @param arrayValores ArrayList, a lista com os valores para o gráfico
     * @return BufferedImage, a imagem do Gráfico gerada
     *
     * A classe DefaultCategoryDataset recebe os valores que irão gerar o gráfico
     * DefaultCategoryDataset.addValue(Number, Comparable, Comparable)
     * DefaultCategoryDataset.addValue(Double, Comparable, Comparable)
     */
    public static BufferedImage gerarGraficoBarraVertical3D(String tituloGrafico, String tituloEixoX, String tituloEixoY,
            ArrayList arrayValores) throws Exception {
        BufferedImage buf = null;
        try {
            DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
            Iterator iterator = arrayValores.iterator();
            while (iterator.hasNext()) {
                ModeloGraficoItem modelo = (ModeloGraficoItem) iterator.next();
                defaultCategoryDataset.addValue(modelo.getValor(),
                                    modelo.getTipoMare(), modelo.getHora());
            }

            JFreeChart chart = ChartFactory.createBarChart3D("", tituloEixoX,
                          tituloEixoY, defaultCategoryDataset, PlotOrientation.VERTICAL,
                          true, false, false);
            //chart.setBorderVisible(true);
            //chart.setBorderPaint(Color.black);
            buf = chart.createBufferedImage(550, 250);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return buf;
    }

    /**
     * Gera um gráfico de barras Vertical
     */

    public static BufferedImage gerarGraficoBarraVertical(String tituloGrafico, String
                tituloEixoX, String tituloEixoY, ArrayList arrayValores)throws Exception {
        BufferedImage buf = null;
        try {
            DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
            Iterator iterator = arrayValores.iterator();
            while (iterator.hasNext()) {
                ModeloGraficoItem modelo = (ModeloGraficoItem) iterator.next();
                defaultCategoryDataset.addValue(modelo.getValor(),
                                    modelo.getTipoMare(), modelo.getHora());
            }
            JFreeChart chart = ChartFactory.createBarChart(tituloGrafico, tituloEixoX,
        tituloEixoY, defaultCategoryDataset, PlotOrientation.VERTICAL, true, false, false);
            chart.setBorderVisible(true);
            chart.setBorderPaint(Color.black);
            buf = chart.createBufferedImage(400, 250);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return buf;
    }
 
    /**
     * Gera um Grafico de Linhas
     */
    public static BufferedImage gerarGraficoLinha(String tituloGrafico, String tituloEixoX,
                             String tituloEixoY, ArrayList arrayValores) throws Exception {
        BufferedImage buf = null;
        try {
            DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
            Iterator iterator = arrayValores.iterator();
            while (iterator.hasNext()) {
                ModeloGraficoItem modelo = (ModeloGraficoItem) iterator.next();
                defaultCategoryDataset.addValue(modelo.getValor(),
                                    modelo.getTipoMare(), modelo.getHora());
            }
            JFreeChart chart = ChartFactory.createLineChart(tituloGrafico, tituloEixoX,
                             tituloEixoY, defaultCategoryDataset, PlotOrientation.VERTICAL,
                             true, false, false);
            chart.setBorderVisible(true);
            chart.setBorderPaint(Color.black);
            buf = chart.createBufferedImage(400, 250);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return buf;
    }
 
    /**
     * Gera um grafico de linhas 3D
     * @param tituloGrafico
     * @param tituloEixoX
     * @param tituloEixoY
     * @param arrayValores
     * @return 
     * @throws java.lang.Exception 
     */
    public static BufferedImage gerarGraficoLinha3D(String tituloGrafico, String
                tituloEixoX, String tituloEixoY, ArrayList arrayValores)throws Exception {
        BufferedImage buf = null;
        try {
            DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
            Iterator iterator = arrayValores.iterator();
            while (iterator.hasNext()) {
                ModeloGraficoItem modelo = (ModeloGraficoItem) iterator.next();
                defaultCategoryDataset.addValue(modelo.getValor(),
                                    modelo.getTipoMare(), modelo.getHora());
            }
            JFreeChart chart = ChartFactory.createLineChart(tituloGrafico, tituloEixoX,
                             tituloEixoY, defaultCategoryDataset, PlotOrientation.VERTICAL,
                             true, false, false);
            //chart.setBorderVisible(true);
            //chart.setBorderPaint(Color.black);
            buf = chart.createBufferedImage(600, 350);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return buf;
   }
 
}