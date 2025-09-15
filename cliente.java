import java.time.LocalDate;
import java.time.Period;

public class Cliente {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private int idade;
    private LocalDate dataCadastro;
    private boolean clienteAntigo; // Define se o cliente tem direito a um desconto adicional por ser antigo

    // Construtor para novos clientes
    public Cliente(String nome, int idade) {
        this.id = proximoId++;
        this.nome = nome;
        this.idade = idade;
        this.dataCadastro = LocalDate.now();
        this.clienteAntigo = determinarSeClienteAntigo(); // Lógica para definir se é antigo
    }

    // Construtor para clientes já existentes (se carregados de um arquivo, por exemplo)
    public Cliente(int id, String nome, int idade, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.dataCadastro = dataCadastro;
        this.clienteAntigo = determinarSeClienteAntigo();
        if (id >= proximoId) {
            proximoId = id + 1;
        }
    }

    // Lógica para determinar se um cliente é "antigo"
    // Exemplo: Cliente é considerado antigo se está cadastrado há mais de 1 ano.
    private boolean determinarSeClienteAntigo() {
        if (dataCadastro == null) return false;
        return Period.between(dataCadastro, LocalDate.now()).getYears() >= 1;
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
        return clienteAntigo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", idade=" + idade +
               ", dataCadastro=" + dataCadastro +
               ", clienteAntigo=" + clienteAntigo +
               '}';
    }
}
