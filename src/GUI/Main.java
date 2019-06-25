/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import inicioSesion.Login;

/**
 *
 * @author Nahomi
 */
public class Main 
{
    public static void main(String[] args)
    {
        Login login = Login.getInstancia();
        login.setVisible(true);
    }
    
    public static void entrar()
    {
        Principal principal = Principal.getInstancia();
        principal.setVisible(true);
    }
}
