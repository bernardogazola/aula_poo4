import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*
    SPOTIFY - PLAYLISTS (escopo reduzido)
     */
    private static Usuario usuario;
    private static ArrayList<Musica> bibliotecaMusicas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarMusicasExemplo();

        boolean loop = true;
        while (loop) {
            if (usuario == null) {
                exibirMenuInicial();
                int opcao = scanner.nextInt();
                scanner.nextLine();
                loop = processarOpcaoInicial(opcao);
            } else {
                exibirMenuPrincipal();
                int opcao = scanner.nextInt();
                scanner.nextLine();
                loop = processarOpcaoPrincipal(opcao);
            }
        }
    }

    // EXEMPLOS
    private static void inicializarMusicasExemplo() {
        bibliotecaMusicas.add(new Musica("the ends", "Travis Scott", 3.2));
        bibliotecaMusicas.add(new Musica("Nightcrawler", "Travis Scott", 3.5));
        bibliotecaMusicas.add(new Musica("YOSEMITE", "Travis Scott", 2.3));
        bibliotecaMusicas.add(new Musica("FRANCHISE", "Travis Scott", 3.2));
    }

    private static void exibirMenuInicial() {
        System.out.println("\nMenu:");
        System.out.println("1 - Cadastrar");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\nMenu:");
        System.out.println("1 - Criar Playlist");
        System.out.println("2 - Listar Playlists");
        System.out.println("3 - Listar Músicas de uma Playlist");
        System.out.println("4 - Adicionar Música a uma Playlist");
        System.out.println("5 - Remover Música de uma Playlist");
        System.out.println("6 - Tocar Música");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static boolean processarOpcaoInicial(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarUsuario();
                return true;
            case 0:
                System.out.println("Saindo...");
                return false;
            default:
                System.out.println("Opção inválida.");
                return true;
        }
    }

    private static boolean processarOpcaoPrincipal(int opcao) {
        switch (opcao) {
            case 1:
                criarPlaylist();
                break;
            case 2:
                listarPlaylists();
                break;
            case 3:
                listarMusicasPlaylist();
                break;
            case 4:
                adicionarMusicaPlaylist();
                break;
            case 5:
                removerMusicaPlaylist();
                break;
            case 6:
                tocarMusica();
                break;
            case 0:
                System.out.println("Saindo...");
                return false;
            default:
                System.out.println("Opção inválida.");
        }
        return true;
    }

    private static void cadastrarUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Email do usuário: ");
        String email = scanner.nextLine();
        usuario = new Usuario(nome, email);
        System.out.println("Usuário " + nome + " cadastrado com sucesso.");
    }

    private static void criarPlaylist() {
        System.out.print("Nome da nova playlist: ");
        String nomePlaylist = scanner.nextLine();
        usuario.criarPlaylist(nomePlaylist);
    }

    private static void adicionarMusicaPlaylist() {
        if (!usuario.temPlaylists()) {
            System.out.println("Você ainda não criou uma playlist.");
            return;
        }
        usuario.listarPlaylists();
        System.out.print("Selecione a playlist para adicionar uma música: ");
        int escolhaPlaylist = scanner.nextInt();
        scanner.nextLine();
        Playlist playlistSelecionada = usuario.getPlaylistPorIndice(escolhaPlaylist - 1);
        if (playlistSelecionada != null) {
            listarMusicasDisponiveis();
            System.out.print("Selecione a música: ");
            int escolhaMusica = scanner.nextInt();
            scanner.nextLine();
            if (escolhaMusica >= 1 && escolhaMusica <= bibliotecaMusicas.size()) {
                playlistSelecionada.adicionarMusica(bibliotecaMusicas.get(escolhaMusica - 1));
            } else {
                System.out.println("Música inválida.");
            }
        } else {
            System.out.println("Playlist inválida.");
        }
    }

    private static void listarPlaylists() {
        usuario.listarPlaylists();
    }

    private static void listarMusicasPlaylist() {
        if (!usuario.temPlaylists()) {
            System.out.println("Você ainda não criou uma playlist.");
            return;
        }
        usuario.listarPlaylists();
        System.out.print("Selecione uma playlist para listar as músicas: ");
        int escolhaPlaylistListar = scanner.nextInt();
        scanner.nextLine();
        Playlist playlist = usuario.getPlaylistPorIndice(escolhaPlaylistListar - 1);
        if (playlist != null) {
            playlist.listarMusicas();
        } else {
            System.out.println("Playlist inválida.");
        }
    }

    private static void tocarMusica() {
        listarMusicasDisponiveis();
        System.out.print("Selecione uma música para tocar: ");
        int escolhaTocar = scanner.nextInt();
        scanner.nextLine();
        if (escolhaTocar >= 1 && escolhaTocar <= bibliotecaMusicas.size()) {
            tocarMusica(bibliotecaMusicas.get(escolhaTocar - 1));
        } else {
            System.out.println("Música inválida.");
        }
    }

    private static void removerMusicaPlaylist() {
        if (!usuario.temPlaylists()) {
            System.out.println("Você ainda não criou uma playlist.");
            return;
        }
        usuario.listarPlaylists();
        System.out.print("Selecione uma playlist para remover uma música: ");
        int escolhaPlaylistRemover = scanner.nextInt();
        scanner.nextLine();
        Playlist playlistRemover = usuario.getPlaylistPorIndice(escolhaPlaylistRemover - 1);
        if (playlistRemover != null) {
            playlistRemover.listarMusicas();
            if (!playlistRemover.getMusicas().isEmpty()) {
                System.out.print("Selecione a música para remover: ");
                int escolhaMusicaRemover = scanner.nextInt();
                scanner.nextLine();
                playlistRemover.removerMusica(escolhaMusicaRemover - 1);
            }
        } else {
            System.out.println("Playlist inválida.");
        }
    }

    private static void listarMusicasDisponiveis() {
        System.out.println("Músicas disponíveis:");
        for (int i = 0; i < bibliotecaMusicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            bibliotecaMusicas.get(i).mostrarDetalhes();
        }
    }

    private static void tocarMusica(Musica musica) {
        System.out.println("Tocando música: " + musica.getTitulo());
        System.out.println("Pressione X para parar...");
        String comando = scanner.nextLine();
        if (comando.equalsIgnoreCase("X")) {
            System.out.println("Música parada.");
        }
    }
}
