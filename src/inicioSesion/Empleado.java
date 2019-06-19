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
public class Empleado extends Usuario
{
    @Override
    public Usuario crearUsuario(String nombre, String usuario, String contraseña) {
        this.setNombre(nombre);
        this.setUsuario(usuario);
        this.setContraseña(contraseña);
        return this;
    }  

    @Override
    public void activarPermisos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
