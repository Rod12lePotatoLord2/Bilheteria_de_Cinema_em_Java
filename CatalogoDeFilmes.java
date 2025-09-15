import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoDeFilmes {
    private Map<Integer, Filme> filmes;
    private int proximoId;

    public CatalogoDeFilmes() {
        filmes = new HashMap<>();
        proximoId = 1;
        // Filmes
        adicionarFilme(new Filme(proximoId++, "O Senhor dos Anéis: A Sociedade do Anel", 178));
        adicionarFilme(new Filme(proximoId++, "Star Wars Episódio IV: Uma Nova Esperança", 121));
        adicionarFilme(new Filme(proximoId++, "Harry Potter e a Pedra Filosofal", 152));
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
        System.out.println("\nCatálogo de Filmes");
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado no momento.");
        } else {
            for (Filme filme : filmes.values()) {
                System.out.println(filme);
            }
        }
        System.out.println("");
    }
}