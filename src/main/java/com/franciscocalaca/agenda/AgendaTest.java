/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.franciscocalaca.agenda;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 */
public class AgendaTest {

    public static void main(String[] args) {
        Contato c1 = new Contato("Maria da Silva");
        c1.getEmails().add(new Email("maria@gmail.com"));
        c1.getEmails().add(new Email("maria@hotmail.com"));
        c1.getEmails().add(new Email("maria@badoo.com"));
        c1.getEnderecos().add(new Endereco("Rua 1", "Goiania"));
        c1.getEnderecos().add(new Endereco("Rua 2", "Goiania"));
        c1.getTelefones().add(new Telefone("3232-1212"));
        
        Contato c2 = new Contato("Jose da Silva");
        c2.getEmails().add(new Email("jose@gmail.com"));
        c2.getEmails().add(new Email("jose@hotmail.com"));
        c2.getEmails().add(new Email("jose@badoo.com"));
        c2.getEnderecos().add(new Endereco("Rua 3", "Goiania"));
        c2.getEnderecos().add(new Endereco("Rua 4", "Goiania"));
        c2.getTelefones().add(new Telefone("3233-1212"));

        Contato c3 = new Contato("Carlos da Silva");
        c3.getEmails().add(new Email("carlos@gmail.com"));
        c3.getEmails().add(new Email("carlim@hotmail.com"));
        c3.getEmails().add(new Email("carlimdaesquina@badoo.com"));
        c3.getEnderecos().add(new Endereco("Rua 5", "Goiania"));
        c3.getEnderecos().add(new Endereco("Rua 6", "Goiania"));
        c3.getTelefones().add(new Telefone("3239-1212"));
        
        try {
            ContatoDao.incluirContato(c1);
            ContatoDao.incluirContato(c2);
            ContatoDao.incluirContato(c3);
        } catch (Exception ex) {
            Logger.getLogger(AgendaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Contato c : ContatoDao.listar()){
            System.out.println(c);
        }
    }
}
