/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Date;

/**
 *
 * @author e945952
 */
public class Ticket {
    
    private String numero;
    private String codigo;
    private String terminal;
    private String matricula;
    private Date fechaVenta;
    private Date inicioEstacionamiento;
    private String cantMin;
    private String importeTotal;
    private String agencia;

    public Ticket(String numero, String terminal, String matricula, Date fechaVenta, Date inicioEstacionamiento, String cantMin, String importeTotal, String agencia) {
        this.numero = numero;
        this.terminal = terminal;
        this.matricula = matricula;
        this.fechaVenta = fechaVenta;
        this.inicioEstacionamiento = inicioEstacionamiento;
        this.cantMin = cantMin;
        this.importeTotal = importeTotal;
        this.agencia = agencia;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Date getInicioEstacionamiento() {
        return inicioEstacionamiento;
    }

    public void setInicioEstacionamiento(Date inicioEstacionamiento) {
        this.inicioEstacionamiento = inicioEstacionamiento;
    }

    public String getCantMin() {
        return cantMin;
    }

    public void setCantMin(String cantMin) {
        this.cantMin = cantMin;
    }

    public String getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(String importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Ticket() {
    }
    
    
}