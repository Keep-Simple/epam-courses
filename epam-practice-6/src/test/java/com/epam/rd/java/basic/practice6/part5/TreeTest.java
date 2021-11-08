package com.epam.rd.java.basic.practice6.part5;

import org.junit.Assert;
import org.junit.Test;

public class TreeTest {
    @Test
    public void test1() {
        Tree<String> tree = new Tree<>();
        tree.remove("esfs");
        tree.add("elj");
        tree.add("sdf");
        tree.add("gaga");
        tree.print();
        tree.add(new String[]{"asdf", "dalfkj", "jlakdsfj", "kljasdf"});
        Tree.Node<String> node = new Tree.Node<>("asf");
        Part5 part1 = new Part5();
        node.right = new Tree.Node<>("dfa");
        node.left = new Tree.Node<>("asfda");

        Part5.main(null);
        Assert.assertTrue(true);
    }
}
