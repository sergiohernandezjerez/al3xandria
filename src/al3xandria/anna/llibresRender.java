/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xhuix
 */
public class llibresRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable taula, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(taula,
                value, isSelected, hasFocus, row, column);

        DefaultTableModel model1 = (DefaultTableModel) taula.getModel();

        if (taula.getSelectedRow() == row) {
            cell.setForeground(Color.RED);
        } else {
            cell.setForeground(Color.BLUE);
        }

        String titol = model1.getValueAt(row, 1).toString();

        if ((boolean) model1.getValueAt(row, 11) == false) {
            cell.setBackground(Color.GRAY);
        } else {
            cell.setBackground(Color.YELLOW);
        }
        if (titol.indexOf("anarq") != -1) {
            cell.setBackground(Color.pink);
        }

        return cell;
    }
}
