package regras;

import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Emprestimo;
import modelo.ItemBiblioteca;
import modelo.Leitor;

/**
 * Prática de 17/11/2026.
 * Camada de REGRAS. Não lê teclado e não escreve na tela:
 * quem conversa com o usuário é a camada de tela (MenuBiblioteca).
 * Toda regra violada vira RegraException com uma mensagem clara.
 */
public class Biblioteca {
    private String nome;
    private ArrayList<ItemBiblioteca> acervo;
    private ArrayList<Leitor> leitores;
    private ArrayList<Emprestimo> emprestimos;   // composição: só a Biblioteca cria

    public Biblioteca(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        this.nome = nome;
        this.acervo = new ArrayList<>();
        this.leitores = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    // ---------------- Acervo (pronto, de 10/11) ----------------

    public void adicionarItem(ItemBiblioteca item) {
        if (item == null) {
            throw new IllegalArgumentException("Item obrigatório");
        }
        if (buscarPorTitulo(item.getTitulo()) != null) {
            throw new RegraException("Já existe um item com o título \"" + item.getTitulo() + "\"");
        }
        acervo.add(item);
    }

    public ItemBiblioteca buscarPorTitulo(String titulo) {
        for (ItemBiblioteca item : acervo) {
            if (item.getTitulo().equalsIgnoreCase(titulo.trim())) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<ItemBiblioteca> getAcervo() {
        return new ArrayList<>(acervo);
    }

    public ArrayList<ItemBiblioteca> itensDisponiveis() {
        ArrayList<ItemBiblioteca> livres = new ArrayList<>();
        for (ItemBiblioteca item : acervo) {
            if (item.isDisponivel()) {
                livres.add(item);
            }
        }
        return livres;
    }

    // ---------------- Leitores (pronto, de 10/11) ----------------

    public Leitor cadastrarLeitor(String nomeLeitor) {
        Leitor novo = new Leitor(nomeLeitor);
        leitores.add(novo);
        return novo;
    }

    public void adicionarLeitor(Leitor leitor) {
        if (leitores.contains(leitor)) {
            throw new RegraException("Matrícula repetida: " + leitor.getMatricula());
        }
        leitores.add(leitor);
    }

    public Leitor buscarLeitor(int matricula) {
        for (Leitor l : leitores) {
            if (l.getMatricula() == matricula) {
                return l;
            }
        }
        return null;
    }

    public ArrayList<Leitor> getLeitores() {
        return new ArrayList<>(leitores);
    }

    // ---------------- Empréstimos (novo) ----------------

    public Emprestimo emprestar(Leitor leitor, ItemBiblioteca item) {
        return emprestar(leitor, item, LocalDate.now());
    }

    public Emprestimo emprestar(Leitor leitor, ItemBiblioteca item, LocalDate data) {
        if (leitor == null || item == null) {
            throw new RegraException("Escolha um leitor e um item");
        }
        // TODO 5: se o leitor atingiu o limite, lançar RegraException
        //         com a mensagem: <nome> já está com <LIMITE> itens
        // TODO 6: mandar a mensagem item.emprestar(). Se ela devolver false,
        //         lançar RegraException: "<título>" já está emprestado
        // TODO 7: o leitor recebe o item (leitor.receber). Depois, criar o
        //         Emprestimo(leitor, item, data), guardar em "emprestimos" e devolvê-lo.
        if (leitor.atingiuLimite()) {
            throw new RegraException(leitor.getNome() + " já está com " + Leitor.LIMITE + " itens");
        }
        if (!item.emprestar()) {
            throw new RegraException("\"" + item.getTitulo() + "\" já está emprestado");
        }
        leitor.receber(item);
        Emprestimo emprestimo = new Emprestimo(leitor, item, data);
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public void devolver(Leitor leitor, ItemBiblioteca item) {
        if (leitor == null || item == null) {
            throw new RegraException("Escolha um leitor e um item");
        }
        // TODO 8: se o leitor NÃO está com o item (leitor.estaCom), lançar
        //         RegraException: <nome> não está com "<título>"
        // TODO 9: o leitor entrega o item, o item é devolvido, e o empréstimo
        //         ativo deste item é encerrado (e.isAtivo() && e.getItem() == item).
        if (!leitor.estaCom(item)) {
            throw new RegraException(leitor.getNome() + " não está com \"" + item.getTitulo() + "\"");
        }
        leitor.entregar(item);
        item.devolver();
        for (Emprestimo e : emprestimos) {
            if (e.isAtivo() && e.getItem() == item) {
                e.encerrar();
                break;
            }
        }
    }

    public ArrayList<Emprestimo> emprestimosAtivos() {
        ArrayList<Emprestimo> ativos = new ArrayList<>();
        // TODO 10: colocar em "ativos" só os empréstimos que estão ativos.
        for (Emprestimo e : emprestimos) {
            if (e.isAtivo()) {
                ativos.add(e);
            }
        }
        return ativos;
    }

    public String relatorio() {
        return nome + "\nItens: " + acervo.size()
                + " | Disponíveis: " + itensDisponiveis().size()
                + " | Leitores: " + leitores.size()
                + " | Empréstimos em aberto: " + emprestimosAtivos().size();
    }
}
