/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Cliente;
import controller.Produto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.DAO;

/**
 *
 * @author diego
 */
public class ExcluiProduto extends JFrame implements ActionListener{
    JLabel id;
    JTextField cid;
    JButton procurar;
    
    public ExcluiProduto(){
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
        
        procurar.addActionListener(this);
        
        setSize(400,140);
        setLocation(450,90);
        setTitle("Exclusão cadastro de produto - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new ExcluiProduto();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        if(e.getActionCommand().equals("Procurar")){
            System.out.println("Procurando produto..");
            if(!dao.verificaProdutoPorIndice(cid.getText())){
                JOptionPane.showMessageDialog(null, "Não há produto com este ID");
            }
            else{
                Produto a = dao.retornaProdutoporIndice(cid.getText());
                Object[] options = { "Confirmar", "Cancelar" };
                int res = JOptionPane.showOptionDialog(null, "Deseja excluir o cadastro deste produto?\n\nID: "+a.getId()+"\nNome: "+a.getNome()+"", "Confirmação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if(res == 0){
                    System.out.println("Excluindo...");
                    if(dao.excluirProduto(cid.getText())){
                        JOptionPane.showMessageDialog(null, "Cadastro excluido!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Erro ao excluir!\nVerifique se há vendas relacionadas ao produto.");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operação cancelada");
                }
            }
        }
        
    }
}
