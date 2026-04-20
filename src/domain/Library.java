package domain;

import util.structure.BinarySearchTree;

import java.util.*;

public class Library {
    private final BinarySearchTree<Book> bookTree = new BinarySearchTree<>();
    private final Stack<Book> browsingHistory = new Stack<>();
    private final Map<Book, Queue<User>> waitlists = new HashMap<>();
    private final Map<Book, Set<Book>> recommendations = new HashMap<>();

    public void addBook(Book book) {
        bookTree.insert(book);
        waitlists.put(book, new LinkedList<>());
    }

    public List<Book> getBooks() {
        return bookTree.getInOrder();
    }

    public Book searchBook(String title) {
        Book dummySearchBook = new Book(title, "Autor Falso", 2000);
        return bookTree.search(dummySearchBook);
    }

    private boolean containsBook(Book book) {
        return bookTree.search(book) != null;
    }

    public void viewBook(Book book) {
        if (!containsBook(book)) {
            throw new IllegalArgumentException("O livro não pertence ao catálogo desta biblioteca.");
        }
        browsingHistory.push(book);
    }

    public Book getLastViewedBook() {
        if (browsingHistory.isEmpty()) {
            return null;
        }
        return browsingHistory.peek();
    }

    public Stack<Book> getBrowsingHistory() {
        return browsingHistory;
    }

    public void joinWaitlist(Book book, String userName) {
        if (!containsBook(book)) {
            throw new IllegalArgumentException("O livro não pertence ao catálogo desta biblioteca.");
        }

        User user = new User(userName);
        Queue<User> queue = waitlists.get(book);
        queue.offer(user);
    }

    public Queue<User> getWaitlist(Book book) {
        if (!containsBook(book)) {
            throw new IllegalArgumentException("O livro não pertence ao catálogo desta biblioteca.");
        }

        return new LinkedList<>(waitlists.get(book));
    }

    public String callNextFromWaitlist(Book book) {
        Queue<User> queue = waitlists.get(book);

        if (queue == null || queue.isEmpty()) {
            return null;
        }

        return queue.poll().getName();
    }

    public void addRecommendation(Book book, Book recommendedBook) {
        if (!containsBook(book) || !containsBook(recommendedBook)) {
            throw new IllegalArgumentException("Ambos os livros devem pertencer ao catálogo desta biblioteca.");
        }

        recommendations.computeIfAbsent(book, (Book k) -> new HashSet<Book>()).add(recommendedBook);
    }

    public Set<Book> getRecommendations(Book book) {
        return recommendations.getOrDefault(book, Collections.emptySet());
    }

    public List<Book> getDfsPath(String title) {
        Book dummySearchBook = new Book(title, "Autor Falso", 2000);
        return bookTree.searchDFS(dummySearchBook);
    }

    public List<Book> getBfsPath(String title) {
        Book dummySearchBook = new Book(title, "Autor Falso", 2000);
        return bookTree.searchBFS(dummySearchBook);
    }

    public Map<Book, Integer> calculateDijkstra(Book source) {
        if (!containsBook(source)) {
            throw new IllegalArgumentException("O livro não pertence ao catálogo.");
        }

        Map<Book, Integer> distances = new HashMap<>();
        Queue<Book> queue = new LinkedList<>();

        distances.put(source, 0);
        queue.add(source);

        while (!queue.isEmpty()) {
            Book current = queue.poll();
            int currentDistance = distances.get(current);

            Set<Book> neighbors = recommendations.getOrDefault(current, Collections.emptySet());

            for (Book neighbor : neighbors) {
                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, currentDistance + 1);
                    queue.add(neighbor);
                }
            }
        }

        return distances;
    }
}