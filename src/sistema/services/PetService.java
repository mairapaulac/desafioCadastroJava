package sistema.services;

import sistema.cli.Menu;
import sistema.cli.formReader;
import sistema.domain.Address;
import sistema.domain.enums.PetSex;
import sistema.domain.enums.PetType;
import sistema.storage.FileStorage;
import sistema.util.Constantes;
import sistema.util.StringNormalizer;
import sistema.util.exceptions.*;
import sistema.domain.Pet;

//import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetService {

    List<Pet> petsCadastrados = new ArrayList<>();

    public void cadastraPet() {
        Scanner sc = new Scanner(System.in);
        Pet pet = new Pet();

        formReader.showForm();

        // 1 Nome
        String nomeCompleto = sc.nextLine().trim();

        if (!nomeCompleto.matches("[A-Za-zÀ-ÿ\\s]+")) {
            throw new InvalidNameException("O nome não pode conter caracteres especiais");
        }

        String[] partesNome = nomeCompleto.split("\\s+", 2);

        if (partesNome.length < 2) {
            throw new InvalidNameException("Informe o nome e o sobrenome");
        }

        pet.setNome(partesNome[0]);
        pet.setSobrenome(partesNome[1]);

        // 2 Tipo (Enum)
        PetType tipoPet = null;
        while (tipoPet == null) {
            try {
                tipoPet = PetType.valueOf(sc.nextLine().trim().toUpperCase());
                pet.setTipo(tipoPet);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo do animal invalido (CACHORRO, GATO ou PASSARO)");
            }
        }

        // 3 Sexo
        PetSex sexoPet = null;
        while (sexoPet == null) {
            try {
                sexoPet = PetSex.valueOf(sc.nextLine().trim().toUpperCase());
                pet.setSexo(sexoPet);
            } catch (IllegalArgumentException e) {
                System.out.println("Sexo do animal invalido (MACHO ou FEMEA)");
            }
        }

        // 4 Endereço
        System.out.println("Número: ");
        String numero = sc.nextLine();
        if (numero.isBlank()) {
            numero = Constantes.NAO_INFORMADO;
        }

        System.out.println("Rua: ");
        String rua = sc.nextLine();


        System.out.println("Cidade: ");
        String cidade = sc.nextLine();

        pet.setEndereco(new Address(numero, rua, cidade));

        // 5 Idade
        String idadeStr = sc.nextLine().replace(",", ".");
        double idade = Double.parseDouble(idadeStr);

        if (idade > 20) {
            throw new InvalidAgeException("Confira a idade do pet");
        }

        if (idade < 1) {
            idade = idade / 12.0;
        }

        pet.setIdade(idade);

        // 6 Peso
        String pesoStr = sc.nextLine();

        double peso = -1;
        if (!pesoStr.isBlank()) {

            pesoStr = pesoStr.replace(",", ".");
            peso = Double.parseDouble(pesoStr);

            if (peso < 0.5 || peso > 60) {
                throw new IllegalArgumentException("Peso inválido");
            }
        }

        pet.setPeso(peso);

        // 7 Raça
        String raca = sc.nextLine();

        if (raca.isBlank()) {
            raca = Constantes.NAO_INFORMADO;
        } else if (!raca.matches("[A-Za-zÀ-ÿ\\s]+")) {
            throw new IllegalArgumentException("Raça inválida");
        }

        pet.setRaca(raca);

        petsCadastrados.add(pet);

        try {
            FileStorage.salvaArquivo(pet.toString(), nomeCompleto.replaceAll(" ", ""));
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo");
        }
    }


    public void alteraPet() {
        buscaPet();
        Pet petAlterado = new Pet();
    }

    public List<Pet> buscaPet() {

        List<Pet> petsEncontrados = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        Menu.mostraMenuBusca();

        String tipoDoPet = sc.nextLine();
        System.out.println("Quantos criterios de busca você quer utilizar? Máx 2");

        int qtdCriterios = Integer.parseInt(sc.nextLine());

        Menu.mostraMenuCriterios();

        int criterioUm = Integer.parseInt(sc.nextLine());
        System.out.println("Valor para o criterio 1:");
        String valorUm = sc.nextLine();

        int criterioDois = 0;
        String valorDois = "";

        if (qtdCriterios == 2) {
            criterioDois = Integer.parseInt(sc.nextLine());
            System.out.println("Valor para o criterio 2:");
            valorDois = sc.nextLine();
        }

        for (Pet pet : petsCadastrados) {

            if (pet.getTipo().name().equalsIgnoreCase(tipoDoPet)) {

                boolean passouUm = false;
                boolean passouDois = (qtdCriterios == 1);

                StringNormalizer.normaliza(valorUm);

                switch (criterioUm) {
                    case 1:
                        String nomePet = StringNormalizer.normaliza(pet.getNome() + " " + pet.getSobrenome());
                        if (nomePet.contains(valorUm))
                            passouUm = true;
                        break;

                    case 2:
                        if (pet.getSexo().name().equalsIgnoreCase(valorUm))
                            passouUm = true;
                        break;

                    case 3:
                        if (String.valueOf(pet.getIdade()).equals(valorUm))
                            passouUm = true;
                        break;

                    case 4:
                        if (String.valueOf(pet.getPeso()).equals(valorUm))
                            passouUm = true;
                        break;

                    case 5:
                        String racaPet = StringNormalizer.normaliza(pet.getRaca());
                        if (racaPet.contains(valorUm))
                            passouUm = true;
                        break;

                    case 6:
                        String e1 = StringNormalizer.normaliza(pet.getEndereco().getRua() + " " + pet.getEndereco().getCidade());
                        if (e1.contains(valorUm))
                            passouUm = true;
                        break;
                }

                if (qtdCriterios == 2) {

                    StringNormalizer.normaliza(valorDois);

                    switch (criterioDois) {
                        case 1:
                            //refactor pro StringNormalizer
                            String n2 = java.text.Normalizer.normalize(pet.getNome() + " " + pet.getSobrenome(), java.text.Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase();
                            if (n2.contains(valorDois))
                                passouDois = true;
                            break;
                        case 2:
                            if (pet.getSexo().name().equalsIgnoreCase(valorDois))
                                passouDois = true;
                            break;
                        case 3:
                            if (String.valueOf(pet.getIdade()).equals(valorDois))
                                passouDois = true;
                            break;
                        case 4:
                            if (String.valueOf(pet.getPeso()).equals(valorDois))
                                passouDois = true;
                            break;
                        case 5:
                            //refactor pro StringNormalizer
                            String r2 = java.text.Normalizer.normalize(pet.getRaca(), java.text.Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase();
                            if (r2.contains(valorDois))
                                passouDois = true;
                            break;
                        case 6:
                            //refactor pro StringNormalizer
                            String e2 = java.text.Normalizer.normalize(pet.getEndereco().getRua() + " " + pet.getEndereco().getCidade(), java.text.Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase();
                            if (e2.contains(valorDois))
                                passouDois = true;
                            break;
                    }
                }

                if (passouUm && passouDois) { //Se passar nos dois criterios eu adiciono o pet à lista dos pets encontrados pra depois poder mostrar
                    petsEncontrados.add(pet);
                }
            }
        }

        if (petsEncontrados.isEmpty()) {
            System.out.println("Nenhum resultado.");
        } else {
            for (int i = 0; i < petsEncontrados.size(); i++) {
                Pet p = petsEncontrados.get(i);
                String linha = (i + 1) + ". " + p.getNome() + " " + p.getSobrenome() + " - " + p.getTipo() + " - " + p.getSexo() + " - " + p.getEndereco().getRua() + ", " + p.getEndereco().getNumeroCasa() + " - " + p.getEndereco().getCidade() + " - " + p.getIdade() + " anos - " + p.getPeso() + "kg - " + p.getRaca();
                String res = linha.replaceAll("(?i)" + valorUm, "**$0**");
                if (qtdCriterios == 2) {
                    res = res.replaceAll("(?i)" + valorDois, "**$0**");
                }
                System.out.println(res);
            }
        }

        return petsEncontrados;
    }

    public void buscaPetPorCriterio(String criterio) {

    }


    public void deletaPet() {

    }


}
