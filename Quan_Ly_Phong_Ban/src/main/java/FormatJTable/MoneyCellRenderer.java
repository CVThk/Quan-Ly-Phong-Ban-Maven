/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormatJTable;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Chau Thinh
 */
public class MoneyCellRenderer extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Format the value as currency
        value = String.format("%,.0f", Float.parseFloat(value.toString()));
        setHorizontalAlignment(RIGHT);
        // Call the parent renderer to format the cell
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
