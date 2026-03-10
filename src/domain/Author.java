package domain;

public final class Author extends Name {

    public Author(String name) {
        super(name);
    }

    @Override
    protected String getTypeName() {
        return "Autor";
    }

    public String getName() {
        return value;
    }

    public Author withName(String name) {
        return new Author(name);
    }
}
