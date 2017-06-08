/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.modelo;

/**
 *
 * @author Carina Esthela
 */
public class Indicador_model {
    
    private double total;
    private double aguadando_enc;
    private double nao_iniciado;
    private double em_andamento;
    private double finalizado;
    private String mes;
    private String oficina;

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the nao_iniciado
     */
    public double getNao_iniciado() {
        return nao_iniciado;
    }

    /**
     * @param nao_iniciado the nao_iniciado to set
     */
    public void setNao_iniciado(double nao_iniciado) {
        this.nao_iniciado = nao_iniciado;
    }

    /**
     * @return the em_andamento
     */
    public double getEm_andamento() {
        return em_andamento;
    }

    /**
     * @param em_andamento the em_andamento to set
     */
    public void setEm_andamento(double em_andamento) {
        this.em_andamento = em_andamento;
    }

    /**
     * @return the finalizado
     */
    public double getFinalizado() {
        return finalizado;
    }

    /**
     * @param finalizado the finalizado to set
     */
    public void setFinalizado(double finalizado) {
        this.finalizado = finalizado;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the oficina
     */
    public String getOficina() {
        return oficina;
    }

    /**
     * @param oficina the oficina to set
     */
    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    /**
     * @return the aguadando_enc
     */
    public double getAguadando_enc() {
        return aguadando_enc;
    }

    /**
     * @param aguadando_enc the aguadando_enc to set
     */
    public void setAguadando_enc(double aguadando_enc) {
        this.aguadando_enc = aguadando_enc;
    }
    
}
