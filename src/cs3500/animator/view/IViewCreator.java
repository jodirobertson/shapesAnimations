package cs3500.animator.view;

import cs3500.animator.model.IModel;

/**
 * Represents the factory of views that returns an IView depending on which one was specified by the
 * user.
 */
public class IViewCreator {

  /**
   * Default constructor for the factory of views.
   */
  public IViewCreator() {
    //default constructor
  }

  /**
   * Returns a text summary view based on the given model, Appendable output, and tempo as specified
   * by the user.
   *
   * @param model  The given IModel that represents the data to be rendered by the IView.
   * @param output The given Appendable to which the view should be appended.
   * @param tempo  The given tempo of the speed of the animation.
   * @return Returns a text summary view based on the given arguments.
   */
  public IViewImplTextSummary createTextSummary(IModel model, Appendable output, double tempo) {
    return new IViewImplTextSummary(model, output, tempo);
  }

  /**
   * Returns a visual animation view based on the given model and tempo as specified by the user.
   *
   * @param model The given IModel that represents the data to be rendered by the IView.
   * @param tempo The given tempo of the speed of the animation.
   * @return Returns a visual animation view based on the given arguments.
   */
  public IViewImplVisualAnimation createVisual(IModel model, double tempo) {
    return new IViewImplVisualAnimation(model, tempo);
  }

  /**
   * Returns an SVG animation view based on the given model, Appendable output, tempo, and screen
   * dimensions as specified by the user.
   *
   * @param model        The given IModel that represents the data to be rendered by the IView.
   * @param output       The given Appendable to which the view should be appended.
   * @param tempo        The given tempo of the speed of the animation.
   * @param screenWidth  The given width of the desired screen's x-dimension.
   * @param screenHeight The given height of the desired screen's y-dimension.
   * @return Returns an SVG animation view based on the given arguments.
   */
  public IViewImplSVGAnimation createSVG(IModel model, Appendable output, double tempo,
                                         int screenWidth, int screenHeight) {
    return new IViewImplSVGAnimation(model, output, tempo, screenWidth, screenHeight);
  }

  /**
   * Returns an interactive visual animation view with the option to output SVG animations based on
   * the given model and tempo as specified by the user.
   *
   * @param model The given IModel that represents the data to be rendered by the IView.
   * @param tempo The given tempo of the speed of the animation.
   * @return Returns an interactive visual animation view based on the given arguments.
   */
  public IViewImplHybridAnimation createHybrid(IModel model, double tempo) {
    return new IViewImplHybridAnimation(model, tempo);
  }
}
