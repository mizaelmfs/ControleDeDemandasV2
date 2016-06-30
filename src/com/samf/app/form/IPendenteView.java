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
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mizae
 */
public final class IPendenteView extends javax.swing.JInternalFrame {

    /**
     * Creates new form IPendenteView
     */
    private List<Demanda> demandaList;
    private Long idDemanda;
    public static Demanda demandaAlterar;

    public IPendenteView() {
        initComponents();
        inicializarTabela();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(72, 61, 139, 85));
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    }

    public void inicializarTabela() {
        demandaList = new DemandaController().findFirst();
        if (demandaList != null) {
            tabela.setModel(new DemandaTableModel(demandaList));
            tabela.setDefaultRenderer(Object.class, new DemandaCellRenderer());
        }
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
            if (idDemanda == null) {
                result = new DemandaController().addDemanda(demanda);
            } else {
                demanda.setId(idDemanda);
                result = new DemandaController().alterarDemanda(demanda);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmCodigo = new javax.swing.JPopupMenu();
        mmRecortar1 = new javax.swing.JMenuItem();
        mmCopiar1 = new javax.swing.JMenuItem();
        mmColar1 = new javax.swing.JMenuItem();
        separador1 = new javax.swing.JPopupMenu.Separator();
        mmAll1 = new javax.swing.JMenuItem();
        pmDescricao = new javax.swing.JPopupMenu();
        mmRecortar = new javax.swing.JMenuItem();
        mmCopiar = new javax.swing.JMenuItem();
        mmColar = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        mmAll = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        dataChosser = new com.toedter.calendar.JDateChooser();
        cbSetor = new javax.swing.JComboBox();
        rbTramitado = new javax.swing.JRadioButton();
        rbConcluido = new javax.swing.JRadioButton();
        lblDescricao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lblObrigatorio1 = new javax.swing.JLabel();
        lblObrigatorio3 = new javax.swing.JLabel();
        lblObrigatorio2 = new javax.swing.JLabel();
        lblObrigatorio4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnLimpar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        mmRecortar1.setText("Recorta");
        mmRecortar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmRecortar1ActionPerformed(evt);
            }
        });
        pmCodigo.add(mmRecortar1);

        mmCopiar1.setText("Copiar");
        mmCopiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmCopiar1ActionPerformed(evt);
            }
        });
        pmCodigo.add(mmCopiar1);

        mmColar1.setText("Colar");
        mmColar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmColar1ActionPerformed(evt);
            }
        });
        pmCodigo.add(mmColar1);
        pmCodigo.add(separador1);

        mmAll1.setText("Selecionar Tudo");
        mmAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmAll1ActionPerformed(evt);
            }
        });
        pmCodigo.add(mmAll1);

        mmRecortar.setText("Recorta");
        mmRecortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmRecortarActionPerformed(evt);
            }
        });
        pmDescricao.add(mmRecortar);

        mmCopiar.setText("Copiar");
        mmCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmCopiarActionPerformed(evt);
            }
        });
        pmDescricao.add(mmCopiar);

        mmColar.setText("Colar");
        mmColar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmColarActionPerformed(evt);
            }
        });
        pmDescricao.add(mmColar);
        pmDescricao.add(separador);

        mmAll.setText("Selecionar Tudo");
        mmAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmAllActionPerformed(evt);
            }
        });
        pmDescricao.add(mmAll);

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionar Demanda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Codigo :");

        txtCodigo.setComponentPopupMenu(pmCodigo);

        cbSetor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ACS", "ADMPR", "ALMOX", "AQR", "ATI", "BIBL", "CAIP", "CPADS", "DPC", "DRH", "DRL", "ENGER", "GABIN", "GRAF", "LCONT", "MALOT", "MANPRED", "NUTEL", "NUTRAN", "OUVIR", "PAC", "PROT", "SCOMP", "SECAP", "SERVMED", "SIATI", "SINPE", "SIOFI", "SISUP", "SPAT" }));

        rbTramitado.setText("Tramitado");

        rbConcluido.setText("Cocluido");

        lblDescricao.setText("Descrição : ");

        txtDescricao.setColumns(20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(5);
        txtDescricao.setComponentPopupMenu(pmDescricao);
        jScrollPane1.setViewportView(txtDescricao);

        lblObrigatorio1.setForeground(new java.awt.Color(255, 0, 0));

        lblObrigatorio3.setForeground(new java.awt.Color(255, 0, 0));

        lblObrigatorio2.setForeground(new java.awt.Color(255, 0, 0));

        lblObrigatorio4.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblObrigatorio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rbConcluido, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(rbTramitado)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                                .addComponent(cbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(dataChosser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblObrigatorio4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblObrigatorio3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblObrigatorio2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblObrigatorio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(dataChosser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblObrigatorio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbConcluido)
                    .addComponent(rbTramitado)
                    .addComponent(cbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescricao)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblObrigatorio3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(lblObrigatorio4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Demandas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabela.setName(""); // NOI18N
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabela);

        jPanel3.add(jScrollPane2);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpar)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir)
                .addGap(146, 146, 146))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnExcluir)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLimpar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        // TODO add your handling code here:
        rbConcluido.setSelected(false);
        rbTramitado.setSelected(false);
        onAlterar();
    }//GEN-LAST:event_tabelaMouseClicked

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
        onLimpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        onExcluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void mmRecortar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmRecortar1ActionPerformed
        txtCodigo.cut();
    }//GEN-LAST:event_mmRecortar1ActionPerformed

    private void mmCopiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmCopiar1ActionPerformed
        txtCodigo.copy();
    }//GEN-LAST:event_mmCopiar1ActionPerformed

    private void mmColar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmColar1ActionPerformed
        txtCodigo.paste();
    }//GEN-LAST:event_mmColar1ActionPerformed

    private void mmAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmAll1ActionPerformed
        txtCodigo.selectAll();
    }//GEN-LAST:event_mmAll1ActionPerformed

    private void mmRecortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmRecortarActionPerformed
        txtDescricao.cut();
    }//GEN-LAST:event_mmRecortarActionPerformed

    private void mmCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmCopiarActionPerformed
        txtDescricao.copy();
    }//GEN-LAST:event_mmCopiarActionPerformed

    private void mmColarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmColarActionPerformed
        txtDescricao.paste();
    }//GEN-LAST:event_mmColarActionPerformed

    private void mmAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmAllActionPerformed
        txtDescricao.selectAll();
    }//GEN-LAST:event_mmAllActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        onSave();
    }//GEN-LAST:event_btnSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
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
    private javax.swing.JMenuItem mmAll;
    private javax.swing.JMenuItem mmAll1;
    private javax.swing.JMenuItem mmColar;
    private javax.swing.JMenuItem mmColar1;
    private javax.swing.JMenuItem mmCopiar;
    private javax.swing.JMenuItem mmCopiar1;
    private javax.swing.JMenuItem mmRecortar;
    private javax.swing.JMenuItem mmRecortar1;
    private javax.swing.JPopupMenu pmCodigo;
    private javax.swing.JPopupMenu pmDescricao;
    private javax.swing.JRadioButton rbConcluido;
    private javax.swing.JRadioButton rbTramitado;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JPopupMenu.Separator separador1;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescricao;
    // End of variables declaration//GEN-END:variables
}
