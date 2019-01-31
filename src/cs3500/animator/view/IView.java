package cs3500.animator.view;

import java.awt.event.ActionListener;

/**
 * Represents the common methods that can be called while using an IView.
 */
public interface IView {
  /**
   * Shows an animation.
   */
  void play();

  /**
   * Sets the IView to be "listening" to the controller for user input during the EasyAnimator.
   *
   * @param input Mouse events and buttons clicked of the user.
   */
  void setListeners(ActionListener input);

  /**
   * Resets the focus on the parts of the view that has the button listener so that the buttons do
   * not stay in focus after a used has interacted with it.
   */
  void resetFocus();

}
