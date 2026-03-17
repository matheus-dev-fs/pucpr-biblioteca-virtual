package domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Library {
    private final LinkedList<Book> books = new LinkedList<>();
    private final Stack<Book> browsingHistory = new Stack<>();
    private final Map<Book, Queue<User>> waitlists = new HashMap<>();

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


    public void joinWaitlist(Book book, String userName) {
        if (!books.contains(book)) {
            throw new IllegalArgumentException("O livro não pertence ao catálogo desta biblioteca.");
        }

        User user = new User(userName);
        Queue<User> queue = waitlists.get(book);
        queue.offer(user);
    }

    public String callNextFromWaitlist(Book book) {
        Queue<User> queue = waitlists.get(book);

        if (queue == null || queue.isEmpty()) {
            return null;
        }

        return queue.poll().getName();
    }

    public String peekNextInWaitlist(Book book) {
        Queue<User> queue = waitlists.get(book);

        if (queue == null || queue.isEmpty()) {
            return null;
        }

        return queue.peek().getName();
    }
}