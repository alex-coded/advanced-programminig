package app;

import frame.MainFrame;

import javax.swing.*;

    public class Main {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainFrame game = new MainFrame();
                }
            });
        }
    }


