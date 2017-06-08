package src.modelo;

import java.io.Serializable;
import java.util.ArrayList;
 
public class ModeloGraficoItem implements Serializable {
 
    private String hora;
    private String tipoMare;
    private int valor;
 
    public ModeloGraficoItem() {
    }
 
    public ModeloGraficoItem(String hora, String tipoMare, int valor) {
        this.hora = hora;
        this.tipoMare = tipoMare;
        this.valor = valor;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the tipoMare
     */
    public String getTipoMare() {
        return tipoMare;
    }

    /**
     * @param tipoMare the tipoMare to set
     */
    public void setTipoMare(String tipoMare) {
        this.tipoMare = tipoMare;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }
 
}