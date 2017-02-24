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
public class Venda {
    private String id_venda;
    private String qtd_venda;
    private String valor;
    private String id_cliente;
    private String id_produto;
    private String id_usuario;
    private String datahora;
    
    public Venda(){}

    public Venda(String id_venda, String qtd_venda, String valor, String id_cliente, String id_produto, String id_usuario, String datahora) {
        this.id_venda = id_venda;
        this.qtd_venda = qtd_venda;
        this.valor = valor;
        this.id_cliente = id_cliente;
        this.id_produto = id_produto;
        this.id_usuario = id_usuario;
        this.datahora = datahora;
    }

    public String getDatahora() {
        return datahora;
    }

    public void setDatahora(String datahora) {
        this.datahora = datahora;
    }

    public String getId_venda() {
        return id_venda;
    }

    public void setId_venda(String id_venda) {
        this.id_venda = id_venda;
    }

    public String getQtd_venda() {
        return qtd_venda;
    }

    public void setQtd_venda(String qtd_venda) {
        this.qtd_venda = qtd_venda;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_produto() {
        return id_produto;
    }

    public void setId_produto(String id_produto) {
        this.id_produto = id_produto;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
}
