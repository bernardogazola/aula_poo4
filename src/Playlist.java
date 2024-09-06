import java.util.ArrayList;

public class Playlist {
    private String nome;
    private ArrayList<Musica> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
        System.out.println("Música " + musica.getTitulo() + " adicionada à playlist " + this.nome);
    }

    public void removerMusica(int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            Musica musicaRemovida = musicas.remove(indice);
            System.out.println("Música " + musicaRemovida.getTitulo() + " removida da playlist " + this.nome);
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("A playlist está vazia.");
        } else {
            for (int i = 0; i < musicas.size(); i++) {
                System.out.print((i + 1) + ". ");
                musicas.get(i).mostrarDetalhes();
            }
        }
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public String getNome() {
        return this.nome;
    }
}
