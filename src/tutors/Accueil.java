/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutors;

import com.placeholder.PlaceHolder;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author cena M
 */
public final class Accueil extends javax.swing.JFrame {

    int Xmouse, Ymouse, vrf = 0, x = 0, but = 1;
    Timer tm;
    JPanel bb = new JPanel();
    Color color_btn;
    File f = null, f2 = null;
    public ArrayList<Etudiant> ListFilleul = new ArrayList<>();
    public ArrayList<Parrain> ListParrain = new ArrayList<>();
    ArrayList<Object> ListChoix = new ArrayList<>();

    public ArrayList<Etudiant> getListFilleul() {
        return ListFilleul;
    }

    public void setListFilleul(ArrayList<Etudiant> ListFilleul) {
        this.ListFilleul = ListFilleul;
    }

    public ArrayList<Parrain> getListParrain() {
        return ListParrain;
    }

    public void setListParrain(ArrayList<Parrain> ListParrain) {
        this.ListParrain = ListParrain;
    }

    /**
     * Creates new form TutFrame
     */
    public Accueil() {
        initComponents();

        
        jTable1.setBorder(null);

        jScrollPane2.getViewport().setBackground(new Color(255, 255, 255));
        jScrollPane2.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        this.setLocationRelativeTo(null);
        PlaceHolder p = new PlaceHolder(lbl_seach, new Color(200, 200, 200), Color.black,
                "nom, prenom, matricule", false, "Agency FB", 16);
        ChargeTab();
        ChrgListPar();
//        setImageAndInfo(ListParrain.size()-1);
//System.out.println(ListParrain.size());

        tm = new Timer(50, (ActionEvent e) -> {
            setImageAndInfo(x);
            x += 1;
            if (x >= ListParrain.size()) {
                x = 0;
            }
            
        });
//        tm.start();
    }

    public void setColor(Component label, Component panel, Color Fgc, Color Bgc) {
        label.setForeground(Fgc);
        panel.setBackground(Bgc);
    }

    private void TrirTab() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> Tri = new TableRowSorter<>(model);
        jTable1.setRowSorter(Tri);

