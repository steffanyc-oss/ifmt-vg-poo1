package modelo;

import java.util.ArrayList;

/**
 * Prática de 17/11/2026.
 * O leitor só guarda a lista do que está levando.
 * Quem DECIDE se pode emprestar agora é a Biblioteca (camada de regras).
 */
public class Leitor {
    public static final int LIMITE = 2;
    private static int proximaMatricula = 1;   // um contador só, da classe

    private int matricula;
    private String nome;
    private ArrayList<ItemBiblioteca> itens;

    public Leitor(String nome) {
        this(proximaMatricula, nome);
    }

    public Leitor(int matricula, String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        if (matricula <= 0) {
            throw new IllegalArgumentException("Matrícula inválida");
        }
        this.matricula = matricula;
        this.nome = nome.trim();
        this.itens = new ArrayList<>();
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

    public ArrayList<ItemBiblioteca> getItens() {
        return new ArrayList<>(itens);
    }

    /** Chamado pela Biblioteca depois de conferir as regras. */
    public void receber(ItemBiblioteca item) {
        // TODO 3: guardar o item na lista "itens".
        itens.add(item);
    }

    /** Chamado pela Biblioteca na devolução. */
    public void entregar(ItemBiblioteca item) {
        // TODO 4: tirar o item da lista "itens".
        itens.remove(item);
    }

    @Override
    public String toString() {
        return matricula + " - " + nome + " (" + itens.size() + "/" + LIMITE + ")";
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) return true;
        if (!(outro instanceof Leitor)) return false;
        return matricula == ((Leitor) outro).matricula;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(matricula);
    }
}
