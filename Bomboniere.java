import java.util.HashMap;
import java.util.Map;

public class Bomboniere {
    private Map<Integer, Produto> produtos;

    public Bomboniere() {
        produtos = new HashMap<>();
        produtos.put(1, new Produto("Pipoca", 12.00));
        produtos.put(2, new Produto("Refrigerante", 8.00));
        produtos.put(3, new Produto("Chocolate", 10.00));
    }

    public void mostrarProdutos() {
        System.out.println("Produtos da Bomboniere");
        for (Map.Entry<Integer, Produto> entry : produtos.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome() + " - R$ " + entry.getValue().getPreco());
        }
    }

    public double comprarProduto(int codigo) {
        Produto p = produtos.get(codigo);
        if (p != null) {
            System.out.println("Produto " + p.getNome() + " adicionado.");
            return p.getPreco();
        }
        return 0.0;
    }
}