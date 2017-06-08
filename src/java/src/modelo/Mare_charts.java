/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.modelo;

/**
 *
 * @author root
 */
public class Mare_charts {

    private String period;
    private Integer previsto;
    private Integer real;

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }
    
    public void setPeriod() {
        this.period = null;
    }

    /**
     * @return the previsto
     */
    public Integer getPrevisto() {
        return previsto;
    }

    /**
     * @param previsto the previsto to set
     */
    public void setPrevisto(Integer previsto) {
        this.previsto = previsto;
    }
    
    public void setPrevisto() {
        this.previsto = null;
    }

    /**
     * @return the real
     */
    public Integer getReal() {
        return real;
    }

    /**
     * @param real the real to set
     */
    public void setReal(Integer real) {
        this.real = real;
    }
    
    public void setReal() {
        this.real = null;
    }


}
