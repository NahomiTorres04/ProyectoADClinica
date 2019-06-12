/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacionbd;

import java.sql.Connection;

/**
 *
 * @author Nahomi
 */
public class Consultas
{
    private Connection con = null;
    private Conexion conexion = Conexion.getInstancia();
    private static Consultas consultas = null;
    
    private Consultas()
    {
        con = conexion.getConnection();
    }
    public static Consultas getInstancia()
    {
        if(consultas == null)
        {
            consultas = new Consultas();
        }
        return consultas;
    }
}
