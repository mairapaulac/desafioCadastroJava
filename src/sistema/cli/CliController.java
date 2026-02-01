package sistema.cli;

import java.util.InputMismatchException;
import java.util.Scanner;
import sistema.services.PetService;


public class CliController {

    private final PetService petService = new PetService();

    public void iniciaSistema() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            Menu.mostrarMenu();
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("O sistema não aceita letras ou caracteres especiais.");
                scanner.nextLine();
                opcao = -1;
                continue;
            }
            escolheOpcao(opcao);
        } while (opcao != 6);
    }

    private void escolheOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Vc vai cadastrar seu pet agora, responda as perguntas: ");
                petService.cadastraPet();
                break;

            case 2:
                System.out.println("vc vai alterar os dados de um pet já cadastrado");
                petService.alteraPet();
                break;

            case 3:
                System.out.println("vc vai deletar um pet já cadastrado");
                petService.deletaPet();
                break;

            case 4:
                System.out.println("vc vai listar os pets já cadastrados");
                petService.buscaPet();
                break;

            case 5:
                System.out.println("vc vai listar um pet de acordo com um criterio");
//                petService.buscaPetPorCriterio();
                break;

            case 6:
                System.out.println("vc esta saindo do sistema, adeus...");

            default:
                System.out.println("Opção inválida");
        }
    }
}
