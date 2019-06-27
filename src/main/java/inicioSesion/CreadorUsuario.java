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
public class CreadorUsuario 
{
    private FabricaUsuario fabrica;
    private Usuario persona;
    
    public CreadorUsuario(FabricaUsuario fabrica)
    {
        this.fabrica = fabrica;
    }
    
    public Usuario crear(int tipo)
    {
        if(tipo == Usuario.ADMIN) fabrica = new Administrador();
        else if(tipo == Usuario.BODEGUERO) fabrica = new Bodeguero();
        else if(tipo == Usuario.EMPLEADO) fabrica = new Empleado();
        return fabrica.crearUsuario("Nuevo usuario", "user1", "contraseña");
    }
}
