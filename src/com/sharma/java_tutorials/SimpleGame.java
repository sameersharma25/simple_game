package com.sharma.java_tutorials;

import javax.swing.*;
import java.awt.*;

public class SimpleGame extends JFrame {

    private JLabel statusbar;
    public SimpleGame() {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);

        Board board = new Board(this);
        add(board);
        board.start();

        setTitle("First Game");
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public JLabel getStatusbar() {
        return statusbar;
    }

    public void setStatusbar(JLabel statusbar) {
        this.statusbar = statusbar;
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            SimpleGame game = new SimpleGame();
            game.setVisible(true);
        });

    }
}
