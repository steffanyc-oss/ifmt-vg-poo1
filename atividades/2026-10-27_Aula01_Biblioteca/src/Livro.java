/**
 * Livro é um ItemBiblioteca e acrescenta o autor.
 * Use super(titulo). Não copie título nem disponibilidade para esta classe.
 */
public class Livro extends ItemBiblioteca {
    private String autor;

    public Livro(String titulo, String autor) {
        super(titulo);// o titulo é um atributo da classe mãe que é construído pela classe mãe.
        // TODO: se autor for null ou em branco, lançar IllegalArgumentException.
         if (autor == null || autor.isBlank()){
            throw new IllegalArgumentException("O Autor é obrigatório");
        }
        this.autor = autor;// TODO: guardar o autor.
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String descricao() {
        // TODO: retornar "Livro: ", o título, " | Autor: " e o autor.
        // Exemplo: Livro: O Pequeno Príncipe | Autor: Antoine de Saint-Exupéry
        return "Livro: " + getTitulo() +  " | Autor: " + autor;
    }
}
