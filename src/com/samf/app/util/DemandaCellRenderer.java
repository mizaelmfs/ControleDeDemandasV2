package com.samf.app.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class DemandaCellRenderer extends DefaultTableCellRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String concluido = (String) table.getValueAt(row, 2);
        String tramitado = (String) table.getValueAt(row, 3);
        
        if (concluido.equalsIgnoreCase("Não")) {
            setBackground(Color.red);
            setForeground(Color.WHITE);
        } else if(concluido.equalsIgnoreCase("Sim") && tramitado.equalsIgnoreCase("Sim")){
            setBackground(Color.DARK_GRAY);
            setForeground(Color.WHITE);
        } else {
            setBackground(null);
            setForeground(Color.black);
        }

        if (isSelected) {
            setBackground(Color.BLUE);
            setForeground(Color.WHITE);
        }

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(90);
        table.getColumnModel().getColumn(2).setMaxWidth(50);
        table.getColumnModel().getColumn(3).setMaxWidth(50);
        table.getColumnModel().getColumn(4).setMaxWidth(60);
        table.getColumnModel().getColumn(5).setMaxWidth(2000);

        return this;
    }
}
