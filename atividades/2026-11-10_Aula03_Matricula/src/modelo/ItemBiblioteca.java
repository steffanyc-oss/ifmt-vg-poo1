package modelo;

/**
 * Tudo que a biblioteca empresta. É a sua classe de 27/10, já corrigida
 * e agora dentro do pacote modelo. Só falta o TODO 1.
 */
public class ItemBiblioteca {
    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título obrigatório");
        }
        this.titulo = titulo.trim();
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean emprestar() {
        if (!disponivel) {
            return false;
        }
        disponivel = false;
        return true;
    }

    public void devolver() {
        disponivel = true;
    }

    public String descricao() {
        return "Item: " + titulo;
    }

    @Override
    public String toString() {
        // TODO 1: se disponível, retornar "[disponível] " + descricao();
        //         senão, "[emprestado] " + descricao().
        //         Rode ANTES de fazer: o que aparece no lugar? (algo como modelo.Livro@1b6d3586)
        if (disponivel) {
            return "[disponível] " + descricao();
        }
        return "[emprestado] " + descricao();
    }
}
