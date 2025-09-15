import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GerenciadorClientes {
    private Map<Integer, Cliente> clientes;
    private int proximoIdCliente;

    public GerenciadorClientes() {
        clientes = new HashMap<>();
        proximoIdCliente = 1;
    }

    public Cliente registrarCliente(String nome, int idade) {
        Cliente novoCliente = new Cliente(nome, idade);
        clientes.put(novoCliente.getId(), novoCliente);
        System.out.println("Cliente registrado com sucesso: " + novoCliente.getNome() + ", ID: " + novoCliente.getId());
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
                return cliente;
            } else {
                System.out.println("ID inválido. Vamos registrar você.");
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
            System.out.println("Idade inválida. Por favor, digite entre 0 e 120.");
            idade = lerInteiro(scanner);
        }
        return registrarCliente(nome, idade);
    }

    private int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número inteiro.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}