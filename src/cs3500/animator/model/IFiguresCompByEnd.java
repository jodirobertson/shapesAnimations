package cs3500.animator.model;

import java.util.Comparator;

/**
 * Represent the comparison of two figures based on when they disappear in the scene.
 */
public class IFiguresCompByEnd implements Comparator<IFigure> {

  @Override
  public int compare(IFigure first, IFigure second) {
    return first.getDisappearTime() - second.getDisappearTime();
  }
}