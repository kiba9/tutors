/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutors;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.dnd.DropTarget;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Cena
 */
public class Modif extends javax.swing.JDialog {

    
    String nom="", prenom="", matricule="", filiere="", email="";
    int Xmou, Ymou, pos, niveau=0;
    public File img = new File("default_img.jpg"), f = null;
    public int vrf = 0;

    Accueil Acc = new Accueil();
    Etudiant Etd;
    Parrain Par;
    Color color_btn;
    
    public int getVrf() {
        return vrf;
    }

    public void setVrf(int vrf) {
        this.vrf = vrf;
    }
    
    /**
     * Creates new form Modif
     */
    
    public Modif(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }
    
    public Modif(java.awt.Frame parent, boolean modal, 
         String nom, String prenom, String matr, String Fil, String mail, int niv, File fimg, int pos) {
        
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        setLocationRelativeTo(Acc);
        
        BufferedImage bi = null;
        try{
            bi = ImageIO.read(fimg);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ImageIcon ico = new ImageIcon(bi.getScaledInstance(lbl_ico.getWidth(), lbl_ico.getHeight(), Image.SCALE_SMOOTH));
        
        lbl_Matricule.setForeground(Color.darkGray);
        lbl_Email.setForeground(Color.darkGray);
        lbl_prenom.setForeground(Color.darkGray);
        lbl_Nom.setForeground(Color.darkGray);
        
        this.lbl_Matricule.setText(matr); 
        this.lbl_Nom.setText(nom);
        this.lbl_prenom.setText(prenom);
        this.cbo_Filiere.setSelectedItem((String)Fil.toUpperCase());
        this.cbo_Niv.setSelectedIndex(niv-1);
        this.lbl_Email.setText(mail);
        this.img = fimg;
        this.lbl_ico.setIcon(ico);
        this.pos = pos;

        niveau = niv;
        if(niv==1) opt_btn.setVisible(false);
      connectToDnD();
    }
    
    private void connectToDnD(){
         DragListener dl = new DragListener(lbl_ico);
        new DropTarget(this,dl);
//        System.out.println(img.getPath());
    }
    
    private static String WriteString(String OldString){
        
         String  NewString="";
         String TWordOfStr[] = OldString.split(" ");
         int p=0, pos = 0;
        while( p < TWordOfStr.length){
               if(!TWordOfStr[p].equalsIgnoreCase("")) {
                   if ( pos == 0){
                   NewString +=TWordOfStr[p];
                   pos = 1;
                   }else{NewString +=" "+TWordOfStr[p];}
               }
            p++;
        }
        return NewString; 
     }
    
    private static boolean verifInfo(String nom, String prenom, String matricule, String email) {
        
        boolean t = true;
        if( WriteString(nom).equals("Entrer le nom") || WriteString(prenom).equals("Entrer le prenom") || WriteString(matricule).equals("Ex: 1718MY7O")
                || WriteString(email).equals("Ex: monemail@isj.com") ){
            
            t = false;
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Les Champs Suivant Matricule , Nom et Prenom\n ne doivent pas etre vide.", 
                   "Erreur", JOptionPane.ERROR_MESSAGE);
            
            } else if((nom.length() < 2 ) || (prenom.length() < 3) ||  (matricule.length() < 7) || (!email.contains("@"))
               || (!email.contains(".") )){ 
                
                t = false;
                 java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Entrer des données valide\n Verifier Nom(2 charactères minimun),  "
                        + "le prenom(3 charactères minimun), \n matricule(7 charactères minimun) ou l'Email", 
                "Erreur", JOptionPane.ERROR_MESSAGE);       
                
            }
        return t;
    }
   
    
    public void SaveStud(){

        nom=lbl_Nom.getText();  prenom=lbl_prenom.getText();  matricule=lbl_Matricule.getText();
        filiere = (String)cbo_Filiere.getSelectedItem(); niveau = Integer.parseInt((String)cbo_Niv.getSelectedItem());
        
            if(cbo_Niv.getSelectedIndex()== 1){

            email= WriteString(lbl_Email.getText()); 
            //System.out.println(verifInfo(nom, prenom, matricule, email));
            if(verifInfo(nom, prenom, matricule, email)  == true){
            Par = new Parrain(WriteString(nom), WritePrenom(WriteString(prenom)), WriteString(matricule), filiere, niveau, img, email);
            vrf = 1;
            f = new File("parrain.ths");
            Acc.getListParrain().add(Par);
            
            try {
                        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));                
                        oos.writeObject(Acc.getListParrain());
                        oos.flush();
                        oos.close();
                        vrf = 2;
                    } catch (IOException e) { 
                         java.awt.Toolkit.getDefaultToolkit().notify();
                    JOptionPane.showMessageDialog(null, e.getMessage(), 
                "Enregistrement", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }else{
                 
            if(verifInfo(nom, prenom, matricule,"no@isj.cm")  == true){
            Etd = new Etudiant(WriteString(nom), WritePrenom(WriteString(prenom)), WriteString(matricule), filiere, niveau, img);
            vrf = 1;
            f = new File("Filleul.ths");
            Acc.getListFilleul().add(Etd);
            
            try {
                        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));                
                        oos.writeObject(Acc.getListFilleul());
                        oos.flush();
                        oos.close();
                        vrf = 2;
                    } catch (IOException e) { 
                         java.awt.Toolkit.getDefaultToolkit().notify();
                    JOptionPane.showMessageDialog(null, e.getMessage(), 
                "Enregistrement", JOptionPane.INFORMATION_MESSAGE);
                }
            
            }
        }
                
            if(vrf == 2){
                dispose();
        }  
    }
    
    
    private static String WritePrenom(String prenom){
       String  Np="";
       String[] tprenom = prenom.split(" ");
        int p=0;
        
        while( p < tprenom.length){
            if(1 != (tprenom[p].length())){
            tprenom[p] = tprenom[p].toUpperCase().charAt(0)+ tprenom[p].toLowerCase().substring(1, tprenom[p].length());
                if(p == 0) {Np = Np+tprenom[p];}else{Np = Np+" "+tprenom[p];}
            
            }else{
            tprenom[p] = String.valueOf(tprenom[p].toUpperCase());    
            if(p == 0) {Np = Np+tprenom[p];}else{Np = Np+" "+tprenom[p];}
            }
            p++;  
        }
        return Np;
    }
    
    private void imporImg(){
        try{
            
            JFileChooser ChoixImg = new JFileChooser();
            FileNameExtensionFilter txt = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
            ChoixImg.setFileFilter(txt);
            int r = ChoixImg.showOpenDialog(null);
            if(r == JFileChooser.APPROVE_OPTION){
                
           img = ChoixImg.getSelectedFile();
           }
            //repaint();
            BufferedImage bi = null;
            try{
            bi = ImageIO.read(img);
            } catch (IOException e){
            JOptionPane.showMessageDialog(null, e);
            }
            ImageIcon icon = new ImageIcon(bi.getScaledInstance(lbl_ico.getWidth(), 
                    lbl_ico.getHeight(), Image.SCALE_DEFAULT));
            lbl_ico.setIcon(icon);
            
        }catch(HeadlessException e){
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void setColor(Component label, Component panel, Color Fgc, Color Bgc){ 
       label.setForeground(Fgc);
       panel.setBackground(Bgc);
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
        lbl_Nom = new javax.swing.JTextField();
        lbl_prenom = new javax.swing.JTextField();
        lbl_Matricule = new javax.swing.JTextField();
        cbo_Filiere = new javax.swing.JComboBox();
        cbo_Niv = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_ico = new javax.swing.JLabel();
        btn_Parcourir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lbl_Email = new javax.swing.JTextField();
        btn_save = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        opt_btn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.lightGray, new java.awt.Color(51, 51, 51), new java.awt.Color(153, 153, 153)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        lbl_Nom.setBackground(new java.awt.Color(243, 243, 243));
        lbl_Nom.setFont(new java.awt.Font("Agency FB", 0, 16)); // NOI18N
        lbl_Nom.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Nom.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbl_Nom.setAutoscrolls(false);
        lbl_Nom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        lbl_Nom.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lbl_Nom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lbl_Nomlbl_MatriculeFocusGained(evt);
            }
        });
        lbl_Nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_NomActionPerformed(evt);
            }
        });

        lbl_prenom.setBackground(new java.awt.Color(243, 243, 243));
        lbl_prenom.setFont(new java.awt.Font("Agency FB", 0, 16)); // NOI18N
        lbl_prenom.setForeground(new java.awt.Color(255, 255, 255));
        lbl_prenom.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbl_prenom.setAutoscrolls(false);
        lbl_prenom.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        lbl_prenom.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lbl_prenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_prenomActionPerformed(evt);
            }
        });

        lbl_Matricule.setBackground(new java.awt.Color(243, 243, 243));
        lbl_Matricule.setFont(new java.awt.Font("Agency FB", 0, 16)); // NOI18N
        lbl_Matricule.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Matricule.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbl_Matricule.setAutoscrolls(false);
        lbl_Matricule.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        lbl_Matricule.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        cbo_Filiere.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N
        cbo_Filiere.setForeground(new java.awt.Color(255, 255, 255));
        cbo_Filiere.setMaximumRowCount(2);
        cbo_Filiere.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INGENIEUR", "LICENCE" }));
        cbo_Filiere.setSelectedIndex(1);
        cbo_Filiere.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cbo_Niv.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N
        cbo_Niv.setForeground(new java.awt.Color(255, 255, 255));
        cbo_Niv.setMaximumRowCount(4);
        cbo_Niv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));
        cbo_Niv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbo_Niv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_NivActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Filiere et Niveau");

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Matricule");

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Prenom");

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Nom");

        lbl_ico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 3));

        btn_Parcourir.setBackground(new java.awt.Color(0, 51, 102));
        btn_Parcourir.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N
        btn_Parcourir.setForeground(new java.awt.Color(255, 255, 255));
        btn_Parcourir.setText("Importer une Photo");
        btn_Parcourir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Parcourir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ParcourirActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Email");

        lbl_Email.setBackground(new java.awt.Color(243, 243, 243));
        lbl_Email.setFont(new java.awt.Font("Agency FB", 0, 16)); // NOI18N
        lbl_Email.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Email.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbl_Email.setAutoscrolls(false);
        lbl_Email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));
        lbl_Email.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lbl_Email.setEnabled(false);

        btn_save.setBackground(new java.awt.Color(0, 51, 102));
        btn_save.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N
        btn_save.setForeground(new java.awt.Color(255, 255, 255));
        btn_save.setText("Enregistrer");
        btn_save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_reset.setBackground(new java.awt.Color(0, 51, 102));
        btn_reset.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N
        btn_reset.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset.setText("Annuler");
        btn_reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));

        jLabel7.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Modification");
        jLabel7.setPreferredSize(new java.awt.Dimension(90, 22));
        jLabel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel7MouseDragged(evt);
            }
        });
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(204, 204, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tutorsv0/img/Trash_Can-512.png"))); // NOI18N
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 30));

        opt_btn.setFont(new java.awt.Font("Agency FB", 0, 12)); // NOI18N
        opt_btn.setForeground(new java.awt.Color(153, 153, 153));
        opt_btn.setText(">> Plus");
        opt_btn.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        opt_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opt_btnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_Matricule, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(lbl_Nom)
                            .addComponent(lbl_prenom)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(lbl_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbo_Filiere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbo_Niv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(opt_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_reset))
                    .addComponent(lbl_ico, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Parcourir, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lbl_Nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lbl_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Matricule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbo_Filiere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_Niv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbl_ico, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btn_Parcourir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_reset)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_save)
                        .addComponent(opt_btn)))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_Nomlbl_MatriculeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lbl_Nomlbl_MatriculeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_Nomlbl_MatriculeFocusGained

    private void lbl_NomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_NomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_NomActionPerformed

    private void cbo_NivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_NivActionPerformed
        // TODO add your handling code here:
        if(cbo_Niv.getSelectedIndex() == 1){
            lbl_Email.setEnabled(true);
        }else{
            lbl_Email.setEnabled(false);
        }
    }//GEN-LAST:event_cbo_NivActionPerformed

    private void btn_ParcourirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ParcourirActionPerformed
        try {
            // TODO add your handling code here:
            javax.swing.UIManager.setLookAndFeel(new WindowsLookAndFeel());
            imporImg();
            javax.swing.UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Ajouter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_ParcourirActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
//        SaveStud();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void jLabel7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - Xmou - 5, y - Ymou - 5);
    }//GEN-LAST:event_jLabel7MouseDragged

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        // TODO add your handling code here:
        Xmou = evt.getX();
        Ymou = evt.getY();
    }//GEN-LAST:event_jLabel7MousePressed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
        JOptionPane jop = new JOptionPane();
        int option = jop.showConfirmDialog(null, "\tVoulez-vous vraiment Supprimer cet Etudiant ?"+pos, "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
           
        }
        this.dispose();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        // TODO add your handling code here:
        color_btn = evt.getComponent().getBackground();
        setColor(jLabel8, jPanel3, Color.white, new Color(220,0,0));
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        // TODO add your handling code here:
        setColor(jLabel8, jPanel3, Color.black, color_btn);
    }//GEN-LAST:event_jPanel3MouseExited

    private void opt_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opt_btnMouseClicked
        // TODO add your handling code here:
        Modif_Ad MA;
        if(cbo_Niv.getSelectedIndex() == 0){
            MA = new Modif_Ad(Acc, true);
        }else{
            MA = new Modif_Ad(Acc, true, Acc.ListParrain.get(pos).getStatutPAR(), Acc.ListParrain.get(pos).getNbFilPAR());
        }
        MA.setVisible(true);
    }//GEN-LAST:event_opt_btnMouseClicked

    private void lbl_prenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_prenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_prenomActionPerformed

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
            java.util.logging.Logger.getLogger(Modif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modif.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Modif dialog = new Modif(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Parcourir;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox cbo_Filiere;
    private javax.swing.JComboBox cbo_Niv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lbl_Email;
    private javax.swing.JTextField lbl_Matricule;
    private javax.swing.JTextField lbl_Nom;
    private javax.swing.JLabel lbl_ico;
    private javax.swing.JTextField lbl_prenom;
    public javax.swing.JLabel opt_btn;
    // End of variables declaration//GEN-END:variables
}
