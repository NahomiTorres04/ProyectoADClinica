/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjeta_Responsabilidad;

import controlador.BienJpaController;
import controlador.Conexion;
import controlador.TarjetaResponsabilidadJpaController;
import entidades.Empleado;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nahomi
 */
public class Articulo_Tarjeta implements Responsabilidad{

    private String codigo;
    private String descripcion;
    private double costo;
    
    public Articulo_Tarjeta(String codigo, String descripcion, double costo)
    {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    @Override
    public void guardar(Empleado empleado) {
        BienJpaController controladorBien = new BienJpaController(Conexion.getInstancia().getEntityManager());
        TarjetaResponsabilidadJpaController controladorTarjeta = new TarjetaResponsabilidadJpaController(Conexion.getInstancia().getEntityManager());
        entidades.TarjetaResponsabilidad tarjeta = new entidades.TarjetaResponsabilidad();
        tarjeta.setEmpleadoId(empleado);
        tarjeta.setFungible((short) 1);
        tarjeta.setFecha(new Date());
        controladorTarjeta.create(tarjeta);
        int id = controladorTarjeta.getTarjetaResponsabilidadCount();
        entidades.Bien bien;
        bien = controladorBien.findBien(codigo);
        bien.setTarjetaResponsabilidadId(tarjeta);
        try {
            controladorBien.edit(bien);
        } catch (Exception ex) {
            Logger.getLogger(Articulo_Tarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
