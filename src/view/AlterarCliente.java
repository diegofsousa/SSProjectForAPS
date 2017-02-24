/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;
import controller.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import model.DAO;
import model.teclasPermitidas;
import model.teclasPermitidasLetras;

/**
 *
 * @author diego
 */
public class AlterarCliente extends JFrame implements ActionListener{
    
    JLabel id, tNome, tCpf, tSexo, tTelefone;
    JTextField cid, cNome, cCpf, cTelefone;
    ButtonGroup cSexo;
    JRadioButton mas, fem;
    JButton procurar, alterar;
    JFrame fp;
    
  
    
    public AlterarCliente(JFrame fPrincipal){
        fp = fPrincipal;
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
        
        tNome = new JLabel("Nome:");
        tNome.setSize(100, 25);
        tNome.setLocation(50, 170);
        
        add(tNome);
        
        cNome = new JTextField();
        cNome.setSize(200, 25);
        cNome.setLocation(120, 170);
        cNome.setDocument(new teclasPermitidasLetras());
        add(cNome);
        
        tCpf = new JLabel("CPF:");
        tCpf.setSize(100, 25);
        tCpf.setLocation(50, 220);
        add(tCpf);
        
        cCpf = new JTextField();
        cCpf.setSize(200, 25);
        cCpf.setLocation(120, 220);
        cCpf.setDocument(new teclasPermitidas(11));
        add(cCpf);
        
        tSexo = new JLabel("Sexo:");
        tSexo.setSize(100, 25);
        tSexo.setLocation(50, 270);
        add(tSexo);
        
        cSexo = new ButtonGroup();
        
        mas = new JRadioButton("M", false);
        mas.setSize(40, 25);
        mas.setLocation(105, 270);
        
        fem = new JRadioButton("F", false);
        fem.setSize(40, 25);
        fem.setLocation(150, 270);
        
        cSexo.add(mas);
        cSexo.add(fem);
        add(mas);
        add(fem);
        
        tTelefone = new JLabel("Telefone:");
        tTelefone.setSize(100, 25);
        tTelefone.setLocation(50, 320);
        add(tTelefone);
        
        cTelefone = new JTextField();
        cTelefone.setSize(200, 25);
        cTelefone.setLocation(120, 320);
        cTelefone.setDocument(new teclasPermitidas(11));
        add(cTelefone);
        
        alterar = new JButton("Alterar");
        alterar.setSize(100, 25);
        alterar.setLocation(150, 350);
        add(alterar);
        
        procurar.addActionListener(this);
        alterar.addActionListener(this);
        
        setSize(400, 160);
        setLocation(450,90);
        setTitle("Alterar cadastro de cliente - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new AlterarCliente(null);
    }
    
    void deixaInvisivel(){
        tNome.setVisible(false);
        cNome.setVisible(false);
        tCpf.setVisible(false);
        cCpf.setVisible(false);
        tSexo.setVisible(false);
        mas.setVisible(false);
        fem.setVisible(false);
        tTelefone.setVisible(false);
        cTelefone.setVisible(false);
        alterar.setVisible(false);
    }
    
    public void deixaVisivel(){
        tNome.setVisible(true);
        cNome.setVisible(true);
        tCpf.setVisible(true);
        cCpf.setVisible(true);
        tSexo.setVisible(true);
        mas.setVisible(true);
        fem.setVisible(true);
        tTelefone.setVisible(true);
        cTelefone.setVisible(true);
        alterar.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        if(e.getActionCommand().equals("Procurar")){
            System.out.println("Procurando cliente...");
            boolean tem = dao.verificaClientePorIndice(cid.getText());
            
            if(tem == false){
                JOptionPane.showMessageDialog(null, "Não há cliente com este ID");
            }
            else{
                Cliente a = dao.retornaClienteporIndice(cid.getText());
                setSize(400, 410);
                deixaVisivel();
                cNome.setText(a.getNome());
                cCpf.setText(a.getCpf());
                if(a.getSexo().equals("M")){
                    mas.doClick();
                }
                else{
                    fem.doClick();
                }
                cTelefone.setText(a.getTelefone());
                               
            }
            
            
        }
        
        if(e.getActionCommand().equals("Alterar")){
            
            if(cNome.getText().equals("") || cCpf.getText().equals("") || cSexo.isSelected(null) || cTelefone.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Um ou mais campos estão nulos");
            }
            else if(cCpf.getText().length()<11 || cTelefone.getText().length()<8){
                JOptionPane.showMessageDialog(null, "CPF e/ou Telefone invalido(s)");
            }
            
            else if(dao.verificaClientePorIndice(cid.getText())){
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
             
                if(dao.atualizaCliente(cid.getText(), nome, telefone, cpf, sexo)){
                    JOptionPane.showMessageDialog(null, "Cadastro alterado:\n\nID: "+cid.getText()+"\nNome: "+nome+"\nCPF: "+cpf+"\nSexo: "+sexo+"\nTelefone: "+telefone+"");
                    fp.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Falha ao alterar");
                }
            }
        }
    }
}
