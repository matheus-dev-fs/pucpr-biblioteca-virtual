package enums;

public enum BookCategory {
    PROGRAMMING("Programação"),
    FANTASY("Fantasia"),
    SCIENCE_FICTION_AND_DYSTOPIA("Ficção Científica e Distopia");

    private final String displayName;

    BookCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

