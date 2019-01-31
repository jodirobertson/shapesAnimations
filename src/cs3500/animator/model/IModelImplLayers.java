package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that represents an IModel that can also implement layering of IFigures such that they
 * are drawn in order of layering (and within that, of creation), such that they overlap one another
 * by order of layer.
 */
public class IModelImplLayers extends IModelImpl implements IModel {
  private HashMap<Integer, ArrayList<String>> groupFigs = new HashMap<>();
  private IModel mod;

  /**
   * Constructs an IModel that is capable of implementing layers of IFigures.
   *
   * @param mod IModel that it is taking information from.
   */
  public IModelImplLayers(IModel mod) {
    super();
    super.masterList = mod.getMasterList();
    super.figures = mod.getUpdatedFigures();
    super.figuresByTime = mod.getFiguresByStartTime();
    super.animationsByStartTime = mod.getAnimationsByStartTime();
    super.animationTimes = mod.getAnimationsByShape();
    super.figuresByAdd = mod.getFiguresByAdd();
    this.groupFigs = mod.getGroupFigs();
  }

  @Override
  public HashMap<Integer, ArrayList<String>> getGroupFigs() {
    HashMap<Integer, ArrayList<String>> copy;
    copy = (this.groupFigs);
    return copy;
  }

  @Override
  public void addGroupFigs(int layer, ArrayList<String> figs) {
    this.groupFigs.put(layer - 1, new ArrayList<>());
    this.groupFigs.get(layer - 1).addAll(figs);
  }
}
