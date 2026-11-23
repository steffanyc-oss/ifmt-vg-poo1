package tela;

import java.util.Scanner;
import modelo.Emprestimo;
import modelo.ItemBiblioteca;
import modelo.Leitor;
import modelo.Livro;
import regras.Biblioteca;
import regras.RegraException;

/**
 * Prática de 17/11/2026.
 * Camada de TELA (console): só conversa com o usuário e com a Biblioteca.
 * Aqui não tem regra nenhuma: quem decide é a Biblioteca. A tela só mostra o resultado
 * ou a mensagem da RegraException.
 * Rode com Run File (Shift+F6) e digite as opções no Output.
 */
public class MenuBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Biblioteca do Campus VG");
        biblioteca.adicionarItem(new Livro("Dom Casmurro", "Machado de Assis", 1899));
        biblioteca.adicionarItem(new Livro("Vidas Secas", "Graciliano Ramos", 1938));
        biblioteca.cadastrarLeitor("Ana");
        biblioteca.cadastrarLeitor("Bruno");

        Scanner teclado = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("1-Listar  2-Emprestar  3-Devolver  4-Em aberto  0-Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(teclado.nextLine().trim());
            if (opcao == 1) {
                for (ItemBiblioteca item : biblioteca.getAcervo()) {
                    System.out.println("  " + item);
                }
                for (Leitor l : biblioteca.getLeitores()) {
                    System.out.println("  " + l);
                }
            } else if (opcao == 2 || opcao == 3) {
                System.out.print("Matrícula: ");
                Leitor leitor = biblioteca.buscarLeitor(Integer.parseInt(teclado.nextLine().trim()));
                System.out.print("Título: ");
                ItemBiblioteca item = biblioteca.buscarPorTitulo(teclado.nextLine());
                // TODO 11: dentro de um try: se a opção for 2, biblioteca.emprestar(leitor, item)
                //         e mostrar "Emprestado: " + o empréstimo; se for 3, biblioteca.devolver(leitor, item)
                //         e mostrar "Devolvido.". No catch (RegraException e), mostrar "Erro: " + e.getMessage().
                try {
                    if (opcao == 2) {
                        Emprestimo emprestimo = biblioteca.emprestar(leitor, item);
                        System.out.println("Emprestado: " + emprestimo);
                    } else {
                        biblioteca.devolver(leitor, item);
                        System.out.println("Devolvido.");
                    }
                } catch (RegraException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            } else if (opcao == 4) {
                for (Emprestimo e : biblioteca.emprestimosAtivos()) {
                    System.out.println("  " + e);
                }
            }
        }
        System.out.println("Até mais!");
        teclado.close();
    }
}
