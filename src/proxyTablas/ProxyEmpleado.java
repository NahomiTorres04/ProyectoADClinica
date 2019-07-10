/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyTablas;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import entidades.Empleado;

/**
 *
 * @author nahomi
 */
public class ProxyEmpleado implements TableModel
{
        DefaultTableModel objetoReal = new DefaultTableModel();
    
    public ProxyEmpleado(List<Empleado> empleados)
    {
        objetoReal = new DefaultTableModel(new Object[]{"Nombre", "Apellido",
        "Lugar", "Profesión"}, 0);
        Object[] fila = new Object[4];
        for (Empleado empleado : empleados)
        {
            fila[0] = empleado.getNombres();
            fila[1] = empleado.getApellidos();
            fila[2] = empleado.getLugar();
            fila[3] = empleado.getPuesto(); // no se si es profesion cambiar por cargo si no.
            objetoReal.addRow(fila);
        }
    }
    @Override
    public int getRowCount() {
        return objetoReal.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return objetoReal.getColumnCount();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return objetoReal.getColumnName(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return objetoReal.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return objetoReal.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       return objetoReal.getValueAt(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        objetoReal.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        objetoReal.addTableModelListener(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        objetoReal.removeTableModelListener(l);
    }
    
    public DefaultTableModel getModel()
    {
        return objetoReal;
    }
}
