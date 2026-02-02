package sistema.cli;

public class Menu {

    private static final String RESET = "\u001B[0m";
    private static final String AZUL = "\u001B[34m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String BRANCO = "\u001B[37m";

    public static void mostrarMenu() {
        System.out.println(AZUL + "============================================" + RESET);
        System.out.println(AZUL + "        SISTEMA DE CADASTRO DE PETS          " + RESET);
        System.out.println(AZUL + "============================================" + RESET);
        System.out.println();

        System.out.println(VERDE + " 1 - Cadastrar um novo pet" + RESET);
        System.out.println(VERDE + " 2 - Alterar os dados do pet cadastrado" + RESET);
        System.out.println(VERDE + " 3 - Deletar um pet cadastrado" + RESET);
        System.out.println(VERDE + " 4 - Listar todos os pets cadastrados" + RESET);
        System.out.println(VERDE + " 5 - Listar pets por critério" + RESET);

        System.out.println(BRANCO + "     a) Idade" + RESET);
        System.out.println(BRANCO + "     b) Nome" + RESET);
        System.out.println(BRANCO + "     c) Raça" + RESET);

        System.out.println(VERDE + " 6 - Sair" + RESET);
        System.out.println();

        System.out.println(AZUL + "============================================" + RESET);
        System.out.print(AMARELO + "Digite a opção desejada: " + RESET);

    }

    public static void mostraMenuBusca() {
        System.out.println("\nPrimeiro, escolha o TIPO de animal:");
        System.out.println("1 - Cachorro");
        System.out.println("2 - Gato");
        System.out.println("3 - Passaro");
    }

    public static void mostraMenuCriterios() {
        System.out.println("\nAgora, escolha ATÉ 2 critérios de busca:");
        System.out.println("1 - Nome ou sobrenome");
        System.out.println("2 - Sexo");
        System.out.println("3 - Idade");
        System.out.println("4 - Peso");
        System.out.println("5 - Raça");
        System.out.println("6 - Endereço");
    }

}
