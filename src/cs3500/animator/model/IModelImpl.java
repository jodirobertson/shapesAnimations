package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the concrete implementation of the IModel.
 */
public class IModelImpl implements IModel {
  // INVARIANT: all IFigures in this list are in order of ascending appear time
  // represents all IFigures in the model
  protected List<IFigure> figuresByTime;

  // INVARIANT: all IAnimations in this list are in order of ascending start time
  // represents all IAnimations in the model
  protected List<IAnimation> animationsByStartTime;

  // couples the IAnimations to the IFigures
  protected HashMap<IAnimation, String> masterList;

  // INVARIANT: stores the most up-to-date IFigure according to their name
  protected HashMap<String, IFigure> figures;

  // stores which animations are occuring on each shape at a time
  protected HashMap<String, List<IAnimation>> animationTimes;

  // stores a list of figures based on when they are added to the composition
  protected List<IFigure> figuresByAdd;

  /**
   * Constructs an IModelImpl to create a composition of IFigures and IAnimations.
   */
  public IModelImpl() {
    this.masterList = new HashMap<>();
    this.figures = new HashMap<>();
    this.figuresByTime = new LinkedList<>();
    this.animationsByStartTime = new LinkedList<>();
    this.animationTimes = new HashMap<>();
    this.figuresByAdd = new LinkedList<>();
  }

  // Update: moved printing methods and capability to the IViewTextSummary class, as per
  // assignment suggestion

  // Update: added this in view implementation to return sorted list of IFigures in assignment of
  // IViewVisualAnimation anonymous class
  @Override
  public int maxTime() {
    LinkedList<IFigure> copy = new LinkedList<>();
    copy.addAll(this.figuresByTime);
    if (this.figuresByTime.size() != 0) {
      Collections.sort(copy, new IFiguresCompByEnd());
    } else {
      return 0;
    }
    return copy.getFirst().getDisappearTime();
  }

  // Update: added all of these getters as part of Model merge, which made it mush easier to
  // implement views without parsing through String description
  @Override
  public IFigure getFigure(String fName) {
    if (fName == null || (!(this.figures.containsKey(fName)))) {
      throw new IllegalArgumentException("Cannot retrieve object.");
    }
    return this.figures.get(fName);
  }

  @Override
  public HashMap<String, List<IAnimation>> getAnimationsByShape() {
    HashMap<String, List<IAnimation>> copy = (this.animationTimes);
    return copy;
  }

  @Override
  public List<IFigure> getFiguresByStartTime() {
    List<IFigure> copy = new LinkedList<>();
    copy.addAll(this.figuresByTime);
    return copy;
  }

  @Override
  public List<IAnimation> getAnimationsByStartTime() {
    List<IAnimation> copy = new LinkedList<>();
    copy.addAll(this.animationsByStartTime);
    return copy;
  }

  @Override
  public HashMap<IAnimation, String> getMasterList() {
    HashMap<IAnimation, String> copy = (this.masterList);
    return copy;
  }

  @Override
  public HashMap<String, IFigure> getUpdatedFigures() {
    HashMap<String, IFigure> copy = (this.figures);
    return copy;
  }

  @Override
  public List<IFigure> getFiguresByAdd() {
    List<IFigure> copy = new LinkedList<>();
    copy.addAll(this.figuresByAdd);
    return copy;
  }

  /**
   * Updates the list of IFigures to be sorted according to their appear times.
   */
  protected void sortFigures() {
    Collections.sort(this.figuresByTime, new IFiguresComp());
  }

  /**
   * Updates the list of IAnimations to be sorted according to their start times.
   */
  protected void sortAnimationsByStart() {
    Collections.sort(this.animationsByStartTime, new IAnimationsCompStart());
  }

  @Override
  public void addGroupFigs(int layer, ArrayList<String> figs) {
    // do nothing
  }

  @Override
  public HashMap<Integer, ArrayList<String>> getGroupFigs() {
    return new HashMap<>();
  }

  @Override
  public void add(IFigure f) throws IllegalArgumentException {
    if (f == null) {
      throw new IllegalArgumentException("Cannot add a null object.");
    }
    for (IFigure fig : this.figuresByTime) {
      if (fig.getName().equals(f.getName())) {
        throw new IllegalArgumentException("An IFigure with this name already exists.");
      }
    }
    this.figuresByTime.add(f);
    this.figuresByAdd.add(f);
    this.figures.put(f.getName(), f);
    this.animationTimes.put(f.getName(), new LinkedList<IAnimation>());
    sortFigures();
  }

  /**
   * Returns whether the animations' times would overlap with each other.
   *
   * @param existingAnim  The IAnimation that has already been applied to the IFigure.
   * @param potentialAnim The IAnimation in question of being applied to the IFigure.
   * @return Returns whether the animations' start and end times overlap.
   */
  protected boolean theyOverlap(IAnimation existingAnim, IAnimation potentialAnim) {
    return ((existingAnim.getEndTime() <= potentialAnim.getStartTime())
            || existingAnim.getStartTime() <= potentialAnim.getEndTime());
  }

  /**
   * Determines whether the given animation can be added to the scence.
   *
   * @param potentialAnim The IAnimation to be added to an IFigure.
   * @return Returns true if the animation competes with an existing animation.
   */
  protected boolean isCompeting(IAnimation potentialAnim, IFigure f) {
    if (this.animationTimes.containsKey(f.getName())) {
      for (IAnimation existingAnim : this.animationTimes.get(f.getName())) {
        if ((existingAnim instanceof IAnimationImplMove)
                && (potentialAnim instanceof IAnimationImplMove)) {
          if (theyOverlap(potentialAnim, existingAnim)) {
            return true;
          } //else, keep checking
        } else if ((existingAnim instanceof IAnimationImplScale)
                && (potentialAnim instanceof IAnimationImplScale)) {
          if (theyOverlap(potentialAnim, existingAnim)) {
            return true;
          } //else, keep checking
        } else if ((existingAnim instanceof IAnimationImplColorChange)
                && (potentialAnim instanceof IAnimationImplColorChange)) {
          if (theyOverlap(potentialAnim, existingAnim)) {
            return true;
          }
        }//else, keep checking
          else if ((existingAnim instanceof IAnimationImplRotate)
                  && (potentialAnim instanceof IAnimationImplRotate)) {
            if (theyOverlap(potentialAnim, existingAnim)) {
              return true;
            } //else, keep checking
        } else {
          //keep checking
        }
      }
    }
    return false;
  }

  @Override
  public void animate(IAnimation a, String fName) throws IllegalArgumentException {
    if (a == null || fName == null) {
      throw new IllegalArgumentException("Cannot animate with a null object.");
    }

    if (!(this.figures.containsKey(fName))) {
      throw new IllegalArgumentException("Cannot find specified IFigure");
    }

    // use the name of the IFigure to get the most updated IFigure in the animation process
    IFigure f = this.figures.get(fName);

    // check to make sure that the animation can be added
    if (isCompeting(a, f)) {
      throw new IllegalArgumentException("This animation competes with an existing animation.");
    }

    // update master list of animations with figure name
    this.masterList.put(a, fName);

    // uphold invariant to store all animations by time
    this.animationsByStartTime.add(a);
    sortAnimationsByStart();

    // update hashmap of animations based on figure name
    this.animationTimes.get(fName).add(a);
    Collections.sort(this.animationTimes.get(f.getName()), new IAnimationsCompEnd());

    // apply animation to IFigure and update the register of current IFigures
    IFigure newFig = f.apply(a);
    this.figures.replace(fName, newFig);
  }
}
