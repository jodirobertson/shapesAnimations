package cs3500.animator.model;

/**
 * Represents an IAnimation that rotates the given IFigure about its center for the specified
 * degrees, in degrees.
 */
public class IAnimationImplRotate extends IAnimationAbstract implements IAnimation {
  private String type = "rotates";
  private double degree;

  /**
   * Constructor for an IAnimation that rotates the IFigure.
   *
   * @param model     The current IModel.
   * @param fig       The name of the IFigure to which this IAnimation will be applied.
   * @param degree    The degree to which the IFigure will rotate.
   * @param startTime The start time of this IAnimation.
   * @param endTime   The end time of this IAnimation.
   * @throws IllegalArgumentException if the given start or end times are invalid.
   */
  public IAnimationImplRotate(IModel model, String fig, double degree, int startTime,
                            int endTime) throws IllegalArgumentException {
    super();
    super.model = model;
    super.fig = model.getFigure(fig);
    this.degree = degree;
    super.type = type;
    super.startTime = startTime;
    super.endTime = endTime;
    checkTimes();
  }

  @Override
  public double[] getNewColor() {

    return new double[0];
  }

  @Override
  public double getDX() {
    return 0;
  }

  @Override
  public double getDY() {
    return 0;
  }

  @Override
  public double getXFactor() {
    return 0;
  }

  @Override
  public double getYFactor() {
    return 0;
  }

  @Override
  public double getDegree() {
    double copy = this.degree;
    return copy;
  }

  // make sure this gets updated if a circle with just a radius is added.
  @Override
  public IFigure accept(IFigure f) {
    return f.updateRotation(this.degree);
  }
}
