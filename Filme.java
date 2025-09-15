public class Filme {
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