/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Produto;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import model.DAO;

/**
 *
 * @author diego
 */
public class ListarProdutos extends JFrame{
    JList lista;
    DefaultListModel modelo;
    JPanel plista;
    JLabel informe;
    JButton detalhar;
    DAO dao = new DAO();

    public ListarProdutos() {
        setLayout(null);
        
        modelo = new DefaultListModel();
        lista = new JList(modelo);
        lista.setSize(700, 200);
        lista.setLocation(50, 65);
        
        ArrayList<Produto> c= dao.retornaTodosOsProdutos();
            modelo.clear();
            for(int i=0; i<c.size();i++){
                
                    modelo.add(i, "ID = "+c.get(i).getId()+" | Nome: "+c.get(i).getNome()+" | Valor: R$: "+c.get(i).getValor()+" | Estoque: "+c.get(i).getQtd_estoque()+"");
                
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
        setTitle("Lista de Produtos - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
        new ListarProdutos();
    }
    
    
}
