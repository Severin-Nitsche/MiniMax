package com.github.severinnitsche.game.run;

import com.github.severinnitsche.algorithm.MiniMax;
import com.github.severinnitsche.game.abstracts.Turn;
import com.github.severinnitsche.game.exceutable.TicTacToe;
import com.github.severinnitsche.game.exceutable.TicTacToeTurn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        JFrame frame = new JFrame("TicTacToe");
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int s = Math.min(getWidth(), getHeight());
                BufferedImage img = new BufferedImage(s, s, BufferedImage.TYPE_INT_RGB);
                img.setRGB(0, 0, s, s, game.display(s), 0, s);
                g.drawImage(img, 0, 0, null);
            }
        };
        frame.add(panel);
        frame.setSize(500,500);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int min = Math.min(panel.getWidth(),panel.getHeight())/3;
                int x = e.getX()/min;
                int y = e.getY()/min;
                System.out.println(x+" "+y);
                if(x>2 || y>2) return;
                if (game.turn(new TicTacToeTurn(game.next(),x,y))) {
                    MiniMax smartBoii = new MiniMax();
                    Turn t = smartBoii.miniMax(game);
                    if (t != null) game.turn(t);
                }
                panel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
