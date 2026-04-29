import domain.Book;
import enums.BookCategory;
import domain.Library;
import domain.User;
import util.InputHandler;
import util.LibrarySeeder;
import util.sorting.BookNameGenerator;
import util.sorting.BubbleSort;
import util.sorting.MergeSort;

import java.util.*;

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
                    case 9:
                        suggestBooksDijkstra(library);
                        waitForEnter();
                        break;
                    case 10:
                        searchBookWithTrace(library);
                        waitForEnter();
                        break;
                    case 11:
                        compareSortingEfficiency();
                        waitForEnter();
                        break;
                    case 0:
                        System.out.println("\nEncerrando o sistema. Até logo!");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("\nOpção inválida. Tente novamente.");
                        waitForEnter();
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
        System.out.println("8. Sugestões de Leitura");
        System.out.println("9. Sugestões de Leitura (Dijkstra)");
        System.out.println("10. Comparar buscas avançadas");
        System.out.println("11. Ordenação (Bubble vs Merge)");
        System.out.println("0. Sair");
        System.out.println("=============================================");
    }

    private static void waitForEnter() {
        System.out.println("\nPressione ENTER para continuar...");
        InputHandler.getScanner().nextLine();
    }

    private static void listBooks(Library library) {
        System.out.println("\n--- Catálogo de Livros por Categoria ---");

        Map<BookCategory, List<Book>> booksByCategory = library.getBooksGroupedByCategory();

        if (booksByCategory.isEmpty()) {
            System.out.println("Não há livros cadastrados no catálogo.");
            return;
        }

        booksByCategory.forEach((category, books) -> {
            System.out.println("\n" + category.getDisplayName() + ":");
            books.forEach((Book book) -> System.out.println("- " + book));
        });
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

        Book foundBook = library.searchBook(title);

        if (foundBook == null) {
            System.out.println("Livro não encontrado no catálogo.");
        }

        return foundBook;
    }

    private static void suggestBooks(Library library) {
        System.out.println("\n--- Sugestões de Leitura ---");

        Book lastBook = library.getLastViewedBook();

        if (lastBook == null) {
            System.out.println("Você ainda não acessou nenhum livro.");
            System.out.println("Acesse um livro (Opção 2) para receber recomendações!");
            return;
        }

        System.out.println("Livros que possuem relação direta com '" + lastBook.getTitle().getName() + "':");

        Set<Book> recommendations = library.getRecommendations(lastBook);

        if (recommendations.isEmpty()) {
            System.out.println("Nenhuma recomendação direta encontrada para este livro.");
        } else {
            for (Book recommendedBook : recommendations) {
                System.out.println("-> " + recommendedBook);
            }
        }
    }

    private static void suggestBooksDijkstra(Library library) {
        System.out.println("\n--- Sugestões de Leitura (Dijkstra) ---");
        Book lastBook = library.getLastViewedBook();

        if (lastBook == null) {
            System.out.println("Acesse um livro para gerar recomendações.");
            return;
        }

        Map<Book, Integer> recommendations = library.calculateDijkstra(lastBook);
        recommendations.remove(lastBook);

        if (recommendations.isEmpty()) {
            System.out.println("Nenhuma recomendação encontrada.");
        } else {
            System.out.println("Baseado em: '" + lastBook.getTitle().getName() + "'");
            recommendations.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach((Map.Entry<Book, Integer> entry) -> {
                        String relevance = entry.getValue() == 1 ? "[Alta Relevância]" : "[Relacionado]";
                        System.out.printf("- %s %s (Distância: %d)\n",
                                entry.getKey().getTitle().getName(), relevance, entry.getValue());
                    });
        }
    }

    private static void searchBookWithTrace(Library library) {
        System.out.println("\n--- Comparar Buscas: Profundidade (DFS) vs Largura (BFS) ---");

        String title = InputHandler.getStringValue(
                "Digite o título do livro que deseja rastrear: ",
                "O título não pode ser vazio."
        );

        Book foundBook = library.searchBook(title);

        if (foundBook == null) {
            System.out.println("O livro '" + title + "' não foi encontrado no catálogo.");
            return;
        }

        System.out.println("\nLivro encontrado!");

        System.out.println("\n[ Rastro DFS - Busca em Profundidade ]");

        List<Book> dfsPath = library.getDfsPath(title);
        for (int i = 0; i < dfsPath.size(); i++) {
            System.out.println((i + 1) + "º nó visitado: " + dfsPath.get(i).getTitle().getName());
        }

        System.out.println("=> Total de passos no DFS: " + dfsPath.size());

        System.out.println("\n[ Rastro BFS - Busca em Largura ]");

        List<Book> bfsPath = library.getBfsPath(title);
        for (int i = 0; i < bfsPath.size(); i++) {
            System.out.println((i + 1) + "º nó visitado: " + bfsPath.get(i).getTitle().getName());
        }

        System.out.println("=> Total de passos no BFS: " + bfsPath.size());
    }

    private static void compareSortingEfficiency() {
        System.out.println("\n--- Avaliação de Eficiência de Ordenação ---");

        int amount = 10000;

        System.out.println("Gerando " + amount + " nomes de livros aleatórios...");
        String[] randomNames = BookNameGenerator.generateNames(amount);

        System.out.println("Executando Bubble Sort...");

        util.sorting.BubbleSort bubble = new BubbleSort();
        long startTimeBubble = System.currentTimeMillis();
        bubble.sort(randomNames);
        long endTimeBubble = System.currentTimeMillis();

        System.out.println("Executando Merge Sort...");

        util.sorting.MergeSort merge = new MergeSort();
        long startTimeMerge = System.currentTimeMillis();
        merge.sort(randomNames);
        long endTimeMerge = System.currentTimeMillis();

        System.out.println("\n================ RESULTADOS ================");
        System.out.printf("%-15s | %-15s | %-10s\n", "Algoritmo", "Comparações", "Tempo (ms)");
        System.out.println("--------------------------------------------");
        System.out.printf("%-15s | %-15d | %-10d\n", "Bubble Sort", bubble.getComparisons(), (endTimeBubble - startTimeBubble));
        System.out.printf("%-15s | %-15d | %-10d\n", "Merge Sort", merge.getComparisons(), (endTimeMerge - startTimeMerge));
        System.out.println("============================================");
    }
}