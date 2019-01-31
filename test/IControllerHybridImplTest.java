import org.junit.Test;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import cs3500.animator.controller.IController;
import cs3500.animator.controller.IControllerHybridImpl;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IModelImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.IViewImplHybridAnimation;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the controller using a mock view to ensure listeners are delegating correctly.
 */
public class IControllerHybridImplTest {

  /**
   * Mock View that extends the IViewImplHybridAnimation and implements IView to check whether the
   * controller's action listeners are set correctly in the view.
   */
  class MockView extends IViewImplHybridAnimation implements IView {
    Appendable output;
    JButton pauseButton;
    JButton restartButton;
    JButton fasterButton;
    JButton slowerButton;
    JButton svgButton;
    JTextField fileName;
    JCheckBox loopBox;
    JLabel itemStateDisplay;

    MockView(IModel model, Appendable output, double tempo) {
      super(model, tempo);
      this.output = output;
      pauseButton = new JButton("Pause/Play");
      restartButton = new JButton("Restart");
      fasterButton = new JButton("Speed up");
      slowerButton = new JButton("Slow down");
      svgButton = new JButton("SVG output");
      fileName = new JTextField(10);
      loopBox = new JCheckBox("Loop");
      itemStateDisplay = new JLabel("Animation is playing.");

      pauseButton.setActionCommand("Pause");
      restartButton.setActionCommand("Restart");
      fasterButton.setActionCommand("Faster");
      slowerButton.setActionCommand("Slower");
      svgButton.setActionCommand("Output SVG");
      loopBox.setActionCommand("Loop");
    }

    @Override
    public void play() {
      //empty
    }

    @Override
    public void setListeners(ActionListener listener) {
      pauseButton.addActionListener(listener);
      restartButton.addActionListener(listener);
      fasterButton.addActionListener(listener);
      slowerButton.addActionListener(listener);
      svgButton.addActionListener(listener);
      loopBox.addActionListener(listener);
    }

    @Override
    public void resetFocus() {
      //empty
    }

    // to simplify try/catch statements
    private void appendHelper(String str, Appendable output) {
      try {
        this.output.append(str);
      } catch (IOException e) {
        throw new IllegalStateException();
      }
    }

    @Override
    public void pause() {
      appendHelper("Paused/Played ", this.output);
    }

    @Override
    public void restart() {
      appendHelper("Restarted ", this.output);
    }

    @Override
    public void speedUp() {
      appendHelper("Sped up ", this.output);
    }

    @Override
    public void speedDown() {
      appendHelper("Slowed down ", this.output);
    }

    @Override
    public void loop() {
      appendHelper("Looped ", this.output);
    }

    @Override
    public void outputSVG() {
      appendHelper("SVG Outputted ", this.output);
    }
  }

  // tests every button of the controller to make sure every listener works
  @Test
  public void testController1() {
    IModel model = new IModelImpl();
    Appendable output = new StringBuffer();
    IViewImplHybridAnimation view = new MockView(model, output, 1);
    IController controller = new IControllerHybridImpl(view);
    assertEquals("", ((MockView) view).output.toString());
    ((MockView) view).pauseButton.doClick();
    assertEquals("Paused/Played ", ((MockView) view).output.toString());
    ((MockView) view).restartButton.doClick();
    assertEquals("Paused/Played Restarted ", ((MockView) view).output.toString());
    ((MockView) view).fasterButton.doClick();
    assertEquals("Paused/Played Restarted Sped up ", ((MockView) view).output.toString());
    ((MockView) view).slowerButton.doClick();
    assertEquals("Paused/Played Restarted Sped up Slowed down ",
            ((MockView) view).output.toString());
    ((MockView) view).loopBox.doClick();
    assertEquals("Paused/Played Restarted Sped up Slowed down Looped ",
            ((MockView) view).output.toString());
    ((MockView) view).svgButton.doClick();
    assertEquals("Paused/Played Restarted Sped up Slowed down Looped SVG Outputted ",
            ((MockView) view).output.toString());
  }

  // tests every button, multiple clicks in a row, of the controller to make sure every listener
  // continues to work after being called mulitple times and in various orders
  @Test
  public void testController2() {
    IModel model = new IModelImpl();
    Appendable output = new StringBuffer();
    IViewImplHybridAnimation view = new MockView(model, output, 1);
    IController controller = new IControllerHybridImpl(view);
    assertEquals("", ((MockView) view).output.toString());
    ((MockView) view).pauseButton.doClick();
    assertEquals("Paused/Played ", ((MockView) view).output.toString());
    ((MockView) view).pauseButton.doClick();
    assertEquals("Paused/Played Paused/Played ", ((MockView) view).output.toString());
    ((MockView) view).restartButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted ",
            ((MockView) view).output.toString());
    ((MockView) view).pauseButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played ",
            ((MockView) view).output.toString());
    ((MockView) view).pauseButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played ",
            ((MockView) view).output.toString());
    ((MockView) view).restartButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
            "Restarted ", ((MockView) view).output.toString());
    ((MockView) view).fasterButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
            "Restarted Sped up ", ((MockView) view).output.toString());
    ((MockView) view).fasterButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
            "Restarted Sped up Sped up ", ((MockView) view).output.toString());
    ((MockView) view).slowerButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
            "Restarted Sped up Sped up Slowed down ", ((MockView) view).output.toString());
    ((MockView) view).fasterButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
            "Restarted Sped up Sped up Slowed down Sped up ", ((MockView) view).output.toString());
    ((MockView) view).loopBox.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
                    "Restarted Sped up Sped up Slowed down Sped up Looped ",
            ((MockView) view).output.toString());
    ((MockView) view).slowerButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
                    "Restarted Sped up Sped up Slowed down Sped up Looped Slowed down ",
            ((MockView) view).output.toString());
    ((MockView) view).slowerButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
                    "Restarted Sped up Sped up Slowed down Sped up Looped Slowed down Slowed down ",
            ((MockView) view).output.toString());
    ((MockView) view).loopBox.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
                    "Restarted Sped up Sped up Slowed down Sped up Looped Slowed down " +
                    "Slowed down Looped ",
            ((MockView) view).output.toString());
    ((MockView) view).loopBox.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
                    "Restarted Sped up Sped up Slowed down Sped up Looped Slowed down " +
                    "Slowed down Looped Looped ",
            ((MockView) view).output.toString());
    ((MockView) view).svgButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
                    "Restarted Sped up Sped up Slowed down Sped up Looped Slowed down " +
                    "Slowed down Looped Looped SVG Outputted ",
            ((MockView) view).output.toString());
    ((MockView) view).svgButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
                    "Restarted Sped up Sped up Slowed down Sped up Looped Slowed down " +
                    "Slowed down Looped Looped SVG Outputted SVG Outputted ",
            ((MockView) view).output.toString());
    ((MockView) view).restartButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
                    "Restarted Sped up Sped up Slowed down Sped up Looped Slowed down " +
                    "Slowed down Looped Looped SVG Outputted SVG Outputted Restarted ",
            ((MockView) view).output.toString());
    ((MockView) view).svgButton.doClick();
    assertEquals("Paused/Played Paused/Played Restarted Paused/Played Paused/Played " +
            "Restarted Sped up Sped up Slowed down Sped up Looped Slowed down " +
            "Slowed down Looped Looped SVG Outputted SVG Outputted Restarted " +
            "SVG Outputted ", ((MockView) view).output.toString());
  }
}
