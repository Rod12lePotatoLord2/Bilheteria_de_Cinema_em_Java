import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Classe para representar um Filme
class Filme {
    private int id;
    private String titulo;
    private int duracaoMinutos;

    public Filme(int id, String titulo, int duracaoMinutos) {
        this.id = id;
        this.titulo = titulo;
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo + ", Duração: " + duracaoMinutos + " min";
    }
}

// Classe para gerenciar o catálogo de filmes
class CatalagoDeFilmes {
    private Map<Integer, Filme> filmes;
    private int proximoId;

    public CatalagoDeFilmes() {
        filmes = new HashMap<>();
        proximoId = 1;
        // Adicionando alguns filmes de exemplo
        adicionarFilme(new Filme(proximoId++, "O Senhor dos Anéis: A Sociedade do Anel", 178));
        adicionarFilme(new Filme(proximoId++, "Interestelar", 169));
        adicionarFilme(new Filme(proximoId++, "Parasita", 132));
    }

    public void adicionarFilme(Filme filme) {
        filmes.put(filme.getId(), filme);
    }

    public Filme buscarFilmePorId(int id) {
        return filmes.get(id);
    }

    public List<Filme> listarTodosOsFilmes() {
        return new ArrayList<>(filmes.values());
    }

    public void exibirCatalogo() {
        System.out.println("\n--- Catálogo de Filmes ---");
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado no momento.");
        } else {
            for (Filme filme : filmes.values()) {
                System.out.println(filme);
            }
        }
        System.out.println("------------------------");
    }
}

// Classe para representar um Cliente
class Cliente {
    private int id;
    private String nome;
    private int idade;
    private int frequenciaVisitas; // Para controle de "cliente antigo"

    public Cliente(int id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.frequenciaVisitas = 0; // Inicializa com 0 visitas
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

    public int getFrequenciaVisitas() {
        return frequenciaVisitas;
    }

    public void incrementarFrequenciaVisitas() {
        this.frequenciaVisitas++;
    }

    public boolean ehClienteAntigo() {
        // Define um critério para ser considerado "cliente antigo"
        // Por exemplo, mais de 10 visitas registradas
        return frequenciaVisitas > 10;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Idade: " + idade + ", Visitas: " + frequenciaVisitas;
    }
}

// Classe para gerenciar clientes
class GerenciadorClientes {
    private Map<Integer, Cliente> clientes;
    private int proximoIdCliente;

    public GerenciadorClientes() {
        clientes = new HashMap<>();
        proximoIdCliente = 1;
    }

    public Cliente registrarCliente(String nome, int idade) {
        Cliente novoCliente = new Cliente(proximoIdCliente++, nome, idade);
        clientes.put(novoCliente.getId(), novoCliente);
        System.out.println("Cliente registrado com sucesso: " + novoCliente.getNome());
        return novoCliente;
    }

    public Cliente buscarClientePorId(int id) {
        return clientes.get(id);
    }

    public Cliente buscarOuRegistrarCliente(Scanner scanner) {
        System.out.print("Você já é um cliente cadastrado? (S/N): ");
        String resposta = scanner.next();

        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("Digite seu ID de cliente: ");
            int idCliente = lerInteiro(scanner);
            Cliente cliente = buscarClientePorId(idCliente);
            if (cliente != null) {
                System.out.println("Bem-vindo de volta, " + cliente.getNome() + "!");
                cliente.incrementarFrequenciaVisitas();
                return cliente;
            } else {
                System.out.println("ID de cliente inválido. Por favor, registre-se.");
                return registrarNovoCliente(scanner);
            }
        } else {
            return registrarNovoCliente(scanner);
        }
    }

    private Cliente registrarNovoCliente(Scanner scanner) {
        System.out.println("Vamos registrar você!");
        System.out.print("Digite seu nome: ");
        String nome = scanner.next();
        System.out.print("Digite sua idade: ");
        int idade = lerInteiro(scanner);
        while (idade < 0 || idade > 120) {
            System.out.println("Idade inválida. Por favor, digite uma idade entre 0 e 120.");
            System.out.print("Digite sua idade: ");
            idade = lerInteiro(scanner);
        }
        return registrarCliente(nome, idade);
    }

