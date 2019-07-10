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
import vista.Bien;

/**
 *
 * @author nahomi
 */
public class ProxyInventario implements TableModel
{
    DefaultTableModel objetoReal = new DefaultTableModel();
    
    public ProxyInventario(List<Bien> bienes)
    {
        Object[] fila = new Object[6];
        for (Bien bien : bienes)
        {
            fila[0] = bien.getCodigo();
            fila[1] = bien.getDescripcion();
            fila[2] = bien.getCantidad();
            fila[3] = bien.getPrecioUnitario();
            fila[4] = bien.getEstado();
            fila[5] = bien.getFungible();
            fila[6] = bien.getPrecioTotal();
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
}
