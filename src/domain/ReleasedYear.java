package domain;

import java.time.DateTimeException;
import java.time.Year;
import java.util.Objects;

public final class ReleasedYear {
    private final Year year;

    public ReleasedYear(Year year) {
        if (year == null) {
            throw new IllegalArgumentException("O ano de publicação não pode ser nulo.");
        }

        Year currentYear = Year.now();
        if (year.isAfter(currentYear)) {
            throw new IllegalArgumentException("O ano de publicação não pode ser no futuro.");
        }

        this.year = year;
    }

    public ReleasedYear(int year) {
        this(toYear(year));
    }

    private static Year toYear(int year) {
        try {
            return Year.of(year);
        } catch (DateTimeException exception) {
            throw new IllegalArgumentException("Ano de publicação inválido: " + year + ".", exception);
        }
    }

    public Year getYear() {
        return year;
    }

    public int getValue() {
        return year.getValue();
    }

    public ReleasedYear withYear(Year newYear) {
        return new ReleasedYear(newYear);
    }

    public ReleasedYear withYear(int newYear) {
        return new ReleasedYear(newYear);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReleasedYear that)) return false;
        return year.equals(that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year);
    }

    @Override
    public String toString() {
        return String.valueOf(year.getValue());
    }
}
