package sistema.domain;

public class Address {
    private String cidade, rua, numeroCasa;

    public Address(String numeroCasa, String rua, String cidade) {
        this.numeroCasa = numeroCasa;
        this.cidade = cidade;
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    @Override
    public String toString() {
        return rua + ", " + numeroCasa + ", " + cidade;
    }
}
