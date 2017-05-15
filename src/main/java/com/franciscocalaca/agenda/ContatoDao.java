/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.franciscocalaca.agenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 */
public class ContatoDao {
    
    private static List<Contato> contatos = new ArrayList<>();
    
    private static String arqContato = "contato.csv";
    private static String arqEmail = "email.csv";
    private static String arqTelefone = "telefone.csv";
    private static String arqEndereco = "endereco.csv";
    
    static{
        contatos = listar();
    }
    
    public static void incluirContato(Contato contato) throws Exception{
        int id = IdManager.getProximoId("contato");
        contato.setId(id);
        contatos.add(contato);
        save();
    }

    private static void save() throws Exception{
        FileOutputStream fosContato = new FileOutputStream(arqContato);
        FileOutputStream fosEmail = new FileOutputStream(arqEmail);
        FileOutputStream fosTelefone = new FileOutputStream(arqTelefone);
        FileOutputStream fosEndereco = new FileOutputStream(arqEndereco);
        
        for(Contato c : contatos){
            fosContato.write(String.format("%d;%s%n", c.getId(), c.getNome()).getBytes());
            for(Email e : c.getEmails()){
                fosEmail.write(String.format("%d;%s%n", c.getId(), e.getValor()).getBytes());
            }
            for(Telefone e : c.getTelefones()){
                fosTelefone.write(String.format("%d;%s%n", c.getId(), e.getNumero()).getBytes());
            }
            for(Endereco e : c.getEnderecos()){
                fosEndereco.write(String.format("%d;%s;%s%n", c.getId(), e.getRua(), e.getCidade()).getBytes());
            }
        }
        
        fosContato.close();
        fosEndereco.close();
        fosTelefone.close();
        fosEmail.close();
        
    }
    
    private static String[] lerLinha(String linha){
        return linha.split(";");
    }
    
    private static List<String[]> lerArquivo(String arquivo){
        List<String[]> result = new ArrayList<>();
        File file = new File(arquivo);
        if(file.exists()){
            try(FileInputStream fis = new FileInputStream(file)) {
                Scanner scan = new Scanner(fis);
                while(scan.hasNext()){
                    result.add(lerLinha(scan.nextLine()));
                }
            } catch (Exception ex) {
                Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    private static Map<Integer, List<String[]>> mapear(List<String[]> dados){
        Map<Integer, List<String[]>> result = new HashMap<>();
        for(String [] linha : dados){
            Integer key = Integer.parseInt(linha[0]);
            List<String[]> grupo = result.get(key);
            if(grupo == null){
                grupo = new ArrayList<>();
                result.put(key, grupo);
            }
            grupo.add(linha);
        }
        return result;
    }
    
    public static List<Email> mapearEmail(List<String[]> dados){
        List<Email> result = new ArrayList<>();
        for(String[] linha : dados){
            Email email = new Email(linha[1]);
            result.add(email);
        }
        return result;
    }
    
    public static List<Telefone> mapearTelefone(List<String[]> dados){
        List<Telefone> result = new ArrayList<>();
        for(String[] linha : dados){
            Telefone telefone = new Telefone(linha[1]);
            result.add(telefone);
        }
        return result;
    }
    
    public static List<Endereco> mapearEndereco(List<String[]> dados){
        List<Endereco> result = new ArrayList<>();
        for(String[] linha : dados){
            Endereco endereco = new Endereco(linha[1], linha[2]);
            result.add(endereco);
        }
        return result;
    }
    
    public static List<Contato> listar(){
        List<String[]> conteudoContato = lerArquivo(arqContato);
        List<String[]> conteudoEmail = lerArquivo(arqEmail);
        List<String[]> conteudoTelefone = lerArquivo(arqTelefone);
        List<String[]> conteudoEndereco = lerArquivo(arqEndereco);
        
        Map<Integer, List<String[]>> mapaEmail = mapear(conteudoEmail);
        Map<Integer, List<String[]>> mapaTelefone = mapear(conteudoTelefone);
        Map<Integer, List<String[]>> mapaEndereco = mapear(conteudoEndereco);
        
        List<Contato> result = new ArrayList<>();
        for(String [] contStr : conteudoContato){
            Contato contato = new Contato(contStr[1]);
            contato.setId(Integer.parseInt(contStr[0]));
            contato.getEmails().addAll(mapearEmail(mapaEmail.get(contato.getId())));
            contato.getTelefones().addAll(mapearTelefone(mapaTelefone.get(contato.getId())));
            contato.getEnderecos().addAll(mapearEndereco(mapaEndereco.get(contato.getId())));
            result.add(contato);
        }
        return result;
    }
    
}
