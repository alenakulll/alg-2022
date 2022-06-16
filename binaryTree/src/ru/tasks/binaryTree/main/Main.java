package ru.tasks.binaryTree.main;

import binaryTree.BinaryTree;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.put(8);
        tree.put(10);
        tree.put(14);
        tree.put(13);
        tree.put(3);
        tree.put(1);
        tree.put(6);
        tree.put(4);
        tree.put(7);
        System.out.println("Высота дерева: " + tree.treeHeight());
        System.out.println("Удаление значений 8, 3 и 10");
        tree.remove(8);
        tree.remove(3);
        tree.remove(10);
        ArrayList<Integer> list = tree.toArray();
        for (int value : list) {
            System.out.println(value);
        }
        System.out.println("Число 13 присутствует в дереве: " + tree.contains(13));
        System.out.println("Число 15 присутствует в дереве: " + tree.contains(15));
    }
}