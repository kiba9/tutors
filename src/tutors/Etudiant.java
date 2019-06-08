/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tutors;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author cena M
 */
public class Etudiant implements Serializable {
    
   String nomETD;
   private String prenomETD;
   private String matETD;
  // private String ageETD;
   private String filETD;
   private int nivETD;
   private File fimage;
   private boolean Possed_par = false;

    public Etudiant(String nomETD, String prenomETD, String matETD, String filETD, int nivETD, File fimage) {
        this.nomETD = nomETD;
        this.prenomETD = prenomETD;
        this.matETD = matETD;
        this.filETD = filETD;
        this.nivETD = nivETD;
        this.fimage = fimage;
    }

   
    public String getNomETD() {
        return nomETD;
    }

    public void setNomETD(String nomETD) {
        this.nomETD = nomETD;
    }

    public String getPrenomETD() {
        return prenomETD;
    }

    public void setPrenomETD(String prenomETD) {
        this.prenomETD = prenomETD;
    }

    public String getMatETD() {
        return matETD;
    }

    public void setMatETD(String matETD) {
        this.matETD = matETD;
    }

    /*    public String getAgeETD() {
    return ageETD;
    }
    
    public void setAgeETD(String ageETD) {
    this.ageETD = ageETD;
    }*/

    public String getFilETD() {
        return filETD;
    }

    public void setFilETD(String filETD) {
        this.filETD = filETD;
    }

    public int getNivETD() {
        return nivETD;
    }

    public void setNivETD(int nivETD) {
        this.nivETD = nivETD;
    }

    public File getFimage() {
        return fimage;
    }

    public void setFimage(File fimage) {
        this.fimage = fimage;
    }

    public boolean isPossed_par() {
        return Possed_par;
    }

    public void setPossed_par(boolean Possed_par) {
        this.Possed_par = Possed_par;
    }

    @Override
    public String toString() {
        return this.nomETD+"\n"+
                this.prenomETD+"\n"+
                this.matETD+"\n"+
                this.filETD+" "+this.nivETD;
    }
    
    

    
}
