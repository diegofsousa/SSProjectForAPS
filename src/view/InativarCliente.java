/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;
import model.DAO;


/**
 *
 * @author diego
 */
public class InativarCliente extends JFrame implements ActionListener{
    JLabel id;
    JTextField cid;
    JButton procurar;
 
    public InativarCliente(){
        setLayout(null);
        
        id = new JLabel("Informe o ID:");
        id.setSize(100, 25);
        id.setLocation(50, 30);
        add(id);
        
        cid = new JTextField();
        cid.setSize(100, 25);
        cid.setLocation(150, 30);
        add(cid);
        
        procurar = new JButton("Procurar");
        procurar.setSize(100, 25);
        procurar.setLocation(150, 100);
        add(procurar);
        
        procurar.addActionListener(this);
        
        setSize(400, 180);
        setLocation(450,90);
        setTitle("Inativar cadastro de cliente - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new InativarCliente();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        if(e.getActionCommand().equals("Procurar")){            
            if(!dao.verificaClientePorIndice(cid.getText())){
                JOptionPane.showMessageDialog(null, "Não há cliente com este ID");
            }
            else{
                Cliente a = dao.retornaClienteporIndice(cid.getText());
                Object[] options = { "Confirmar", "Cancelar" };
                int res = JOptionPane.showOptionDialog(null, "Deseja inativar o cadastro deste cliente?\n\nID: "+a.getId()+"\nNome: "+a.getNome()+"", "Confirmação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if(res == 0){
                    System.out.println("Inativando");
                    Cliente teste = dao.retornaClienteporIndice(cid.getText());
                    if(teste.getAtividade().equals("0")){
                        JOptionPane.showMessageDialog(null, "Cliente já é inativado");
                    }
                    
                    else if(dao.inativaCliente(cid.getText())){
                        
                        JOptionPane.showMessageDialog(null, "Cliente inativado");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Erro ao inativar");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Operação cancelada");
                }
            }
            
            
        }
    }
}
