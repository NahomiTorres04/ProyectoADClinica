/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiero;

/**
 *
 * @author Nahomi
 */
public abstract class Articulos 
{
    protected String codigo;
    protected String descripcion;
    protected int cantidad;
    protected double costoIndividual;
    protected boolean activo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoIndividual() {
        return costoIndividual;
    }

    public void setCostoIndividual(double costoIndividual) {
        this.costoIndividual = costoIndividual;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public abstract double total();
}
