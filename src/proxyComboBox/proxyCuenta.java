/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyComboBox;

import entidades.Cuenta;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author nahomi
 */
public class proxyCuenta implements ComboBoxModel<Cuenta>{
    private DefaultComboBoxModel<Cuenta> objetoReal = new DefaultComboBoxModel<>();
    public proxyCuenta(List<Cuenta> cuentas)
    {
        for(Cuenta cuenta : cuentas)
        {
            objetoReal.addElement(cuenta);
        }
    }
    @Override
    public void setSelectedItem(Object anItem) {
        objetoReal.setSelectedItem(anItem);
    }

    @Override
    public Object getSelectedItem() {
      return  objetoReal.getSelectedItem();
    }

    @Override
    public int getSize() {
      return objetoReal.getSize();
    }

    @Override
    public Cuenta getElementAt(int index) {
       return  objetoReal.getElementAt(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        objetoReal.addListDataListener(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        objetoReal.removeListDataListener(l);
    }
    public DefaultComboBoxModel getModel()
    {
        return objetoReal;
    }
}
