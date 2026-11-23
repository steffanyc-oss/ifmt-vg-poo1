package regras;

import java.util.ArrayList;
import modelo.ItemBiblioteca;
import modelo.Leitor;

/**
 * Camada de REGRAS: toda regra violada vira RegraException.
 * Complete os TODO 9 a 12.
 */
public class Biblioteca {
    private String nome;
    private ArrayList<ItemBiblioteca> acervo;
    private ArrayList<Leitor> leitores;

    public Biblioteca(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        this.nome = nome;
        this.acervo = new ArrayList<>();
        this.leitores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    // ---------------- Acervo ----------------

    public void adicionarItem(ItemBiblioteca item) {
        if (item == null) {
            throw new IllegalArgumentException("Item obrigatório");
        }
        // TODO 9: se buscarPorTitulo(item.getTitulo()) não for null, lançar
        //         RegraException("Já existe um item com o título \"<título>\"").
        //         Dica: \" escreve aspas dentro de uma String.
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
        // TODO 10: percorrer o acervo e colocar em "livres" só os itens disponíveis.
        for (ItemBiblioteca item : acervo) {
            if (item.isDisponivel()) {
                livres.add(item);
            }
        }
        return livres;
    }

    // Desafio 1 (para quem terminar): buscarPorTrecho(String trecho), ver o LEIAME.

    // ---------------- Leitores ----------------

    public Leitor cadastrarLeitor(String nomeLeitor) {
        Leitor novo = new Leitor(nomeLeitor);
        leitores.add(novo);
        return novo;
    }

    /** Usado ao carregar do arquivo, quando a matrícula já existe. */
    public void adicionarLeitor(Leitor leitor) {
        // TODO 11: se a lista leitores já contém (contains) este leitor, lançar
        //          RegraException("Matrícula repetida: " + matrícula).
        //          O contains pergunta ao equals do Leitor (TODO 8).
        if (leitores.contains(leitor)) {
            throw new RegraException("Matrícula repetida: " + leitor.getMatricula());
        }
        leitores.add(leitor);
    }

    public Leitor buscarLeitor(int matricula) {
        // TODO 12: percorrer leitores e devolver o que tem esta matrícula.
        //          Se nenhum tiver, retornar null.
        for (Leitor leitor : leitores) {
            if (leitor.getMatricula() == matricula) {
                return leitor;
            }
        }
        return null;
    }

    public ArrayList<Leitor> getLeitores() {
        return new ArrayList<>(leitores);
    }

    public String relatorio() {
        return nome + "\nItens: " + acervo.size()
                + " | Disponíveis: " + itensDisponiveis().size()
                + " | Leitores: " + leitores.size();
    }
}
