public class Vendas {
    private int quantidade;
    private int tipoIngressoEscolhido;
    private int idadeCliente;
    private double precoBase = 22.0;

    private String tipoAplicado;
    private double valorTotal;

    public Vendas(int quantidade, int tipoIngressoEscolhido, int idadeCliente) {
        this.quantidade = quantidade;
        this.tipoIngressoEscolhido = tipoIngressoEscolhido;
        this.idadeCliente = idadeCliente;
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        if (idadeCliente < 5) {
            tipoAplicado = "Grátis";
            valorTotal = 0.0;
        } else {
            switch (tipoIngressoEscolhido) {
                case 1:
                    tipoAplicado = "Inteira";
                    valorTotal = quantidade * precoBase;
                    break;
                case 2:
                    tipoAplicado = "Meia-Entrada";
                    valorTotal = quantidade * (precoBase * 0.5);
                    break;
                case 3:
                    tipoAplicado = "Promocional";
                    valorTotal = quantidade * (precoBase * 0.4);
                    break;
                default:
                    tipoAplicado = "Desconhecido";
                    valorTotal = quantidade * precoBase;
                    break;
            }
        }
    }

    public void aplicarDesconto(double desconto) {
        valorTotal -= desconto;
        if (valorTotal < 0) valorTotal = 0;
    }

    public void somarValor(double valorExtra) {
        valorTotal += valorExtra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getTipoAplicado() {
        return tipoAplicado;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}