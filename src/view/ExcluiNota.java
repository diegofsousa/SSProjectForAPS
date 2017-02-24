/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Nota;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.*;
import model.DAO;

/**
 *
 * @author diego
 */
public class ExcluiNota extends JFrame implements ActionListener{

 JLabel id;
    JTextField cid;
    JButton procurar;
    DefaultListModel alter;
    
    public ExcluiNota(DefaultListModel modelo){
        setLayout(null);
        
        alter = modelo;
        
        id = new JLabel("ID:");
        id.setSize(100, 25);
        id.setLocation(50, 30);
        add(id);
        
        cid = new JTextField();
        cid.setSize(200, 25);
        cid.setLocation(120, 30);
        add(cid);
        
        procurar = new JButton("Excluir");
        procurar.setSize(130,25);
        procurar.setLocation(130, 75);
        add(procurar);        
        
        procurar.addActionListener(this);
        
        setSize(400,140);
        setLocation(450,90);
        setTitle("Exclusão de nota - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        if(e.getActionCommand().equals("Excluir")){
            if(cid.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Não há nada escrito");
            }
            else{
                
                if(dao.verificaNota(cid.getText())){
                    
                    dao.deletaNota(cid.getText());                
                    ArrayList<Nota> outra = dao.retornaTodasNotas();

                    alter.clear();
                    for(int i=0; i<outra.size();i++){
                        //System.out.println("indice: "+r.get(i).getId()+" - nome: "+r.get(i).getNome()+"");
                        alter.add(i, "ID = ["+outra.get(i).getIdNota()+"]  - "+outra.get(i).getTitulo()+" -  "+outra.get(i).getData()+"");
                    }

                    JOptionPane.showMessageDialog(null, "Nota excluída");
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Este índice não existe no banco de dados!");
                }
                
                
            }
        }
        
    }
    
}
