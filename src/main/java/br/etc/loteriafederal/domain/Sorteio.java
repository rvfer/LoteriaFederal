package br.etc.loteriafederal.domain;

import java.util.ArrayList;

public class Sorteio {
    private static long contador = 0;
    private final long id;
    private String nome;
    private ArrayList<Bilhete> bilhetes = new ArrayList<Bilhete>();
    private long premio = 0;
    private Jogo resultado = new Jogo();
    private ArrayList<Bilhete> ganhadores = new ArrayList<Bilhete>();

    public Sorteio(String nome) {
        id = ++contador;
        this.nome = nome;
    }

    public void adicionarBilhete(Bilhete bilhete) {
        bilhetes.add(bilhete);
        premio += 600;
    }

    public void sortearResultado() {
        resultado.fazerAposta();
    }

    public void verificarGanhadores() {
        for (Bilhete bilhete : bilhetes)
            if (bilhete.getJogo().getAposta().equals(resultado.getAposta()))
                ganhadores.add(bilhete);
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public long getPremio() {
        return premio;
    }

    public Jogo getResultado() {
        return resultado;
    }

    public ArrayList<Bilhete> getGanhadores() {
        return ganhadores;
    }
}
