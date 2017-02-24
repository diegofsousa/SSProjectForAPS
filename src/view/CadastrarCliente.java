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
import model.teclasPermitidas;
import model.teclasPermitidasLetras;

/**
 *
 * @author diego
 */
public class CadastrarCliente extends JFrame implements ActionListener{
    
    JLabel tNome, tCpf, tSexo, tTelefone;
    JTextField cNome, cCpf, cTelefone;
    ButtonGroup cSexo;
    JRadioButton mas, fem;
    JButton cadastrar;
    TelaInicial a;
    
    public CadastrarCliente(TelaInicial b){
        
        a = b;
        
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
        
        
        tCpf = new JLabel("CPF:");
        tCpf.setSize(100, 25);
        tCpf.setLocation(50, 75);
        add(tCpf);
        
        cCpf = new JTextField();
        cCpf.setSize(200, 25);
        cCpf.setLocation(120, 75);
        add(cCpf);
        cCpf.setDocument(new teclasPermitidas(11));
        
        tSexo = new JLabel("Sexo:");
        tSexo.setSize(100, 25);
        tSexo.setLocation(50, 125);
        add(tSexo);
        
        cSexo = new ButtonGroup();
        
        mas = new JRadioButton("M", false);
        mas.setSize(40, 25);
        mas.setLocation(105, 125);
        
        fem = new JRadioButton("F", false);
        fem.setSize(40, 25);
        fem.setLocation(150, 125);
        
        cSexo.add(mas);
        cSexo.add(fem);
        add(mas);
        add(fem);
        
        tTelefone = new JLabel("Telefone:");
        tTelefone.setSize(100, 25);
        tTelefone.setLocation(50, 175);
        add(tTelefone);
        
        cTelefone = new JTextField();
        cTelefone.setSize(200, 25);
        cTelefone.setLocation(120, 175);
        cTelefone.setDocument(new teclasPermitidas(11));
        add(cTelefone);
        
        cadastrar = new JButton("Cadastrar");
        cadastrar.setSize(130,25);
        cadastrar.setLocation(130, 225);
        add(cadastrar);
        
        cadastrar.addActionListener(this);
        
        setSize(400, 350);
        setLocation(450,90);
        setTitle("Cadastro de cliente - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new CadastrarCliente(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Cadastrar")){
            System.out.println("Tentando cadastrar cliente");
            
            if(cNome.getText().equals("") || cCpf.getText().equals("") || cSexo.isSelected(null) || cTelefone.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Um ou mais campos estão nulos");
            }
            
            else if(cCpf.getText().length()<11 || cTelefone.getText().length()<8){
                JOptionPane.showMessageDialog(null, "CPF e/ou Telefone invalido(s)");
            }
            else{
                String nome = cNome.getText();
                nome = nome.toUpperCase();
                System.out.println(nome);
                String cpf = cCpf.getText();
                System.out.println(cpf);
                String sexo;
                if(mas.isSelected()){
                    sexo = "M";
                }
                else{
                    sexo = "F";
                }
                System.out.println(sexo);
                String telefone = cTelefone.getText();
                System.out.println(telefone);
                Object[] options = { "Confirmar", "Cancelar" };
                int res = JOptionPane.showOptionDialog(null, "Deseja realmente cadastrar?", "Cadastro de estudante", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if(res == 0){
                    Cliente a = new Cliente(null, nome, telefone, cpf, sexo, null);
                    DAO dao = new DAO();
                    boolean sinal = dao.cadastrarCliente(a);
                    if(sinal == true){
                        String indice  = dao.idUltimoCadastrado();
              
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado\n\nID: "+indice+"\nNome: "+nome+"\nTelefone: "+telefone+"\nSexo: "+sexo+"");
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
                    }
                    
                }
            
            }
            
            //
            
        }
    }
}
