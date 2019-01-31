package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.IFigure;
import cs3500.animator.model.IModel;

public class IViewImplSVGLayeredLooped extends IViewImplSVGLooped {
  /**
   * Constructs an IView using SVG file formatting, given an IModel, tick, dimensions for the
   * composition window, and an output, and has the ability to make it a looped animation, as well
   * as layers of IFigures.
   */
  public IViewImplSVGLayeredLooped(IModel model, Appendable output, double tick,
                                   int screenWidth, int screenHeight) throws IllegalArgumentException {
    super(model, output, tick, screenWidth, screenHeight);
  }

  public void play(List<String> figs) {
    // a list of animations that occur to a particular IFigure (as referenced by its unique name)
    List<IAnimation> animByShape;

    super.firstLine();

    String addLoop = "<rect> \n <animate id=\"base\" begin=\"0;base.end\" dur=\""
            + super.f.format(model.maxTime() * 1000 / tick)
            + "ms\" " + "attributeName=\"visibility\" "
            + "from=\"hide\" to=\"hide\"/> </rect>";
    appendHelper(super.output, addLoop);


    // writes all figures into SVG format
    for(String fg : figs) {
      for (IFigure f : super.model.getFiguresByAdd()) {
        if (fg.equals(f.getName())) {
          IFigure fig = f;

          switch (fig.getShape()) {
            case "oval":
              openOval(fig);
              // writes all animations on figure in between open and close shape code
              animByShape = super.model.getAnimationsByShape().get(fig.getName());
              for (IAnimation a : animByShape) {
                super.delegateAnimPrint(a);
              }
              closeOval();
              break;
            case "rectangle":
              openRectangle(fig);
              // writes all animations on figure in between open and close shape code
              animByShape = super.model.getAnimationsByShape().get(fig.getName());
              for (IAnimation a : animByShape) {
                super.delegateAnimPrint(a);
              }
              closeRectangle();
              break;
            default:
              return;
          }
        }
      }
    }
    super.lastLine();
  }
}
