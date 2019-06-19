/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inicioSesion;

/**
 *
 * @author Nahomi
 */
public abstract class Usuario implements FabricaUsuario, Permisos
{
    private String nombre, usuario, contraseña;
    public static final int ADMIN = 1, EMPLEADO = 2, BODEGUERO = 3, VISITANTE = 4;

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getUsuario() 
    {
        return usuario;
    }

    public void setUsuario(String usuario) 
    {
        this.usuario = usuario;
    }

    public String getContrasenia() 
    {
        return contraseña;
    }

    public void setContraseña(String contraseña) 
    {
        this.contraseña = contraseña;
    }

}
