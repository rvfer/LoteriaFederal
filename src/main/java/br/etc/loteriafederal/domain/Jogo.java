package br.etc.loteriafederal.domain;

import java.util.TreeSet;
import java.util.Random;

public class Jogo {
    private TreeSet<Integer> aposta = new TreeSet<Integer>();

    public Jogo() {
    }

    public void fazerAposta() {
        Random num = new Random();
        while (aposta.size() < 6)
            aposta.add(num.nextInt(60) + 1);
    }

    public TreeSet<Integer> getAposta() {
        return aposta;
    }

    public void setAposta(TreeSet<Integer> aposta) {
        this.aposta = aposta;
    }
}
