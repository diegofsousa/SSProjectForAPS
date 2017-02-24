/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Cliente;
import controller.Nota;
import controller.atributosMenuPrincipal;
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
public class CriarNota extends JFrame implements ActionListener{
    
    JLabel id, qt;
    JTextArea cqt;
    JTextField cid;
    JButton definir;
    DefaultListModel alter;
    

    public CriarNota(DefaultListModel modelo) {
        setLayout(null);
        
        alter = modelo;
        
        id = new JLabel("Titulo:");
        id.setSize(100, 25);
        id.setLocation(35, 30);
        add(id);
        
        cid = new JTextField();
        cid.setSize(200, 25);
        cid.setLocation(140, 30);
        add(cid);
        
        qt = new JLabel("Texto:");
        qt.setSize(100, 25);
        qt.setLocation(35, 80);
        add(qt);
        
        
        
        cqt = new JTextArea();
        cqt.setSize(200, 225);
        cqt.setLocation(140, 80);
        cqt.setLineWrap(rootPaneCheckingEnabled);
        //add(cqt);
        
        JScrollPane scrollPane = new JScrollPane();
        //scrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 

        
        scrollPane.setLocation(140, 80);
        scrollPane.setSize(200, 225);
        //scrollPane.add(lista);
        scrollPane.getViewport().add(cqt);
                
        
        
        add(scrollPane);
        
        definir = new JButton("Definir");
        definir.setSize(130,25);
        definir.setLocation(130, 345);
        add(definir);        
        
        definir.addActionListener(this);
        
        setSize(400,440);
        setLocation(450,90);
        setTitle("Criar nota - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
        new CriarNota(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        if(e.getActionCommand().equals("Definir")){
            if(cid.getText().equals("")||cqt.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Não há nada escrito");
            }
            else{
                Locale locale = new Locale("pt","BR");
                GregorianCalendar calendar = new GregorianCalendar(); 
                SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
                String data = formatador.format(calendar.getTime());
                
                Nota nova = new Nota(null, cid.getText(), cqt.getText(), data);
                dao.insereNota(nova);
                
                ArrayList<Nota> outra = dao.retornaTodasNotas();
                
                alter.clear();
                for(int i=0; i<outra.size();i++){
                    //System.out.println("indice: "+r.get(i).getId()+" - nome: "+r.get(i).getNome()+"");
                    alter.add(i, "ID = ["+outra.get(i).getIdNota()+"]  - "+outra.get(i).getTitulo()+" -  "+outra.get(i).getData()+"");
                }
                
                JOptionPane.showMessageDialog(null, "Nota inserida");
                dispose();
            }
        }
        
        
    }
        
        
}
