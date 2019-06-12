/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacionbd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class Lector
{
    private static Lector lector = new Lector();
    
    private Lector(){}
    public static Lector getInstancia()
    {
        return lector;
    }
    public ArrayList leerDatos(String ruta)
    {
        ArrayList<String> arreglo = new ArrayList<>();
        File archivo = new File(ruta);
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String st;
            while((st = br.readLine()) != null)
            {
                arreglo.add(st);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arreglo;
    }
}
