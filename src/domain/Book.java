package domain;

import java.util.Objects;

public final class Book {

    private final Title title;
    private final Author author;
    private final ReleasedYear releasedYear;

    public Book(String title, String author, int releasedYear) {
        this(new Title(title), new Author(author), new ReleasedYear(releasedYear));
    }

    public Book(Title title, Author author, ReleasedYear releasedYear) {
        this.title = Objects.requireNonNull(title, "O título não pode ser nulo.");
        this.author = Objects.requireNonNull(author, "O autor não pode ser nulo.");
        this.releasedYear = Objects.requireNonNull(releasedYear, "O ano de publicação não pode ser nulo.");
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

    public Book withTitle(String newTitle) {
        return new Book(newTitle, author.getName(), releasedYear.getValue());
    }

    public Book withAuthor(String newAuthor) {
        return new Book(title.getName(), newAuthor, releasedYear.getValue());
    }

    public Book withReleasedYear(int newReleasedYear) {
        return new Book(title.getName(), author.getName(), newReleasedYear);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return title.equals(book.title) && author.equals(book.author) && releasedYear.equals(book.releasedYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, releasedYear);
    }

    @Override
    public String toString() {
        return String.format("[Título: %s | Autor: %s | Ano: %d]",
                title.getName(),
                author.getName(),
                releasedYear.getValue());
    }
}
