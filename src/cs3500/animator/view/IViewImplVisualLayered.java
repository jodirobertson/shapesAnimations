package cs3500.animator.view;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import cs3500.animator.model.IAnimation;
import cs3500.animator.model.IFigure;
import cs3500.animator.model.IModel;

public class IViewImplVisualLayered extends IViewImplVisualAnimation implements IView{
  /**
   * Consutructs an IView that uses JavaSwing to draw its composition that can handle layers of
   * IFigures.
   *
   * @param model The given IModel to be drawn.
   * @param tempo The given tempo at which to play the animation.
   */
  public IViewImplVisualLayered(IModel model, double tempo) {
    super(model, tempo);
  }

  /**
   * Returns a list of IFigures to be drawn at a given time.
   *
   * @param time The given time to be queried for figures to draw.
   * @return Returns an ArrayList of IFigures to be drawn at a given time, according to when they
   *         were first created.
   */
  protected ArrayList<IFigure> draw(List<String> figs, int time) {
    ArrayList<IFigure> result = new ArrayList<>();
    for (String fg : figs) {
    // check all figures based on when they were added
    for (IFigure f : model.getFiguresByAdd()) {

        if (fg.equals(f.getName())) {

          IFigure figInProgress = f;
          super.upToDateFigs.put(f.getName(), f);
          // if the figure is visible
          if (f.getAppearTime() <= time && f.getDisappearTime() >= time) {
            // go through all animations to see which apply to this figure
            for (IAnimation a : model.getAnimationsByStartTime()) {
              // if this animation is applied to the given figure
              if (a.getFigure().getName().equals(f.getName())
                      && (a.getStartTime() <= time) && (a.getEndTime() > time)) {
                // update accordingly
                figInProgress = super.tween(a, super.upToDateFigs.get(f.getName()), time);
                super.upToDateFigs.put(figInProgress.getName(), figInProgress);
              }
              // if the figure has already had that animation completed, update the data to hold newest
              else if (a.getFigure().getName().equals(f.getName()) && a.getEndTime() <= time) {
                super.upToDateFigs.put(f.getName(), a.accept(super.upToDateFigs.get(f.getName())));
              }
            }
            // add the appearing figure to the list to be drawn and update data accordingly
            result.add(super.upToDateFigs.get(figInProgress.getName()));
          }
        }
      }
    }
    return result;
  }
}
