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
public class Nota {
    private String idNota;
    private String titulo;
    private String texto;
    private String data;

    public Nota(String idNota, String titulo, String texto, String data) {
        this.idNota = idNota;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
    }

    public Nota() {
    }
    
    

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdNota() {
        return idNota;
    }

    public void setIdNota(String idNota) {
        this.idNota = idNota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
}
