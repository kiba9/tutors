/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutors;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Cena
 */
public class DragListener implements DropTargetListener{
    
    JLabel imageLabel = new JLabel();
    public File  f = null;

    public DragListener(JLabel image) {
        imageLabel = image;
    }
    
    
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    //get drop img and add label
    @Override
    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable t=dtde.getTransferable();
        DataFlavor[] df=t.getTransferDataFlavors();
        for (DataFlavor dataFlavor : df) {
            try{
                if(dataFlavor.isFlavorJavaFileListType()){
                    List<File> files=(List<File>) t.getTransferData(dataFlavor);
                    for(File file:files){
                        displayImage(file.getPath());
                    }
                }
            }catch(Exception e){
                
            }
        }
    }

    private void displayImage(String path) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File(path));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        ImageIcon icon = new ImageIcon(bi.getScaledInstance(imageLabel.getWidth(),
                imageLabel.getHeight(), Image.SCALE_DEFAULT));
        imageLabel.setIcon(icon);
        f = new File(path);

    }

}
