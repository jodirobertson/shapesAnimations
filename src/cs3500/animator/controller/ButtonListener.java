package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Represents the ability to "listen" to user input from the buttons they click and interact with in
 * the view within the Ccontroller, and creates maps to initiate specific actions based on which
 * buttons are clicked.
 */
public class ButtonListener implements ActionListener {
  private Map<String, Runnable> buttonsClicked;

  /**
   * Empty default constructor.
   */
  protected ButtonListener() {
    //default constructor.
  }

  /**
   * Sets the map to associate a button click with a specific corresponding action that the program
   * should respond with.
   */
  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonsClicked = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonsClicked.containsKey(e.getActionCommand())) {
      buttonsClicked.get(e.getActionCommand()).run();
    }
  }
}
