/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Funcionario;
import controller.Produto;
import controller.atributosMenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.DAO;
import model.teclasPermitidasFloat;
import model.teclasPermitidasLetras;

/**
 *
 * @author diego
 */
public class AlterarFuncionario extends JFrame implements ActionListener{
    JLabel id;
    JLabel tNome, tUsername, tSenha;
    JTextField cNome, cUsername, cid;
    JPasswordField cSenha;
    JButton procurar, alterar;

    public AlterarFuncionario() {
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
        
        tUsername = new JLabel("Username:");
        tUsername.setSize(100, 25);
        tUsername.setLocation(50, 185);
        add(tUsername);
        
        cUsername = new JTextField();
        cUsername.setSize(200, 25);
        cUsername.setLocation(120, 185);
        //cUsername.setDocument(new teclasPermitidasFloat(8));
        add(cUsername);
        
        tSenha = new JLabel("Senha:");
        tSenha.setSize(100, 25);
        tSenha.setLocation(50, 240);
        add(tSenha);
        
        cSenha = new JPasswordField();
        cSenha.setSize(200, 25);
        cSenha.setLocation(120, 240);
        //cUsername.setDocument(new teclasPermitidasFloat(8));
        add(cSenha);
        
        alterar = new JButton("Alterar");
        alterar.setSize(100, 25);
        alterar.setLocation(150, 295);
        add(alterar);
        
        procurar.addActionListener(this);
        alterar.addActionListener(this);
        
        setSize(400,130);
        setLocation(450,90);
        setTitle("Alterar cadastro de funcionário - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
        void deixaInvisivel(){
        tNome.setVisible(false);
        cNome.setVisible(false);
        tSenha.setVisible(false);
        cSenha.setVisible(false);
        tUsername.setVisible(false);
        cUsername.setVisible(false);
        alterar.setVisible(false);
    }
    
    public void deixaVisivel(){
        tNome.setVisible(true);
        cNome.setVisible(true);
        tSenha.setVisible(true);
        cSenha.setVisible(true);
        tUsername.setVisible(true);
        cUsername.setVisible(true);
        alterar.setVisible(true);
    }
    
    public static void main(String[] args) {
        new AlterarFuncionario();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        if(e.getActionCommand().equals("Procurar")){
            System.out.println("Procurando funcionário...");
            boolean tem = dao.verificaFuncionarioPorIndice(cid.getText());
            
            if(tem == false){
                JOptionPane.showMessageDialog(null, "Não há funcionário com este ID");
            }
            else{
                Funcionario a = dao.retornaFuncionarioporIndice(cid.getText());
                setSize(400, 380);
                deixaVisivel();
                cNome.setText(a.getNome());
                cUsername.setText(a.getLogin());
                cSenha.setText("");                            
            }
        }
        
        if(e.getActionCommand().equals("Alterar")){
            
            if(cNome.getText().equals("") || cUsername.getText().equals("") || cSenha.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Um ou mais campos estão nulos");
            }
            
            else if(dao.verificaFuncionarioPorIndice(cid.getText())){
                String nome = cNome.getText();
                //nome = nome.toUpperCase();
                System.out.println(nome);
                String username = cUsername.getText();
                System.out.println(username);
                String pass = cSenha.getText();
                
                if(dao.atualizaFuncionario(cid.getText(), nome, username, pass)){
                    JOptionPane.showMessageDialog(null, "Cadastro alterado:\n\nID: "+cid.getText()+"\nNome: "+nome+"\nUsername: "+username+"");
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Falha ao alterar");
                }
            }
        }
        
    }
    
}
