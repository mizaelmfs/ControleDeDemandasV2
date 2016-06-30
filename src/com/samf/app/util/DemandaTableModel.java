package com.samf.app.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.samf.app.entity.Demanda;
import java.util.Date;

public class DemandaTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private static final int COL_DATA = 1;
    private static final int COL_CODIGO = 0;
    private static final int COL_CONCLUIDO = 2;
    private static final int COL_IMPRESSO = 3;
    private static final int COL_SETOR = 4;
    private static final int COL_DESCRICAO = 5;

    public List<Demanda> valores;

    public DemandaTableModel(List<Demanda> valores) {
        this.valores = valores;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {

        return valores.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Demanda demanda = valores.get(rowIndex);
        if (columnIndex == COL_DATA) {
            return demanda.getData();
        } else if (columnIndex == COL_CODIGO) {
            return demanda.getCodigo();
        } else if (columnIndex == COL_CONCLUIDO) {
            return demanda.getConcluido();
        } else if (columnIndex == COL_IMPRESSO) {
            return demanda.getTramitado();
        } else if (columnIndex == COL_SETOR) {
            return demanda.getSetor();
        } else if (columnIndex == COL_DESCRICAO) {
            return demanda.getDescricao();
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_DATA:
                coluna = "Data";
                break;
            case COL_CODIGO:
                coluna = "Código";
                break;
            case COL_CONCLUIDO:
                coluna = "Concluido";
                break;
            case COL_IMPRESSO:
                coluna = "Tramitado";
                break;
            case COL_SETOR:
                coluna = "Setor";
                break;
            case COL_DESCRICAO:
                coluna = "Descrição";
                break;
            default:
                throw new IllegalArgumentException("Coluna Invalida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int ColumnIndex) {
        if (ColumnIndex == COL_DATA) {
            return Date.class;
        } else if (ColumnIndex == COL_CODIGO) {
            return Long.class;
        } else if (ColumnIndex == COL_CONCLUIDO) {
            return String.class;
        } else if (ColumnIndex == COL_IMPRESSO) {
            return String.class;
        } else if (ColumnIndex == COL_SETOR) {
            return String.class;
        } else if (ColumnIndex == COL_DESCRICAO) {
            return String.class;
        }
        return null;
    }

    public Demanda get(int row) {
        return valores.get(row);
    }

}
