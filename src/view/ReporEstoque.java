/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Produto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.DAO;

/**
 *
 * @author diego
 */
public class ReporEstoque extends JFrame implements ActionListener{
    
    JLabel id, qt;
    JTextField cid, cqt;
    JButton definir;

    public ReporEstoque() {
        setLayout(null);
        
        id = new JLabel("ID:");
        id.setSize(100, 25);
        id.setLocation(35, 30);
        add(id);
        
        cid = new JTextField();
        cid.setSize(200, 25);
        cid.setLocation(140, 30);
        add(cid);
        
        qt = new JLabel("Adicionar:");
        qt.setSize(100, 25);
        qt.setLocation(35, 80);
        add(qt);
        
        cqt = new JTextField();
        cqt.setSize(200, 25);
        cqt.setLocation(140, 80);
        add(cqt);
        
        definir = new JButton("Definir");
        definir.setSize(130,25);
        definir.setLocation(130, 145);
        add(definir);        
        
        definir.addActionListener(this);
        
        setSize(400,240);
        setLocation(450,90);
        setTitle("Reposição de estoque de produto - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new ReporEstoque();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Definir")){
            System.out.println("Definindo nova quantidade de estoque");
            
            if(cid.getText().equals("") || cqt.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Um ou mais campos estão nulos");
            }
            else{
                int tamanhoIncrementa = Integer.parseInt(cqt.getText());
                if(tamanhoIncrementa<1){
                    JOptionPane.showMessageDialog(null, "Apenas reposições com mais de 10 unidades são permitidas");
                }
                else{
                    DAO dao = new DAO();
                    if(dao.verificaProdutoPorIndice(cid.getText())){
                        int atual = tamanhoIncrementa + Integer.parseInt(dao.verificaEstoqueProduto(cid.getText()));
                        String ref = String.valueOf(atual);
                        dao.atualizaEstoqueProduto(cid.getText(), ref);
                        Produto ver = dao.retornaProdutoporIndice(cid.getText());
                        JOptionPane.showMessageDialog(null, "Foram adicionadas "+tamanhoIncrementa+ " novas unidades ao estoque do produto"
                                + "\n\nID: "+ver.getId()+""
                                + "\nProduto: "+ver.getNome()+""
                                + "\nNovo valor de estoque: "+ver.getQtd_estoque());
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Este indice não existe no banco de dados!");
                    }
                }
            }
        }
    }
    
}
