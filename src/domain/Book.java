package domain;

import java.util.Objects;

public final class Book implements Comparable<Book> {

    private final Title title;
    private final Author author;
    private final ReleasedYear releasedYear;
    private final Category category;

    public Book(String title, String author, int releasedYear, BookCategory category) {
        this(new Title(title), new Author(author), new ReleasedYear(releasedYear), new Category(category));
    }

    public Book(Title title, Author author, ReleasedYear releasedYear, Category category) {
        this.title = Objects.requireNonNull(title, "O título não pode ser nulo.");
        this.author = Objects.requireNonNull(author, "O autor não pode ser nulo.");
        this.releasedYear = Objects.requireNonNull(releasedYear, "O ano de publicação não pode ser nulo.");
        this.category = Objects.requireNonNull(category, "A categoria não pode ser nula.");
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public ReleasedYear getReleasedYear() {
        return releasedYear;
    }

    public Category getCategory() {
        return category;
    }

    public Book withTitle(String newTitle) {
        return new Book(newTitle, author.getName(), releasedYear.getValue(), category.getValue());
    }

    public Book withAuthor(String newAuthor) {
        return new Book(title.getName(), newAuthor, releasedYear.getValue(), category.getValue());
    }

    public Book withReleasedYear(int newReleasedYear) {
        return new Book(title.getName(), author.getName(), newReleasedYear, category.getValue());
    }

    public Book withCategory(BookCategory newCategory) {
        return new Book(title.getName(), author.getName(), releasedYear.getValue(), newCategory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return title.equals(book.title)
                && author.equals(book.author)
                && releasedYear.equals(book.releasedYear)
                && category.equals(book.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, releasedYear, category);
    }

    @Override
    public String toString() {
        return String.format("[Título: %s | Autor: %s | Ano: %d | Categoria: %s]",
                title.getName(),
                author.getName(),
                releasedYear.getValue(),
                category.getDisplayName());
    }

    @Override
    public int compareTo(Book otherBook) {
        if (otherBook == null) {
            return 1;
        }
        
        return this.title.getName().compareToIgnoreCase(otherBook.getTitle().getName());
    }
}
