package util.structure;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T data) {
        root = insertRecursive(root, data);
    }

    private Node<T> insertRecursive(Node<T> current, T data) {
        if (current == null) {
            return new Node<>(data);
        }

        int comparison = data.compareTo(current.getData());

        if (comparison < 0) {
            current.setLeft(insertRecursive(current.getLeft(), data));
        } else if (comparison > 0) {
            current.setRight(insertRecursive(current.getRight(), data));
        }

        return current;
    }

    public T search(T data) {
        Node<T> resultNode = searchRecursive(root, data);
        return resultNode != null ? resultNode.getData() : null;
    }

    private Node<T> searchRecursive(Node<T> current, T data) {
        if (current == null) {
            return null;
        }

        int comparison = data.compareTo(current.getData());

        if (comparison == 0) {
            return current;
        }

        if (comparison < 0) {
            return searchRecursive(current.getLeft(), data);
        } else {
            return searchRecursive(current.getRight(), data);
        }
    }

    public List<T> getInOrder() {
        List<T> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(Node<T> current, List<T> result) {
        if (current != null) {
            inOrderRecursive(current.getLeft(), result);
            result.add(current.getData());
            inOrderRecursive(current.getRight(), result);
        }
    }
}
