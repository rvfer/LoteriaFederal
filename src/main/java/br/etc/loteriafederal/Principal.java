package br.etc.loteriafederal;

import br.etc.loteriafederal.domain.*;

public class Principal {
    public static void main(String[] args) {
        int numeroDeBilhetes = 15;
        Sorteio sorteio = new Sorteio("Mega da Virada 2022");
        for (int i = 0; i < numeroDeBilhetes; i++)
            sorteio.adicionarBilhete(new Bilhete("CENTRAL", "SUZANO", "SP"));
        for (int i = 0; i < numeroDeBilhetes; i++) {
            Bilhete bilhete = sorteio.getBilhetes().get(i);
            System.out.println("Número do bilhete: " + bilhete.getId());
            System.out.println("Loteria: " +
                                bilhete.getNomeDaLoteria() + ", " +
                                bilhete.getCidadeDaLoteria() + "/" +
                                bilhete.getEstadoDaLoteria());
            System.out.println("Jogo: " + bilhete.getJogo().getAposta().toString() + "\n");
        }
        sorteio.sortearResultado();
        sorteio.verificarGanhadores();
        System.out.println("Prêmio do sorteio " + sorteio.getNome() +
                           ": R$" + String.format("%d", sorteio.getPremio() / 100) + "," +
                           String.format("%02d", sorteio.getPremio() % 100));
        System.out.println("Número de ganhadores do sorteio " + sorteio.getNome() + ": " +
                            sorteio.getGanhadores().size());
        System.out.println("Resultado sorteado: " + sorteio.getResultado().getAposta().toString());
    }
}
