public class Musica {
    private String titulo;
    private String artista;
    private double duracao;

    public Musica(String titulo, String artista, double duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
    }

    public void mostrarDetalhes() {
        System.out.println("Título: " + this.titulo + " | Artista: " + this.artista + " | Duração: " + this.duracao + " minutos");
    }

    public String getTitulo() {
        return this.titulo;
    }
}
