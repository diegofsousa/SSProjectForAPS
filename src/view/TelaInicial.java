/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Nota;
import controller.atributosMenuPrincipal;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import model.DAO;

/**
 *
 * @author diego
 */
public class TelaInicial extends JFrame implements ActionListener{
    
    JFrame TelaL;
    JPanel linha1;
    JPanel linha2;
    JPanel linha3;
    JPanel logador;
    JLabel data, id, produto, qtd;
    JButton detaheUltimo, vender, cadCliente, cadProd, lisVenda, adNota, excluiNota, gerarNota;
    JTextArea notas;
    JLabel nome, imagem, funcao;
    DefaultListModel modelo;
    JList lista;
    ArrayList<Nota> c;
    JLabel rodape, link;
    String []r = new String[2];
    
    
    public TelaInicial(JFrame a, String[] r){
       TelaL = a;
        
        atributosMenuPrincipal b  = new atributosMenuPrincipal();
        DAO dao = new DAO();
        b = dao.atualiza(null);
        this.r = r;
        
        
        //System.out.println("\nHORA DA VERDADE: "+b.getProduto());
        
        setLayout(null);
        JMenuBar menuBar = new JMenuBar();
        
        imagem = new JLabel();
        imagem.setIcon(new javax.swing.ImageIcon("/media/diego/HD1/Documentos/Sistemas de Informação/5º Período/Programação Orientada a Objetos II/Dados/Serralheria Sousa/imagemhome.png"));
        imagem.setBounds(20, 40, 817, 240);
        add(imagem);
                
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem deslogar = new JMenuItem("Deslogar");
        JMenuItem sair = new JMenuItem("Sair");
        menuArquivo.add(deslogar);
        menuArquivo.add(new JSeparator());
        menuArquivo.add(sair);
        menuBar.add(menuArquivo);
        
        JMenu menuVenda = new JMenu("Venda");
        JMenuItem realizarVenda = new JMenuItem("Realizar venda");
        JMenuItem excluirVenda = new JMenuItem("Excluir venda");
        menuVenda.add(realizarVenda);
        menuVenda.add(new JSeparator());
        menuVenda.add(excluirVenda);
        menuBar.add(menuVenda);
        
        JMenu menuConsulta = new JMenu("Consultar");
        JMenuItem consultarVenda = new JMenuItem("Consultar venda");
        JMenuItem consultarCliente = new JMenuItem("Consultar clientes");
        JMenuItem consultarProduto = new JMenuItem("Consultar produto");
        menuConsulta.add(consultarVenda);
        menuConsulta.add(new JSeparator());
        menuConsulta.add(consultarCliente);
        menuConsulta.add(new JSeparator());
        menuConsulta.add(consultarProduto);
        menuBar.add(menuConsulta); 
        
        JMenu menuCliente = new JMenu("Clientes");
        JMenuItem cadastrarCliente = new JMenuItem("Cadastrar cliente");
        JMenuItem alterarCliente = new JMenuItem("Alterar cadastro de cliente");
        JMenuItem excluirCliente = new JMenuItem("Inativar cadastro de cliente");
        JMenuItem ressucitaCliente = new JMenuItem("Reativar cadastro de cliente");
        menuCliente.add(cadastrarCliente);
        menuCliente.add(new JSeparator());
        menuCliente.add(alterarCliente);
        menuCliente.add(new JSeparator());
        menuCliente.add(excluirCliente);
        menuCliente.add(new JSeparator());
        menuCliente.add(ressucitaCliente);
        menuBar.add(menuCliente); 
        
        JMenu menuProduto = new JMenu("Produtos");
        JMenuItem cadastrarProduto = new JMenuItem("Cadastrar produto");
        JMenuItem alterarProduto = new JMenuItem("Alterar cadastro de produto");
        JMenuItem excluirProduto = new JMenuItem("Excluir cadastro de produto");
        JMenuItem reporProduto = new JMenuItem("Repor estoque de produto");
        menuProduto.add(cadastrarProduto);
        menuProduto.add(new JSeparator());
        menuProduto.add(alterarProduto);
        menuProduto.add(new JSeparator());
        menuProduto.add(excluirProduto);
        menuProduto.add(new JSeparator());
        menuProduto.add(reporProduto);
        menuBar.add(menuProduto);
        
        if(r[1].equals("1")){
            JMenu menuFuncionario = new JMenu("Funcionários");
            JMenuItem cadastrarFuncionario = new JMenuItem("Cadastrar funcionário");
            JMenuItem alterarFuncionario = new JMenuItem("Alterar cadastro de funcionário");
            JMenuItem excluirFuncionario = new JMenuItem("Excluir cadastro de funcionário");
            JMenuItem listarFuncionario = new JMenuItem("Listar funcionários");
            menuFuncionario.add(cadastrarFuncionario);
            menuFuncionario.add(new JSeparator());
            menuFuncionario.add(listarFuncionario);
            menuFuncionario.add(new JSeparator());
            menuFuncionario.add(alterarFuncionario);
            menuFuncionario.add(new JSeparator());
            menuFuncionario.add(excluirFuncionario);
            menuBar.add(menuFuncionario);
            //JMenu menuApuracao = new JMenu("Contas");
            JMenuItem apuracaoTotal = new JMenuItem("Contas");
            menuVenda.add(new JSeparator());
            menuVenda.add(apuracaoTotal);
            //menuBar.add(menuApuracao);
            cadastrarFuncionario.addActionListener(this);
            alterarFuncionario.addActionListener(this);
            excluirFuncionario.addActionListener(this);
            listarFuncionario.addActionListener(this);
            apuracaoTotal.addActionListener(this);
        }
        
         //JMenu menuFinancas = new JMenu("Finanças");
        //menuBar.add(menuFinancas);
        
       
        linha1 = new JPanel();
        linha1.setLayout(null);
        //linha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        linha1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Última venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 22), new java.awt.Color(0, 0, 0)));
        linha1.setSize(400, 400);
        linha1.setLocation(30, 280);
        add(linha1);
       
        
        linha2 = new JPanel();
        linha2.setLayout(null);
        //linha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        linha2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acesso rápido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 22), new java.awt.Color(0, 0, 0)));
        linha2.setSize(400, 400);
        linha2.setLocation(450, 280);
        add(linha2);
        
        modelo = new DefaultListModel();
        lista = new JList(modelo);
        
        JScrollPane scrollPane = new JScrollPane();
        //scrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder()); 

        
        scrollPane.setLocation(60, 108);
        //scrollPane.add(lista);
        scrollPane.getViewport().add(lista);
        
        c= dao.retornaTodasNotas();
            modelo.clear();
            for(int i=0; i<c.size();i++){
                //System.out.println("indice: "+c.get(i).getId()+" - nome: "+c.get(i).getNome()+" - R$: ("+c.get(i).getValor()+")");
                modelo.add(i, "ID = ["+c.get(i).getIdNota()+"]  - "+c.get(i).getTitulo()+" -  "+c.get(i).getData()+"");
            }        
            
           lista.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
              c= dao.retornaTodasNotas();
              if(e.getClickCount() == 2){
                int indice = lista.locationToIndex(e.getPoint());

                JOptionPane.showMessageDialog(null, "Titulo: "+c.get(lista.getSelectedIndex()).getTitulo()+"\n\n"+c.get(lista.getSelectedIndex()).getTexto()+"\nData: "+c.get(lista.getSelectedIndex()).getData()+"");;
              }
            }
          });  
           
        linha3 = new JPanel();
        linha3.setLayout(new GridLayout(0, 2));
        //linha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        //linha3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 22), new java.awt.Color(0, 0, 0)));
        linha3.setSize(800, 330);
        linha3.setLocation(870, 292);
        linha3.add(scrollPane);
        add(linha3);
        
        logador = new JPanel();
        logador.setLayout(null);
        //linha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        logador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Login", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 14), new java.awt.Color(0, 0, 0)));
        logador.setSize(400, 200);
        logador.setLocation(870, 50);
        add(logador);
        
        
        nome = new JLabel(r[0]);
        nome.setSize(400, 100);
        nome.setLocation(50, 15);
        nome.setFont(new Font("Dialog", Font.PLAIN, 35));
        logador.add(nome);
        funcao= new JLabel();
        if(r[1].equals("0")){
            funcao.setText("(Funcionário)");
        }
        else{
            funcao.setText("(Adminstrador)");
        }
        
        funcao.setSize(400, 100);
        funcao.setLocation(50, 50);
        funcao.setFont(new Font("Dialog", Font.PLAIN, 25));
        logador.add(funcao);
        
        JButton des = new JButton("Deslogar");
        des.setSize(200, 50);
        des.setLocation(100, 130);
        des.setFont(new Font("Dialog", Font.PLAIN, 18));
        logador.add(des);
        
        data = new JLabel("Data: " + b.getData());
        data.setFont(new Font("Dialog", Font.PLAIN, 16));
        data.setSize(300, 60);
        data.setLocation(30, 30);
        linha1.add(data);
        
        id = new JLabel("ID Venda: " + b.getId_venda());
        id.setFont(new Font("Dialog", Font.PLAIN, 25));
        id.setSize(300, 60);
        id.setLocation(30, 80);
        linha1.add(id);
        
        produto = new JLabel(b.getProduto());
        produto.setFont(new Font("Dialog", Font.PLAIN, 25));
        produto.setSize(300, 60);
        produto.setLocation(30, 120);
        linha1.add(produto);
        
        qtd = new JLabel("Quantidade: " + b.getQtd());
        qtd.setFont(new Font("Dialog", Font.PLAIN, 25));
        qtd.setSize(300, 60);
        qtd.setLocation(30, 160);
        linha1.add(qtd);
        
        detaheUltimo = new JButton("Detalhes da venda");
        detaheUltimo.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        detaheUltimo.setSize(300, 40);
        detaheUltimo.setLocation(50, 330);
        linha1.add(detaheUltimo);
        
        
        gerarNota = new JButton("Gerar NF-e");
        gerarNota.setFont(new Font("Ubuntu", Font.PLAIN, 13));
        gerarNota.setSize(100, 30);
        gerarNota.setLocation(137, 240);
        linha1.add(gerarNota);
        
        vender = new JButton("Realizar venda");
        vender.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        vender.setSize(300, 60);
        vender.setLocation(50, 60);
        linha2.add(vender);
        
        cadCliente = new JButton("Cadastrar cliente");
        cadCliente.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        cadCliente.setSize(300, 60);
        cadCliente.setLocation(50, 140);
        linha2.add(cadCliente);
        
        cadProd = new JButton("Cadastrar produto");
        cadProd.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        cadProd.setSize(300, 60);
        cadProd.setLocation(50, 220);
        linha2.add(cadProd);
        
        lisVenda = new JButton("Listar vendas");
        lisVenda.setFont(new Font("Ubuntu", Font.PLAIN, 30));
        lisVenda.setSize(300, 60);
        lisVenda.setLocation(50, 300);
        linha2.add(lisVenda);
        
        
       
        adNota = new JButton("Criar nota");
        adNota.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        adNota.setSize(160, 40);
        adNota.setLocation(900, 635);
        add(adNota);
        
        excluiNota = new JButton("Excluir");
        excluiNota.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        excluiNota.setSize(160, 40);
        excluiNota.setLocation(1090, 635);
        add(excluiNota);
        
        
        rodape = new JLabel("Serralheria Sousa - Todos os direitos reservados. Desenvolvido por Diego Fernando");
        rodape.setFont(new Font("Dialog", Font.PLAIN, 16));
        rodape.setSize(780, 60);
        rodape.setLocation(256, 690);
        add(rodape);
        
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
        link.setFont(new Font("Dialog", Font.PLAIN, 16));
        link.setForeground(Color.blue);
        link.setSize(780, 60);
        link.setLocation(456, 665);
        add(link);     
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        System.out.println("Screen width = " + d.width);
        System.out.println("Screen height = " + d.height);
        
        
        
        
        menuBar.setSize(1366, 25);
        add(menuBar);
        
        if(d.width == 1366 && d.height == 768){
            
            setExtendedState(MAXIMIZED_BOTH);
            setVisible(true);
            System.out.println("Layout justo");
        }
        else{
            setSize(1366, 768);
            setVisible(true);
            System.out.println("Layout adaptável");
            setResizable(false);
        }
     
        
        setTitle("Serralheria Sousa - Início");
        
        
        
        setVisible(true);
        //setResizable();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        deslogar.addActionListener(this);
        sair.addActionListener(this);
        realizarVenda.addActionListener(this);
        cadastrarCliente.addActionListener(this);
        cadCliente.addActionListener(this);
        cadastrarProduto.addActionListener(this);
        alterarProduto.addActionListener(this);
        excluirProduto.addActionListener(this);
        alterarCliente.addActionListener(this);
        excluirCliente.addActionListener(this);
        vender.addActionListener(this);
        detaheUltimo.addActionListener(this);
        des.addActionListener(this);
        excluirVenda.addActionListener(this);
        consultarVenda.addActionListener(this);
        lisVenda.addActionListener(this);
        consultarCliente.addActionListener(this);
        consultarProduto.addActionListener(this);
        reporProduto.addActionListener(this);
        cadProd.addActionListener(this);
        adNota.addActionListener(this);
        excluiNota.addActionListener(this);
        gerarNota.addActionListener(this);
        ressucitaCliente.addActionListener(this);
    }
    

    
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
            DAO dao = new DAO();
                c= dao.retornaTodasNotas();
                modelo.clear();
                for(int i=0; i<c.size();i++){
                    //System.out.println("indice: "+c.get(i).getId()+" - nome: "+c.get(i).getNome()+" - R$: ("+c.get(i).getValor()+")");
                    modelo.add(i, "ID = ["+c.get(i).getIdNota()+"]  - "+c.get(i).getTitulo()+" -  "+c.get(i).getData()+"");
                }
                System.out.println("Lendo nota");
        switch (e.getActionCommand()) {
            case "Deslogar":
                System.out.println("Deslogou");
                TelaL.show();
                dispose();
                break;
            case "Sair":
                System.out.println("Saiu");
                dispose(); 
                break;
            case "Realizar venda":
                System.out.println("Chamou venda");
                String au = nome.getText();
                new Vender(this, au);
                break;
            case "Cadastrar cliente":
                System.out.println("Chamou cadastrar cliente");
                new CadastrarCliente(this);
                break;
            case "Contas":
                JOptionPane.showMessageDialog(null, "O acumulado total das vendas é:\n\nR$ " + dao.retornaSOMA());
                System.out.println("Chamou contas");
                break;
            case "Alterar cadastro de produto":
                System.out.println("Chamou alterar cadastro de produtos");
                if(r[1].equals("0")){
                    JOptionPane.showMessageDialog(null, "Você não um administrador");
                }else{
                    new AlterarProduto(this);
                }                
                break;
            case "Cadastrar produto":
                System.out.println("Chamou cadastrar produto");
                if(r[1].equals("0")){
                    JOptionPane.showMessageDialog(null, "Você não um administrador");
                }else{
                    new CadastrarProduto();
                }                
                break;
            case "Excluir cadastro de produto":
                System.out.println("Chamou exclusão de cadastro de produtos");
                if(r[1].equals("0")){
                    JOptionPane.showMessageDialog(null, "Você não um administrador");
                }else{
                    new ExcluiProduto();
                }                
                break;
            case "Alterar cadastro de cliente":
                System.out.println("Chamou alterar cadastro de cliente");
                new AlterarCliente(this);
                break;
            case "Inativar cadastro de cliente":
                System.out.println("chamou inativar cliente");
                new InativarCliente();
                break;
            case "Reativar cadastro de cliente":
                System.out.println("chamou reativar cliente");
                new ReativarCliente();
                break;
            case "Detalhes da venda":
                System.out.println("Tentando detalhar a última venda");
              
                atributosMenuPrincipal a = dao.atualiza(null);
                JOptionPane.showMessageDialog(null, "Detalhes da ultima venda:\n\n"
                        + "ID da venda: "+a.getId_venda()+""
                        + "\nProduto: "+a.getProduto()+""
                        + "\nQuantidade: "+a.getQtd()+""
                        + "\nValor da venda: "+a.getValor_venda()+""
                        + "\nCliente: "+a.getNome_cliente()+""
                        + "\nData e horário: "+a.getData()+""
                        + "\nUsuário do sistema: "+a.getNome_usuario()+"");
                break;
            case "Excluir venda":
                new ExcluiVenda(this);
                break;
            case "Listar vendas":
                new ListarVendas(null);
                break;
            case "Consultar venda":
                new ListarVendas(null);
                break;
            case "Consultar clientes":
                new ListarClientes();
                break;
            case "Consultar produto":
                new ListarProdutos();
                break;
            case "Repor estoque de produto":
                if(r[1].equals("0")){
                    JOptionPane.showMessageDialog(null, "Você não um administrador");
                }else{
                    new ReporEstoque();
                }
                
                break;
            case "Criar nota":
                new CriarNota(modelo);
                break;
            
                
            case "Gerar NF-e":
                System.out.println("Produzindo e exibindo PDF...");
                new GerarNotaFiscal();
                break;
                
            case "Excluir":
                System.out.println("Abriu excluir nota");
                new ExcluiNota(modelo);
                break;
                
            case "Cadastrar funcionário":
                System.out.println("Abriu cadastrar funcionário");
                new CriarFuncionario();
                break;
            
            case "Alterar cadastro de funcionário":
                System.out.println("Abriu alterar funcionário");
                new AlterarFuncionario();
                break;
            case "Excluir cadastro de funcionário":
                System.out.println("Abriu excluir funcionário");
                new ExcluiFuncionario();
                break;
            case "Listar funcionários":
                System.out.println("Abriu listar funcionários");
                new ListarFuncionarios();
                break;
        }
    }
    
}