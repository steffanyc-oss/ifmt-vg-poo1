package modelo;

import java.time.LocalDate;

/**
 * Prática de 17/11/2026.
 * Registro de um empréstimo: liga um leitor a um item numa data.
 * Quem cria é a Biblioteca (composição): a tela nunca faz new Emprestimo.
 */
public class Emprestimo {
    private Leitor leitor;
    private ItemBiblioteca item;
    private LocalDate data;
    private boolean ativo;

    public Emprestimo(Leitor leitor, ItemBiblioteca item, LocalDate data) {
        this.leitor = leitor;
        this.item = item;
        this.data = data;
        this.ativo = true;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public ItemBiblioteca getItem() {
        return item;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void encerrar() {
        // TODO 1: o empréstimo deixa de estar ativo (ativo passa a false).
        ativo = false;
    }

    @Override
    public String toString() {
        // TODO 2: montar o texto: data | nome do leitor | título do item | situação
        //         Situação: "em aberto" se ativo, senão "devolvido".
        //         Exemplo: 2026-11-17 | Ana | Dom Casmurro | em aberto
        if (ativo) {
            return data + " | " + leitor.getNome() + " | " + item.getTitulo() + " | em aberto";
        }
        return data + " | " + leitor.getNome() + " | " + item.getTitulo() + " | devolvido";
    }
}
