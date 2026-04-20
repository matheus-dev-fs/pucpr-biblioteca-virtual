package util;

import domain.Book;
import domain.BookCategory;
import domain.Library;
import util.structure.Pair;

import java.util.List;

public class LibrarySeeder {

    public static void seed(Library library) {
        Book cleanCode = new Book("Clean Code", "Robert C. Martin", 2008, BookCategory.PROGRAMMING);
        Book refactoring = new Book("Refactoring", "Martin Fowler", 1999, BookCategory.PROGRAMMING);
        Book designPatterns = new Book("Design Patterns", "Erich Gamma", 1994, BookCategory.PROGRAMMING);
        Book cleanArchitecture = new Book("Clean Architecture", "Robert C. Martin", 2017, BookCategory.PROGRAMMING);
        Book ddd = new Book("Domain-Driven Design", "Eric Evans", 2003, BookCategory.PROGRAMMING);

        Book lotr = new Book("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, BookCategory.FANTASY);
        Book hobbit = new Book("O Hobbit", "J.R.R. Tolkien", 1937, BookCategory.FANTASY);
        Book harryPotter = new Book("Harry Potter e a Pedra Filosofal", "J.K. Rowling", 1997, BookCategory.FANTASY);
        Book narnia = new Book("As Crônicas de Nárnia", "C.S. Lewis", 1950, BookCategory.FANTASY);
        Book windName = new Book("O Nome do Vento", "Patrick Rothfuss", 2007, BookCategory.FANTASY);

        Book orwell1984 = new Book("1984", "George Orwell", 1949, BookCategory.SCIENCE_FICTION_AND_DYSTOPIA);
        Book braveNewWorld = new Book("Admirável Mundo Novo", "Aldous Huxley", 1932, BookCategory.SCIENCE_FICTION_AND_DYSTOPIA);
        Book dune = new Book("Duna", "Frank Herbert", 1965, BookCategory.SCIENCE_FICTION_AND_DYSTOPIA);
        Book fahrenheit = new Book("Fahrenheit 451", "Ray Bradbury", 1953, BookCategory.SCIENCE_FICTION_AND_DYSTOPIA);
        Book foundation = new Book("Fundação", "Isaac Asimov", 1951, BookCategory.SCIENCE_FICTION_AND_DYSTOPIA);

        LibrarySeeder.addBooksToLibrary(
                library,
                cleanCode, refactoring, designPatterns, cleanArchitecture, ddd,
                lotr, hobbit, harryPotter, narnia, windName,
                orwell1984, braveNewWorld, dune, fahrenheit, foundation
        );

        List<Pair<Book>> recommendations = List.of(
                new Pair<>(cleanCode, cleanArchitecture),
                new Pair<>(cleanCode, refactoring),
                new Pair<>(refactoring, cleanCode),
                new Pair<>(refactoring, designPatterns),
                new Pair<>(designPatterns, refactoring),
                new Pair<>(designPatterns, cleanArchitecture),
                new Pair<>(cleanArchitecture, cleanCode),
                new Pair<>(cleanArchitecture, ddd),
                new Pair<>(ddd, cleanArchitecture),
                new Pair<>(ddd, designPatterns),

                new Pair<>(lotr, hobbit),
                new Pair<>(lotr, narnia),
                new Pair<>(hobbit, lotr),
                new Pair<>(hobbit, windName),
                new Pair<>(harryPotter, narnia),
                new Pair<>(harryPotter, windName),
                new Pair<>(narnia, lotr),
                new Pair<>(narnia, harryPotter),
                new Pair<>(windName, hobbit),
                new Pair<>(windName, harryPotter),

                new Pair<>(orwell1984, braveNewWorld),
                new Pair<>(orwell1984, fahrenheit),
                new Pair<>(braveNewWorld, orwell1984),
                new Pair<>(braveNewWorld, fahrenheit),
                new Pair<>(dune, foundation),
                new Pair<>(dune, orwell1984),
                new Pair<>(fahrenheit, orwell1984),
                new Pair<>(fahrenheit, braveNewWorld),
                new Pair<>(foundation, dune),
                new Pair<>(foundation, braveNewWorld),

                new Pair<>(cleanCode, orwell1984),

                new Pair<>(dune, lotr)
        );

        LibrarySeeder.addRecommendations(library, recommendations);
    }

    private static void addBooksToLibrary(Library library, Book... books) {
        for (Book book : books) {
            library.addBook(book);
        }
    }

    private static void addRecommendations(Library library, List<Pair<Book>> recommendations) {
        for (Pair<Book> pair : recommendations) {
            library.addRecommendation(pair.getFirst(), pair.getSecond());
        }
    }
}