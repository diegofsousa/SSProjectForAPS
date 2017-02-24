/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Cliente;
import controller.Produto;
import controller.Venda;
import controller.atributosMenuPrincipal;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.swing.*;
import model.DAO;

/**
 *
 * @author diego
 */
public class Vender extends JFrame implements ActionListener{
    
    JTabbedPane pane, comCadastri;
    JPanel SemCadastro, procuraNome, procuraId, plista, plistaC, plistaP;
    JLabel tNome, tNomeC, tProduto, tqtd1;
    JTextField cNome, cNomeC, cProduto, cNomeID, cProdutoID;
    DefaultListModel modelo, modeloC, modeloP;
    JList lista, listaC, listaP;
    JButton procurar, procurarC, procurarP, venderSC, vender, venderID;
    JComboBox<Integer> j1 = new JComboBox<>();
    JComboBox<Integer> j2 = new JComboBox<>();
    JComboBox<Integer> j3 = new JComboBox<>();
    TelaInicial tela;
    String user;
            
    public Vender(TelaInicial tl, String user){
        tela = tl;
        this.user = user;
        setLayout(null);
        
        //Parte dos sem cadastros
        
        tNome = new JLabel("Nome do produto:");
        tNome .setSize(150, 25);
        tNome .setLocation(10, 30);
        
        cNome = new JTextField();
        cNome.setSize(200, 25);
        cNome.setLocation(160, 30);

        procurar = new JButton("Procurar");
        procurar.setSize(130,25);
        procurar.setLocation(130, 75);
        
        modelo = new DefaultListModel();
        lista = new JList(modelo);
        
        
        
 
        JScrollPane scrollPane = new JScrollPane();
        //scrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 

        scrollPane.setLocation(60, 108);
        scrollPane.add(lista);
        scrollPane.getViewport().add(lista);
       
        plista = new JPanel();
        plista.setLayout(new GridLayout(0, 2));
        plista.setSize(520,85);
        plista.setLocation(60, 108);
        plista.add(scrollPane);
        
        tqtd1 = new JLabel("Quantidade:");
        tqtd1.setSize(100,25);
        tqtd1.setLocation(100, 210);
        
        j1.addItem(1);
        j1.addItem(2);
        j1.addItem(3);
        j1.addItem(4);
        j1.addItem(5);
        j1.setLocation(200, 210);
        j1.setSize(40, 25);
        
        venderSC = new JButton("Vender!");
        venderSC.setSize(130,25);
        venderSC.setLocation(130, 255);
        venderSC.setBackground(Color.red);
        venderSC.setForeground(Color.white);
        
        
        
        SemCadastro = new JPanel();
        SemCadastro.add(tNome);
        SemCadastro.add(cNome);
        SemCadastro.add(procurar);
        SemCadastro.add(plista);
        SemCadastro.add(venderSC);
        SemCadastro.add(j1);
        SemCadastro.add(tqtd1);
        
        SemCadastro.setLayout(null);
        
        
        //parte dos com cadastros por nome
        
        tNomeC = new JLabel("Nome do cliente: ");
        tNomeC.setSize(150, 25);
        tNomeC.setLocation(10, 30);
        
        cNomeC = new JTextField();
        cNomeC.setSize(200, 25);
        cNomeC.setLocation(160, 30);

        procurarC = new JButton("Buscar");
        procurarC.setSize(130,25);
        procurarC.setLocation(130, 75);
        
        modeloC = new DefaultListModel();
        listaC = new JList(modeloC);
        
      
       
 
        JScrollPane scrollPaneC = new JScrollPane();
        //scrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 

        scrollPaneC.setLocation(60, 108);
        scrollPaneC.add(listaC);
        scrollPaneC.getViewport().add(listaC);
        
        
        plistaC = new JPanel();
        plistaC.setLayout(new GridLayout(0, 2));
        plistaC.setSize(520,85);
        plistaC.setLocation(60, 108);
        plistaC.add(scrollPaneC);
        
        tProduto = new JLabel("Nome do Produto: ");
        tProduto.setSize(150, 25);
        tProduto.setLocation(10, 240);
        
        cProduto = new JTextField();
        cProduto.setSize(200, 25);
        cProduto.setLocation(160, 240);
        
        procurarP = new JButton("Buscar produto");
        procurarP.setSize(170,25);
        procurarP.setLocation(100, 285);        
        
        modeloP = new DefaultListModel();
        listaP = new JList(modeloP);
        
       
        JScrollPane scrollPaneP = new JScrollPane();
        //scrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 

        scrollPaneP.setLocation(60, 108);
        scrollPaneP.add(listaP);
        scrollPaneP.getViewport().add(listaP);        
        
        plistaP = new JPanel();
        plistaP.setLayout(new GridLayout(0, 2));
        plistaP.setSize(520,85);
        plistaP.setLocation(60, 330);
        plistaP.add(scrollPaneP);
        
        tqtd1 = new JLabel("Quantidade:");
        tqtd1.setSize(100,25);
        tqtd1.setLocation(100, 420);
        
        j2.addItem(1);
        j2.addItem(2);
        j2.addItem(3);
        j2.addItem(4);
        j2.addItem(5);
        j2.setLocation(200, 420);
        j2.setSize(40, 25);
        
        
        vender = new JButton("Vender para cadastrado");
        vender.setSize(250,25);
        vender.setLocation(60, 450);
        vender.setBackground(Color.red);
        vender.setForeground(Color.white);
        
        
        procuraNome = new JPanel();
        procuraNome.add(tNomeC);
        procuraNome.add(cNomeC);
        procuraNome.add(procurarC);
        procuraNome.add(plistaC);
        procuraNome.add(tProduto);
        procuraNome.add(cProduto);
        procuraNome.add(procurarP);
        procuraNome.add(plistaP);
        procuraNome.add(vender);
        procuraNome.add(tqtd1);
        procuraNome.add(j2);
        procuraNome.setLayout(null);
        
        //parte dos cadastros por ID
        
        
        tNomeC = new JLabel("ID do cliente: ");
        tNomeC.setSize(150, 25);
        tNomeC.setLocation(10, 30);
        
        cNomeID = new JTextField();
        cNomeID.setSize(200, 25);
        cNomeID.setLocation(160, 30);
        
        tProduto = new JLabel("ID do produto: ");
        tProduto.setSize(150, 25);
        tProduto.setLocation(10, 70);
        
        cProdutoID = new JTextField();
        cProdutoID.setSize(200, 25);
        cProdutoID.setLocation(160, 70);
        
        tqtd1 = new JLabel("Quantidade:");
        tqtd1.setSize(100,25);
        tqtd1.setLocation(100, 120);
        
        j3.addItem(1);
        j3.addItem(2);
        j3.addItem(3);
        j3.addItem(4);
        j3.addItem(5);
        j3.setLocation(200, 120);
        j3.setSize(40, 25);
        
        venderID = new JButton("Vender por ID");
        venderID.setSize(250,25);
        venderID.setLocation(60, 200);
        venderID.setBackground(Color.red);
        venderID.setForeground(Color.white);
        
        procuraId = new JPanel();
        procuraId.add(tNomeC);
        procuraId.add(cNomeID);
        procuraId.add(tProduto);
        procuraId.add(cProdutoID);
        procuraId.add(tqtd1);
        procuraId.add(j3);
        procuraId.add(venderID);
        procuraId.setLayout(null);
        
        comCadastri = new JTabbedPane();
        comCadastri.setSize(400, 550);
        comCadastri.add(procuraNome, "Procura por nome");
        comCadastri.add(procuraId, "Procura por ID");
        
        
        pane = new JTabbedPane();
        pane.setSize(400, 570);
        pane.add(SemCadastro, "Sem cadastro");
        pane.add(comCadastri, "Com cadastro"); 
        pane.setVisible(true);
       
          
        add(pane);  
        
        procurar.addActionListener(this);
        venderSC.addActionListener(this);
        procurarC.addActionListener(this);
        procurarP.addActionListener(this);
        vender.addActionListener(this);
        venderID.addActionListener(this);
        
        setSize(400,570);
        setLocation(450,90);
        setTitle("Vender - Serralheria Sousa");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new Vender(null, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        /*SEM CADASTRO*/
        if(e.getActionCommand().equals("Procurar")){
            System.out.println("Procurando produtos...");
            ArrayList<Produto> c= dao.retornaTodosOsProdutosComLike(cNome.getText());
            modelo.clear();
            for(int i=0; i<c.size();i++){
                System.out.println("indice: "+c.get(i).getId()+" - nome: "+c.get(i).getNome()+" - R$: ("+c.get(i).getValor()+")");
                modelo.add(i, "ID = ~"+c.get(i).getId()+"~  - "+c.get(i).getNome()+" - R$: "+c.get(i).getValor()+"");
            }
       
        }
        if(e.getActionCommand().equals("Vender!")){
            String indiceProduto = (String) lista.getSelectedValue();
            if(indiceProduto == null){
                //System.out.println("PAROU AQUI");
                JOptionPane.showMessageDialog(null, "Alguns campos devem ser preenchidos");
            }
            String indiceUser = dao.retornaIndiceDeSenha(user);
            String qtd =  j1.getSelectedItem().toString();
            
            String[] t = indiceProduto.split (Pattern.quote ("~"));
            
            System.out.println("qtd venda:"+qtd);
            
            System.out.println("indice do produto:"+t[1]);
            System.out.println("indice user:"+indiceUser);
            
            String valor = dao.retornaValorDeProdutoPeloIndice(t[1]);
            System.out.println("valor: "+valor);
            
             
            float totalVenda = Float.parseFloat(valor) * Integer.parseInt(qtd);
            String tV = String.valueOf(totalVenda);
            System.out.println("Total venda: "+ tV);
            
            //pegando a hora
            Locale locale = new Locale("pt","BR");
            GregorianCalendar calendar = new GregorianCalendar(); 
            SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
            String data = formatador.format(calendar.getTime());
            //
            System.out.println("hora da venda:"+data);
            
            String q = dao.verificaEstoqueProduto(t[1]);
            int qt = Integer.parseInt(q);
            
            if(qtd == null || tV == null || t[1] == null || t[1].equals("") || indiceUser == null || data == null || data.equals("")){
                System.out.println("AQUI?");
                JOptionPane.showMessageDialog(null, "Alguns campos devem ser preenchidos");
            }
            else if(qt < Integer.parseInt(qtd)){
                System.out.println("Segundo IF");
                JOptionPane.showMessageDialog(null, "Não há unidades suficientes deste produto no nosso estoque para esta solicitação!");
            }
            else{
                int novoEstoque = qt - Integer.parseInt(qtd);
                System.out.println("Decrementaçao: "+qt+"-"+Integer.parseInt(qtd)+"="+novoEstoque+"");
                String nEstoque = String.valueOf(novoEstoque);
                dao.atualizaEstoqueProduto(t[1], nEstoque);
                System.out.println("passou");
                Venda vend = new Venda();
                vend.setDatahora(data);
                vend.setId_produto(t[1]);
                vend.setId_usuario(indiceUser);
                vend.setValor(tV);
                vend.setQtd_venda(qtd);
                vend.setId_cliente("1");
                dao.vender(vend);
                
                atributosMenuPrincipal a = dao.atualiza(null);
                
                tela.id.setText("ID Venda: "+a.getId_venda());
                tela.produto.setText(a.getProduto());
                tela.data.setText("Data: "+a.getData());
                tela.qtd.setText("Quantidade: "+a.getQtd());
                
                System.out.println("Log de atualizaçao: ");
                System.out.println("ID venda"+a.getId_venda());
                System.out.println("Produto"+a.getProduto());
                
                Object[] options = { "Confirmar", "Cancelar" };
                int res = JOptionPane.showOptionDialog(null, "Deseja emitir nota fiscal?",  "Confirmação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if(res == 0){
                    new GerarNotaFiscal();
                }
                                
                JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!\n\n"
                        + "ID da venda: "+a.getId_venda()+""
                        + "\nProduto: "+a.getProduto()+""
                        + "\nQuantidade: "+a.getQtd()+""
                        + "\nValor da venda: "+a.getValor_venda()+""
                        + "\nCliente: "+a.getNome_cliente()+""
                        + "\nData e horário: "+a.getData()+""
                        + "\nUsuário do sistema: "+a.getNome_usuario()+"");
                
                dispose();
                
                
            }
            
        }
        
        /*COM CADASTRO E BUSCA POR LIKE*/
        if(e.getActionCommand().equals("Buscar")){
            if(cNomeC.getText().equals("")){
                modeloC.clear();
            }
            else{
                System.out.println("Buscando cadastros das pessoas no banco...");
                ArrayList<Cliente> r= dao.retornaTodosOsClientesComLike(cNomeC.getText());
                modeloC.clear();
                for(int i=0; i<r.size();i++){
                    System.out.println("indice: "+r.get(i).getId()+" - nome: "+r.get(i).getNome()+"");
                    modeloC.add(i, "ID = ~"+r.get(i).getId()+"~  - "+r.get(i).getNome()+"");
                }
            }
            
        }
        if(e.getActionCommand().equals("Buscar produto")){
            if(cProduto.getText().equals("")){
                modeloP.clear();
            }
            else{
                System.out.println("Procurando produtos...");
                ArrayList<Produto> c= dao.retornaTodosOsProdutosComLike(cProduto.getText());
                modeloP.clear();
                for(int i=0; i<c.size();i++){
                    System.out.println("indice: "+c.get(i).getId()+" - nome: "+c.get(i).getNome()+" - R$: ("+c.get(i).getValor()+")");
                    modeloP.add(i, "ID = ~"+c.get(i).getId()+"~  - "+c.get(i).getNome()+" - R$: "+c.get(i).getValor()+"");
                }
            }
        }
        if(e.getActionCommand().equals("Vender para cadastrado")){
            
            
            System.out.println("Vender para cadastrado");
            String indiceUser = dao.retornaIndiceDeSenha(user);
            //indice pessoa 
            String indicePessoa = (String) listaC.getSelectedValue();
            if(indicePessoa == null){
                
                JOptionPane.showMessageDialog(null, "Alguns campos devem ser preenchidos");
            }
            
            
            
                        
            String[] indPess = indicePessoa.split (Pattern.quote ("~"));
            System.out.println("Indice do cliente: "+ indPess[1]);
            
            //indice produto
            
            String qtd =  j2.getSelectedItem().toString();
            String indiceProduto = (String) listaP.getSelectedValue();
           if(indiceProduto == null){
               
                JOptionPane.showMessageDialog(null, "Alguns campos devem ser preenchidos");
            }
                        
            String[] indProd = indiceProduto.split (Pattern.quote ("~"));
            System.out.println("Indice do cliente: "+ indProd[1]);
            
            
            
            String valor = dao.retornaValorDeProdutoPeloIndice(indProd[1]);
            System.out.println("Valor do produto eh: "+valor);
            
             
            float totalVenda = Float.parseFloat(valor) * Integer.parseInt(qtd);
            String tV = String.valueOf(totalVenda);
            System.out.println("Total venda somado eh: "+ tV);
            
            //pegando a hora
            Locale locale = new Locale("pt","BR");
            GregorianCalendar calendar = new GregorianCalendar(); 
            SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
            String data = formatador.format(calendar.getTime());
            //
            System.out.println("hora da venda:"+data);
            
            String q = dao.verificaEstoqueProduto(indProd[1]);
            int qt = Integer.parseInt(q);
            if(qtd == null || tV == null || indPess[1] == null || indPess[1].equals("") || indProd == null || indProd.equals("") || indiceUser == null || data == null || data.equals("")){
                System.err.println("Problema no indice pessoa");
                System.out.println("qtd: "+qtd);
                System.out.println("tV:" +tV);
                System.out.println("indpess: "+indPess[1]);
                System.out.println("indprod:"+indProd);
                System.out.println("indiceuser:"+indiceUser);
                System.out.println(data);
                JOptionPane.showMessageDialog(null, "Alguns campos devem ser preenchidos");
            }
            else if(qt < Integer.parseInt(qtd)){
                JOptionPane.showMessageDialog(null, "Não há unidades suficientes deste produto no nosso estoque para esta solicitação!");
            }
            else{
                System.out.println("passou");
                int novoEstoque = qt - Integer.parseInt(qtd);
                System.out.println("Decrementaçao: "+qt+"-"+Integer.parseInt(qtd)+"="+novoEstoque+"");
                String nEstoque = String.valueOf(novoEstoque);
                dao.atualizaEstoqueProduto(indProd[1], nEstoque);
                System.out.println("passou");
                Venda vend = new Venda();
                vend.setDatahora(data);
                vend.setId_produto(indProd[1]);
                vend.setId_usuario(indiceUser);
                vend.setValor(tV);
                vend.setQtd_venda(qtd);
                vend.setId_cliente(indPess[1]);
                dao.vender(vend);
                
                atributosMenuPrincipal a = dao.atualiza(null);
                
                tela.id.setText("ID Venda: "+a.getId_venda());
                tela.produto.setText(a.getProduto());
                tela.data.setText("Data: "+a.getData());
                tela.qtd.setText("Quantidade: "+a.getQtd());
                
                System.out.println("Log de atualizaçao: ");
                System.out.println("ID venda"+a.getId_venda());
                System.out.println("Produto"+a.getProduto());
                
                Object[] options = { "Confirmar", "Cancelar" };
                int res = JOptionPane.showOptionDialog(null, "Deseja emitir nota fiscal?",  "Confirmação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if(res == 0){
                    new GerarNotaFiscal();
                }
                
                JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!\n\n"
                        + "ID da venda: "+a.getId_venda()+""
                        + "\nProduto: "+a.getProduto()+""
                        + "\nQuantidade: "+a.getQtd()+""
                        + "\nValor da venda: "+a.getValor_venda()+""
                        + "\nCliente: "+a.getNome_cliente()+""
                        + "\nData e horário: "+a.getData()+""
                        + "\nUsuário do sistema: "+a.getNome_usuario()+"");
                
                dispose();
                
            }

        }
        /*VENDA POR ID*/
        if(e.getActionCommand().equals("Vender por ID")){
            System.out.println("Vendendo por ID");

            if(cNomeID.getText().equals("") || cProdutoID.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios!");
            }
            else{
                if(dao.verificaClientePorIndice(cNomeID.getText()) || dao.verificaProdutoPorIndice(cProdutoID.getText())){
                    String qtd =  j3.getSelectedItem().toString();
                    String valor = dao.retornaValorDeProdutoPeloIndice(cProdutoID.getText());
                    float totalVenda = Float.parseFloat(valor) * Integer.parseInt(qtd);
                    String tV = String.valueOf(totalVenda);
                    String q = dao.verificaEstoqueProduto(cProdutoID.getText());
                    int qt = Integer.parseInt(q);
                    String indiceUser = dao.retornaIndiceDeSenha(user);
                    
                    Locale locale = new Locale("pt","BR");
                    GregorianCalendar calendar = new GregorianCalendar(); 
                    SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
                    String data = formatador.format(calendar.getTime());
                    
                    if(qt < Integer.parseInt(qtd)){
                        JOptionPane.showMessageDialog(null, "Não há unidades suficientes deste produto no nosso estoque para esta solicitação!");
                    }
                    else{
                        System.out.println("qtd: "+qtd);
                        System.out.println("valor: "+valor);
                        System.out.println("idcliente: "+cNomeID.getText());
                        System.out.println("idproduto: "+cProdutoID.getText());
                        System.out.println("idusuario: "+indiceUser);
                        System.out.println("data: "+data);
                        
                        
                       //////////////////falta fazer para decrementar o estoque 
                        Venda novo = new Venda(null, qtd, valor, cNomeID.getText(), cProdutoID.getText(), indiceUser, data);
                        if(dao.vender(novo)){
                            atributosMenuPrincipal a = dao.atualiza(null);
                
                            tela.id.setText("ID Venda: "+a.getId_venda());
                            tela.produto.setText(a.getProduto());
                            tela.data.setText("Data: "+a.getData());
                            tela.qtd.setText("Quantidade: "+a.getQtd());

                            System.out.println("Log de atualizaçao: ");
                            System.out.println("ID venda"+a.getId_venda());
                            System.out.println("Produto"+a.getProduto());
                            
                            Object[] options = { "Confirmar", "Cancelar" };
                            int res = JOptionPane.showOptionDialog(null, "Deseja emitir nota fiscal?",  "Confirmação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                            if(res == 0){
                                new GerarNotaFiscal();
                            }

                            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!\n\n"
                                    + "ID da venda: "+a.getId_venda()+""
                                    + "\nProduto: "+a.getProduto()+""
                                    + "\nQuantidade: "+a.getQtd()+""
                                    + "\nValor da venda: "+a.getValor_venda()+""
                                    + "\nCliente: "+a.getNome_cliente()+""
                                    + "\nData e horário: "+a.getData()+""
                                    + "\nUsuário do sistema: "+a.getNome_usuario()+"");

                            dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Falha ao realizar operação");
                        }
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Algum destes ID's não existem no banco!");
                }
                
            }
            
        }
    }
}
