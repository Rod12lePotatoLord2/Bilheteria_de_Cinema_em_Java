import java.util.ArrayList;
import java.util.List;

public class Bilheteria {
    private List<Vendas> vendasRealizadas;

    public Bilheteria() {
        vendasRealizadas = new ArrayList<>();
    }

    public void registrarVenda(Vendas venda) {
        vendasRealizadas.add(venda);
    }

    public void exibirRelatorioFinal() {
        System.out.println("\nRelatório Final da Bilheteria.");
        if (vendasRealizadas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
            return;
        }
        double totalArrecadado = 0.0;
        int totalIngressosVendidos = 0;

        for (Vendas venda : vendasRealizadas) {
            System.out.printf("Venda: %d ingressos, Tipo: %s, Valor: R$ %.2f\n",
                    venda.getQuantidade(), venda.getTipoAplicado(), venda.getValorTotal());
            totalArrecadado += venda.getValorTotal();
            totalIngressosVendidos += venda.getQuantidade();
        }

        System.out.printf("Total de ingressos vendidos: %d\n", totalIngressosVendidos);
        System.out.printf("Total arrecadado: R$ %.2f\n", totalArrecadado);
    }
}