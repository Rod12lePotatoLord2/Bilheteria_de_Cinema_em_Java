import java.time.LocalDate;
import java.time.Period;

public class Cliente {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private int idade;
    private LocalDate dataCadastro;

    public Cliente(String nome, int idade) {
        this.id = proximoId++;
        this.nome = nome;
        this.idade = idade;
        this.dataCadastro = LocalDate.now();
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public int getIdade() {
        return idade;
    }
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public boolean isClienteAntigo() {
        return Period.between(dataCadastro, LocalDate.now()).getYears() >= 1;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}