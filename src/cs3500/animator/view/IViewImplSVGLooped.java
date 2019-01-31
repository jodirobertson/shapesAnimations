package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.IFigure;
import cs3500.animator.model.IModel;

/**
 * Represents a view similar to the IViewImplSVGAnimation view in that it appends SVG code of the
 * given animation scene to the Appendable output, but it also creates a looping animation in SVG.
 */
public class IViewImplSVGLooped extends IViewImplSVGAnimation implements IView {

  /**
   * Constructs an IView using SVG file formatting, given an IModel, tick, dimensions for the
   * composition window, and an output, and has the ability to make it a looped animation.
   */
  public IViewImplSVGLooped(IModel model, Appendable output, double tick, int screenWidth,
                            int screenHeight) throws IllegalArgumentException {
    super(model, output, tick, screenWidth, screenHeight);
  }

  /**
   * Formats the start time of the IFigure according to SVG format, with a starting time relevant to
   * the dummy shape to use for looping.
   *
   * @param f The IFigure that is having its start and end times converted.
   * @return Returns a String of the start time in SVG format.
   */
  @Override
  protected String formatStartTimeFigure(IFigure f) {
    return "base.begin+" + super.f.format(f.getAppearTime() * 1000 / tick);
  }

  /**
   * Writes the first line that adds a rectangle to the SVG output. Resets all attributes to their
   * initial values upon completion of the animation to allow for seamless looping.
   *
   * @param fig The given IFigure that is to be added to the SVG output.
   */
  @Override
  protected void openRectangle(IFigure fig) {
    String addRect = "<rect id=\"" + fig.getName() + "\" x=\"" + fig.getXPos() + "\" y=\""
            + fig.getYPos() + "\" width=\"" + fig.getXDim() + "\" height=\"" + fig.getYDim()
            + "\" \n fill=\"" + convertColor(fig.getColor()) + "\" visibility=\"hidden\" >\n";
    appendHelper(this.output, addRect);
    appendHelper(this.output, super.setVis(fig));
    appendHelper(this.output, "<animate attributeType=\"CSS\" begin=\"base.end\" "
            + "dur=\"1.0ms\" \nattributeName=\"" + "fill" + "\" to=\""
            + super.convertColor(fig.getColor()) + "\" fill=\"freeze\" />\n");
    appendHelper(this.output, "<animate attributeType=\"xml\" begin=\"base.end\" "
            + "dur=\"1.0ms\" \nattributeName=\"" + "x" + "\" to=\""
            + fig.getXPos() + "\" fill=\"freeze\" />\n");
    appendHelper(this.output, "<animate attributeType=\"xml\" begin=\"base.end\" "
            + "dur=\"1.0ms\" \nattributeName=\"" + "y" + "\" to=\""
            + fig.getYPos() + "\" fill=\"freeze\" />\n");
    appendHelper(this.output, "<animate attributeType=\"xml\" begin=\"base.end\" "
            + "dur=\"1.0ms\" \nattributeName=\"" + "width" + "\" to=\""
            + fig.getXDim() + "\" fill=\"freeze\" />\n");
    appendHelper(this.output, "<animate attributeType=\"xml\" begin=\"base.end\" "
            + "dur=\"1.0ms\" \nattributeName=\"" + "height" + "\" to=\""
            + fig.getYDim() + "\" fill=\"freeze\" />\n");
  }

  /**
   * Writes the last line of the rectangle representation in SVG format so that it disappears upon
   * looping.
   */
  @Override
  protected void closeRectangle() {
    appendHelper(this.output, "<set attributeType=\"xml\" attributeName=\"visibility\" " +
            "from=\"visible\"\n" + "  to=\"hidden\" begin=\"base.end\" />\n</rect>\n");
  }

  /**
   * Writes the first line of adding an oval to the SVG output. Resets all attributes to their
   * initial values upon completion of the animation to allow for seamless looping.
   *
   * @param fig The given IFigure that is to be added to the SVG output.
   */
  @Override
  protected void openOval(IFigure fig) {
    String addOval = "<ellipse id=\"" + fig.getName() + "\" cx=\"" + fig.getXPos()
            + "\" cy=\"" + fig.getYPos() + "\" rx=\"" + fig.getXDim() + "\" ry=\""
            + fig.getYDim() + "\" \n" + "fill=\"" + convertColor(fig.getColor())
            + "\" visibility=\"hidden\" >\n";
    appendHelper(this.output, addOval);
    appendHelper(this.output, super.setVis(fig));
    appendHelper(this.output, "<animate attributeType=\"CSS\" begin=\"base.end\" "
            + "dur=\"1.0ms\" \nattributeName=\"" + "fill" + "\" to=\""
            + super.convertColor(fig.getColor()) + "\" fill=\"freeze\" />\n");
    appendHelper(this.output, "<animate attributeType=\"xml\" begin=\"base.end\" "
            + "dur=\"1.0ms\" \nattributeName=\"" + "cx" + "\" to=\""
            + fig.getXPos() + "\" fill=\"freeze\" />\n");
    appendHelper(this.output, "<animate attributeType=\"xml\" begin=\"base.end\" "
            + "dur=\"1.0ms\" \nattributeName=\"" + "cy" + "\" to=\""
            + fig.getYPos() + "\" fill=\"freeze\" />\n");
    appendHelper(this.output, "<animate attributeType=\"xml\" begin=\"base.end\" "
            + "dur=\"1.0ms\" \nattributeName=\"" + "rx" + "\" to=\""
            + fig.getXDim() + "\" fill=\"freeze\" />\n");
    appendHelper(this.output, "<animate attributeType=\"xml\" begin=\"base.end\" "
            + "dur=\"1.0ms\" \nattributeName=\"" + "ry" + "\" to=\""
            + fig.getYDim() + "\" fill=\"freeze\" />\n");
  }

