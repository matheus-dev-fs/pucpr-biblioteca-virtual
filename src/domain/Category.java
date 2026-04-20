package domain;

import enums.BookCategory;

import java.util.Objects;

public final class Category {
    private final BookCategory value;

    public Category(BookCategory value) {
        this.value = Objects.requireNonNull(value, "A categoria não pode ser nula.");
    }

    public BookCategory getValue() {
        return value;
    }

    public String getDisplayName() {
        return value.getDisplayName();
    }

    public Category withValue(BookCategory newValue) {
        return new Category(newValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return value == category.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.getDisplayName();
    }
}