    // Método auxiliar para ler inteiros com validação
    private int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            scanner.next(); // Descarta a entrada inválida
        }
        return scanner.nextInt();
    }
}

// Classe para representar uma Venda (cálculo e regras de preço)
class Venda {
    private static final double PRECO_BASE_INGRESSO = 30.00;
    private static final int VISITAS_CLIENTE_ANTIGO = 10; // Limiar para cliente antigo

    private int quantidadeIngressos;
    private int tipoIngressoEscolhido; // 1: Inteira, 2: Meia, 3: Promocional
    private int idadeCliente;
    private String tipoIngressoAplicado; // Inteira, Meia, Promocional, Gratuito
    private double valorTotalCompra;
    private String metodoPagamento;
    private double valorPago;
    private double troco;
    private Cliente cliente; // Referência ao cliente associado a esta venda

    public Venda(Cliente cliente) {
        this.cliente = cliente;
        this.quantidadeIngressos = 0;
        this.tipoIngressoEscolhido = 0;
        this.idadeCliente = 0;
        this.valorTotalCompra = 0.0;
        this.troco = 0.0;
    }

    public void coletarDadosVenda(Scanner scanner) {
        // 1. Coletar dados do cliente (se necessário)
        if (this.cliente == null) {
            GerenciadorClientes gerenciadorClientes = new GerenciadorClientes(); // Instanciação temporária para busca/registro
            this.cliente = gerenciadorClientes.buscarOuRegistrarCliente(scanner);
        }

        // 2. Perguntar sobre o filme (assumindo que a escolha do filme precede a compra do ingresso)
        // Na sua implementação original, o filme não era mencionado.
        // Vamos integrar com o CatalagoDeFilmes.
        CatalagoDeFilmes catalago = new CatalagoDeFilmes(); // Instanciação temporária para exibir filmes
        catalago.exibirCatalogo();
        System.out.print("Digite o ID do filme que deseja assistir: ");
        int idFilme = lerInteiro(scanner);
        Filme filmeEscolhido = catalago.buscarFilmePorId(idFilme);
        if (filmeEscolhido == null) {
            System.out.println("Filme não encontrado. Saindo da compra.");
            return; // Interrompe a coleta de dados da venda
        }
        System.out.println("Você escolheu: " + filmeEscolhido.getTitulo());

        // 3. Coletar quantidade de ingressos
        System.out.print("Digite a quantidade de ingressos (≥ 1): ");
        this.quantidadeIngressos = lerInteiro(scanner);
        while (this.quantidadeIngressos < 1) {
            System.out.println("Quantidade inválida. Por favor, digite pelo menos 1 ingresso.");
            System.out.print("Digite a quantidade de ingressos (≥ 1): ");
            this.quantidadeIngressos = lerInteiro(scanner);
        }

        // 4. Coletar tipo de ingresso desejado
        System.out.println("Tipos de ingresso:");
        System.out.println("  1 - Inteira (preço cheio)");
        System.out.println("  2 - Meia (50% do preço base)");
        System.out.println("  3 - Promocional (30% de desconto)");
        System.out.print("Escolha o tipo de ingresso desejado (1, 2 ou 3): ");
        this.tipoIngressoEscolhido = lerInteiro(scanner);
        while (this.tipoIngressoEscolhido < 1 || this.tipoIngressoEscolhido > 3) {
            System.out.println("Tipo de ingresso inválido. Por favor, escolha 1, 2 ou 3.");
            System.out.print("Escolha o tipo de ingresso desejado (1, 2 ou 3): ");
            this.tipoIngressoEscolhido = lerInteiro(scanner);
        }

        // 5. Coletar idade do cliente (se a gratuidade for aplicável)
        // A idade do cliente pode ser a informada no registro ou solicitada novamente se não houver cliente registrado
        if (this.cliente != null) {
            this.idadeCliente = this.cliente.getIdade();
        } else {
            System.out.print("Digite sua idade (entre 0 e 120): ");
            this.idadeCliente = lerInteiro(scanner);
            while (this.idadeCliente < 0 || this.idadeCliente > 120) {
                System.out.println("Idade inválida. Por favor, digite uma idade entre 0 e 120.");
                System.out.print("Digite sua idade (entre 0 e 120): ");
                this.idadeCliente = lerInteiro(scanner);
            }
        }

        calcularValorTotal();
        exibirResumoVenda();
        processarPagamento(scanner);
    }

