package cs3500.animator.controller;

import java.util.HashMap;
import java.util.Map;

import cs3500.animator.view.IViewImplHybridAnimation;

/**
 * Represent the controller for the EasyAnimator program. Handles user input and gives the view
 * actions to display based on the user input.
 */
public class IControllerHybridImpl implements IController {
  private IViewImplHybridAnimation view;

  /**
   * Constructs a controller for the interactive view to handle user input and passes the view
   * function objects to update itself accordingly.
   *
   * @param view The interactive view associated with this controller.
   */
  public IControllerHybridImpl(IViewImplHybridAnimation view) {
    this.view = view;
    //createScrubberListener();
    createButtonListener();
  }

  /**
   * Create anonymous classes of function objects that are given to the view to update itself based
   * on the buttons that the user interacted with, and sets the listeners within the view.
   */
  private void createButtonListener() {
    Map<String, Runnable> clickableButtons = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    clickableButtons.put("Pause", new Runnable() {
      @Override
      public void run() {
        view.pause();
        view.resetFocus();
      }
    });
    clickableButtons.put("Restart", new Runnable() {
      @Override
      public void run() {
        view.restart();
        view.resetFocus();
      }
    });
    clickableButtons.put("Faster", new Runnable() {
      @Override
      public void run() {
        view.speedUp();
        view.resetFocus();
      }
    });
    clickableButtons.put("Slower", new Runnable() {
      @Override
      public void run() {
        view.speedDown();
        view.resetFocus();
      }
    });
    clickableButtons.put("Output SVG", new Runnable() {
      @Override
      public void run() {
        view.outputSVG();
        view.resetFocus();
      }
    });
    clickableButtons.put("Loop", new Runnable() {
      @Override
      public void run() {
        view.loop();
        view.resetFocus();
      }
    });

    buttonListener.setButtonClickedActionMap(clickableButtons);
    this.view.setListeners(buttonListener);
    this.view.play();
  }
}
