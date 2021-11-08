package com.epam.rd.java.basic.practice6.part5;


import java.util.Arrays;

public class Tree<E extends Comparable<E>> {
    private Node<E> root;
    private Node<E> parent;

    public boolean add(E element) {
        if (root == null) {
            this.root = new Node<>(element);
            return true;
        }

        Node<E> cur = root;

        while (cur != null) {
            int c = cur.value.compareTo(element);
            if (c == 0) return false;
            cur = helper(c, cur, element);
        }

        return true;
    }

    private Node<E> helper(int c, Node<E> cur, E e) {
        boolean isLeft = c > 0;

        if (isLeft && cur.left == null) {
            cur.left = new Node<>(e);
            return null;
        }
        if (!isLeft && cur.right == null) {
            cur.right = new Node<>(e);
            return null;
        }

        return isLeft ? cur.left : cur.right;
    }

    public void add(E[] elements) {
        Arrays.stream(elements).forEach(this::add);
    }

    public boolean remove(E element) {
        // Deleting root Node, third case is cowered in Else block
        if (this.root != null && this.root.value.compareTo(element) == 0) {
            if (this.root.left == null && this.root.right == null) {
                this.root = null;
                return true;
            }

            if (deleteNodeWithOneChild(this.root)) return true;
        }

        Node<E> node = find(element);
        Node<E> temp;

        if (node == null) return false;

        // Deleting leaf
        if (deleteLeaf(node)) return true;

        // Else
        temp = findBiggestLeft(node);
        node.value = temp.value;
        deleteLeaf(temp);
        return true;
    }

    private boolean deleteNodeWithOneChild(Node<E> node) {
        if (node == null || (node.left != null && node.right != null) || (node.left == null && node.right == null)) return false;
        boolean hasLeft = node.right == null;

        if (hasLeft) {
            clone(node, node.left);
            return true;
        }

        clone(node, node.right);
        return true;
    }

    private boolean deleteLeaf(Node<E> node) {
        if (node == null) return false;
        if (node.left == null && node.right == null) {
            boolean isLeft = this.parent.left.value.compareTo(node.value) == 0;
            if (isLeft) this.parent.left = null;
            else this.parent.right = null;
            return true;
        }
        return false;
    }

    private void clone(Node<E> to, Node<E> from) {
        to.value = from.value;
        to.right = from.right;
        to.left = from.left;
    }

    private Node<E> findBiggestLeft(Node<E> node) {
        node = node.left;

        if (node == null) throw new IllegalArgumentException("Node doesn't have left child");

        while (node.right != null) {
            this.parent = node;
            node = node.right;
        }

        return node;
    }

    private Node<E> find(E element) {
        Node<E> cur = root;

        while (cur != null) {
            int c = cur.value.compareTo(element);

            if (c == 0) return cur;

            this.parent = cur;

            cur = c > 0 ? cur.left : cur.right;
        }

        return null;
    }

    public void print() {
        printHelper(this.root, "");
    }

    private void printHelper(Node<E> node, String s) {
        if (node != null) {
            printHelper(node.left, s + "  ");
            System.out.println(s + node.value);
            printHelper(node.right, s + "  ");
        }
    }

    static final class Node<E> {
        Node<E> left;
        Node<E> right;
        E value;

        public Node(E value) {
            this.value = value;
        }
    }

}
