package domain;

import java.util.*;

public class Library {
    private final LinkedList<Book> books = new LinkedList<>();
    private final Stack<Book> browsingHistory = new Stack<>();
    private final Map<Book, Queue<User>> waitlists = new HashMap<>();
    private final Map<Book, Set<Book>> recommendations = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
        waitlists.put(book, new LinkedList<>());
    }

    public void removeBook(Book book) {
        books.remove(book);
        waitlists.remove(book);
    }

    public LinkedList<Book> getBooks() {
        return new LinkedList<>(books);
    }

    public void viewBook(Book book) {
        if (!books.contains(book)) {
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
        if (!books.contains(book)) {
            throw new IllegalArgumentException("O livro não pertence ao catálogo desta biblioteca.");
        }

        User user = new User(userName);
        Queue<User> queue = waitlists.get(book);
        queue.offer(user);
    }

    public Queue<User> getWaitlist(Book book) {
        if (!books.contains(book)) {
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
        if (!books.contains(book) || !books.contains(recommendedBook)) {
            throw new IllegalArgumentException("Ambos os livros devem pertencer ao catálogo desta biblioteca.");
        }

        recommendations.computeIfAbsent(book, (Book k) -> new HashSet<Book>()).add(recommendedBook);
    }

    public Set<Book> getRecommendations(Book book) {
        return recommendations.getOrDefault(book, Collections.emptySet());
    }
}