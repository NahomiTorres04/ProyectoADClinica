/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

/**
 *
 * @author Nahomi
 */
public class redondear extends AbstractBorder
{
    private final static int MARGIN = 40;
    private static final long serialVersionUID = 1L;
    private Color color;

    redondear(Color clr) {
        color = clr;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        ((Graphics2D) g).setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, MARGIN, MARGIN);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(MARGIN, MARGIN, MARGIN, MARGIN);
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = MARGIN;
        insets.top = MARGIN;
        insets.right = MARGIN;
        insets.bottom = MARGIN;
        return insets;
    }

    }



