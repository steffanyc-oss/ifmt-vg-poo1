package testes;

import java.time.LocalDate;
import modelo.Livro;
import modelo.Leitor;
import modelo.Revista;
import regras.Biblioteca;
import regras.RegraException;

/**
 * Prática de 17/11/2026.
 * Testa as REGRAS sem abrir tela nenhuma (cap. 13). Rode com Run File (Shift+F6).
 * Cada teste começa com uma biblioteca nova: um teste não atrapalha o outro.
 */
public class TesteBiblioteca {
    private static final LocalDate DIA = LocalDate.of(2026, 11, 17);

    public static void main(String[] args) {
        testeEmprestimoMarcaItem();
        testeLimiteDoLeitor();
        testeItemJaEmprestado();
        testeDevolverQuemNaoEsta();
        testeDevolucaoLiberaItem();
        Verificador.resumo();
    }

    private static Biblioteca novaBiblioteca() {
        Biblioteca b = new Biblioteca("Teste");
        b.adicionarItem(new Livro("Dom Casmurro", "Machado de Assis"));
        b.adicionarItem(new Livro("Vidas Secas", "Graciliano Ramos"));
        b.adicionarItem(new Revista("Revista do Campus", 1));
        return b;
    }

    private static void testeEmprestimoMarcaItem() {
        Biblioteca b = novaBiblioteca();
        Leitor ana = b.cadastrarLeitor("Ana");
        b.emprestar(ana, b.buscarPorTitulo("Dom Casmurro"), DIA);
        Verificador.verificar(!b.buscarPorTitulo("Dom Casmurro").isDisponivel(), "emprestar marca o item como emprestado");
        Verificador.verificar(ana.quantidadeItens() == 1, "o leitor fica com o item");
        Verificador.verificar(b.emprestimosAtivos().size() == 1, "o empréstimo fica em aberto");
    }

    private static void testeLimiteDoLeitor() {
        Biblioteca b = novaBiblioteca();
        Leitor ana = b.cadastrarLeitor("Ana");
        b.emprestar(ana, b.buscarPorTitulo("Dom Casmurro"), DIA);
        b.emprestar(ana, b.buscarPorTitulo("Vidas Secas"), DIA);
        try {
            b.emprestar(ana, b.buscarPorTitulo("Revista do Campus"), DIA);
            Verificador.verificar(false, "o terceiro item é recusado (limite 2)");
        } catch (RegraException e) {
            Verificador.verificar(true, "o terceiro item é recusado (limite 2)");
        }
        Verificador.verificar(b.buscarPorTitulo("Revista do Campus").isDisponivel(), "item recusado continua disponível");
    }

    private static void testeItemJaEmprestado() {
        Biblioteca b = novaBiblioteca();
        Leitor ana = b.cadastrarLeitor("Ana");
        Leitor bruno = b.cadastrarLeitor("Bruno");
        b.emprestar(ana, b.buscarPorTitulo("Dom Casmurro"), DIA);
        try {
            b.emprestar(bruno, b.buscarPorTitulo("Dom Casmurro"), DIA);
            Verificador.verificar(false, "item emprestado não sai de novo");
        } catch (RegraException e) {
            Verificador.verificar(true, "item emprestado não sai de novo");
        }
        Verificador.verificar(bruno.quantidadeItens() == 0, "quem foi recusado fica sem o item");
    }

    private static void testeDevolverQuemNaoEsta() {
        Biblioteca b = novaBiblioteca();
        Leitor bruno = b.cadastrarLeitor("Bruno");
        // TODO 12: escreva o teste: Bruno tenta devolver "Vidas Secas", que ele não pegou.
        //         Use try/catch como nos testes acima e verifique que a RegraException acontece.
        try {
            b.devolver(bruno, b.buscarPorTitulo("Vidas Secas"));
            Verificador.verificar(false, "não pode devolver item que não pegou");
        } catch (RegraException e) {
            Verificador.verificar(true, "não pode devolver item que não pegou");
        }
    }

    private static void testeDevolucaoLiberaItem() {
        Biblioteca b = novaBiblioteca();
        Leitor ana = b.cadastrarLeitor("Ana");
        // TODO 13: escreva o teste: Ana pega e devolve "Dom Casmurro". Verifique que o item
        //         voltou a ficar disponível e que não há mais empréstimos em aberto.
        b.emprestar(ana, b.buscarPorTitulo("Dom Casmurro"), DIA);
        b.devolver(ana, b.buscarPorTitulo("Dom Casmurro"));
        Verificador.verificar(b.buscarPorTitulo("Dom Casmurro").isDisponivel(), "devolução libera o item");
        Verificador.verificar(b.emprestimosAtivos().isEmpty(), "devolução encerra o empréstimo");
    }
}
