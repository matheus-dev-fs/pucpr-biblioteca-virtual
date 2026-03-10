package domain;

import java.util.LinkedList;

public class Library {
    private final LinkedList<Book> books = new LinkedList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public LinkedList<Book> getBooks() {
        return new LinkedList<>(books);
    }
}
