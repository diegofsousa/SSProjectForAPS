/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Cliente;
import controller.atributosMenuPrincipal;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.DAO;

/**
 *
 * @author diego
 */
public class ListarClientes extends JFrame implements ActionListener{
    JList lista;
    DefaultListModel modelo;
    JPanel plista;
    JButton detalhar;
    DAO dao = new DAO();
    JLabel informe;
    
    public ListarClientes() {
        setLayout(null);
        
        modelo = new DefaultListModel();
        lista = new JList(modelo);
        lista.setSize(700, 200);
        lista.setLocation(50, 65);
        
        informe = new JLabel("Selecione um item e aperte 'Detalhar' para ver suas compras");
        informe.setSize(700, 25);
        informe.setLocation(170, 20);
        add(informe);
        
        ArrayList<Cliente> c= dao.retornaTodosOsClientes();
            modelo.clear();
            for(int i=0; i<c.size();i++){
                if(!c.get(i).getId().equals("1")){
                    if(c.get(i).getAtividade().equals("0")){
                        c.get(i).setAtividade("Não-ativo");
                    }
                    else{
                        c.get(i).setAtividade("Ativo");
                    }
                    modelo.add(i, "ID = "+c.get(i).getId()+" | Nome: "+c.get(i).getNome()+" | CPF: R$: "+c.get(i).getCpf()+" | Sexo: "+c.get(i).getSexo()+" | Telefone: "+c.get(i).getTelefone()+" | Atividade: "+c.get(i).getAtividade());
                }
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
        
        detalhar = new JButton("Detalhar");
        detalhar.setSize(100, 25);
        detalhar.setLocation(340, 285);
        add(detalhar);
        
        detalhar.addActionListener(this);
        
        setSize(805,340);
        setLocation(300,200);
        setTitle("Lista de Clientes - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
        new ListarClientes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Detalhar")){
            System.out.println("Detalhando venda");
            if(lista.getSelectedValue() == null){
                JOptionPane.showMessageDialog(null, "Nenhum item selecionado");
            }
            else{
                ArrayList<Cliente> c= dao.retornaTodosOsClientes();
                int i = lista.getSelectedIndex();
                
                String aux = c.get(i).getId();
                if(dao.retornaVendasDeUmCliente(aux) == null){
                    JOptionPane.showMessageDialog(null, "Não há nenhuma compra deste cliente");
                }
                else{
                    ListarVendas nova = new ListarVendas(aux);
                    nova.setLocation(400, 300);
                }
                //System.out.println("indice: "+lista.getSelectedValue());
                //JOptionPane.showMessageDialog(null, "ID = "+c.get(i).getId_venda()+"\nProduto: "+c.get(i).getProduto()+"\nValor da venda: R$: "+c.get(i).getValor_venda()+"\nQuantidade: "+c.get(i).getQtd()+"\nCliente: "+c.get(i).getNome_cliente()+"\nData: "+c.get(i).getData()+"\nOperador: "+c.get(i).getNome_usuario()+"");
            }
        }
    }
    
}
