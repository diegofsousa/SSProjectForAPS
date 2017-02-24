/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

/**
 *
 * @author diego
 */
public class atributosMenuPrincipal {
    private String data;
    private String Id_venda;
    private String produto;
    private String qtd;
    private String nome_cliente;
    private String valor_venda;
    private String nome_usuario;

    public atributosMenuPrincipal(String data, String Id_venda, String produto, String qtd, String nome_cliente, String valor_venda, String nome_usuario) {
        this.data = data;
        this.Id_venda = Id_venda;
        this.produto = produto;
        this.qtd = qtd;
        this.nome_cliente = nome_cliente;
        this.valor_venda = valor_venda;
        this.nome_usuario = nome_usuario;
    }
       
   
    
    public atributosMenuPrincipal(){}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId_venda() {
        return Id_venda;
    }

    public void setId_venda(String Id_venda) {
        this.Id_venda = Id_venda;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(String valor_venda) {
        this.valor_venda = valor_venda;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }


    
    
}
