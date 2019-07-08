/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class Conexion
{
    private EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("ProyectoAD");
    private EntityManager em;
    private static Conexion conexion = null;
    
    private Conexion()
    {
        this.em = fabrica.createEntityManager();
    }
    
    public static Conexion getInstancia()
    {
        if(conexion == null) conexion = new Conexion();
        return conexion;
    }
    
    public EntityManager getEntityManager()
    {
        return em;
    }
}
