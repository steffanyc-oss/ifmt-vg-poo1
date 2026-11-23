package modelo;

/**
 * Livro agora tem ano, que é opcional. Dois construtores (sobrecarga):
 * o completo valida e guarda; o curto só repassa para o completo.
 */
public class Livro extends ItemBiblioteca {
    private String autor;
    private int ano;   // 0 = não informado

    public Livro(String titulo, String autor, int ano) {
        super(titulo);
        // TODO 2: autor null ou em branco: IllegalArgumentException("Autor obrigatório").
        //         ano negativo: IllegalArgumentException("Ano inválido").
        //         Guardar autor.trim() e ano.
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("Autor obrigatório");
        }
        if (ano < 0) {
            throw new IllegalArgumentException("Ano inválido");
        }
        this.autor = autor.trim();
        this.ano = ano;
    }

    public Livro(String titulo, String autor) {
        // TODO 3: apague a linha abaixo e chame o construtor completo com ano 0:
        //         this(titulo, autor, 0);
        //         Assim a validação fica num lugar só.
        this(titulo, autor, 0);
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    @Override
    public String descricao() {
        // TODO 4: se ano for 0, fica como está.
        //         Senão, acrescentar " (" + ano + ")" no fim.
        //         Exemplo: Livro: Dom Casmurro | Autor: Machado de Assis (1899)
        if (ano == 0) {
            return "Livro: " + getTitulo() + " | Autor: " + autor;
        }
        return "Livro: " + getTitulo() + " | Autor: " + autor + " (" + ano + ")";
    }
}
