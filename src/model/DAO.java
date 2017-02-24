/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import controller.Cliente;
import controller.Funcionario;
import controller.Nota;
import controller.Produto;
import controller.Venda;
import controller.atributosMenuPrincipal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class DAO {
    Connection connection;
    atributosMenuPrincipal s;
    
    public DAO() {
        this.connection = Conexao.getConnection();
        
    }
    
    public String[] validaSenha(controller.Senha senha){
        
        String cmd = "select nome, login, senha, administrador from usuario;";
        Statement stmt;
        ResultSet dados = null;
        boolean re= false;
        String []resultado = new String[2];
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                String aux = dados.getString(1);
                String loginUsuario = dados.getString(2);
                String senhaUsuario = dados.getString(3);
                String adm = dados.getString(4);
                if(loginUsuario.equals(senha.getLogin()) && senhaUsuario.equals(senha.getSenha())){
                    resultado[0] = aux;
                    resultado[1] = adm;
                    re = true;
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
        if(!re){
            return null;
        }
        return resultado;
    }
    
    public atributosMenuPrincipal atualiza(atributosMenuPrincipal a){
        String cmd = "select v.data, v.idvenda, p.nome, v.qtd_venda, v.valor, c.nome, u.nome from venda v, produto p, cliente c, usuario u\n" +
"where p.idproduto = v.produto_idproduto\n" +
"and c.idcliente = v.cliente_idcliente and u.idusuario = v.usuario_idusuario order by v.idvenda;";
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        String data = null, idvenda = null, nomep = null, qtd = null, nome_cliente = null;
        String valor_venda = null, nome_usuario = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                data = dados.getString(1);
                idvenda = dados.getString(2);
                nomep = dados.getString(3);
                qtd = dados.getString(4);                
                valor_venda = dados.getString(5);
                nome_cliente = dados.getString(6);
                nome_usuario = dados.getString(7);
            }
            s = new atributosMenuPrincipal(data, idvenda, nomep, qtd, nome_cliente, valor_venda, nome_usuario);
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return s;
    }
    
    public boolean cadastrarCliente(Cliente a){
        
        System.out.println("nome eh" + a.getNome());
        String cmd = "INSERT INTO serralheria_sousa.cliente (nome, telefone, cpf, sexo, atividade) VALUES (?,?,?,?,?);";
                
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
          
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getTelefone());
            stmt.setString(3, a.getCpf());
            stmt.setString(4, a.getSexo());
            stmt.setString(5, "1");
            
            stmt.execute();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    }
    
    public String idUltimoCadastrado(){
        String cmd = "select idcliente from cliente;";
        Statement stmt;
        ResultSet dados = null;
        String id = null;
        
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return id;
    }
    
    public boolean verificaClientePorIndice(String i){
        String cmd = "select idcliente from cliente where idcliente != 1;";
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                String aux = dados.getString(1);
                if(i.equals(aux)){
                    return true;
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return false;
    }
    
    public boolean verificaVendaPorIndice(String i){
        String cmd = "select idvenda from venda;";
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                String aux = dados.getString(1);
                if(i.equals(aux)){
                    return true;
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return false;
    }
    
    public Cliente retornaClienteporIndice(String Indice){
        String cmd = "select * from cliente where idcliente = "+Indice+" and idcliente != 1";
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        Cliente cliente = null;
        String id = null, nome = null, telefone = null, cpf = null, sexo = null, atividade = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
                nome = dados.getString(2);
                telefone = dados.getString(3);
                cpf = dados.getString(4);
                sexo = dados.getString(5);
                atividade = dados.getString(6);
            }
           cliente = new Cliente(id, nome, telefone, cpf, sexo, atividade);
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return cliente;
    }
    
    public boolean inativaCliente(String indice){
                
        String cmd = "update cliente set atividade = 0 where idcliente = "+indice+" and idcliente != 1;";
                
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
                    
            stmt.executeUpdate();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    }
    
        public boolean reativaCliente(String indice){
                
        String cmd = "update cliente set atividade = 1 where idcliente = "+indice+" and idcliente != 1;";
                
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
                    
            stmt.executeUpdate();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    }
    
    public boolean atualizaCliente(String id, String nome, String telefone, String cpf, String sexo){
        String cmd = "update cliente set nome='"+nome+"', telefone='"+telefone+"', cpf='"+cpf+"', sexo='"+sexo+"' where idcliente = "+id+" and idcliente != 1;";
        
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
                    
            stmt.executeUpdate();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    
    }
    
    public boolean cadastrarProduto(Produto a){
        
        String cmd = "INSERT INTO serralheria_sousa.produto (nome, valor, qtd_estoque) VALUES (?,?,?);";
                
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
          
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getValor());
            stmt.setString(3, a.getQtd_estoque());
            
            stmt.execute();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    }
    
        public String idUltimoPCadastrado(){
        String cmd = "select idproduto from produto;";
        Statement stmt;
        ResultSet dados = null;
        String id = null;
        
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return id;
    }
        
    public boolean verificaProdutoPorIndice(String i){
        String cmd = "select idproduto from produto;";
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                String aux = dados.getString(1);
                if(i.equals(aux)){
                    return true;
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return false;
    }
    
    public Venda retornaVendaPorIndice(String Indice){
        String cmd = "select * from venda where idvenda = "+Indice+"";
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        Venda venda = null;
        String id_venda = null,qtd_venda = null, valor = null, id_cliente = null,id_produto = null,id_usuario = null,datahora = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id_venda = dados.getString(1);
                qtd_venda = dados.getString(2);
                valor = dados.getString(3);
                id_cliente = dados.getString(4);
                id_produto = dados.getString(5);
                id_usuario = dados.getString(6);
                datahora = dados.getString(7);
            }
           venda = new Venda(id_venda, qtd_venda, valor, id_cliente, id_produto, id_usuario, datahora);
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return venda;
    }
    
    
    public Produto retornaProdutoporIndice(String Indice){
        String cmd = "select * from produto where idproduto = "+Indice+"";
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        Produto produto = null;
        String id = null, nome = null, valor = null, qtd_estoque = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
                nome = dados.getString(2);
                valor = dados.getString(3);
                qtd_estoque = dados.getString(4);
            }
           produto = new Produto(id, nome, valor, qtd_estoque);
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return produto;
    }
    
    public boolean excluirProduto(String indice){
        String cmd = "DELETE FROM serralheria_sousa.produto WHERE idproduto='"+indice+"';";
                
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
            
            stmt.execute();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    }
    
    public boolean atualizaProduto(String id, String nome, String valor){
        String cmd = "update produto set nome='"+nome+"', valor='"+valor+"' where idproduto = "+id+";";
        
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
                    
            stmt.executeUpdate();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    
    }
    
    public ArrayList<Cliente> retornaTodosOsClientesComLike(String like){
        String cmd = "select * from cliente where nome like '%"+like+"%' and idcliente != '1' and atividade = '1';";
        ArrayList<Cliente> results = new ArrayList<Cliente>();
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        Cliente cliente = null;
        String id = null, nome = null, telefone = null, cpf = null, sexo = null, atividade = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
                nome = dados.getString(2);
                telefone = dados.getString(3);
                cpf = dados.getString(4);
                sexo = dados.getString(5);
                atividade = dados.getString(6);
                cliente = new Cliente(id, nome, telefone, cpf, sexo, atividade);
                results.add(cliente);
                
            }
           
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return results;
    }
    
    public ArrayList<Produto> retornaTodosOsProdutosComLike(String like){
        String cmd = "select * from produto where nome like '%"+like+"%';";
        ArrayList<Produto> results = new ArrayList<Produto>();
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        Produto produto = null;
        String id = null, nome = null, valor = null, qtd = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
                nome = dados.getString(2);
                valor = dados.getString(3);
                qtd = dados.getString(4);
                produto = new Produto(id, nome, valor, qtd);
                results.add(produto);
                
            }
           
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return results;
    }
    
    
    public ArrayList<atributosMenuPrincipal> retornaTodosAsVendas(){
        String cmd = "select v.data, v.idvenda, p.nome, v.qtd_venda, v.valor, c.nome, u.nome from venda v, produto p, cliente c, usuario u\n" +
"where p.idproduto = v.produto_idproduto\n" +
"and c.idcliente = v.cliente_idcliente and u.idusuario = v.usuario_idusuario order by v.idvenda desc;";
        ArrayList<atributosMenuPrincipal> results = new ArrayList<atributosMenuPrincipal>();
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        atributosMenuPrincipal v = null;
         String data = null, idvenda = null, nomep = null, qtd = null, nome_cliente = null;
        String valor_venda = null, nome_usuario = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                data = dados.getString(1);
                idvenda = dados.getString(2);
                nomep = dados.getString(3);
                qtd = dados.getString(4);                
                valor_venda = dados.getString(5);
                nome_cliente = dados.getString(6);
                nome_usuario = dados.getString(7);
                v = new atributosMenuPrincipal(data, idvenda, nomep, qtd, nome_cliente, valor_venda, nome_usuario);
                results.add(v);
                
            }
           
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return results;
    }
    
    public ArrayList<Cliente> retornaTodosOsClientes(){
        String cmd = "select * from cliente order by idcliente desc;";
        ArrayList<Cliente> results = new ArrayList<Cliente>();
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        Cliente cliente = null;
        String id = null, nome = null, telefone = null, cpf = null, sexo = null, atividade = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
                nome = dados.getString(2);
                telefone = dados.getString(3);
                cpf = dados.getString(4);
                sexo = dados.getString(5);
                atividade = dados.getString(6);
                cliente = new Cliente(id, nome, telefone, cpf, sexo, atividade);
                results.add(cliente);
                
            }
           
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return results;
    }
    
    public ArrayList<Produto> retornaTodosOsProdutos(){
        String cmd = "select * from produto order by idproduto desc;";
        ArrayList<Produto> results = new ArrayList<Produto>();
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        Produto produto = null;
        String id = null, nome = null, valor = null, qtd_estoque = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
                nome = dados.getString(2);
                valor = dados.getString(3);
                qtd_estoque = dados.getString(4);
                produto = new Produto(id, nome, valor, qtd_estoque);
                results.add(produto);
                
            }
           
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return results;
    }
    
    
    public ArrayList<atributosMenuPrincipal> retornaVendasDeUmCliente(String indice){
        String cmd = "select v.data, v.idvenda, p.nome, v.qtd_venda, v.valor,c.nome, u.nome from venda v, produto p, cliente c, usuario u\n" +
"where p.idproduto = v.produto_idproduto\n" +
"and c.idcliente = v.cliente_idcliente and u.idusuario = v.usuario_idusuario and c.idcliente = "+indice+"";
        ArrayList<atributosMenuPrincipal> results = new ArrayList<atributosMenuPrincipal>();
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        String data = null, idvenda = null, nomep = null, qtd = null, nome_cliente = null;
        String valor_venda = null, nome_usuario = null;
        atributosMenuPrincipal at = null;
        int counter = 0;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                data = dados.getString(1);
                idvenda = dados.getString(2);
                nomep = dados.getString(3);
                qtd = dados.getString(4);                
                valor_venda = dados.getString(5);
                nome_cliente = dados.getString(6);
                nome_usuario = dados.getString(7);
                at = new atributosMenuPrincipal(data, idvenda, nomep, qtd, nome_cliente, valor_venda, nome_usuario);
                results.add(at);
                counter++;
            }
            
            System.out.println("Contador: "+counter);
            
            }catch(SQLException ex){
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
                return null;
            }
        
            if(counter == 0){
                return null;
            }
            return results;
    }
    
    public boolean vender(Venda v){
        String cmd = "INSERT INTO serralheria_sousa.venda (qtd_venda, valor, cliente_idcliente, produto_idproduto, usuario_idusuario, data) VALUES (?, ?, ?, ?, ?, ?);";
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
          
            stmt.setString(1, v.getQtd_venda());
            stmt.setString(2, v.getValor());
            stmt.setString(3, v.getId_cliente());
            stmt.setString(4, v.getId_produto());
            stmt.setString(5, v.getId_usuario());
            stmt.setString(6, v.getDatahora());
            
            
            stmt.execute();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    
    }
    
    public boolean excluirVenda(String indice){
        String cmd = "DELETE FROM serralheria_sousa.venda WHERE idvenda='"+indice+"';";
                
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
            
            stmt.execute();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    }
    
    public String retornaIndiceDeSenha(String s){
        String cmd = "select idusuario from usuario where nome = '"+s+"';";
         Statement stmt;
        ResultSet dados = null;
        
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                String aux = dados.getString(1);                
                return aux;
                
            }
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return null;
    }
    
    public String retornaValorDeProdutoPeloIndice(String indice){
        String cmd = "select valor from produto where idproduto = "+indice+"";
        
        Statement stmt;
        ResultSet dados = null;
        String id = null ;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
                
            }
           
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
        return id;
    }
    
    public String verificaEstoqueProduto(String indice){
        String cmd = "select qtd_estoque from produto where idproduto = "+indice+";";
         Statement stmt;
        ResultSet dados = null;
        
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                String aux = dados.getString(1);                
                return aux;
                
            }
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return null;
    }
    
    public boolean atualizaEstoqueProduto(String indice, String novoValor){
        String cmd = "UPDATE serralheria_sousa.produto SET qtd_estoque ='"+novoValor+"' WHERE idproduto='"+indice+"';";
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
                    
            stmt.executeUpdate();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    
    }
    
    public boolean insereNota(Nota nova){
        String cmd = "INSERT INTO serralheria_sousa.nota (titulo, texto, data) VALUES (?,?,?);";
                
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
          
            stmt.setString(1, nova.getTitulo());
            stmt.setString(2, nova.getTexto());
            stmt.setString(3, nova.getData());
            
            stmt.execute();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    }
    
   public ArrayList<Nota> retornaTodasNotas(){
        String cmd = "select * from nota;";
        
        ArrayList<Nota> results = new ArrayList<Nota>();
        Statement stmt;
        ResultSet dados = null;
        Nota nota;
        //String resultado = null;
        String id= null, titulo = null, texto = null, data = null;
      
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
                titulo = dados.getString(2);
                texto = dados.getString(3);
                data = dados.getString(4);
                
                nota = new Nota(id,titulo, texto, data);
                results.add(nota);
                
            }
            
            
            
            }catch(SQLException ex){
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
                return null;
            }

            return results;
    }
   
   public boolean deletaNota(String indice){
       String cmd = "DELETE FROM nota WHERE idnota='"+indice+"';";
                
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
            
            stmt.execute();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
   }
   
   public boolean verificaNota(String indice){
       String cmd = "select * from nota;";
        
        
        Statement stmt;
        ResultSet dados = null;
        
      
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                
                if(indice.equals(dados.getString(1))){
                    return true;
                }
            }

        }
        catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    return false;
   }
   
   public boolean cadastrarFuncionario(Funcionario a){
        
        String cmd = "INSERT INTO serralheria_sousa.usuario (nome, login, senha, administrador) VALUES (?,?,?,?);";
                
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
          
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getLogin());
            stmt.setString(3, a.getSenha());
            stmt.setString(4, "0");
            
            stmt.execute();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    }
   
   public boolean verificaFuncionarioPorIndice(String i){
        String cmd = "select idusuario from usuario where administrador=0;";
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                String aux = dados.getString(1);
                if(i.equals(aux)){
                    return true;
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return false;
    }
   
   public Funcionario retornaFuncionarioporIndice(String Indice){
        String cmd = "select * from usuario where idusuario = "+Indice+" and administrador=0";
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        Funcionario funcionario = null;
        String id = null, nome = null, usuario = null, senha = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
                nome = dados.getString(2);
                usuario = dados.getString(3);
                senha = dados.getString(4);
            }
           funcionario = new Funcionario(null, nome, usuario, senha);
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return funcionario;
    }
   
    public boolean atualizaFuncionario(String id, String nome, String username, String password){
        String cmd = "update usuario set nome='"+nome+"',login ='"+username+"', senha='"+password+"' where idusuario = "+id+";";
        
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
                    
            stmt.executeUpdate();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    
    }
    public boolean excluirFuncionario(String indice){
        String cmd = "DELETE FROM serralheria_sousa.usuario WHERE idusuario='"+indice+"' and administrador='0';";
                
        try{
                     
            PreparedStatement stmt = connection.prepareStatement(cmd);
            
            stmt.execute();
            stmt.close();
            
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
        return true;
    }
    
    public ArrayList<Funcionario> retornaTodosOsFuncionarios(){
        String cmd = "select idusuario, nome, login from usuario where administrador='0' order by idusuario desc;";
        ArrayList<Funcionario> results = new ArrayList<Funcionario>();
        Statement stmt;
        ResultSet dados = null;
        //String resultado = null;
        Funcionario funcionario = null;
        String id = null, nome = null, username = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                id = dados.getString(1);
                nome = dados.getString(2);
                username = dados.getString(3);
                funcionario = new Funcionario(id, nome, username,null);
                results.add(funcionario);
                
            }
           
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return results;
    }
    
    public String retornaSOMA(){
        String cmd = "select SUM(valor) from venda;";
        //ArrayList<atributosMenuPrincipal> results = new ArrayList<atributosMenuPrincipal>();
        Statement stmt;
        ResultSet dados = null;
        String valor = null;
        try{
            stmt = connection.prepareStatement(cmd);
            dados = stmt.executeQuery(cmd);
            
            while(dados.next()){
                valor = dados.getString(1);
                
            }
           
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return valor;
    }
    
}