    private void calcularValorTotal() {
        double precoIndividual = 0.0;
        boolean gratuidadeAplicada = false;

        // Regras de gratuidade/desconto
        if (this.idadeCliente < 5) {
            this.tipoIngressoAplicado = "Gratuito";
            precoIndividual = 0.0;
            gratuidadeAplicada = true;
            System.out.println("Gratuidade aplicada: Cliente com menos de 5 anos.");
        } else {
            // Aplica descontos com base no tipo escolhido e se é cliente antigo
            switch (this.tipoIngressoEscolhido) {
                case 1: // Inteira
                    precoIndividual = PRECO_BASE_INGRESSO;
                    this.tipoIngressoAplicado = "Inteira";
                    break;
                case 2: // Meia
                    precoIndividual = PRECO_BASE_INGRESSO * 0.50;
                    this.tipoIngressoAplicado = "Meia";
                    break;
                case 3: // Promocional
                    precoIndividual = PRECO_BASE_INGRESSO * 0.70; // 30% de desconto
                    this.tipoIngressoAplicado = "Promocional";
                    break;
            }

            // Aplica desconto adicional para cliente antigo (se não for gratuito)
            if (this.cliente != null && this.cliente.ehClienteAntigo() && !gratuidadeAplicada) {
                System.out.println("Desconto de cliente antigo aplicado!");
                precoIndividual *= 0.90; // Mais 10% de desconto
                // Atualiza o tipo aplicado para refletir o desconto extra
                this.tipoIngressoAplicado += " (Cliente Antigo)";
            }
        }

        this.valorTotalCompra = precoIndividual * this.quantidadeIngressos;
    }

    public void exibirResumoVenda() {
        System.out.println("\n--- Resumo da Venda ---");
        System.out.println("Cliente: " + (this.cliente != null ? this.cliente.getNome() : "Não registrado"));
        System.out.println("Quantidade de Ingressos: " + this.quantidadeIngressos);
        System.out.println("Tipo de Ingresso Aplicado: " + this.tipoIngressoAplicado);
        System.out.printf("Valor Total da Compra: R$ %.2f\n", this.valorTotalCompra);
        System.out.println("-----------------------");
    }

    public void processarPagamento(Scanner scanner) {
        System.out.println("Método de Pagamento:");
        System.out.println("  1 - Dinheiro");
        System.out.println("  2 - Cartão");
        System.out.print("Escolha o método de pagamento (1 ou 2): ");
        int escolhaPagamento = lerInteiro(scanner);

        while (escolhaPagamento < 1 || escolhaPagamento > 2) {
            System.out.println("Opção inválida. Por favor, escolha 1 para Dinheiro ou 2 para Cartão.");
            System.out.print("Escolha o método de pagamento (1 ou 2): ");
            escolhaPagamento = lerInteiro(scanner);
        }

        if (escolhaPagamento == 1) { // Dinheiro
            System.out.printf("Valor total: R$ %.2f\n", this.valorTotalCompra);
            System.out.print("Digite o valor pago: R$ ");
            this.valorPago = scanner.nextDouble();
            while (this.valorPago < this.valorTotalCompra) {
                System.out.println("Valor pago insuficiente. Por favor, digite um valor maior ou igual ao total.");
                System.out.print("Digite o valor pago: R$ ");
                this.valorPago = scanner.nextDouble();
            }
            this.troco = this.valorPago - this.valorTotalCompra;
            System.out.printf("Troco: R$ %.2f\n", this.troco);
            this.metodoPagamento = "Dinheiro";
        } else { // Cartão
            System.out.println("Transação confirmada no cartão.");
            this.metodoPagamento = "Cartão";
        }

        // Se o cliente existe, ele deve ter sua frequência atualizada após uma venda bem-sucedida
        if (this.cliente != null) {
            this.cliente.incrementarFrequenciaVisitas();
        }
    }

