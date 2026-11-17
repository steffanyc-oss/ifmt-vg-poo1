/**
 * Teste pronto da prática de 03/11/2026.
 * Não altere este arquivo. Implemente Leitor e Biblioteca.
 * Antes, copie para esta pasta src o seu ItemBiblioteca.java e o seu Livro.java
 * da prática de 27/10, já corrigidos.
 */
public class Aplicacao {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Biblioteca do Campus VG");
        Livro principe = new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry");
        Livro domCasmurro = new Livro("Dom Casmurro", "Machado de Assis");
        Livro vidasSecas = new Livro("Vidas Secas", "Graciliano Ramos");
        biblioteca.adicionar(principe);
        biblioteca.adicionar(domCasmurro);
        biblioteca.adicionar(vidasSecas);
        System.out.println("Itens no acervo: " + biblioteca.quantidadeItens());
        // Esperado: Itens no acervo: 3

        Leitor ana = new Leitor("Ana");
        Leitor bruno = new Leitor("Bruno");

        System.out.println("Ana pega Dom Casmurro: " + ana.pegar(domCasmurro));
        // Esperado: Ana pega Dom Casmurro: true
        System.out.println("Bruno pega Dom Casmurro: " + bruno.pegar(domCasmurro));
        // Esperado: Bruno pega Dom Casmurro: false   (já está com a Ana)
        System.out.println("Ana pega Vidas Secas: " + ana.pegar(vidasSecas));
        // Esperado: Ana pega Vidas Secas: true
        System.out.println("Ana pega O Pequeno Príncipe: " + ana.pegar(principe));
        // Esperado: Ana pega O Pequeno Príncipe: false   (limite de 2)
        System.out.println("Ana está com " + ana.quantidadeEmprestados() + " itens");
        // Esperado: Ana está com 2 itens
        System.out.println("Disponíveis: " + biblioteca.contarDisponiveis());
        // Esperado: Disponíveis: 1

        System.out.println("Bruno devolve Vidas Secas: " + bruno.devolver(vidasSecas));
        // Esperado: Bruno devolve Vidas Secas: false   (não está com ele)
        System.out.println("Ana devolve Dom Casmurro: " + ana.devolver(domCasmurro));
        // Esperado: Ana devolve Dom Casmurro: true
        System.out.println("Bruno pega Dom Casmurro: " + bruno.pegar(domCasmurro));
        // Esperado: Bruno pega Dom Casmurro: true

        ItemBiblioteca achado = biblioteca.buscarPorTitulo("vidas secas");
        System.out.println("Busca: " + achado.descricao());
        // Esperado: Busca: Livro: Vidas Secas | Autor: Graciliano Ramos
        System.out.println("Busca sem resultado: " + biblioteca.buscarPorTitulo("Iracema"));
        // Esperado: Busca sem resultado: null

        System.out.println(ana.listarEmprestados());
        // Esperado:
        // Ana está com:
        // - Livro: Vidas Secas | Autor: Graciliano Ramos

        System.out.println(biblioteca.relatorio());
        // Esperado:
        // Biblioteca do Campus VG
        // [disponível] Livro: O Pequeno Príncipe | Autor: Antoine de Saint-Exupéry
        // [emprestado] Livro: Dom Casmurro | Autor: Machado de Assis
        // [emprestado] Livro: Vidas Secas | Autor: Graciliano Ramos
    }
}
