/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Cliente;
import controller.Produto;
import controller.atributosMenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.DAO;
import model.teclasPermitidasFloat;
import model.teclasPermitidasLetras;

/**
 *
 * @author diego
 */
public class AlterarProduto extends JFrame implements ActionListener{
    JLabel tNome, tValor, id;
    JTextField cNome, cValor, cid;
    JButton procurar, alterar;
    TelaInicial fp;
    
    public AlterarProduto(TelaInicial fPrincipal){
        fp = fPrincipal;
        
       
        
        setLayout(null);
        id = new JLabel("ID:");
        id.setSize(100, 25);
        id.setLocation(50, 30);
        add(id);
        
        cid = new JTextField();
        cid.setSize(200, 25);
        cid.setLocation(120, 30);
        add(cid);
        
        procurar = new JButton("Procurar");
        procurar.setSize(130,25);
        procurar.setLocation(130, 75);
        add(procurar);
        
        tNome = new JLabel("Nome:");
        tNome.setSize(100, 25);
        tNome.setLocation(50, 130);
        add(tNome);
        
        cNome = new JTextField();
        cNome.setSize(200, 25);
        cNome.setLocation(120, 130);
        cNome.setDocument(new teclasPermitidasLetras());
        add(cNome);
        
        tValor = new JLabel("Valor(R$):");
        tValor.setSize(100, 25);
        tValor.setLocation(50, 185);
        add(tValor);
        
        cValor = new JTextField();
        cValor.setSize(200, 25);
        cValor.setLocation(120, 185);
        cValor.setDocument(new teclasPermitidasFloat(8));
        add(cValor);
        
        alterar = new JButton("Alterar");
        alterar.setSize(100, 25);
        alterar.setLocation(150, 240);
        add(alterar);
        
        procurar.addActionListener(this);
        alterar.addActionListener(this);
        
        setSize(400,130);
        setLocation(450,90);
        setTitle("Alterar cadastro de produto - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new AlterarProduto(null);
    }
    
    void deixaInvisivel(){
        tNome.setVisible(false);
        cNome.setVisible(false);
        tValor.setVisible(false);
        cValor.setVisible(false);
        alterar.setVisible(false);
    }
    
    public void deixaVisivel(){
        tNome.setVisible(true);
        cNome.setVisible(true);
        tValor.setVisible(true);
        cValor.setVisible(true);
        alterar.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        if(e.getActionCommand().equals("Procurar")){
            System.out.println("Procurando produto...");
            
            
            boolean tem = dao.verificaProdutoPorIndice(cid.getText());
            
            if(tem == false){
                JOptionPane.showMessageDialog(null, "Não há cliente com este ID");
            }
            else{
                Produto a = dao.retornaProdutoporIndice(cid.getText());
                setSize(400, 310);
                deixaVisivel();
                cNome.setText(a.getNome());
                cValor.setText(a.getValor());                               
            }
            
            
        }
        
        if(e.getActionCommand().equals("Alterar")){
            
            if(cNome.getText().equals("") || cValor.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Um ou mais campos estão nulos");
            }
            
            else if(dao.verificaProdutoPorIndice(cid.getText())){
                String nome = cNome.getText();
                nome = nome.toUpperCase();
                System.out.println(nome);
                String valor = cValor.getText();
                System.out.println(valor);
                
                
                if(dao.atualizaProduto(cid.getText(), nome, valor)){
                    JOptionPane.showMessageDialog(null, "Cadastro alterado:\n\nID: "+cid.getText()+"\nNome: "+nome+"\nValor: "+valor+"");
                    
                    atributosMenuPrincipal a  = dao.atualiza(null);
                    
                    fp.id.setText("ID Venda: "+a.getId_venda());
                    fp.produto.setText("Produto: "+a.getProduto());
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Falha ao alterar");
                }
            }
        }
        
    }
}
