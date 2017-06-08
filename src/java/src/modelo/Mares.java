/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.modelo;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author root
 */
public class Mares {
    private int id;
    private int id_estacao;
    private int valor;
    private Date dia;
    private int hora;
    private String usercad;
    private Date datacad;
    private LocalTime horacad;
    private String ipcad;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_estacao
     */
    public int getId_estacao() {
        return id_estacao;
    }

    /**
     * @param id_estacao the id_estacao to set
     */
    public void setId_estacao(int id_estacao) {
        this.id_estacao = id_estacao;
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

    /**
     * @return the dia
     */
    public Date getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(Date dia) {
        this.dia = dia;
    }

    /**
     * @return the hora
     */
    public int getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(int hora) {
        this.hora = hora;
    }

    /**
     * @return the usercad
     */
    public String getUsercad() {
        return usercad;
    }

    /**
     * @param usercad the usercad to set
     */
    public void setUsercad(String usercad) {
        this.usercad = usercad;
    }

    /**
     * @return the datacad
     */
    public Date getDatacad() {
        return datacad;
    }

    /**
     * @param datacad the datacad to set
     */
    public void setDatacad(Date datacad) {
        this.datacad = datacad;
    }

    /**
     * @return the horacad
     */
    public LocalTime getHoracad() {
        return horacad;
    }

    /**
     * @param horacad the horacad to set
     */
    public void setHoracad(LocalTime horacad) {
        this.horacad = horacad;
    }

    /**
     * @return the ipcad
     */
    public String getIpcad() {
        return ipcad;
    }

    /**
     * @param ipcad the ipcad to set
     */
    public void setIpcad(String ipcad) {
        this.ipcad = ipcad;
    }
    
}
