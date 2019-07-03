/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alex
 */
public class Conexion
{
    private String nombreBD=null;
    private String usuario;
    private String password;
    private String url = null;
    private Connection con = null;
    private static Conexion conexion = null;
    
    private Conexion()
    {
        Lector lec = Lector.getInstancia();
        ArrayList arreglo = lec.leerDatos(System.getProperty("user.dir") + System.getProperty("file.separator") + "datos.conf");
        //el arreglo lleva los datos nombreBD usuario y contraseña
        nombreBD = (String) arreglo.remove(0);
        usuario = (String) arreglo.remove(0);
        password = (String) arreglo.remove(0);
        url = "jdbc:mysql://localhost:3306/"+nombreBD+"?autoReconnect=true&useSSL=false";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos, contacte al administrador");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Conexion getInstancia()
    {
        if(conexion == null)
        {
            conexion = new Conexion();
        }
        return conexion;
    }
    public Connection getConnection()
    {
        return con;
    }
    
}
