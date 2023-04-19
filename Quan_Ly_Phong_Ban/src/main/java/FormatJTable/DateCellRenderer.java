/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormatJTable;

import Helper.Utility;
import java.awt.Component;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Chau Thinh
 */
public class DateCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        try {
            value = Utility.getInstance().convertDateToString(value.toString(), "dd/MM/yyyy");
        } catch (ParseException ex) {
            Logger.getLogger(DateCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        setHorizontalAlignment(RIGHT);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
    
}
