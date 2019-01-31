package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.IFigure;

public class MyPanel extends JPanel {

  private ArrayList<IFigure> drawingShapes = new ArrayList<>();

  MyPanel() {
    super(true);
  }

  void drawShapes(ArrayList<IFigure> drawingShapes) {
    this.drawingShapes = drawingShapes;
  }

  private Color formatColor(double[] clr) {
    return new Color((float) clr[0], (float) clr[1], (float) clr[2]);
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();

    //UPDATE: added Affine Transform to account for rotating animations

    for (IFigure f : drawingShapes) {
      switch (f.getShape()) {
        case "rectangle":
          AffineTransform atR = g2d.getTransform();
          g2d.rotate(Math.toRadians(f.getDegree()), f.getXPos() + f.getXDim() / 2,
                  f.getYPos() + f.getYDim() / 2);
          g2d.setColor(formatColor(f.getColor()));
          g2d.fillRect((int) f.getXPos(), (int) f.getYPos(), (int) f.getXDim(), (int) f.getYDim());
          g2d.setTransform(atR);
          break;
        case "oval":
          AffineTransform atO = g2d.getTransform();
          g2d.rotate(Math.toRadians(f.getDegree()), f.getXPos() + f.getXDim() / 2,
                  f.getYPos() + f.getYDim() / 2);
          g2d.setColor(formatColor(f.getColor()));
          g2d.fillOval((int) f.getXPos(), (int) f.getYPos(),
                  (int) f.getXDim() * 2, (int) f.getYDim() * 2);
          g2d.setTransform(atO);
          break;
        default:
          throw new IllegalArgumentException("Invalid shape type");
      }
    }
  }
}
