package cs3500.animator.util;

import java.util.ArrayList;

import cs3500.animator.model.IAnimationImplColorChange;
import cs3500.animator.model.IAnimationImplMove;
import cs3500.animator.model.IAnimationImplRotate;
import cs3500.animator.model.IAnimationImplScale;
import cs3500.animator.model.IFigureImplDummy;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IModelImpl;
import cs3500.animator.model.IModelImplLayers;

/**
 * Represents the Adapter between the TweenModelBuilder and this program's IModel interface.
 */
public class TweenModelBuilderImpl implements TweenModelBuilder<IModel> {
  private IModel model = new IModelImpl();

  /**
   * Converts given values to IModel's representation of color.
   *
   * @param red   The float representing the red color value.
   * @param green The float representing the green color value.
   * @param blue  The float representing the blue color value.
   * @return Returns the IModel's representation of colors in an array of doubles.
   */
  private double[] convertColor(float red, float green, float blue) {
    return new double[]{red, green, blue};
  }

  @Override
  public TweenModelBuilder<IModel> addGroupFigs(int layer, ArrayList<String> figs) {
    IModel newModel = new IModelImplLayers(this.model);
    newModel.addGroupFigs(layer, figs);
    this.model = newModel;
    return this;
  }

  @Override
  public TweenModelBuilder<IModel> addOval(String name, float cx, float cy, float xRadius,
                                           float yRadius, float red, float green, float blue,
                                           int startOfLife, int endOfLife) {
    model.add(new IFigureImplDummy(model, "oval", convertColor(red, green, blue), name,
            cx, cy, startOfLife, endOfLife, xRadius, yRadius));
    return this;
  }

  @Override
  public TweenModelBuilder<IModel> addRectangle(String name, float lx, float ly, float width,
                                                float height, float red, float green, float blue,
                                                int startOfLife, int endOfLife) {

    model.add(new IFigureImplDummy(model, "rectangle", convertColor(red, green, blue),
            name, lx, ly, startOfLife, endOfLife, width, height));
    return this;
  }

  @Override
  public TweenModelBuilder<IModel> addMove(String name, float moveFromX, float moveFromY,
                                           float moveToX, float moveToY,
                                           int startTime, int endTime) {
    // converts moveFrom and MoveTo points to IAnimation's representation of dx and dy
    double dx = moveToX - moveFromX;
    double dy = moveToY - moveFromY;
    model.animate(new IAnimationImplMove(model, name, dx, dy, startTime, endTime), name);
    return this;
  }

  @Override
  public TweenModelBuilder<IModel> addColorChange(String name, float oldR, float oldG,
                                                  float oldB, float newR, float newG,
                                                  float newB, int startTime, int endTime) {
    model.animate(new IAnimationImplColorChange(model, name, convertColor(newR, newG, newB),
            startTime, endTime), name);
    return this;
  }

  @Override
  public TweenModelBuilder<IModel> addScaleToChange(String name, float fromSx,
                                                    float fromSy, float toSx, float toSy,
                                                    int startTime, int endTime) {
    // converts toS and fromS to IAnimation's representation of scaling factors.
    double xFactor = toSx / fromSx;
    double yFactor = toSy / fromSy;
    model.animate(new IAnimationImplScale(model, name, xFactor, yFactor, startTime, endTime), name);
    return this;
  }

  @Override
  public TweenModelBuilder<IModel> addRotation(String name, double degree, int startTime, int endTime) {
    model.animate(new IAnimationImplRotate(model, name, degree, startTime, endTime), name);
    return this;
  }

  @Override
  public IModel build() {
    return model;
  }
}
