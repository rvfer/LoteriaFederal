package br.etc.loteriafederal;

import br.etc.loteriafederal.domain.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Menu {
    ArrayList<Sorteio> sorteios = new ArrayList<>();
    final String menuPrincipal = "Digite a opção desejada:\n" +
            "1 - Fazer novo sorteio\n" +
            "2 - Ver sorteios anteriores\n" +
            "0 - Sair";
    final String menuSorteio = "Digite a opção desejada:\n" +
            "1 - Adicionar bilhete\n" +
            "2 - Ver bilhetes\n" +
            "3 - Sortear resultado\n" +
            "4 - Verificar ganhador(es)\n" +
            "5 - Verificar prêmio\n" +
            "0 - Voltar ao menu principal";
    Scanner input = new Scanner(System.in);

    public void executarMenuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println(menuPrincipal);
            opcao = Integer.parseInt(input.nextLine());
            switch (opcao) {
                case 1 -> realizarNovoSorteio();
                case 2 -> verSorteios();
                case 0 -> System.out.println("Programa finalizado.");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    public void executarMenuSorteio(Sorteio sorteio) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("Sorteio #" + sorteio.getId() + ": " + sorteio.getNome());
            System.out.println(menuSorteio);
            opcao = Integer.parseInt(input.nextLine());
            switch (opcao) {
                case 1 -> adicionarBilhete(sorteio);
                case 2 -> verBilhetes(sorteio);
                case 3 -> sortearResultado(sorteio);
                case 4 -> verificarGanhadores(sorteio);
                case 5 -> verificarPremio(sorteio);
                case 0 -> System.out.println("Programa finalizado.");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    public void realizarNovoSorteio() {
        System.out.println("Insira o nome do sorteio:");
        String nome = input.nextLine();
        sorteios.add(new Sorteio(nome));
        executarMenuSorteio(sorteios.get(sorteios.size() - 1));
    }

    public void verSorteios() {
        System.out.println("Sorteios realizados: " + sorteios.size());
        for (Sorteio sorteio : sorteios)
            System.out.println("Sorteio #" + sorteio.getId() + ": " + sorteio.getNome());
        if (sorteios.size() > 0) {
            System.out.println("Digite o número do sorteio:");
            int opcao = Integer.parseInt(input.nextLine());
            for (Sorteio sorteio : sorteios) {
                if (opcao == sorteio.getId()) {
                    executarMenuSorteio(sorteio);
                    break;
                }
            }
        }
    }

    public void adicionarBilhete(Sorteio sorteio) {
        System.out.println("Digite o nome da loteria: ");
        String loteria = input.nextLine();
        System.out.println("Digite a cidade da loteria: ");
        String cidade = input.nextLine();
        System.out.println("Digite o estado da loteria: ");
        String estado = input.nextLine();
        Bilhete bilhete = new Bilhete(loteria, cidade, estado);
        System.out.println("Digite a opção desejada:\n" +
                "1 - Jogo sorteado\n" +
                "2 - Jogo escolhido");
        int opcao = Integer.parseInt(input.nextLine());
        if (opcao == 1) {
            bilhete.getJogo().fazerAposta();
        } else {
            TreeSet<Integer> aposta = new TreeSet<>();
            System.out.println("Digite os números da aposta:");
            while (aposta.size() < 6)
                aposta.add(Integer.parseInt(input.nextLine()));
            bilhete.getJogo().setAposta(aposta);
        }
        sorteio.adicionarBilhete(bilhete);
    };

    public void verBilhetes(Sorteio sorteio) {
        for (Bilhete bilhete : sorteio.getBilhetes()) {
            System.out.println("Bilhete #" + bilhete.getId());
            System.out.println("Loteria: " + bilhete.getNomeDaLoteria());
            System.out.println("Local: " + bilhete.getCidadeDaLoteria() + ", " + bilhete.getEstadoDaLoteria());
            System.out.println("Jogo: " + bilhete.getJogo().getAposta().toString());
        }
    }

    public void sortearResultado(Sorteio sorteio) {
        sorteio.sortearResultado();
        System.out.println("Resultado: " + sorteio.getResultado().getAposta().toString());
    }

    public void verificarGanhadores(Sorteio sorteio) {
        sorteio.verificarGanhadores();
        System.out.println("Ganhador(es): " + sorteio.getGanhadores().size());
        for (Bilhete bilhete : sorteio.getGanhadores()) {
            System.out.println("Bilhete #" + bilhete.getId());
            System.out.println("Loteria: " + bilhete.getNomeDaLoteria());
            System.out.println("Local: " + bilhete.getCidadeDaLoteria() + ", " + bilhete.getEstadoDaLoteria());
            System.out.println("Jogo: " + bilhete.getJogo().getAposta().toString());
        }
    }

    public void verificarPremio(Sorteio sorteio) {
        System.out.println("Prêmio: R$" + String.format("%.2f", sorteio.getPremio()));
        if (sorteio.getGanhadores().size() == 0) {
            System.out.println("Prêmio acumulado.");
        } else {
            System.out.println("Cada ganhador recebeu R$" +
                                String.format("%.2f", sorteio.getPremio() / sorteio.getGanhadores().size()));
        }
    }
}
