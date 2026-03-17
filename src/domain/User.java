package domain;

public final class User extends Name {

    public User(String name) {
        super(name);
    }

    @Override
    protected String getTypeName() {
        return "Usuário";
    }

    public String getName() {
        return value;
    }

    public User withName(String name) {
        return new User(name);
    }
}
