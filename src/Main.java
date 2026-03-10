import domain.Book;
import domain.Library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book("Livro 1", "Autor 1", 2000));
        library.addBook(new Book("Livro 2", "Autor 2", 2005));
        library.addBook(new Book("Livro 3", "Autor 3", 2010));
        library.addBook(new Book("Livro 4", "Autor 4", 2015));

        System.out.println("Catálogo inicial:");
        for (Book book : library.getBooks()) {
            System.out.println(book);
        }
    }
}