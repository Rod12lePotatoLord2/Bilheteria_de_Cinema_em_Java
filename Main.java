import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bilheteria bilheteria = new Bilheteria();
        String prosseguir;

        do {
            System.out.println("\nBem-vindo(a) ao Atendimento ao Cliente");

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

            int idade;
            do {
                System.out.print("Idade do cliente (0 a 120): ");
                idade = sc.nextInt();
            } while (idade < 0 || idade > 120);

            Vendas venda = new Vendas(quantidade, tipo, idade);
            bilheteria.registrarVenda(venda);

            System.out.println("\nDetalhes da Compra");
            System.out.println("Total de ingressos: " + venda.getQuantidade());
            System.out.println("Tipo aplicado: " + venda.getTipoAplicado());
            System.out.printf("Valor total: R$ %.2f\n", venda.getValorTotal());

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
                            System.out.println("O Valor é insuficiente, por favor, tente novamente.");
                        }
                    } while (pago < venda.getValorTotal());

                    double troco = pago - venda.getValorTotal();
                    System.out.printf("Troco: R$ %.2f\n", troco);
                } else {
                    System.out.println("O Pagamento em cartão foi aprovado.");
                }
            } else {
                System.out.println("Ingresso gratuito.");
            }

            sc.nextLine();
            System.out.print("\n Deseja Atender o(a) próximo cliente? (S/N): ");
            prosseguir = sc.nextLine().trim().toUpperCase();

        } while (prosseguir.equals("S"));

        bilheteria.exibirRelatorioFinal();
        sc.close();
    }
}