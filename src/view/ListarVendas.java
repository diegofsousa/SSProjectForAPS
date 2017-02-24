/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Produto;
import controller.atributosMenuPrincipal;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.DAO;

/**
 *
 * @author diego
 */
public class ListarVendas extends JFrame implements ActionListener{
    JList lista;
    DefaultListModel modelo;
    JPanel plista;
    JLabel informe;
    JButton detalhar;
    DAO dao = new DAO();
    ArrayList<atributosMenuPrincipal> c;
    
    
    
    public ListarVendas(String algo) {
        setLayout(null);
        
        modelo = new DefaultListModel();
        lista = new JList(modelo);
        lista.setSize(700, 200);
        lista.setLocation(50, 65);
        
        informe = new JLabel("Selecione um item e aperte 'Detalhar' para mais informações");
        informe.setSize(700, 25);
        informe.setLocation(170, 20);
        
        if(algo == null){
            c = dao.retornaTodosAsVendas();
        }
        else{
            
            c = dao.retornaVendasDeUmCliente(algo);
        }
        
        add(informe);
            modelo.clear();
            for(int i=0; i<c.size();i++){                
                modelo.add(i, "ID = "+c.get(i).getId_venda()+" | Produto: "+c.get(i).getProduto()+" | Valor da venda: R$: "+c.get(i).getValor_venda()+"| Qtd: "+c.get(i).getQtd()+"");
            }
        
        JScrollPane scrollPane = new JScrollPane();
        //scrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 
        //scrollPane.setSize(500, 300);
        //scrollPane.setLocation(80, 108);
        //scrollPane.add(lista);
        scrollPane.getViewport().add(lista);
        //add(lista);
        
        plista = new JPanel();
        plista.setLayout(new GridLayout(0, 2));
        plista.setSize(1500,200);
        plista.setLocation(30, 65);
        plista.add(scrollPane);
        add(plista);
        
        detalhar = new JButton("Detalhar");
        detalhar.setSize(100, 25);
        detalhar.setLocation(340, 285);
        add(detalhar);
        
        detalhar.addActionListener(this);
        
        setSize(805,340);
        setLocation(300,200);
        setTitle("Lista de vendas - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new ListarVendas(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Detalhar")){
            System.out.println("Detalhando venda");
            if(lista.getSelectedValue() == null){
                JOptionPane.showMessageDialog(null, "Nenhum item selecionado");
            }
            else{
                ;
                int i = lista.getSelectedIndex();
                //System.out.println("indice: "+lista.getSelectedValue());
                JOptionPane.showMessageDialog(null, "ID = "+c.get(i).getId_venda()+"\nProduto: "+c.get(i).getProduto()+"\nValor da venda: R$: "+c.get(i).getValor_venda()+"\nQuantidade: "+c.get(i).getQtd()+"\nCliente: "+c.get(i).getNome_cliente()+"\nData: "+c.get(i).getData()+"\nOperador: "+c.get(i).getNome_usuario()+"");
            }
            
        }
    }
    
}
