package sistema.cli;

import java.util.Scanner;
import sistema.services.PetService;


public class CliController {

    public void iniciaSistema() {
        Scanner scanner = new Scanner(System.in);
        Menu.mostrarMenu();
        int opcao = scanner.nextInt();

        do {

        } while (opcao != 6);
    }

    public void escolheOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Vc vai cadastrar seu pet agora");
                break;

            case 2:
                System.out.println("vc vai alterar os dados de um pet já cadastrado");
                break;

            case 3:
                System.out.println("vc vai deletar um pet já cadastrado");
                break;

            case 4:
                System.out.println("vc vai listar os pets já cadastrados");
                break;

            case 5:
                System.out.println("vc vai listar um pet de acordo com um criterio");
                break;

            case 6:
                System.out.println("vc esta saindo do sistema, adeus...");
        }
    }


}
