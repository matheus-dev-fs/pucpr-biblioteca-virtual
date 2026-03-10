package domain;

public final class Title extends Name {

    public Title(String name) {
        super(name);
    }

    @Override
    protected String getTypeName() {
        return "Título";
    }

    public String getName() {
        return value;
    }

    public Title withName(String name) {
        return new Title(name);
    }
}
