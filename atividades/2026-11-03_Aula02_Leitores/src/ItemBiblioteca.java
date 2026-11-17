/**
 * Item da biblioteca. Complete os TODO a partir do UML da ficha.
 * Consultas não alteram o estado.
 */
public class ItemBiblioteca {
    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(String titulo) {
        // TODO: se titulo for null ou em branco, lançar IllegalArgumentException.
        if (titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("O Título é obrigatório");
        }
        this.titulo = titulo;
        this.disponivel = true;// TODO: guardar o título e iniciar disponivel como true.
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean emprestar() {
        // TODO: se já estiver indisponível, retornar false.
        if(!disponivel){
            return false;
        }
        // TODO: senão, marcar indisponível e retornar true.
        disponivel = false;
        return true;
    }

    public void devolver() {
        disponivel = true;// TODO: voltar a disponibilidade para true.
    }

    public String descricao() {
        // TODO: retornar "Item: " seguido do título.
        return "Item: " + titulo;
    }
}
