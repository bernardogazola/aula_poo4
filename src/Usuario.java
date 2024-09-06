import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String email;
    private ArrayList<Playlist> playlists;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.playlists = new ArrayList<>();
    }

    public void criarPlaylist(String nomePlaylist) {
        Playlist playlist = new Playlist(nomePlaylist);
        this.playlists.add(playlist);
        System.out.println("Playlist " + nomePlaylist + " criada.");
    }

    public boolean temPlaylists() {
        return !this.playlists.isEmpty();
    }

    public void listarPlaylists() {
        if (!temPlaylists()) {
            System.out.println("Você ainda não criou uma playlist.");
            return;
        }
        System.out.println("Playlists de " + this.nome + ":");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getNome());
        }
    }

    public Playlist getPlaylistPorIndice(int indice) {
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice);
        }
        return null;
    }

    public String getNome() {
        return this.nome;
    }
}
