/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Produto;
import controller.Venda;
import controller.atributosMenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.DAO;

/**
 *
 * @author diego
 */
public class ExcluiVenda extends JFrame implements ActionListener{
    JLabel id;
    JTextField cid;
    JButton procurar;
    TelaInicial tela;
    
    public ExcluiVenda(TelaInicial tl){
        
        setLayout(null);
        
        tela = tl;
        
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
        setTitle("Exclusão cadastro de venda - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new ExcluiVenda(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        if(e.getActionCommand().equals("Procurar")){
            System.out.println("Procurando venda..");
            if(!dao.verificaVendaPorIndice(cid.getText())){
                JOptionPane.showMessageDialog(null, "Não há produto com este ID");
            }
            else{
                Venda a = dao.retornaVendaPorIndice(cid.getText());
                String lembra = a.getId_venda();
                Object[] options = { "Confirmar", "Cancelar" };
                atributosMenuPrincipal b = dao.atualiza(null);
                int res = JOptionPane.showOptionDialog(null, "Deseja excluir o cadastro deste produto?\n\nID: "+b.getId_venda()+"\nProduto: "+a.getId_produto()+"\nCliente: "+b.getNome_cliente()+"\nValor: "+b.getValor_venda()+"\nQuantidade: "+b.getQtd()+"\nData: "+b.getData()+"", "Confirmação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if(res == 0){
                    System.out.println("Excluindo...");
                    if(dao.excluirVenda(cid.getText())){
                        JOptionPane.showMessageDialog(null, "Cadastro excluido!");
                        
                        int auxb = Integer.parseInt(b.getId_venda());
                        int auxa = Integer.parseInt(lembra);
                        
                        if(auxa>auxb){
                            tela.id.setText("ID Venda: "+b.getId_venda());
                            tela.produto.setText(b.getProduto());
                            tela.data.setText("Data: "+b.getData());
                            tela.qtd.setText("Quantidade: "+b.getQtd());
                        }
                        
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



