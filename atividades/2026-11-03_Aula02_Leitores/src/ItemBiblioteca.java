/**
 * Item da biblioteca. Complete os TODO a partir do UML da ficha.
 * Consultas não alteram o estado.
 */
public class ItemBiblioteca {
    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(String titulo) {
        // TODO: se titulo for null ou em branco, lançar IllegalArgumentException.
        // TODO: guardar o título e iniciar disponivel como true.
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
        // TODO: se já estiver indisponível, retornar false.
        // TODO: senão, marcar indisponível e retornar true.
        if (!disponivel) {
            return false;
        }
        disponivel = false;
        return true;
    }

    public void devolver() {
        // TODO: voltar a disponibilidade para true.
        disponivel = true;
    }

    public String descricao() {
        // TODO: retornar "Item: " seguido do título.
        return "Item: " + titulo;
    }
}
