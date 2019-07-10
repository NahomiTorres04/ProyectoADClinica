/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjeta_Responsabilidad;

import java.util.List;
import entidades.Empleado;

/**
 *
 * @author Nahomi
 */
public class Tarjeta implements Responsabilidad{

    private List<Articulo_Tarjeta> articulos;
    
    public void agregarArticulo(Articulo_Tarjeta articulo)
    {
        articulos.add(articulo);
    }

    @Override
    public void guardar(Empleado empleado) {
        for(Articulo_Tarjeta articulo : articulos)
        {
            articulo.guardar(empleado);
        }
    }
}