    // Getters para o relatório acumulado
    public int getQuantidadeIngressos() {
        return quantidadeIngressos;
    }

    public double getValorTotalCompra() {
        return valorTotalCompra;
    }

    public String getTipoIngressoAplicado() {
        return tipoIngressoAplicado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    // Método auxiliar para ler inteiros com validação
    private int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            scanner.next(); // Descarta a entrada inválida
        }
        return scanner.nextInt();
    }
}

// Classe principal que contém o método main e a lógica da Bilheteria
public class BilheteriaCinema {

    private CatalagoDeFilmes catalogoFilmes;
    private GerenciadorClientes gerenciadorClientes;
    private List<Venda> vendasDoDia;

    // Acumuladores para o relatório diário
    private int totalIngressosVendidos;
    private int totalInteira;
    private int totalMeia;
    private int totalPromocional;
    private int totalGratuitos;
    private double valorTotalArrecadado;

    public BilheteriaCinema() {
        catalogoFilmes = new CatalagoDeFilmes();
        gerenciadorClientes = new GerenciadorClientes();
        vendasDoDia = new ArrayList<>();

        // Inicializa acumuladores
        totalIngressosVendidos = 0;
        totalInteira = 0;
        totalMeia = 0;
        totalPromocional = 0;
        totalGratuitos = 0;
        valorTotalArrecadado = 0.0;
    }

    public void iniciarBilheteria() {
        Scanner scanner = new Scanner(System.in);
        char atenderProximo;

        do {
            System.out.println("\n--- Novo Atendimento ---");
            // Cria uma nova venda para cada cliente
            Venda vendaAtual = new Venda(null); // Passamos null inicialmente, pois o cliente será buscado/registrado dentro de Venda
            vendaAtual.coletarDadosVenda(scanner);

            // Atualiza os acumuladores do relatório diário
            processarVendaParaRelatorio(vendaAtual);
            vendasDoDia.add(vendaAtual); // Guarda a venda para possível relatório detalhado

            System.out.print("\nAtender próximo cliente? (S/N): ");
            atenderProximo = scanner.next().charAt(0);
        } while (atenderProximo == 'S' || atenderProximo == 's');

        exibirRelatorioFinal();
        scanner.close();
        System.out.println("Sistema de bilheteria encerrado.");
    }

    private void processarVendaParaRelatorio(Venda venda) {
        this.totalIngressosVendidos += venda.getQuantidadeIngressos();
        if (venda.getValorTotalCompra() > 0) { // Considera apenas ingressos pagos para arrecadação total
            this.valorTotalArrecadado += venda.getValorTotalCompra();
        }

        // Conta por tipo de ingresso aplicado
        String tipo = venda.getTipoIngressoAplicado();
        if (tipo.contains("Gratuito")) {
            this.totalGratuitos += venda.getQuantidadeIngressos();
        } else if (tipo.contains("Inteira")) {
            this.totalInteira += venda.getQuantidadeIngressos();
        } else if (tipo.contains("Meia")) {
            this.totalMeia += venda.getQuantidadeIngressos();
        } else if (tipo.contains("Promocional")) {
            this.totalPromocional += venda.getQuantidadeIngressos();
        }
    }

    public void exibirRelatorioFinal() {
        System.out.println("\n========== RELATÓRIO DIÁRIO FINAL ==========");
        System.out.println("Total de Ingressos Vendidos: " + this.totalIngressosVendidos);
        System.out.println("-------------------------------------------");
        System.out.println("Quantidade por Tipo:");
        System.out.println("  Inteira: " + this.totalInteira);
        System.out.println("  Meia: " + this.totalMeia);
        System.out.println("  Promocional: " + this.totalPromocional);
        System.out.println("  Gratuitos: " + this.totalGratuitos);
        System.out.println("-------------------------------------------");
        System.out.printf("Valor Total Arrecadado (Ingressos Pagos): R$ %.2f\n", this.valorTotalArrecadado);
        System.out.println("============================================");
    }

    public static void main(String[] args) {
        BilheteriaCinema bilheteria = new BilheteriaCinema();
        bilheteria.iniciarBilheteria();
    }
}
