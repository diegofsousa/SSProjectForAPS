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
public class Produto {
    private String id;
    private String nome;
    private String valor;
    private String qtd_estoque;

    public Produto(String id, String nome, String valor, String qtd_estoque) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.qtd_estoque = qtd_estoque;
    }

    public String getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(String qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
