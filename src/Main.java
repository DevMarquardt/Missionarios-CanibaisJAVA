import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Pessoa> ladoEsquerdo = new ArrayList<>();

    static ArrayList<Pessoa> ladoDireito = new ArrayList<>();

    static ArrayList<Pessoa> jangada = new ArrayList<>();

    static boolean lado = false;

    public static void main(String[] args) {
        System.out.println("""
                Bem vindo ao jogo dos canibais e missionários!
                Seu objetivo é atravessar todos os bonecos para o outro lado do rio, sem que os missionarios morram.
                Para isso, você pode atravessar no máximo 2 bonecos por vez, e não pode deixar que os canibais fiquem em maior número que os missionários em nenhum dos lados do rio.
                Vamos começar!
                """);
        adicionarBonecos();
        jogo();
    }

    public static void colocarNaJangada() {
        if (lado == false) {
            for (Pessoa pessoa : ladoDireito) {
                System.out.print(ladoDireito.indexOf(pessoa) + "º" + pessoa.getIcone() + "\n");
            }
            System.out.println("Quem deseja colocar na jangada?");
            int escolha = sc.nextInt();
            jangada.add(ladoDireito.get(escolha));
            ladoDireito.remove(escolha);
        } else {
            for (Pessoa pessoa : ladoEsquerdo) {
                System.out.print(ladoEsquerdo.indexOf(pessoa) + "º" + pessoa.getIcone() + "\n");
            }
            System.out.println("Quem deseja colocar na jangada?");
            int escolha = sc.nextInt();
            jangada.add(ladoEsquerdo.get(escolha));
            ladoEsquerdo.remove(escolha);
        }
    }

    public static void jogo() {
        int opcao = 0;
        do {
            verificarVitoria();
            if (!lado) {
                System.out.println("Atravessando para o lado direita...");
                System.out.println("""
                        [1] - Colocar na direita
                        [2] - Colocar na jangada
                        [3] - Voltar
                        """);
                opcao = sc.nextInt();
                switch (opcao) {
                    case 1:
                        System.out.println("Deseja colocar quem?");
                        for (Pessoa pessoa : jangada) {
                            System.out.print(jangada.indexOf(pessoa) + "º" + pessoa.getIcone() + "\n");
                        }
                        int escolha = sc.nextInt();
                        ladoDireito.add(jangada.get(escolha));
                        jangada.remove(escolha);
                        break;
                    case 2:
                        colocarNaJangada();
                        break;
                    case 3:
                        if (jangada.size() == 0) {
                            System.out.println("Você não atravessar sem nenhum condutor!");
                        }
                        System.out.println("Voltando...");
                        lado = true;
                        break;
                }
            } else if (lado) {
                System.out.println("Atravessando para o lado esquerdo...");
                System.out.println("""
                        [1] - Colocar na esquerda
                        [2] - Colocar na jangada
                        [3] - Voltar
                        """);
                opcao = sc.nextInt();
                switch (opcao) {
                    case 1:
                        System.out.println("Deseja atravessar quem?");
                        for (Pessoa pessoa : jangada) {
                            System.out.print(jangada.indexOf(pessoa) + "º" + pessoa.getIcone() + "\n");
                        }
                        int escolha = sc.nextInt();
                        ladoEsquerdo.add(jangada.get(escolha));
                        jangada.remove(escolha);
                        break;
                    case 2:
                        colocarNaJangada();
                        break;
                    case 3:
                        if (jangada.size() == 0) {
                            System.out.println("Você não atravessar sem nenhum condutor!");
                        }
                        System.out.println("Voltando...");
                        lado = false;
                        break;
                }
            }
        } while (true);
    }

    public static void adicionarBonecos() {
        Pessoa canibal1 = new Canibal();
        ladoDireito.add(canibal1);
        Pessoa canibal2 = new Canibal();
        ladoDireito.add(canibal2);
        Pessoa canibal3 = new Canibal();
        ladoDireito.add(canibal3);
        Pessoa missionario1 = new Missionario();
        ladoDireito.add(missionario1);
        Pessoa missionario2 = new Missionario();
        ladoDireito.add(missionario2);
        Pessoa missionario3 = new Missionario();
        ladoDireito.add(missionario3);
    }

    public static void verificarVitoria() {
        if (ladoEsquerdo.size() == 6) {
            System.out.println("Parabéns, você venceu!");
            System.exit(0);
        }
    }
}
