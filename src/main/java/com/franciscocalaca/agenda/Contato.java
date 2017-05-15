/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.franciscocalaca.agenda;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class Contato {
    private Integer id;
    
    private String nome;
    
    private List<Email> emails = new ArrayList<>();

    private List<Endereco> enderecos = new ArrayList<>();

    private List<Telefone> telefones = new ArrayList<>();

    public Contato(String nome) {
        this.nome = nome;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(String.format("%d - %s%n", id, nome));
        for(Email e : emails){
            sb.append("   ");
            sb.append(e.getValor());
            sb.append(", ");
        }
        for(Telefone e : telefones){
            sb.append("   ");
            sb.append(e.getNumero());
            sb.append(", ");
        }
        for(Endereco e : enderecos){
            sb.append(String.format("   %s - %s%n", e.getRua(), e.getCidade()));
        }
        return sb.toString();
    }
    
    
}
