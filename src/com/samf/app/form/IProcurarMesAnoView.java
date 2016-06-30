/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samf.app.form;

import com.samf.app.controller.DemandaController;
import com.samf.app.entity.Demanda;
import com.samf.app.util.DemandaCellRenderer;
import com.samf.app.util.DemandaTableModel;
import com.samf.app.util.ImprimirTable;
import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mizae
 */
public class IProcurarMesAnoView extends javax.swing.JInternalFrame {

    /**
     * Creates new form IProcurarView
     */
    private List<Demanda> demandaList;
    private Long idDemanda;
    public static Demanda demandaAlterar;

    public IProcurarMesAnoView() {
        initComponents();
        inicializarTabela();
        enableFields(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(72, 61, 139, 85));
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    }

    private void inicializarTabela() {
        demandaList = new DemandaController().findMonth(Monthchooser.getMonth() + 1, YearChooser.getYear());
        if (demandaList != null) {
            tabela.setModel(new DemandaTableModel(demandaList));
            tabela.setDefaultRenderer(Object.class, new DemandaCellRenderer());

        }
    }

    private void enableFields(boolean b) {
        txtCodigo.setEnabled(b);
        txtDescricao.setEnabled(b);
        cbSetor.setEnabled(b);
        dataChosser.setEnabled(b);
        rbConcluido.setEnabled(b);
        rbTramitado.setEnabled(b);
    }

    private void onSave() {
        Demanda demanda = new Demanda();
        if (txtCodigo.getText().length() > 0 && txtDescricao.getText().length() > 0
                && dataChosser.getDate().getTime() != 0) {

            demanda.setCodigo(Long.parseLong(txtCodigo.getText()));
            demanda.setConcluido(capturaConcluido());
            demanda.setTramitado(capturaTramitado());
            demanda.setData(dataChosser.getDate());
            demanda.setDescricao(txtDescricao.getText().trim().toUpperCase());
            demanda.setSetor((String) cbSetor.getSelectedItem());

            int result = 0;
            if (idDemanda != null) {
                demanda.setId(idDemanda);
                result = new DemandaController().alterarDemanda(demanda);
                enableFields(false);
                idDemanda = null;
            }

            if (result == 1) {
                JOptionPane.showMessageDialog(this, "Efetuado com Sucesso");
                onLimpar();
                inicializarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Desculpe, Mas ocorreu um erro!");
            }
        } else {
            new Thread() {
                @Override
                public void run() {
                    try {
                        lblObrigatorio1.setText("*");
                        lblObrigatorio2.setText("*");
                        lblObrigatorio3.setText("*");
                        lblObrigatorio4.setText("Compos Obrigatórios *");
                        sleep(3000);
                    } catch (InterruptedException ex) {
                    }
                    lblObrigatorio1.setText("");
                    lblObrigatorio2.setText("");
                    lblObrigatorio3.setText("");
                    lblObrigatorio4.setText("");
                }

            }.start();
        }

    }

    public void onAlterar() {
        enableFields(true);
        int rowIndex = tabela.getSelectedRow();
        demandaAlterar = new DemandaTableModel(demandaList).get(rowIndex);

        idDemanda = demandaAlterar.getId();

        txtCodigo.setText(String.valueOf(demandaAlterar.getCodigo()));
        txtDescricao.setText(demandaAlterar.getDescricao());
        dataChosser.setDate(demandaAlterar.getData());
        cbSetor.setSelectedItem(demandaAlterar.getSetor());
        if (demandaAlterar.getConcluido().equalsIgnoreCase("Sim")) {
            rbConcluido.setSelected(true);
        }
        if (demandaAlterar.getTramitado().equalsIgnoreCase("Sim")) {
            rbTramitado.setSelected(true);
        }
    }

    private void onLimpar() {
        if (txtCodigo.getText().length() > 0 && txtDescricao.getText().length() > 0
                && dataChosser.getDate().getTime() != 0) {
            txtCodigo.setText("");
            txtDescricao.setText("");
            dataChosser.setDate(new Date());
            cbSetor.setSelectedItem("ACS");
            rbConcluido.setSelected(false);
            rbTramitado.setSelected(false);
            inicializarTabela();
            enableFields(false);
        } else {
            JOptionPane.showMessageDialog(this, "Os campos estão vázios");
        }

    }

    private void onExcluir() {
        int rowIndex = tabela.getSelectedRow();

        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma Demanda para ser removido");
            return;
        }

        Demanda demanda = new DemandaTableModel(demandaList).get(rowIndex);
        int confirm = JOptionPane.showConfirmDialog(this, "Confirmar exclusão", "Ecluir Demanda", JOptionPane.YES_NO_OPTION);

        if (confirm != 0) {
            return;
        }

        int result = new DemandaController().removerDemanda(demanda.getId());

