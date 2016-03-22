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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed
 */
public class ecgDrawPanel extends javax.swing.JPanel {

    Point previousPoint;
    BufferedImage I;
    Graphics2D G;
    int point_s = 4;

    /**
     * Creates new form ecgDrawPanel
     */
    public ecgDrawPanel() {
        setBounds(10, 10, 600, 300);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        G = I.createGraphics();
        G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        G.setColor(Color.white);
        G.fillRect(0, 0, 600, 300);
        G.drawLine(0, 0, 600, 600);
        repaint();
    }

    public void clear(int x, int w) {
        G.setColor(Color.white);
        G.fillRect(x, 0, w, getHeight()); //fill the rectangle with the specified int x, int y, int width, int height
    }

    public void move_1() {

        while (true) {
            try {
                Point p1, p2;
                int p = (int) (Math.random() * 100);
                if (previousPoint == null) {
                    p1 = new Point(0, p - 50);
                } else {
                    p1 = previousPoint;
                }   p2 = new Point(0, p - 50);
                G.drawImage(I, 0, 0, getWidth() - point_s, getHeight(), point_s, 0, getWidth(), getHeight(), null);
                clear(getWidth() - point_s, point_s);
                G.setColor(Color.gray);
                G.drawLine(getWidth() - point_s - 1, p1.y + getHeight() / 2, getWidth() - 1, p2.y + getHeight() / 2);
                previousPoint = p2;
                repaint();
                // allPoints.remove(0);
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ecgDrawPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}