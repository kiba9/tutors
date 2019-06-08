 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tutors;

import java.io.File;

/**
 *
 * @author cena M
 */
public class Parrain extends Etudiant{
    
    private String emailPAR;
    private int statutPAR = 0;
    private int nbFilPAR = 0;
    String Mat_fil = null;
    

    public Parrain(String nomETD, String prenomETD, String matETD, 
            String filETD, int nivETD, File fimage, String emailPAR) {
        
        super(nomETD, prenomETD, matETD, filETD, nivETD, fimage);
        this.emailPAR = emailPAR;
    }

    public String getEmailPAR() {
        return emailPAR;
    }

    public void setEmailPAR(String emailPAR) {
        this.emailPAR = emailPAR;
    }

    public int getStatutPAR() {
        return statutPAR;
    }

    public void setStatutPAR(int statutPAR) {
        this.statutPAR = statutPAR;
    }

    public int getNbFilPAR() {
        return nbFilPAR;
    }

    public void setNbFilPAR(int nbFilPAR) {
        this.nbFilPAR = nbFilPAR;
    }

    public String getMat_fil() {
        return Mat_fil;
    }

    public void setMat_fil(String Mat_fil) {
        this.Mat_fil = Mat_fil;
    }
    

 @Override
    public String toString() {
        return this.nomETD+"\n"+
                this.getPrenomETD()+"\n"+
                this.getMatETD()+"\n"+
                this.getFilETD()+" "+this.getNivETD();
    }    
  
}
