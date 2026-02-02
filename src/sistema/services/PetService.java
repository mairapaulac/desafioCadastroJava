package sistema.services;

import sistema.cli.formReader;
import sistema.domain.Address;
import sistema.domain.enums.PetSex;
import sistema.domain.enums.PetType;
import sistema.storage.FileStorage;
import sistema.util.Constantes;
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



    public void buscaPet() {

    }

    public void buscaPetPorCriterio(String criterio) {

    }

    public void alteraPet() {

    }

    public void deletaPet() {

    }

//    public File armazenaArquivo() {
//        return new File("");
//    }

}
