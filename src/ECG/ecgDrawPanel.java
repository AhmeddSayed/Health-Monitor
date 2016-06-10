/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECG;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Ahmed
 */
public class ecgDrawPanel extends javax.swing.JPanel {

    Point previousPoint;
    BufferedImage I;
    Graphics2D G;
    int point_s = 4;
    boolean isCancelled = false;

    /**
     * Creates new form ecgDrawPanel
     */
    public ecgDrawPanel() {
        setBounds(0, 0, 300, 150);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        G = I.createGraphics();
        G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        G.setColor(Color.white);
        G.fillRect(0, 0, 300, 150);
    }

    public void clear(int x, int w) {
        G.setColor(Color.white);
        G.fillRect(x, 0, w, getHeight()); //fill the rectangle with the specified int x, int y, int width, int height
    }

    public void move_1(int reading) {
        Point p1, p2;

        // Drawing the background
        G.setColor(Color.black);
        G.drawLine(5, getHeight() / 2, getWidth(), getHeight() / 2);
        G.drawImage(I, 0, 0, getWidth() - point_s, getHeight(), point_s, 0, getWidth(), getHeight(), null);
        clear(getWidth() - point_s, point_s);

        if (previousPoint == null) {
            p1 = new Point(0, getHeight() / 2);
        } else {
            p1 = previousPoint;
        }

        if (reading == 0) {
            p2 = new Point(0, getHeight() / 2);
        } else {
            p2 = new Point(0, (getHeight() / 2 - reading / 2));
        }

        //p2 = new Point(0, (reading * 90) / 238);
        // drawing the ecg lines
        G.setColor(Color.red);
        //G.drawLine(getWidth() - point_s - 1, getHeight() - p1.y, getWidth() - 1, getHeight() - p2.y);
        G.drawLine(getWidth() - point_s - 1, p1.y, getWidth() - 1, p2.y);
        previousPoint = p2;
        repaint();

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    void draw(int bpm) {
        move_1(0);
        if (bpm != 0) {
            move_1(bpm);
            move_1(-bpm);

            move_1(2);
            move_1(-2);

            move_1(2);
            move_1(-2);

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
