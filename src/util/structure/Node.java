package util.structure;

public class Node<T> {
    private final T data;
    private Node<T> left;
    private Node<T> right;

    public Node(T data) {
        if (data == null) {
            throw new IllegalArgumentException("O dado do nó não pode ser nulo.");
        }

        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}