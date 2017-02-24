/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Senha;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import model.DAO;

/**
 *
 * @author diego
 */
public class TelaLogin extends JFrame implements ActionListener, KeyListener{
    
    JLabel imagem, qr;
    JButton ajuda;
    String []r;
    JTextField Login;
    JLabel text1, link;
    JPasswordField Senha;
    JLabel text2;
    JButton confirma;
    boolean ver = false;
    JFrame iniciaPrograma;
    private JLabel ajuda1;    
    JLabel ajuda2, ajuda3, ajuda4;
    
    public TelaLogin(){
        setLayout(null);
        
        imagem = new JLabel();
        imagem.setIcon(new javax.swing.ImageIcon("/media/diego/HD1/Documentos/Sistemas de Informação/5º Período/Programação Orientada a Objetos II/Dados/Serralheria Sousa/imagemLogin.png"));
        imagem.setBounds(30, 120, 340, 100);
        add(imagem);
        
        ajuda = new JButton("Ajuda");
        ajuda.setSize(100, 25);
        ajuda.setLocation(280, 10);
        add(ajuda);
        
        text1 = new JLabel("Login: ");
        text1.setSize(100,25);
        text1.setLocation(100,280);
        add(text1);
        
        Login = new JTextField();
        Login.setSize(100, 25);
        Login.setLocation(160, 280);
        add(Login);
        
        text2 = new JLabel("Senha:");
        text2.setSize(50, 25);
        text2.setLocation(100, 320);
        add(text2);
        
        Senha = new JPasswordField();
        Senha.setSize(100, 25);
        Senha.setLocation(160, 320);
        add(Senha);
        
        confirma = new JButton("Entrar");
        confirma.setSize(130, 25);
        confirma.setLocation(140, 360);
        add(confirma);
        
        qr = new JLabel();
        qr.setIcon(new javax.swing.ImageIcon("/media/diego/HD1/Documentos/Sistemas de Informação/5º Período/Programação Orientada a Objetos II/Dados/Serralheria Sousa/QR_codes/QRparaTLogin.jpg"));
        qr.setBounds(30, 420, 140, 138);
        add(qr);
        
        ajuda4 = new JLabel("Website da oficina:");
        ajuda4.setSize(400, 25);
        ajuda4.setLocation(215, 455);
        ajuda4.setVisible(true);
        add(ajuda4);
        
        link = new JLabel("www.serralheriasousa.pe.hu");
        Desktop desk = java.awt.Desktop.getDesktop();
        link.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("Clicou site");
                try {
                    desk.browse(new java.net.URI("http://www.serralheriasousa.pe.hu"));
                } catch (IOException | URISyntaxException ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
            
            @Override
            public void mouseEntered(MouseEvent arg0) {
		link.setText("<html><u>www.serralheriasousa.pe.hu</u>");
                Cursor cursor = Cursor.getPredefinedCursor( Cursor.HAND_CURSOR );  
                setCursor(cursor);
            }// evento q sera executado caso o mouse entre no label
            @Override
            public void mouseExited(MouseEvent arg0) {
		link.setText("<html>www.serralheriasousa.pe.hu");
		//link.setForeground(Color.black);
                Cursor cursor = Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR );  
                setCursor(cursor);
            }
            
          });
        //link.setFont(new Font("Dialog", Font.PLAIN, 16));
        link.setForeground(Color.blue);
        link.setSize(780, 60);
        link.setLocation(180, 470);
        add(link);     
        
        
        //ajuda3 = new JLabel("Desenvolvido por: Diego Fernando");
        //ajuda3.setSize(400, 25);
        //ajuda3.setLocation(70, 410);
        //ajuda3.setVisible(false);
        //add(ajuda3);
        
        ajuda1 = new JLabel("Sistema de gerenciamento da empresa:");
        ajuda1.setSize(400, 25);
        ajuda1.setLocation(60, 340);
        ajuda1.setVisible(false);
        add(ajuda1);
        
        ajuda2 = new JLabel("Serralheria Sousa");
        ajuda2.setSize(400, 25);
        ajuda2.setLocation(130, 360);
        ajuda2.setVisible(false);
        add(ajuda2);
        
        ajuda3 = new JLabel("Desenvolvido por: Diego Fernando");
        ajuda3.setSize(400, 25);
        ajuda3.setLocation(70, 410);
        ajuda3.setVisible(false);
        add(ajuda3);
        
        setSize(400, 600);
        setLocation(450,90);
        setTitle("Login - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        ajuda.addActionListener(this);
        confirma.addActionListener(this);
        Senha.addKeyListener(this);
        ajuda.addKeyListener(this);
        confirma.addKeyListener(this);
        Login.addKeyListener(this);
        
        
        
    }
    
    public static void main(String[] args) {
        new TelaLogin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Ajuda")){
            if(ver == false){
                text1.setVisible(false);
                text2.setVisible(false);
                Login.setVisible(false);
                Senha.setVisible(false);
                confirma.setVisible(false);
                ajuda1.setVisible(true);
                ajuda2.setVisible(true);
                ajuda3.setVisible(true);
                qr.setVisible(false);
                ajuda4.setVisible(false);
                link.setVisible(false);
                ver = true;
            }
            else{
                text1.setVisible(true);
                text2.setVisible(true);
                Login.setVisible(true);
                Senha.setVisible(true);
                confirma.setVisible(true);
                ajuda1.setVisible(false);
                ajuda2.setVisible(false);
                ajuda3.setVisible(false);
                qr.setVisible(true);
                ajuda4.setVisible(true);
                link.setVisible(true);
                ver = false;
            }
        }
        
        else if(e.getActionCommand().equals("Entrar")){
            System.out.println("Apertou entrar");
            String log = Login.getText();
            String sen = Senha.getText();
            
            Senha senha = new Senha(log,sen,null);
            
            DAO dsenha = new DAO();
            r = dsenha.validaSenha(senha);
            
            if(r == null){
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto(s)");
            }
            else{
                System.out.println("Quem logou:" + r);
                iniciaPrograma = new TelaInicial(this, r);
                dispose();
            }  
        }
    }

    @Override
    public void keyTyped(KeyEvent ex) {
    }

    @Override
    public void keyPressed(KeyEvent ex) {
        //System.out.println("Apertou algo");
        if(ex.getKeyCode() == KeyEvent.VK_ENTER){
            if(ver != true){
                System.out.println("Teclou enter");   
                confirma.doClick();
            }           
       }
        
        else if(ex.getKeyCode() == KeyEvent.VK_F1){
            System.out.println("Pediu Ajuda");
            ajuda.doClick();
        }

    }

    @Override
    public void keyReleased(KeyEvent ex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


}
