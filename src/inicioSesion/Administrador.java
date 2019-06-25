/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inicioSesion;

import GUI.Main;
import GUI.Principal;

/**
 *
 * @author Nahomi
 */
public class Administrador extends Usuario
{
    @Override
    public Usuario crearUsuario(String nombre, String usuario, String contraseña)
    {
        this.setNombre(nombre);
        this.setUsuario(usuario);
        this.setContraseña(contraseña);
        return this;
    }    

    @Override
    public void activarPermisos()
    {
        Principal principal = Principal.getInstancia();
        principal.btnusuarios.setVisible(true);
        principal.btnmenuempleado.setVisible(true);
        principal.btnmenuinventario.setVisible(true);
        principal.btnfinanciero.setVisible(true);
        Main.entrar();
    }
}
