import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bilheteria bilheteria = new Bilheteria();
        GerenciadorClientes gerenciadorClientes = new GerenciadorClientes();
        CatalogoDeFilmes catalogo = new CatalogoDeFilmes();

        String prosseguir;

        do {
            System.out.println("\nBem-vindo(a) ao Atendimento ao Cliente!");

            // 1. Registrar ou buscar cliente
            Cliente cliente = gerenciadorClientes.buscarOuRegistrarCliente(sc);

            // 2. Exibir catálogo e escolher filme
            catalogo.exibirCatalogo();
            int idFilmeEscolhido;
            Filme filmeEscolhido;
            do {
                System.out.print("Escolha o ID do filme para assistir: ");
                idFilmeEscolhido = sc.nextInt();
                filmeEscolhido = catalogo.buscarFilmePorId(idFilmeEscolhido);
                if (filmeEscolhido == null) {
                    System.out.println("ID inválido, tente novamente.");
                }
            } while (filmeEscolhido == null);
            System.out.println("Filme escolhido: " + filmeEscolhido.getTitulo());

            // 3. Entrada: quantidade e tipo de ingresso
            int quantidade;
            do {
                System.out.print("Total de ingressos (>=1): ");
                quantidade = sc.nextInt();
            } while (quantidade < 1);

            int tipo;
            do {
                System.out.print("Tipo do ingresso (1 = Inteira, 2 = Meia-Entrada, 3 = Promocional): ");
                tipo = sc.nextInt();
            } while (tipo < 1 || tipo > 3);

            // 4. Criar venda com dados do cliente (idade)
            Vendas venda = new Vendas(quantidade, tipo, cliente.getIdade());

            // Aplica desconto se cliente antigo (ex: 10%)
            if (cliente.isClienteAntigo() && venda.getValorTotal() > 0) {
                double desconto = venda.getValorTotal() * 0.10;
                System.out.printf("Cliente antigo! Aplicando desconto de 10%% (R$ %.2f)\n", desconto);
                venda.aplicarDesconto(desconto);
            }

            // 5. Bomboniere
            Bomboniere bomboniere = new Bomboniere();
            System.out.print("Deseja comprar algo na bomboniere? (S/N): ");
            sc.nextLine();
            String respostaBomboniere = sc.nextLine().trim();
            if (respostaBomboniere.equalsIgnoreCase("S")) {
                bomboniere.mostrarProdutos();
                System.out.print("Digite o código do produto para comprar: ");
                int codigoProduto = sc.nextInt();
                double valorBomboniere = bomboniere.comprarProduto(codigoProduto);
                if (valorBomboniere > 0) {
                    System.out.printf("Valor da bomboniere: R$ %.2f\n", valorBomboniere);
                    venda.somarValor(valorBomboniere);
                } else {
                    System.out.println("Produto inválido ou não comprado.");
                }
            }

            bilheteria.registrarVenda(venda);

            System.out.println("\nDetalhes da Compra");
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Filme: " + filmeEscolhido.getTitulo());
            System.out.println("Total de ingressos: " + venda.getQuantidade());
            System.out.println("Tipo aplicado: " + venda.getTipoAplicado());
            System.out.printf("Valor total: R$ %.2f\n", venda.getValorTotal());

            // 6. Pagamento
            if (venda.getValorTotal() > 0) {
                sc.nextLine();
                System.out.print("Forma de pagamento (Dinheiro/Cartão): ");
                String metodo = sc.nextLine().trim().toLowerCase();

                if (metodo.equals("dinheiro")) {
                    double pago;
                    do {
                        System.out.print("Valor pago: R$ ");
                        pago = sc.nextDouble();
                        if (pago < venda.getValorTotal()) {
                            System.out.println("Valor insuficiente, tente novamente.");
                        }
                    } while (pago < venda.getValorTotal());

                    double troco = pago - venda.getValorTotal();
                    System.out.printf("Troco: R$ %.2f\n", troco);
                } else {
                    System.out.println("Pagamento em cartão aprovado.");
                }
            } else {
                System.out.println("Ingresso gratuito.");
            }

            sc.nextLine();
            System.out.print("\nDeseja atender o(a) próximo cliente? (S/N): ");
            prosseguir = sc.nextLine().trim().toUpperCase();

        } while (prosseguir.equals("S"));

        bilheteria.exibirRelatorioFinal();
        sc.close();
    }
}