        if (result == 1) {
            JOptionPane.showMessageDialog(this, "Valor removido com Sucesso");
            inicializarTabela();
            onLimpar();
            enableFields(false);
        } else {
            JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro!");
        }

    }

    private String capturaConcluido() {
        if (rbConcluido.isSelected()) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    private String capturaTramitado() {
        if (rbTramitado.isSelected()) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    private void onBuscar() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lblDescricao = new javax.swing.JLabel();
        rbConcluido = new javax.swing.JRadioButton();
        rbTramitado = new javax.swing.JRadioButton();
        cbSetor = new javax.swing.JComboBox();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        dataChosser = new com.toedter.calendar.JDateChooser();
        Monthchooser = new com.toedter.calendar.JMonthChooser();
        YearChooser = new com.toedter.calendar.JYearChooser();
        lblObrigatorio1 = new javax.swing.JLabel();
        lblObrigatorio2 = new javax.swing.JLabel();
        lblObrigatorio3 = new javax.swing.JLabel();
        lblObrigatorio4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(1355, 540));
        setMinimumSize(new java.awt.Dimension(1355, 540));
        setPreferredSize(new java.awt.Dimension(1355, 540));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Título 2", "Título 3", "Título 4", "Title 4"
            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jPanel1.add(jScrollPane1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtDescricao.setColumns(20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        lblDescricao.setText("Descrição : ");

        rbConcluido.setText("Cocluido");

        rbTramitado.setText("Tramitado");

        cbSetor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ACS", "ADMPR", "ALMOX", "AQR", "ATI", "BIBL", "CAIP", "CPADS", "DPC", "DRH", "DRL", "ENGER", "GABIN", "GRAF", "LCONT", "MALOT", "MANPRED", "NUTEL", "NUTRAN", "OUVIR", "PAC", "PROT", "SCOMP", "SECAP", "SERVMED", "SIATI", "SINPE", "SIOFI", "SISUP", "SPAT" }));

        jLabel1.setText("Codigo :");

        Monthchooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                MonthchooserPropertyChange(evt);
            }
        });

        YearChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                YearChooserPropertyChange(evt);
            }
        });

        lblObrigatorio1.setForeground(new java.awt.Color(204, 0, 0));

        lblObrigatorio2.setForeground(new java.awt.Color(204, 0, 0));

        lblObrigatorio3.setForeground(new java.awt.Color(204, 0, 0));

        lblObrigatorio4.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblObrigatorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(rbConcluido, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(rbTramitado)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                                .addComponent(cbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(dataChosser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(Monthchooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(YearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDescricao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblObrigatorio4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblObrigatorio3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblObrigatorio2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Monthchooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(YearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dataChosser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObrigatorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObrigatorio2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbConcluido)
                    .addComponent(rbTramitado)
                    .addComponent(cbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricao)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObrigatorio3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblObrigatorio4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/saveKid.png"))); // NOI18N
        btnSalvar.setToolTipText("Salvar");
        btnSalvar.setContentAreaFilled(false);
        btnSalvar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/saveKid.png"))); // NOI18N
        btnSalvar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/save.png"))); // NOI18N
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/garbagerKid.png"))); // NOI18N
        btnExcluir.setToolTipText("Excluir ");
        btnExcluir.setContentAreaFilled(false);
        btnExcluir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/garbagerKid.png"))); // NOI18N
        btnExcluir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/garbager.png"))); // NOI18N
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/clearKid.png"))); // NOI18N
        btnLimpar.setToolTipText("Limpar os Campos");
        btnLimpar.setContentAreaFilled(false);
        btnLimpar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/clearKid.png"))); // NOI18N
        btnLimpar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/clear.png"))); // NOI18N
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/printkid.png"))); // NOI18N
        btnImprimir.setToolTipText("Imprimir");
        btnImprimir.setContentAreaFilled(false);
        btnImprimir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/printkid.png"))); // NOI18N
        btnImprimir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/folder/print.png"))); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpar)
                    .addComponent(btnSalvar))
                .addGap(146, 146, 146))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExcluir)
                    .addComponent(btnImprimir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimir)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
        onLimpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        onSave();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        onExcluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        // TODO add your handling code here:
        rbConcluido.setSelected(false);
        rbTramitado.setSelected(false);
        onAlterar();
    }//GEN-LAST:event_tabelaMouseClicked

    private void MonthchooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_MonthchooserPropertyChange
        // TODO add your handling code here:
        inicializarTabela();
    }//GEN-LAST:event_MonthchooserPropertyChange

    private void YearChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_YearChooserPropertyChange
        // TODO add your handling code here:
        inicializarTabela();
    }//GEN-LAST:event_YearChooserPropertyChange

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        ImprimirTable.onImprimirTable(tabela);
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JMonthChooser Monthchooser;
    private com.toedter.calendar.JYearChooser YearChooser;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cbSetor;
    private com.toedter.calendar.JDateChooser dataChosser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblObrigatorio1;
    private javax.swing.JLabel lblObrigatorio2;
    private javax.swing.JLabel lblObrigatorio3;
    private javax.swing.JLabel lblObrigatorio4;
    private javax.swing.JRadioButton rbConcluido;
    private javax.swing.JRadioButton rbTramitado;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescricao;
    // End of variables declaration//GEN-END:variables
}
