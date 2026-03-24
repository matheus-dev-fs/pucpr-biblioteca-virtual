package util;

import domain.Book;
import domain.Library;

import java.util.List;

public class LibrarySeeder {

    public static void seed(Library library) {
        Book cleanCode = new Book("Clean Code", "Robert C. Martin", 2008);
        Book designPatterns = new Book("Design Patterns", "Erich Gamma", 1994);
        Book dataStructures = new Book("Estruturas de Dados e Algoritmos com JavaScript", "Loiane Groner", 2018);
        Book pragmaticProgrammer = new Book("O Programador Pragmático", "Andrew Hunt", 1999);
        Book ddd = new Book("Domain-Driven Design", "Eric Evans", 2003);

        Book cleanArchitecture = new Book("Clean Architecture", "Robert C. Martin", 2017);
        Book refactoring = new Book("Refactoring", "Martin Fowler", 1999);
        Book introToAlgorithms = new Book("Introduction to Algorithms", "Thomas H. Cormen", 1989);
        Book headFirstDP = new Book("Head First Design Patterns", "Eric Freeman", 2004);
        Book microservices = new Book("Building Microservices", "Sam Newman", 2015);

        LibrarySeeder.addBooksToLibrary(
                library,
                cleanCode,
                designPatterns,
                dataStructures,
                pragmaticProgrammer,
                ddd,
                cleanArchitecture,
                refactoring,
                introToAlgorithms,
                headFirstDP,
                microservices
        );

        List<Pair<Book>> recommendations = List.of(
                new Pair<Book>(cleanCode, designPatterns),
                new Pair<Book>(cleanCode, dataStructures),
                new Pair<Book>(cleanCode, pragmaticProgrammer),

                new Pair<Book>(designPatterns, cleanArchitecture),
                new Pair<Book>(designPatterns, refactoring),

                new Pair<Book>(dataStructures, introToAlgorithms),
                new Pair<Book>(dataStructures, cleanCode),

                new Pair<Book>(pragmaticProgrammer, cleanCode),
                new Pair<Book>(pragmaticProgrammer, refactoring),

                new Pair<Book>(ddd, microservices),
                new Pair<Book>(ddd, cleanArchitecture),

                new Pair<Book>(cleanArchitecture, cleanCode),
                new Pair<Book>(cleanArchitecture, ddd),

                new Pair<Book>(refactoring, cleanCode),
                new Pair<Book>(refactoring, designPatterns),

                new Pair<Book>(introToAlgorithms, dataStructures),
                new Pair<Book>(introToAlgorithms, designPatterns),

                new Pair<Book>(headFirstDP, designPatterns),
                new Pair<Book>(headFirstDP, cleanCode),

                new Pair<Book>(microservices, ddd),
                new Pair<Book>(microservices, cleanArchitecture)
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
