package cs3500.animator.model;

/**
 * Represents a figure that has been rotated from an IAnimationImplRotate so that its degree of
 * rotation may be monitored. It extends IFigureImplDummy as it behaves in all other ways the same.
 */
public class IFigureImplRotatable extends IFigureImplDummy implements IFigure {
  private double degree;

  /**
   * Represents an instance of an IFigure IFigureImplOval.
   *
   * @param model        The current IModel.
   * @param color        The color of the IFigure.
   * @param name         The name given to the IFigure.
   * @param xPos         The center x-coordinate of the IFigure.
   * @param yPos         The center y-coordinate of the IFigure.
   * @param appearsAt    The time at which the IFigure appears on the scene.
   * @param disappearsAt The time at which the IFigure disappears from the scene.
   * @param xDim         The x-dimension of the oval.
   * @param yDim         The y-dimension of the oval.
   * @param degree       The degree (in degrees) of the rotation of the IFigure.
   * @throws IllegalArgumentException if any of the specified parameters are invalid.
   */
  public IFigureImplRotatable(IModel model, String shapeName, double[] color, String name,
                              double xPos, double yPos, int appearsAt, int disappearsAt,
                              double xDim, double yDim, double degree)
          throws IllegalArgumentException {
    super(model, shapeName, color, name, xPos, yPos, appearsAt, disappearsAt, xDim, yDim);
    this.degree = degree;
  }

  @Override
  public IFigure updatePosn(double dx, double dy) {
    double oldX = this.xPos;
    double oldY = this.yPos;
    double newX = oldX + dx;
    double newY = oldY + dy;
    return new IFigureImplRotatable(model, shapeName, color, name, newX, newY, appearsAt, disappearsAt,
            xDim, yDim, degree);
  }

  @Override
  public IFigure updateColor(double[] newColor) {
    return new IFigureImplRotatable(model, shapeName, newColor, name, xPos, yPos,
            appearsAt, disappearsAt, xDim, yDim, degree);
  }

  @Override
  public IFigure updateDims(double xFactor, double yFactor) {
    double oldXDim = this.xDim;
    double oldYDim = this.yDim;
    double newXDim = oldXDim * xFactor;
    double newYDim = oldYDim * yFactor;
    return new IFigureImplRotatable(model, shapeName, color, name, xPos, yPos, appearsAt, disappearsAt,
            newXDim, newYDim, degree);
  }

  @Override
  public IFigure updateRotation(double degree) {
    return new IFigureImplRotatable(model, shapeName, color, name, xPos, yPos, appearsAt,
            disappearsAt, xDim, yDim, degree);
  }

  @Override
  public double getDegree() {
    double copy = this.degree;
    return copy;
  }
}
