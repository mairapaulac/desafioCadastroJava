package sistema.domain;

import sistema.domain.enums.PetSex;
import sistema.domain.enums.PetType;
import sistema.util.exceptions.InvalidNameException;

public class Pet {
    private String nome, sobrenome, raca;
    private Address endereco;
    private PetSex sexo;
    private PetType tipo;
    private double idade, peso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        if(nome == null || nome.isBlank()) {
            throw new InvalidNameException("O nome não pode estar em branco");
        }

        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        if(sobrenome == null || sobrenome.isBlank()) {
            throw new InvalidNameException("O sobrenome não pode estar em branco");
        }

        this.sobrenome = sobrenome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }

    public PetType getTipo() {
        return tipo;
    }

    public void setTipo(PetType tipo) {
        this.tipo = tipo;
    }

    public PetSex getSexo() {
        return sexo;
    }

    public void setSexo(PetSex sexo) {
        this.sexo = sexo;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
