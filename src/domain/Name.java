package domain;

public abstract class Name {
    protected final String value;
    private static final int MIN_LENGTH = 2;

    protected abstract String getTypeName();

    protected Name(String value) {
        if (value == null) {
            throw new IllegalArgumentException(getTypeName() + " não pode ser nulo.");
        }

        String normalized = value.trim();

        if (normalized.isEmpty()) {
            throw new IllegalArgumentException(getTypeName() + " não pode ser vazio.");
        }

        if (normalized.length() < MIN_LENGTH) {
            throw new IllegalArgumentException(getTypeName() + " deve ter pelo menos " + MIN_LENGTH + " caracteres.");
        }

        this.value = normalized;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name other = (Name) o;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
