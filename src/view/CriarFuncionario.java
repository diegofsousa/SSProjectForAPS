/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Funcionario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import model.DAO;
import model.teclasPermitidas;
import model.teclasPermitidasLetras;

/**
 *
 * @author diego
 */
public class CriarFuncionario extends JFrame implements ActionListener{
    JLabel tNome, tUsername, tSenha;
    JTextField cNome, cUsername;
    JPasswordField cSenha;
    ButtonGroup cSexo;
    JRadioButton mas, fem;
    JButton cadastrar;
    
    public CriarFuncionario(){
        
        setLayout(null);
        
        tNome = new JLabel("Nome:");
        tNome.setSize(100, 25);
        tNome.setLocation(50, 30);
        add(tNome);
        
        cNome = new JTextField();
        cNome.setSize(200, 25);
        cNome.setLocation(130, 30);
        cNome.setDocument(new teclasPermitidasLetras());
        add(cNome);
        
        
        tUsername = new JLabel("Username:");
        tUsername.setSize(100, 25);
        tUsername.setLocation(50, 75);
        add(tUsername);
        
        cUsername = new JTextField();
        cUsername.setSize(200, 25);
        cUsername.setLocation(130, 75);
        add(cUsername);
        //cUsername.setDocument(new teclasPermitidas(11));
        
        tSenha = new JLabel("Senha:");
        tSenha.setSize(100, 25);
        tSenha.setLocation(50, 125);
        add(tSenha);
       
        
        cSenha = new JPasswordField();
        cSenha.setSize(200, 25);
        cSenha.setLocation(130, 125);
        //cSenha.setDocument(new teclasPermitidas(11));
        add(cSenha);
        
        cadastrar = new JButton("Cadastrar");
        cadastrar.setSize(130,25);
        cadastrar.setLocation(130, 225);
        add(cadastrar);
        
        cadastrar.addActionListener(this);
        
        setSize(400, 350);
        setLocation(450,90);
        setTitle("Cadastro de funcionário - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        new CriarFuncionario();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       DAO dao =  new DAO();
       if(cNome.getText().equals("") || cSenha.getText().equals("") || cUsername.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Insira todos os campos");
       }
       else{
          if(dao.cadastrarFuncionario(new Funcionario(null, cNome.getText(), cUsername.getText(), cSenha.getText())) != false){
               JOptionPane.showMessageDialog(null, "Novo funcinário cadastrado!\n\n"
                       +"Nome: "+ cNome.getText()+"\n"
                       + "Username: "+ cUsername.getText());
               dispose();
           }
          else{
              JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
          }
       }
    }
}
