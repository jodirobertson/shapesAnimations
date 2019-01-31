package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.IFigure;
import cs3500.animator.model.IModel;

public class IViewImplSVGLayered extends IViewImplSVGAnimation {
  /**
   * Constructs an IView using SVG file formatting, given an IModel, tick, dimensions for the
   * composition window, and an output, and can handle layers of figures.
   */
  public IViewImplSVGLayered(IModel model, Appendable output, double tick,
                             int screenWidth, int screenHeight) throws IllegalArgumentException {
    super(model, output, tick, screenWidth, screenHeight);
  }

  public void play(ArrayList<String> figs) {
    // a list of animations that occur to a particular IFigure (as referenced by its unique name)
    List<IAnimation> animByShape;

    firstLine();

    // writes all figures into SVG format
    for (String fg : figs) {
      for (IFigure f : super.model.getFiguresByAdd()) {
        if (fg.equals(f.getName())) {
          IFigure fig = f;

          // writes all figures into SVG format
          switch (fig.getShape()) {
            case "oval":
              openOval(fig);
              // writes all animations on figure in between open and close shape code
              animByShape = super.model.getAnimationsByShape().get(fig.getName());
              for (IAnimation a : animByShape) {
                delegateAnimPrint(a);
              }
              closeOval();
              break;
            case "rectangle":
              openRectangle(fig);
              // writes all animations on figure in between open and close shape code
              animByShape = super.model.getAnimationsByShape().get(fig.getName());
              for (IAnimation a : animByShape) {
                delegateAnimPrint(a);
              }
              closeRectangle();
              break;
            default:
              return;
          }
        }
      }
    }
    lastLine();
  }
}
