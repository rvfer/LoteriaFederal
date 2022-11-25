package br.etc.loteriafederal.domain;

public class Bilhete {
    private static long contador = 0;
    private final long id;
    private String nomeDaLoteria;
    private String cidadeDaLoteria;
    private String estadoDaLoteria;
    private Jogo jogo = new Jogo();

    public Bilhete(String nomeDaLoteria, String cidadeDaLoteria, String estadoDaLoteria) {
        id = ++contador;
        this.nomeDaLoteria = nomeDaLoteria;
        this.cidadeDaLoteria = cidadeDaLoteria;
        this.estadoDaLoteria = estadoDaLoteria;
        jogo.fazerAposta();
    }

    public long getId() {
        return id;
    }

    public String getNomeDaLoteria() {
        return nomeDaLoteria;
    }

    public String getCidadeDaLoteria() {
        return cidadeDaLoteria;
    }

    public String getEstadoDaLoteria() {
        return estadoDaLoteria;
    }

    public Jogo getJogo() {
        return jogo;
    }
}
