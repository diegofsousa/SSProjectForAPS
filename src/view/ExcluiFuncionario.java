/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Funcionario;
import controller.Produto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.DAO;

/**
 *
 * @author diego
 */
public class ExcluiFuncionario extends JFrame implements ActionListener{
    JLabel id;
    JTextField cid;
    JButton procurar;
    
    public ExcluiFuncionario() {
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
        setTitle("Exclusão cadastro de funcionários - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new ExcluiFuncionario();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        if(e.getActionCommand().equals("Procurar")){
            System.out.println("Procurando produto..");
            if(!dao.verificaFuncionarioPorIndice(cid.getText())){
                JOptionPane.showMessageDialog(null, "Não há funcionário com este ID");
            }
            else{
                Funcionario a = dao.retornaFuncionarioporIndice(cid.getText());
                Object[] options = { "Confirmar", "Cancelar" };
                int res = JOptionPane.showOptionDialog(null, "Deseja excluir o cadastro deste funcionario?\n\nNome: "+a.getNome()+"", "Confirmação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if(res == 0){
                    System.out.println("Excluindo...");
                    if(dao.excluirFuncionario(cid.getText())){
                        JOptionPane.showMessageDialog(null, "Cadastro excluido!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Erro ao excluir!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operação cancelada");
                }
            }
        }
    }
    
}