        Tri.setRowFilter(javax.swing.RowFilter.regexFilter(lbl_seach.getText().toUpperCase()));
        // Tri.setRowFilter(javax.swing.RowFilter.regexFilter(lbl_filter.getText().toLowerCase()));
    }

    public void ChargeTab() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        Object O;
        Etudiant E, e1, e2;
         

        
            f = new File("Filleul.ths");
        
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
            try {
                while ((O = ois.readObject()) != null) {
                    if ((ListFilleul = (ArrayList<Etudiant>) O) != null) {
                        for (Etudiant etudiant : ListFilleul) {
                            if (Filtr_fil.getSelectedItem().toString().
                                    equalsIgnoreCase(etudiant.getFilETD()) && etudiant.isPossed_par() == false) {
                                String[] dataRow = {etudiant.getMatETD().toUpperCase(), etudiant.getNomETD().toUpperCase(),
                                    etudiant.getPrenomETD(), etudiant.getFilETD().toUpperCase(),
                                    String.valueOf(etudiant.getNivETD())};
                                model.addRow(dataRow);
                            } else if (Filtr_fil.getSelectedItem().toString().
                                    equalsIgnoreCase("AUCUN") && etudiant.isPossed_par() == false) {
                                String[] dataRow = {etudiant.getMatETD().toUpperCase(), etudiant.getNomETD().toUpperCase(),
                                    etudiant.getPrenomETD(), etudiant.getFilETD().toUpperCase(),
                                    String.valueOf(etudiant.getNivETD())};
                                model.addRow(dataRow);
                            }
                        }
                    }
                }
                ois.close();
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException e) {

        }

    }

    public void ChrgListPar() {
        Object O;

        
            f2 = new File("parrain.ths");
        
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f2)));
            try {
                while ((O = ois.readObject()) != null) {
                    ListParrain = ((ArrayList<Parrain>) O);

                }
                ois.close();
            } catch (ClassNotFoundException ex) {
            }
        } catch (IOException e) {
            
        }

    }

    public void RandomTri() {
        Random r = new Random();
        boolean trouve = false;
        int Val = r.nextInt(3), i = 0;
        Object O;

        for (Parrain parrain : ListParrain) {
            if (filfil1.getText().equalsIgnoreCase(parrain.getFilETD())) {
                rotate(parrain);
            }
        }
    }

    public void rotate(Parrain parrain) {

        /*try {
        Thread.sleep(10);
        } catch (InterruptedException ex) {
        Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        matpar1.setText(parrain.getMatETD());
        filpar1.setText(parrain.getFilETD());
        nivpar1.setText(String.valueOf(parrain.getNivETD()));
        nompar1.setText(parrain.getNomETD());
        Surnamepar1.setText(parrain.getPrenomETD());
        emailpar1.setText(parrain.getEmailPAR());

        BufferedImage bi = null;
        try {
            bi = ImageIO.read(parrain.getFimage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        ImageIcon icon = new ImageIcon(bi.getScaledInstance(Lbl_ParrainPic.getWidth(),
                Lbl_ParrainPic.getHeight(), Image.SCALE_DEFAULT));
        Lbl_ParrainPic.setIcon(icon);

    }

    public void setImageAndInfo(int i) {
        if (filfil1.getText().equalsIgnoreCase(ListParrain.get(i).getFilETD())) {
            matpar1.setText(ListParrain.get(i).getMatETD());
            filpar1.setText(ListParrain.get(i).getFilETD());
            nivpar1.setText(String.valueOf(ListParrain.get(i).getNivETD()));
            nompar1.setText(ListParrain.get(i).getNomETD());
            Surnamepar1.setText(ListParrain.get(i).getPrenomETD());
            emailpar1.setText(ListParrain.get(i).getEmailPAR());
            BufferedImage bi = null;
            try {
                bi = ImageIO.read(ListParrain.get(i).getFimage());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            ImageIcon icon = new ImageIcon(bi.getScaledInstance(Lbl_ParrainPic.getWidth(),
                    Lbl_ParrainPic.getHeight(), Image.SCALE_DEFAULT));
            Lbl_ParrainPic.setIcon(icon);
        }    
    }
    
    public void Binome(){
        for (Parrain p : ListParrain){
            if( p.getMatETD().equalsIgnoreCase(matpar1.getText())){
                p.setStatutPAR(1);
                p.setNbFilPAR(p.getNbFilPAR()+1);
                p.setMat_fil(matfil1.getText());
            }
        }
        for (Etudiant e: ListFilleul){
            if( e.getMatETD().equalsIgnoreCase(matfil1.getText())){
                e.setPossed_par(true);
            }
        }
        ChargeTab();
    }
    
////    public void printF() {
////        for(Etudiant e:ListFilleul){
////            System.out.println(e.toString());
////        }
////        for(Parrain p:ListParrain){
////            System.out.println(p);
////        }
////    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bg = new javax.swing.JPanel();
        SidePane = new javax.swing.JPanel();
        btn_home = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_AdStud = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_RmvStud = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_LstStud = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        HeadPane = new javax.swing.JPanel();
        lbl_seach = new javax.swing.JTextField();
        Filtr_fil = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        FrameDrag = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_close = new javax.swing.JLabel();
        ComPan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        NivFill = new javax.swing.JLabel();
        FilFill = new javax.swing.JLabel();
        SurnameFill = new javax.swing.JLabel();
        NameFill = new javax.swing.JLabel();
        MatFill = new javax.swing.JLabel();
        Lbl_FilleulPic = new javax.swing.JLabel();
        Lbl_ParrainPic = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        NivPar = new javax.swing.JLabel();
        FilPar = new javax.swing.JLabel();
        SurnamePar = new javax.swing.JLabel();
        NamePar = new javax.swing.JLabel();
        MatPar = new javax.swing.JLabel();
        EmailPar = new javax.swing.JLabel();
        matfil1 = new javax.swing.JLabel();
        matpar1 = new javax.swing.JLabel();
        Surnamefil1 = new javax.swing.JLabel();
        nivfil1 = new javax.swing.JLabel();
        nomfil1 = new javax.swing.JLabel();
        filfil1 = new javax.swing.JLabel();
        filpar1 = new javax.swing.JLabel();
        nivpar1 = new javax.swing.JLabel();
        nompar1 = new javax.swing.JLabel();
        Surnamepar1 = new javax.swing.JLabel();
        emailpar1 = new javax.swing.JLabel();
        atribSpons = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(78, 55, 455, 1));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        Bg.setBackground(new java.awt.Color(255, 255, 255));
        Bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SidePane.setBackground(new java.awt.Color(0, 0, 153));
        SidePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_home.setBackground(new java.awt.Color(0, 81, 153));
        btn_home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Home Sponsoring");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMouseGain(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMouseLost(evt);
            }
        });
        btn_home.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 60));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorsv0/img/home.png"))); // NOI18N
        btn_home.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 60));

        SidePane.add(btn_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 270, 60));

        btn_AdStud.setBackground(new java.awt.Color(0, 41, 153));
        btn_AdStud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Add Student");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_AdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMouseGain(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMouseLost(evt);
            }
        });
        btn_AdStud.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 60));

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("+");
        jLabel6.setToolTipText("");
        btn_AdStud.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 60));

        SidePane.add(btn_AdStud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 270, 60));

        btn_RmvStud.setBackground(new java.awt.Color(0, 41, 153));
        btn_RmvStud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Remove Student");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMouseGain(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMouseLost(evt);
            }
        });
        btn_RmvStud.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 60));

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 44)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("-");
        btn_RmvStud.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 60));

        SidePane.add(btn_RmvStud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 270, -1));

        btn_LstStud.setBackground(new java.awt.Color(0, 0, 153));
        btn_LstStud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("List Of Student");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMouseGain(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMouseLost(evt);
            }
        });
        btn_LstStud.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 60));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorsv0/img/list.png"))); // NOI18N
        btn_LstStud.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 60));

        SidePane.add(btn_LstStud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 270, -1));

        jLabel2.setFont(new java.awt.Font("Agency FB", 2, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TUTOR'S");
        SidePane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 110, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Help Sponsoring");
        SidePane.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 10));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 5));
        SidePane.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 210, 10));

        Bg.add(SidePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 545));

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

        javax.swing.GroupLayout HeadPaneLayout = new javax.swing.GroupLayout(HeadPane);
        HeadPane.setLayout(HeadPaneLayout);
        HeadPaneLayout.setHorizontalGroup(
            HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeadPaneLayout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addGroup(HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Filtr_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(57, 57, 57)
                .addGroup(HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(lbl_seach, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        HeadPaneLayout.setVerticalGroup(
            HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HeadPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Filtr_fil, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_seach, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Bg.add(HeadPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 570, 70));

        FrameDrag.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        FrameDrag.setText("     C . D . A   Copyright ©  ISJ 2018");
        FrameDrag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                FrameDragMouseDragged(evt);
            }
        });
        FrameDrag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FrameDragMousePressed(evt);
            }
        });
        Bg.add(FrameDrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 520, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_close.setBackground(new java.awt.Color(255, 255, 255));
        btn_close.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_close.setText("X");
        btn_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_closeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 50, 30));

        ComPan.setBackground(new java.awt.Color(255, 255, 255));
        ComPan.setPreferredSize(new java.awt.Dimension(570, 500));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricule", "Nom", "Prenom", "Filière", "Niveau"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(0, 102, 153));
        jTable1.setRowHeight(34);
        jTable1.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(dtcr);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
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
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        NivFill.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        NivFill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NivFill.setText("Niveau:");
        NivFill.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        FilFill.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        FilFill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FilFill.setText("Filiere:");
        FilFill.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        SurnameFill.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        SurnameFill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SurnameFill.setText("Surname:");
        SurnameFill.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        NameFill.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        NameFill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NameFill.setText("Name:");
        NameFill.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        MatFill.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        MatFill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MatFill.setText("Matricule:");
        MatFill.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        Lbl_FilleulPic.setBackground(new java.awt.Color(204, 255, 255));
        Lbl_FilleulPic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lbl_FilleulPic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        Lbl_ParrainPic.setBackground(new java.awt.Color(204, 204, 204));
        Lbl_ParrainPic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lbl_ParrainPic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jSeparator2.setBackground(new java.awt.Color(238, 235, 235));
        jSeparator2.setForeground(new java.awt.Color(238, 235, 235));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        NivPar.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        NivPar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NivPar.setText("Niveau:");
        NivPar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        FilPar.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        FilPar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FilPar.setText("Filiere:");
        FilPar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        SurnamePar.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        SurnamePar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SurnamePar.setText("Surname:");
        SurnamePar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        NamePar.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        NamePar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NamePar.setText("Name:");
        NamePar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        MatPar.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        MatPar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MatPar.setText("Matricule:");
        MatPar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        EmailPar.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        EmailPar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmailPar.setText("Email:");
        EmailPar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 51, 255)));

        matfil1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        matpar1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        matpar1.setForeground(new java.awt.Color(0, 51, 153));

        Surnamefil1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        nivfil1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        nivfil1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        nomfil1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        filfil1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        filpar1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        filpar1.setForeground(new java.awt.Color(0, 51, 153));

        nivpar1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        nivpar1.setForeground(new java.awt.Color(0, 51, 153));

        nompar1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        nompar1.setForeground(new java.awt.Color(0, 51, 153));

        Surnamepar1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Surnamepar1.setForeground(new java.awt.Color(0, 51, 153));

        emailpar1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        emailpar1.setForeground(new java.awt.Color(0, 51, 153));

        atribSpons.setBackground(new java.awt.Color(0, 102, 204));
        atribSpons.setFont(new java.awt.Font("Agency FB", 0, 16)); // NOI18N
        atribSpons.setForeground(new java.awt.Color(255, 255, 255));
        atribSpons.setText("Attribuer");
        atribSpons.setEnabled(false);
        atribSpons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atribSponsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ComPanLayout = new javax.swing.GroupLayout(ComPan);
        ComPan.setLayout(ComPanLayout);
        ComPanLayout.setHorizontalGroup(
            ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ComPanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ComPanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(atribSpons, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ComPanLayout.createSequentialGroup()
                        .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ComPanLayout.createSequentialGroup()
                                .addComponent(Lbl_FilleulPic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(FilFill)
                                    .addComponent(MatFill)
                                    .addComponent(matfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(filfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NivFill)
                                    .addComponent(nivfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(ComPanLayout.createSequentialGroup()
                                    .addComponent(SurnameFill)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Surnamefil1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ComPanLayout.createSequentialGroup()
                                    .addComponent(NameFill)
                                    .addGap(18, 18, 18)
                                    .addComponent(nomfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(25, 25, 25)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ComPanLayout.createSequentialGroup()
                                .addComponent(Lbl_ParrainPic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(matpar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(filpar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nivpar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(ComPanLayout.createSequentialGroup()
                                        .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(MatPar)
                                            .addComponent(NivPar)
                                            .addComponent(FilPar))
                                        .addGap(0, 39, Short.MAX_VALUE))))
                            .addGroup(ComPanLayout.createSequentialGroup()
                                .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(ComPanLayout.createSequentialGroup()
                                        .addComponent(SurnamePar)
                                        .addGap(18, 18, 18)
                                        .addComponent(Surnamepar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(ComPanLayout.createSequentialGroup()
                                        .addComponent(EmailPar)
                                        .addGap(18, 18, 18)
                                        .addComponent(emailpar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(ComPanLayout.createSequentialGroup()
                                        .addComponent(NamePar)
                                        .addGap(18, 18, 18)
                                        .addComponent(nompar1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        ComPanLayout.setVerticalGroup(
            ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ComPanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ComPanLayout.createSequentialGroup()
                        .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ComPanLayout.createSequentialGroup()
                                .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(ComPanLayout.createSequentialGroup()
                                        .addComponent(MatPar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(matpar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FilPar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(filpar1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(NivPar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nivpar1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Lbl_ParrainPic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NamePar)
                                    .addComponent(nompar1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addComponent(SurnamePar))
                            .addComponent(Surnamepar1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EmailPar)
                            .addComponent(emailpar1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ComPanLayout.createSequentialGroup()
                        .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ComPanLayout.createSequentialGroup()
                                .addComponent(MatFill)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(matfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FilFill)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NivFill)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nivfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Lbl_FilleulPic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nomfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NameFill))
                        .addGap(18, 18, 18)
                        .addGroup(ComPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Surnamefil1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SurnameFill))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(atribSpons)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        Bg.add(ComPan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 570, 435));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Bg, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseClicked
        try {
            // TODO add your handling code here:
            javax.swing.UIManager.setLookAndFeel(new WindowsLookAndFeel());
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(null, "\tVoulez-vous vraiment quittez ?", "Fermeture", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
            javax.swing.UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_closeMouseClicked

    private void btn_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseEntered
        // TODO add your handling code here:
        setColor(btn_close, jPanel1, Color.white, new Color(240, 0, 0));
    }//GEN-LAST:event_btn_closeMouseEntered

    private void btn_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseExited
        // TODO add your handling code here:
        setColor(btn_close, jPanel1, Color.black, Color.white);
    }//GEN-LAST:event_btn_closeMouseExited

    private void FrameDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FrameDragMousePressed
        // TODO add your handling code here:
        Xmouse = evt.getX();
        Ymouse = evt.getY();
    }//GEN-LAST:event_FrameDragMousePressed

    private void FrameDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FrameDragMouseDragged
        // TODO add your handling code here:
        int xn = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(xn - Xmouse - 270, y - Ymouse);
    }//GEN-LAST:event_FrameDragMouseDragged

    private void btnMouseGain(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMouseGain
        // TODO add your handling code here:
        color_btn = evt.getComponent().getParent().getBackground();
        setColor(evt.getComponent(), evt.getComponent().getParent(), Color.white, new Color(0, 102, 180));
        setCursor(HAND_CURSOR);
    }//GEN-LAST:event_btnMouseGain

    private void btnMouseLost(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMouseLost
        // TODO add your handling code here:
        setColor(evt.getComponent(), evt.getComponent().getParent(), Color.white, color_btn);
        setCursor(DEFAULT_CURSOR);
    }//GEN-LAST:event_btnMouseLost

    private void btn_AdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AdMouseClicked
        // TODO add your handling code here:
        
        if (evt.getButton() == 1) {
            Ajouter fen = new Ajouter(this, true);
            fen.setVisible(true);
        }
        if(but == 3){
            bb.removeAll();
            BListe bl = new BListe();
        
            ComPan.setVisible(false);
            HeadPane.setVisible(false);
            
            bb.setBackground(Color.WHITE);
            bb.add(bl);
            Bg.add(bb, new AbsoluteConstraints(270, 25, 570, 520));
            
            bl.setVisible(true);
            bb.setVisible(true);
        }
        if(but == 1)ChargeTab();
        ChrgListPar();
    }//GEN-LAST:event_btn_AdMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        int i = jTable1.getSelectedRow();
        for (Etudiant etudiant : ListFilleul) {
            if ((jTable1.getValueAt(i, 0)).toString().equalsIgnoreCase(etudiant.getMatETD())) {
                matfil1.setText(etudiant.getMatETD());
                filfil1.setText(etudiant.getFilETD());
                nivfil1.setText(String.valueOf(etudiant.getNivETD()));
                nomfil1.setText(etudiant.getNomETD());
                Surnamefil1.setText(etudiant.getPrenomETD());

                BufferedImage bi = null;
                try {
                    bi = ImageIO.read(etudiant.getFimage());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                ImageIcon icon = new ImageIcon(bi.getScaledInstance(Lbl_FilleulPic.getWidth(),
                        Lbl_FilleulPic.getHeight(), Image.SCALE_FAST));
                Lbl_FilleulPic.setIcon(icon);
                atribSpons.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void lbl_seachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_seachKeyReleased
        // TODO add your handling code here:
        TrirTab();
    }//GEN-LAST:event_lbl_seachKeyReleased

    private void Filtr_filActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Filtr_filActionPerformed
        // TODO add your handling code here: 
            ChargeTab();
    }//GEN-LAST:event_Filtr_filActionPerformed

    private void atribSponsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atribSponsActionPerformed
        // TODO add your handling code here:
        if (ListParrain.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Mentor in this list\n\n Add a new mentor");
        } else {
            JToggleButton jtb = (JToggleButton) evt.getSource();

            if (jtb.isSelected() == true) {
                tm.start();
            }
            if (jtb.isSelected() == false) {
                tm.stop();
                atribSpons.setEnabled(false);
                for (Parrain p : ListParrain){
            if( p.getMatETD().equalsIgnoreCase(matpar1.getText())){
                 System.out.println(p.getMatETD() );
            }
        }
        for (Etudiant e: ListFilleul){
            if( e.getMatETD().equalsIgnoreCase(matfil1.getText())){
                 System.out.println(e.getMatETD());
            }
        }
                System.out.println(matfil1.getText());
            }
        }

    }//GEN-LAST:event_atribSponsActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        
        btn_home.setBackground(new Color(0, 41, 153));
        btn_RmvStud.setBackground(new Color(0, 81, 153));
        color_btn = evt.getComponent().getParent().getBackground();
        BListe bl = new BListe();
        
            ComPan.setVisible(false);
            HeadPane.setVisible(false);
            
            bb.setBackground(Color.WHITE);
            bb.add(bl);
            Bg.add(bb, new AbsoluteConstraints(270, 25, 570, 520));
            
            bl.setVisible(true);
            bb.setVisible(true);
            but=3;
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        
        bb.removeAll();
        bb.setVisible(false);

        ComPan.setVisible(true);
        HeadPane.setVisible(true);
        btn_RmvStud.setBackground(new Color(0, 41, 153));
        btn_home.setBackground(new Color(0, 81, 153));
        color_btn = evt.getComponent().getParent().getBackground();
//        printF();
        ChargeTab();
        ChrgListPar();
        but=1;
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        Accueil acc = new Accueil();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                acc.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bg;
    private javax.swing.JPanel ComPan;
    private javax.swing.JLabel EmailPar;
    private javax.swing.JLabel FilFill;
    private javax.swing.JLabel FilPar;
    public javax.swing.JComboBox<String> Filtr_fil;
    private javax.swing.JLabel FrameDrag;
    public javax.swing.JPanel HeadPane;
    private javax.swing.JLabel Lbl_FilleulPic;
    private javax.swing.JLabel Lbl_ParrainPic;
    private javax.swing.JLabel MatFill;
    private javax.swing.JLabel MatPar;
    private javax.swing.JLabel NameFill;
    private javax.swing.JLabel NamePar;
    private javax.swing.JLabel NivFill;
    private javax.swing.JLabel NivPar;
    private javax.swing.JPanel SidePane;
    private javax.swing.JLabel SurnameFill;
    private javax.swing.JLabel SurnamePar;
    private javax.swing.JLabel Surnamefil1;
    private javax.swing.JLabel Surnamepar1;
    private javax.swing.JToggleButton atribSpons;
    private javax.swing.JPanel btn_AdStud;
    private javax.swing.JPanel btn_LstStud;
    private javax.swing.JPanel btn_RmvStud;
    private javax.swing.JLabel btn_close;
    private javax.swing.JPanel btn_home;
    private javax.swing.JLabel emailpar1;
    private javax.swing.JLabel filfil1;
    private javax.swing.JLabel filpar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField lbl_seach;
    private javax.swing.JLabel matfil1;
    public javax.swing.JLabel matpar1;
    private javax.swing.JLabel nivfil1;
    private javax.swing.JLabel nivpar1;
    private javax.swing.JLabel nomfil1;
    private javax.swing.JLabel nompar1;
    // End of variables declaration//GEN-END:variables

}
