/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Cliente;
import controller.Funcionario;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.DAO;

/**
 *
 * @author diego
 */
public class ListarFuncionarios extends JFrame{
    JList lista;
    DefaultListModel modelo;
    JPanel plista;
    JButton detalhar;
    DAO dao = new DAO();
    JLabel informe;
    public ListarFuncionarios() {
        setLayout(null);
        
        modelo = new DefaultListModel();
        lista = new JList(modelo);
        lista.setSize(700, 200);
        lista.setLocation(50, 65);
        
        informe = new JLabel("Selecione um item e aperte 'Detalhar' para ver suas compras");
        informe.setSize(700, 25);
        informe.setLocation(170, 20);
        add(informe);
        
        ArrayList<Funcionario> c= dao.retornaTodosOsFuncionarios();
            modelo.clear();
            for(int i=0; i<c.size();i++){

                    modelo.add(i, "ID = "+c.get(i).getId()+" | Nome: "+c.get(i).getNome()+" | Username: "+c.get(i).getLogin());
            }
        
        JScrollPane scrollPane = new JScrollPane();
        //scrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 
        //scrollPane.setSize(500, 300);
        scrollPane.setLocation(60, 108);
        scrollPane.add(lista);
        scrollPane.getViewport().add(lista);
        //add(lista);
        
        plista = new JPanel();
        plista.setLayout(new GridLayout(0, 2));
        plista.setSize(1500,200);
        plista.setLocation(30, 65);
        plista.add(scrollPane);
        add(plista);
        
        setSize(805,340);
        setLocation(300,200);
        setTitle("Lista de Funcionários - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
        new ListarFuncionarios();
    }
}
