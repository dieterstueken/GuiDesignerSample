package org.example;

import javax.swing.*;

/**
 * version:     $
 * created by:  d.stueken
 * created on:  17.04.2020 11:30
 * modified by: $
 * modified on: $
 */
public class Hello extends JFrame {

    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.setVisible(true);
    }

    public Hello() {
        super("hello");

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ok.addActionListener(event -> dispose());
        pack();
    }

    private JButton ok;
    private JPanel panel;
}