  /**
   * Writes the last line of the oval shape addition to the SVG output with resetting its visibility
   * to allow for looping.
   */
  @Override
  protected void closeOval() {
    appendHelper(this.output, "<set attributeType=\"xml\" attributeName=\"visibility\" " +
            "from=\"visible\"\n" + " to=\"hidden\" begin=\"base.end\" />\n</ellipse>\n");
  }

  /**
   * Writes the animation of changing color into the SVG output, and readjusts start time for
   * looping based on the start time of the dummy shape.
   *
   * @param a The IAnimationImplColorChange to be converted to SVG format.
   */
  protected void animateColorChange(IAnimation a) {

    String addAnim = "<animate attributeType=\"CSS\" begin=\"" + this.formatStartTimeAnim(a) +
            "ms\" dur=\"" + super.formatDuration(a) + "ms\" \nattributeName=\"fill\" from=\""
            + super.convertColor(a.getFigure().getColor()) + "\" \nto=\""
            + super.convertColor(a.getNewColor()) + "\" fill=\"freeze\" />\n";
    appendHelper(this.output, addAnim);
  }

  /**
   * Writes the animation of scaling into the SVG output, and readjusts start time for looping based
   * on the start time of the dummy shape.
   *
   * @param a  The IAnimation being translated to SVG format.
   * @param xy The dimension that is being scaled.
   */
  @Override
  protected void animateScale(IAnimation a, char xy) {
    String dimName;
    String sub1;
    String sub2;

    // assigns name inputs for animation
    switch (a.getFigure().getShape()) {
      case "oval":
        if (xy == 'x') {
          dimName = "rx";
          sub1 = "" + a.getFigure().getXDim();
          sub2 = "" + a.getFigure().updateDims(a.getXFactor(), a.getYFactor()).getXDim();
        } else {
          dimName = "ry";
          sub1 = "" + a.getFigure().getYDim();
          sub2 = "" + a.getFigure().updateDims(a.getXFactor(), a.getYFactor()).getYDim();
        }
        break;
      case "rectangle":
        if (xy == 'x') {
          dimName = "width";
          sub1 = "" + a.getFigure().getXDim();
          sub2 = "" + a.getFigure().updateDims(a.getXFactor(), a.getYFactor()).getXDim();
        } else {
          dimName = "height";
          sub1 = "" + a.getFigure().getYDim();
          sub2 = "" + a.getFigure().updateDims(a.getXFactor(), a.getYFactor()).getYDim();
        }
        break;
      default:
        dimName = "error";
        sub1 = "";
        sub2 = "";
    }

    String addAnim = "<animate attributeType=\"xml\" begin=\"" + this.formatStartTimeAnim(a)
            + "ms\" dur=\"" + formatDuration(a) + "ms\" \nattributeName=\"" + dimName + "\" from=\""
            + sub1 + "\" to=\"" + sub2 + "\" fill=\"freeze\" />\n";
    appendHelper(this.output, addAnim);
  }

  /**
   * Formats the start time of the animation according to SVG format, and readjusts start time for
   * looping based on the start time of the dummy shape.
   *
   * @param a The IAnimation that is having its start and end times converted.
   * @return Returns a String of the start time in SVG format.
   */
  @Override
  protected String formatStartTimeAnim(IAnimation a) {
    return "base.begin+" + super.f.format(a.getStartTime() * 1000 / tick);
  }

  /**
   * Writes the animation of moving an object into the SVG output, with readjustments for looping at
   * the end.
   *
   * @param a  The IAnimation to be transcribed in SVG format.
   * @param xy The dimension upon which it is acting.
   */
  @Override
  protected void animateMove(IAnimation a, char xy) {
    String attName;
    String sub1;
    String sub2;
    switch (a.getFigure().getShape()) {
      case "oval":
        attName = "c";
        break;
      case "rectangle":
        attName = "";
        break;
      default:
        attName = "";
    }
    if (xy == 'x') {
      attName = attName + "x";
      sub1 = "" + a.getFigure().getXPos();
      sub2 = "" + a.getFigure().updatePosn(a.getDX(), a.getDY()).getXPos();
    } else {
      attName = attName + "y";
      sub1 = "" + a.getFigure().getYPos();
      sub2 = "" + a.getFigure().updatePosn(a.getDX(), a.getDY()).getYPos();
    }
    String addAnim = "<animate attributeType=\"xml\" begin=\"" + this.formatStartTimeAnim(a)
            + "ms\" dur=\"" + formatDuration(a) + "ms\" \nattributeName=\"" + attName + "\" from=\""
            + sub1 + "\" to=\"" + sub2 + "\" fill=\"freeze\" />\n";
    appendHelper(this.output, addAnim);
  }


  @Override
  public void play() {
    // a list of animations that occur to a particular IFigure (as referenced by its unique name)
    List<IAnimation> animByShape;

    super.firstLine();

    String addLoop = "<rect> \n <animate id=\"base\" begin=\"0;base.end\" dur=\""
            + super.f.format(model.maxTime() * 1000 / tick)
            + "ms\" " + "attributeName=\"visibility\" "
            + "from=\"hide\" to=\"hide\"/> </rect>";
    appendHelper(super.output, addLoop);


    // writes all figures into SVG format
    for (IFigure fig : super.model.getFiguresByAdd()) {
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
    super.lastLine();
  }
}
