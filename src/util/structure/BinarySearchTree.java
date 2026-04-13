package util.structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public List<T> searchDFS(T target) {
        List<T> visitedNodes = new ArrayList<>();
        searchDFSRecursive(root, target, visitedNodes);
        return visitedNodes;
    }

    private boolean searchDFSRecursive(Node<T> current, T target, List<T> visitedNodes) {
        if (current == null) {
            return false;
        }

        visitedNodes.add(current.getData());

        if (current.getData().compareTo(target) == 0) {
            return true;
        }

        if (searchDFSRecursive(current.getLeft(), target, visitedNodes)) {
            return true;
        }

        if (searchDFSRecursive(current.getRight(), target, visitedNodes)) {
            return true;
        }

        return false;
    }

    public List<T> searchBFS(T target) {
        List<T> visitedNodes = new ArrayList<>();

        if (root == null) {
            return visitedNodes;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            visitedNodes.add(current.getData());

            if (current.getData().compareTo(target) == 0) {
                return visitedNodes;
            }

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }

        return visitedNodes;
    }
}
