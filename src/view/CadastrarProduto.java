/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;
import controller.Produto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;
import model.DAO;
import model.teclasPermitidas;
import model.teclasPermitidasFloat;
import model.teclasPermitidasLetras;

/**
 *
 * @author diego
 */
public class CadastrarProduto extends JFrame implements ActionListener{
    JLabel tNome, tValor, tEstoque;
    JTextField cNome, cValor, cEstoque;
    JButton cadastrar;
    
    public CadastrarProduto(){
        setLayout(null);
        
        tNome = new JLabel("Nome:");
        tNome.setSize(100, 25);
        tNome.setLocation(50, 30);
        add(tNome);
        
        cNome = new JTextField();
        cNome.setSize(200, 25);
        cNome.setLocation(120, 30);
        cNome.setDocument(new teclasPermitidasLetras());
        add(cNome);
        
        
        tValor = new JLabel("Valor(R$):");
        tValor.setSize(100, 25);
        tValor.setLocation(50, 75);
        add(tValor);
        
        cValor = new JTextField();
        cValor.setSize(200, 25);
        cValor.setLocation(120, 75);
        cValor.setDocument(new teclasPermitidasFloat(8));
        add(cValor);
        
        tEstoque = new JLabel("Estoque:");
        tEstoque.setSize(100, 25);
        tEstoque.setLocation(50, 130);
        add(tEstoque);
        
        cEstoque = new JTextField();
        cEstoque.setSize(200, 25);
        cEstoque.setLocation(120, 130);
        cEstoque.setDocument(new teclasPermitidas(2));
        add(cEstoque);
        
        cadastrar = new JButton("Cadastrar");
        cadastrar.setSize(130,25);
        cadastrar.setLocation(130, 185);
        add(cadastrar);
        
        cadastrar.addActionListener(this);
        
        setSize(400,350);
        setLocation(450,90);
        setTitle("Cadastro de produto - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        new CadastrarProduto();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        if(e.getActionCommand().equals("Cadastrar")){
            System.out.println("Cadastrando produto");
            if(cNome.getText().equals("") || cValor.getText().equals("") || cEstoque.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Um ou mais campos estão nulos");
            }
            else{
                Produto p = new Produto(null, cNome.getText(), cValor.getText(), cEstoque.getText());
                if(dao.cadastrarProduto(p)){
                    String indice  = dao.idUltimoPCadastrado();
                    JOptionPane.showMessageDialog(null, "Produto cadastrado\n\nID: "+indice+"\nNome: "+cNome.getText()+"\nValor: R$"+cValor.getText()+"\nEstoque: "+cEstoque.getText()+"");
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
                }
            }
        }
    }
}
