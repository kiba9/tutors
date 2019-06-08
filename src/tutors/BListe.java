/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutors;

import com.placeholder.PlaceHolder;
import com.sun.java.swing.plaf.windows.WindowsScrollBarUI;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.Collections;

/**
 *
 * @author Cena
 */
public class BListe extends javax.swing.JPanel {

    Accueil Acc = new Accueil();
    File f = null;
    int sel_exe = 0;
    JLabel jl = new JLabel();

    /**
     * Creates new form BListe
     */
    public BListe() {
        initComponents();
        PlaceHolder p = new PlaceHolder(lbl_seach, new Color(200, 200, 200), Color.black,
                "nom, prenom, matricule", false, "Agency FB", 16);
        jScrollPane2.getViewport().setBackground(new Color(242, 245, 255));
        jScrollPane2.getVerticalScrollBar().setUI(new WindowsScrollBarUI());
        jTable1.setRowHeight(60);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
        
        ChargeT();
         
    }
     

    private void TrirTab() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> Tri = new TableRowSorter<>(model);
        jTable1.setRowSorter(Tri);

        Tri.setRowFilter(javax.swing.RowFilter.regexFilter(lbl_seach.getText().toUpperCase()));
        // Tri.setRowFilter(javax.swing.RowFilter.regexFilter(lbl_filter.getText().toLowerCase()));
    }

   
    
    public void ChargeT() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        int n = 0;
        
        if(lic_rd_btn.isSelected()) n = 1;
        else if(ing_rb_btn.isSelected()) n = 2;
        
        int k = 0;
        for (Parrain etudiant : Acc.ListParrain) {
            
            if (Filtr_fil.getSelectedItem().toString().equalsIgnoreCase(etudiant.getFilETD()) 
                    && n == etudiant.getNivETD()) {
                
                Object[] dataRow = {
                    etudiant.getMatETD().toUpperCase(),
                    etudiant.getNomETD().toUpperCase() + " "+ etudiant.getPrenomETD(), 
                    etudiant.getFilETD().toUpperCase() + " "+ String.valueOf(etudiant.getNivETD()),
                    k};
                model.addRow(dataRow);
                k++;
               
                
            } else if (Filtr_fil.getSelectedItem().toString().
                    equalsIgnoreCase("AUCUN") && n == etudiant.getNivETD()) {
                 Object[] dataRow = {
                    etudiant.getMatETD().toUpperCase(),
                    etudiant.getNomETD().toUpperCase() + " "+ etudiant.getPrenomETD(), 
                    etudiant.getFilETD().toUpperCase() + " "+ String.valueOf(etudiant.getNivETD()),
                    k};
                model.addRow(dataRow);
                k++;
            }
            
        }
        k = 0;
        for (Etudiant etudiant : Acc.ListFilleul) {
            
            if (Filtr_fil.getSelectedItem().toString().
                    equalsIgnoreCase(etudiant.getFilETD()) && n == etudiant.getNivETD()) {
      
                Object[] dataRow = {
                    etudiant.getMatETD().toUpperCase(),
                    etudiant.getNomETD().toUpperCase() + " "+ etudiant.getPrenomETD(), 
                    etudiant.getFilETD().toUpperCase() + " "+ String.valueOf(etudiant.getNivETD()),
                    k};
                model.addRow(dataRow);
                k = k+1;
                
            } else if (Filtr_fil.getSelectedItem().toString().
                    equalsIgnoreCase("AUCUN") && n == etudiant.getNivETD()) {
                 Object[] dataRow = {
                    etudiant.getMatETD().toUpperCase(),
                    etudiant.getNomETD().toUpperCase() + " "+ etudiant.getPrenomETD(), 
                    etudiant.getFilETD().toUpperCase() + " "+ String.valueOf(etudiant.getNivETD()),
                    k};
                model.addRow(dataRow);
                k = k+1;
            }
        }
    }
    
    public void SaveFile() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Filleul.ths"))));
            oos.writeObject(Acc.getListFilleul());
            oos.flush();
            oos.close();
        } catch (IOException e) {
            java.awt.Toolkit.getDefaultToolkit().notify();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Enregistrement", JOptionPane.INFORMATION_MESSAGE);
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("parrain.ths"))));
            oos.writeObject(Acc.getListParrain());
            oos.flush();
            oos.close();
        } catch (IOException e) {
            java.awt.Toolkit.getDefaultToolkit().notify();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Enregistrement", JOptionPane.INFORMATION_MESSAGE);
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

        Fil_rdBtn = new javax.swing.ButtonGroup();
        HeadPane = new javax.swing.JPanel();
        lbl_seach = new javax.swing.JTextField();
        Filtr_fil = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ing_rb_btn = new javax.swing.JRadioButton();
        lic_rd_btn = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HeadPane.setBackground(new java.awt.Color(0, 51, 255));

        lbl_seach.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lbl_seach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 2));
        lbl_seach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbl_seachKeyReleased(evt);
            }
        });

        Filtr_fil.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Filtr_fil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aucun", "Licence", "Ingenieur" }));
        Filtr_fil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Filtr_fil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Filtr_filActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Filtre de filiere");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Rechercher");

        Fil_rdBtn.add(ing_rb_btn);
        ing_rb_btn.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        ing_rb_btn.setForeground(new java.awt.Color(255, 255, 255));
        ing_rb_btn.setText("Niveau 2 only");
        ing_rb_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rb_btnMouseClicked(evt);
            }
        });
        ing_rb_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioB(evt);
            }
        });

        Fil_rdBtn.add(lic_rd_btn);
        lic_rd_btn.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lic_rd_btn.setForeground(new java.awt.Color(255, 255, 255));
        lic_rd_btn.setSelected(true);
        lic_rd_btn.setText("Niveau 1 only");
        lic_rd_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rb_btnMouseClicked(evt);
            }
        });
        lic_rd_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioB(evt);
            }
        });

        javax.swing.GroupLayout HeadPaneLayout = new javax.swing.GroupLayout(HeadPane);
        HeadPane.setLayout(HeadPaneLayout);
        HeadPaneLayout.setHorizontalGroup(
            HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeadPaneLayout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addGroup(HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ing_rb_btn)
                    .addComponent(lic_rd_btn))
                .addGap(26, 26, 26)
                .addGroup(HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Filtr_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(31, 31, 31)
                .addGroup(HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(lbl_seach, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        HeadPaneLayout.setVerticalGroup(
            HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadPaneLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(lic_rd_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Filtr_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_seach, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ing_rb_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(HeadPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 70));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null", "null", "null", "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 10));
        jTable1.setOpaque(false);
        jTable1.setRowHeight(100);
        jTable1.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jTable1.setTableHeader(null);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(15);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 81, 570, 434));
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_seachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_seachKeyReleased
        // TODO add your handling code here:
        TrirTab();
    }//GEN-LAST:event_lbl_seachKeyReleased

    private void Filtr_filActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Filtr_filActionPerformed
        // TODO add your handling code here:
        ChargeT();
    }//GEN-LAST:event_Filtr_filActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1
                && jTable1.getSelectedColumn() != 3) {
            int i = 0, m, j = jTable1.getSelectedRow();
            boolean t = false;
            Modif mdf;
            String niv = jTable1.getValueAt(j, 2).toString();
            niv = (niv.length() <= 9) ? niv.substring(8) : niv.substring(10);

            if (niv.equalsIgnoreCase("1")) {
                while (i < (m = Acc.ListFilleul.size()) && t == false) {

                    Etudiant e = Acc.ListFilleul.get(i);
                    if (jTable1.getValueAt(j, 0).toString().equalsIgnoreCase(e.getMatETD())) {
                        t = true;
                        mdf = new Modif(Acc, true, e.getNomETD(), e.getPrenomETD(),
                                e.getMatETD(), e.getFilETD(), "no@isj.cm", e.getNivETD(), e.getFimage(), (int) jTable1.getValueAt(j, 3));
                        mdf.setVisible(true);
                        if(mdf.getVrf() == 2){
                        Acc.ListFilleul.remove(i);
                        }
                    }
                    i++;
                }
            } else if (niv.equalsIgnoreCase("2")) {
                while (i < (m = Acc.ListParrain.size()) && t == false) {
                    Parrain p = Acc.ListParrain.get(i);
                    if (jTable1.getValueAt(j, 0).toString().equalsIgnoreCase(p.getMatETD())) {
                        t = true;
                        mdf = new Modif(Acc, true, p.getNomETD(), p.getPrenomETD(),
                                p.getMatETD(), p.getFilETD(), p.getEmailPAR(), p.getNivETD(), p.getFimage(), (int) jTable1.getValueAt(j, 3));
                        mdf.setVisible(true);
                        if(mdf.getVrf() == 2){
                        Acc.ListParrain.remove(i);
                        }
                    }
                    i++;
                }
            }
//            SaveFile() ;
            ChargeT();
        }  
    }//GEN-LAST:event_jTable1MouseClicked

    private void radioB(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioB
        // TODO add your handling code here:
        ChargeT();
    }//GEN-LAST:event_radioB

    private void rb_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_btnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_btnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Fil_rdBtn;
    public javax.swing.JComboBox<String> Filtr_fil;
    public javax.swing.JPanel HeadPane;
    private javax.swing.JRadioButton ing_rb_btn;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable1;
    public javax.swing.JTextField lbl_seach;
    private javax.swing.JRadioButton lic_rd_btn;
    // End of variables declaration//GEN-END:variables

}
