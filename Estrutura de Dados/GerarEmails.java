/*
programa que gera emails a partir de uma lista de nomes no arquivo nomes.txt
*/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GerarEmails {
    static ArrayList<Pessoa> listaEmails = new ArrayList<>();

    public static void main(String[] args) throws IOException{

        Path path = Paths.get("nomes.txt");
        List<String> linhasArquivo = Files.readAllLines(path);
        
        for (String nome : linhasArquivo) {
            if (nome.contains(" ")){
                listaEmails.add(new Pessoa(nome));
            }
        }

        int max = 0;

        for (Pessoa e : listaEmails) {
            if (max < e.nome.length()) {
                max = e.nome.length();
            }
        }

        for (Pessoa e : listaEmails) {
            System.err.printf("%-"+ max + "s: %s\n",e.nome, e.email);
        }
    }
}

class Pessoa {
    public String nome;
    public String email;
    public Pessoa(String n){
        this.nome = n;
        this.email = this.criaEmail(n);
    }

    public String criaEmail(String n){
        List<String> nomeCompleto = Arrays.asList(n.toLowerCase().split(" "));
        String nome = nomeCompleto.get(0);
        List<String> sobrenome = nomeCompleto.subList(1,nomeCompleto.size());
        String email = "";
        boolean valido = false;
        int c = 0;
        do {
            for (String e : sobrenome) {
                email = nome + "." + e + (c == 0 ? "" : c) + "@computador.com";
                valido = validaEmail(email);
            }
            c++;
        } while(!valido);
        return email;
    }

    public boolean validaEmail(String email){
        for (Pessoa e : GerarEmails.listaEmails) {
            if (email.equals(e.email)) {
                return false;
            }
        }
        return true;
    }
}