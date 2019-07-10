/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyComboBox;

import entidades.Departamento;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author nahomi
 */
public class proxyCmbDepartamento implements ComboBoxModel<Departamento>{
    private DefaultComboBoxModel<Departamento> objetoReal = new DefaultComboBoxModel<>();
    public proxyCmbDepartamento(List<Departamento> departamentos)
    {
        for(Departamento departamento : departamentos)
        {
            objetoReal.addElement(departamento);
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
    public Departamento getElementAt(int index) {
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
    
}
