package util;

import domain.Book;
import domain.Library;

public class LibrarySeeder {

    public static void seed(Library library) {
        library.addBook(new Book("Clean Code", "Robert C. Martin", 2008));
        library.addBook(new Book("Design Patterns", "Erich Gamma", 1994));
        library.addBook(new Book("Estruturas de Dados e Algoritmos com JavaScript", "Loiane Groner", 2018));
        library.addBook(new Book("O Programador Pragmático", "Andrew Hunt", 1999));
        library.addBook(new Book("Domain-Driven Design", "Eric Evans", 2003));
    }
}
