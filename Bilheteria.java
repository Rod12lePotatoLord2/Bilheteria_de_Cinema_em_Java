public class Bilheteria {
    private int totalIngressos = 0;
    private int totalInteira = 0;
    private int totalMeia = 0;
    private int totalPromocional = 0;
    private int totalGratuito = 0;
    private double totalArrecadado = 0.0;

    public void registrarVenda(Vendas venda) {
        totalIngressos += venda.getQuantidade();

        switch (venda.getTipoAplicado()) {
            case "Inteira":
                totalInteira += venda.getQuantidade();
                totalArrecadado += venda.getValorTotal();
                break;
            case "Meia-Entrada":
                totalMeia += venda.getQuantidade();
                totalArrecadado += venda.getValorTotal();
                break;
            case "Promocional":
                totalPromocional += venda.getQuantidade();
                totalArrecadado += venda.getValorTotal();
                break;
            case "Grátis":
                totalGratuito += venda.getQuantidade();
                break;
        }
    }

    public void exibirRelatorioFinal() {
        System.out.println("\nRELATÓRIO FINAL DE VENDAS No Expediente");
        System.out.println("Total de ingressos vendidos: " + totalIngressos);
        System.out.println("Inteira: " + totalInteira);
        System.out.println("Meia-Entrada: " + totalMeia);
        System.out.println("Promocional: " + totalPromocional);
        System.out.println("Gratuitos: " + totalGratuito);
        System.out.printf("Total arrecadado: R$ %.2f\n", totalArrecadado);
    }
}