package modelo;

import java.util.ArrayList;

/**
 * Leitor com matrícula automática e a lista do que está levando (associação).
 * Complete os TODO 5 a 8. O resto já vem pronto.
 */
public class Leitor {
    public static final int LIMITE = 2;
    private static int proximaMatricula = 1;   // static: UM contador para a classe toda

    private int matricula;
    private String nome;
    private ArrayList<ItemBiblioteca> itens;

    /** Leitor novo: recebe a próxima matrícula. */
    public Leitor(String nome) {
        // TODO 5: apague a linha abaixo e chame o outro construtor passando
        //         proximaMatricula e nome: this(proximaMatricula, nome);
        this(proximaMatricula, nome);
    }

    /** Leitor que já existia (lido de um arquivo): mantém a matrícula. */
    public Leitor(int matricula, String nome) {
        // TODO 6a: nome null ou em branco: IllegalArgumentException("Nome obrigatório").
        // TODO 6b: matricula <= 0: IllegalArgumentException("Matrícula inválida").
        // TODO 6c: guardar a matrícula e nome.trim().
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        if (matricula <= 0) {
            throw new IllegalArgumentException("Matrícula inválida");
        }
        this.matricula = matricula;
        this.nome = nome.trim();
        this.itens = new ArrayList<>();
        // TODO 6d: se matricula >= proximaMatricula, avançar o contador para
        //          matricula + 1. Assim o próximo leitor nunca repete um número.
        if (matricula >= proximaMatricula) {
            proximaMatricula = matricula + 1;
        }
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public int quantidadeItens() {
        return itens.size();
    }

    public boolean atingiuLimite() {
        return itens.size() >= LIMITE;
    }

    public boolean estaCom(ItemBiblioteca item) {
        return itens.contains(item);
    }

    /** Uma cópia da lista: quem está fora não consegue mexer na original. */
    public ArrayList<ItemBiblioteca> getItens() {
        return new ArrayList<>(itens);
    }

    // pegar e devolver: os mesmos de 03/11, prontos.
    // Na próxima prática, quem decide isto passa a ser a Biblioteca.
    public boolean pegar(ItemBiblioteca item) {
        if (atingiuLimite()) {
            return false;
        }
        if (!item.emprestar()) {
            return false;
        }
        itens.add(item);
        return true;
    }

    public boolean devolver(ItemBiblioteca item) {
        if (!estaCom(item)) {
            return false;
        }
        itens.remove(item);
        item.devolver();
        return true;
    }

    @Override
    public String toString() {
        // TODO 7: matrícula, " - ", nome, " (", quantos itens, "/", LIMITE, ")".
        //         Exemplo: 1 - Ana (0/2)
        return matricula + " - " + nome + " (" + itens.size() + "/" + LIMITE + ")";
    }

    @Override
    public boolean equals(Object outro) {
        // TODO 8: dois leitores são o mesmo leitor se têm a mesma matrícula.
        //         1) se this == outro, retornar true;
        //         2) se outro não for instanceof Leitor, retornar false;
        //         3) comparar matricula == ((Leitor) outro).matricula.
        if (this == outro) {
            return true;
        }
        if (!(outro instanceof Leitor)) {
            return false;
        }
        return matricula == ((Leitor) outro).matricula;
    }

    // Anda junto com o equals: objetos iguais precisam ter o mesmo hashCode.
    @Override
    public int hashCode() {
        return Integer.hashCode(matricula);
    }
}
