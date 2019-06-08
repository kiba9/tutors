/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutors;

/**
 *
 * @author Cena
 */
public class ALoad {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AcLoad load = new AcLoad();
        load.setVisible(true);
        
        try{
            for(int i = 0; i <= 100; i++){  
                Thread.sleep(40);
                load.loadBar.setValue(i);
                load.loadNum.setText(i+"%");
                if(i==100){
                    load.setVisible(false);
                    new Accueil().setVisible(true);
                }
            }      
        }catch(InterruptedException e){   
    }
        
    }
    
}
