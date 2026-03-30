import domain.Book;
import domain.Library;
import domain.User;
import util.InputHandler;
import util.LibrarySeeder;

import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            InputHandler.setScanner(scanner);

            Library library = new Library();
            LibrarySeeder.seed(library);

            System.out.println("Bem-vindo à Biblioteca Virtual!");

            boolean isRunning = true;
            while (isRunning) {
                printMenu();

                int option = InputHandler.getNumericValue(
                        "\nEscolha uma opção: ",
                        "Opção inválida. Digite um número inteiro.",
                        InputHandler.getScanner()::nextInt
                );

                switch (option) {
                    case 1:
                        listBooks(library);
                        waitForEnter();
                        break;
                    case 2:
                        viewBook(library);
                        waitForEnter();
                        break;
                    case 3:
                        showLastViewedBook(library);
                        waitForEnter();
                        break;
                    case 4:
                        showBrowsingHistory(library);
                        waitForEnter();
                        break;
                    case 5:
                        joinWaitlist(library);
                        waitForEnter();
                        break;
                    case 6:
                        callNextFromWaitlist(library);
                        waitForEnter();
                        break;
                    case 7:
                        showWaitlist(library);
                        waitForEnter();
                        break;
                    case 8:
                        suggestBooks(library);
                        waitForEnter();
                        break;
                    case 0:
                        System.out.println("\nEncerrando o sistema. Até logo!");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("\nOpção inválida. Tente novamente.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            InputHandler.closeScanner();
        }
    }

    private static void printMenu() {
        System.out.println("\n=============================================");
        System.out.println("1. Listar catálogo de livros");
        System.out.println("2. Acessar um livro");
        System.out.println("3. Ver último livro acessado");
        System.out.println("4. Ver histórico completo de navegação");
        System.out.println("5. Entrar na fila de espera de um livro");
        System.out.println("6. Chamar próximo da fila de espera");
        System.out.println("7. Ver fila de espera de um livro");
        System.out.println("8. Sugestões de Leitura"); // Nova opção
        System.out.println("0. Sair");
        System.out.println("=============================================");
    }

    private static void waitForEnter() {
        System.out.println("\nPressione ENTER para continuar...");
        InputHandler.getScanner().nextLine();
    }

    private static void listBooks(Library library) {
        System.out.println("\n--- Catálogo de Livros ---");
        for (Book book : library.getBooks()) {
            System.out.println(book);
        }
    }

    private static void viewBook(Library library) {
        System.out.println("\n--- Acessar Livro ---");
        Book book = searchBookByTitle(library);

        if (book != null) {
            library.viewBook(book);
            System.out.println("Você acessou as informações de: '" + book.getTitle().getName() + "'.");
            System.out.println(book);
        }
    }

    private static void showLastViewedBook(Library library) {
        System.out.println("\n--- Histórico de Navegação ---");
        Book lastBook = library.getLastViewedBook();

        if (lastBook != null) {
            System.out.println("Último livro acessado: " + lastBook.getTitle().getName());
        } else {
            System.out.println("O histórico está vazio. Você ainda não acessou nenhum livro.");
        }
    }

    public static void showBrowsingHistory(Library library) {
        System.out.println("\n--- Histórico Completo de Navegação ---");

        if (library.getBrowsingHistory().isEmpty()) {
            System.out.println("O histórico está vazio. Você ainda não acessou nenhum livro.");
            return;
        }

        library.getBrowsingHistory().forEach(System.out::println);
    }

    private static void joinWaitlist(Library library) {
        System.out.println("\n--- Entrar na Fila de Espera ---");
        Book book = searchBookByTitle(library);

        if (book != null) {
            String userName = InputHandler.getStringValue(
                    "Digite o seu nome para entrar na fila: ",
                    "O nome não pode ser vazio.",
                    (String name) -> name.trim().length() >= 2,
                    "O nome deve conter pelo menos 2 caracteres."
            );

            library.joinWaitlist(book, userName);
            System.out.println(userName + " entrou na fila com sucesso para o livro '" + book.getTitle().getName() + "'.");
        }
    }

    private static void callNextFromWaitlist(Library library) {
        System.out.println("\n--- Processar Fila de Espera ---");
        Book book = searchBookByTitle(library);

        if (book != null) {
            String nextUser = library.callNextFromWaitlist(book);

            if (nextUser != null) {
                System.out.println("O livro '" + book.getTitle().getName() + "' foi liberado para: " + nextUser);
            } else {
                System.out.println("A fila de espera para este livro está vazia no momento.");
            }
        }
    }

    public static void showWaitlist(Library library) {
        System.out.println("\n--- Fila de Espera ---");
        Book book = searchBookByTitle(library);

        if (book == null) {
            System.out.println("Não foi possível encontrar o livro para exibir a fila de espera.");
            return;
        }

        Queue<User> waitlist = library.getWaitlist(book);

        if (waitlist.isEmpty()) {
            System.out.println("A fila de espera para este livro está vazia no momento.");
            return;
        }

        System.out.println("Fila de espera para '" + book.getTitle().getName() + "':");
        waitlist.forEach((User user) -> System.out.println("- " + user.getName()));
    }

    private static Book searchBookByTitle(Library library) {
        String title = InputHandler.getStringValue(
                "Digite o título exato do livro: ",
                "O título não pode ser vazio."
        );

        for (Book book : library.getBooks()) {
            if (book.getTitle().getName().equalsIgnoreCase(title)) {
                return book;
            }
        }

        System.out.println("Livro não encontrado no catálogo.");
        return null;
    }

    private static void suggestBooks(Library library) {
        System.out.println("\n--- Sugestões de Leitura ---");

        Book lastBook = library.getLastViewedBook();

        if (lastBook == null) {
            System.out.println("Você ainda não acessou nenhum livro.");
            System.out.println("Acesse um livro (Opção 2) para receber recomendações personalizadas!");
            return;
        }

        System.out.println("Baseado na sua última leitura ('" + lastBook.getTitle().getName() + "'), sugerimos as seguintes obras relacionadas:");

        Set<Book> recommendations = library.getRecommendations(lastBook);

        if (recommendations.isEmpty()) {
            System.out.println("Nenhuma recomendação encontrada para este livro no momento.");
        } else {
            for (Book recommendedBook : recommendations) {
                System.out.println("-> " + recommendedBook);
            }
        }
    }
}