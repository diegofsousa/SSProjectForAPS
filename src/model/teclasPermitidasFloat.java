/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author diego
 */
public class teclasPermitidasFloat extends PlainDocument{
    private int quantideMax;
    
    public teclasPermitidasFloat(int maxlex) {
        super();
        
        quantideMax = maxlex;
        
    }
    
    
    
    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr)
        throws BadLocationException{
            if(str == null || getLength() == quantideMax){
                return;
            }
            int totalquantia = (getLength()+str.length());
            if(totalquantia<=quantideMax){
                super.insertString(offset, str.replaceAll("[^0-9|^.^]", ""), attr);
                return;
            }
             String nova = str.substring(0,getLength()-quantideMax);
             super.insertString(offset, nova, attr);
    }
    
}

