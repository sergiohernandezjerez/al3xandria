/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnnaDC
 */
public class meuRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable taula, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(taula, value, isSelected, hasFocus, row, column);

        if (taula.getSelectedRow() == row && taula.getSelectedColumn() == column) {

            Font f1 = new Font("Courier", Font.BOLD, 16);

            cell.setFont(f1);

            if (column == 1) {
                cell.setBackground(Color.red);
            } else {
                cell.setBackground(Color.green);
            }

            if (value.toString().equals("x")) {
                cell.setBackground(Color.YELLOW);
            }

        } else {
            cell.setBackground(Color.WHITE);
            cell.setForeground(Color.BLACK);
        }
        return cell;
    }

}
