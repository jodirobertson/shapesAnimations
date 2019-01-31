package cs3500.animator.view;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs3500.animator.model.IFigure;
import cs3500.animator.model.IModel;

/**
 * Represents an interactive visual animation view that uses the JavaSwing library to render
 * animations and uses buttons to allow the user to update the View. It also has the ability to
 * export an SVG view animation file, and supports looping animations.
 */
public class IViewImplHybridAnimation extends JFrame implements IView {
  private MyPanel drawingPanel;
  protected IModel model;
  private double tempo;
  private Timer timer;
  private int time = 0;
  private boolean isLooped = false;
  private ArrayList<ArrayList<IFigure>> animationShapes = new ArrayList<>();

  // all buttons in display:
  private JButton pauseButton;
  private JButton restartButton;
  private JButton fasterButton;
  private JButton slowerButton;
  private JButton svgButton;
  private JTextField fileName;
  private JCheckBox loopBox;
  private JLabel itemStateDisplay;
  private JSlider scrubber;

  /**
   * Constructs an interactive IView that uses JavaSwing to draw its composition, and has the
   * ability to export the SVG view to a specified file.
   *
   * @param model The given IModel to be drawn.
   * @param tempo The given tempo at which to play the animation.
   */
  public IViewImplHybridAnimation(IModel model, double tempo) {
    this.model = model;
    this.tempo = tempo;
    this.timer = new Timer((int) (1000 / tempo), null);

    this.scrubber = new JSlider(JSlider.HORIZONTAL, 0, model.maxTime(), 0);
    this.scrubber.setPaintTicks(true);
    this.scrubber.setMajorTickSpacing(20);
    this.scrubber.setMinorTickSpacing(5);
    this.scrubber.setPaintLabels(true);

    timer.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (time < model.maxTime()) {
          drawingPanel.drawShapes(animationShapes.get(time));
          scrubber.setValue(time);
          drawingPanel.repaint();
          }
          if (time == model.maxTime()) {
          if (isLooped) {
            time = 0;
          } else {
            timer.stop();
          }
          }
        time++;
      }
      }
    );

    this.scrubber.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (scrubber.getValueIsAdjusting()) {
          timer.stop();
          time = scrubber.getValue();
          drawingPanel.drawShapes(animationShapes.get(time));
          drawingPanel.repaint();
          itemStateDisplay.setText("Scrubbing");
        } else if (scrubber.isFocusOwner()) {
          timer.stop();
          time = scrubber.getValue();
          drawingPanel.drawShapes(animationShapes.get(time));
          drawingPanel.repaint();
          itemStateDisplay.setText("Scrubbing");
        } else {
          resetFocus();
          timer.start();
          itemStateDisplay.setText("Animation is playing.");
        }
      }
    });

    //UPDATE: allowed for different ordering of drawing based on whether layers were implemented

    for (int i = 0; i <= model.maxTime(); i++) {
      if (this.model.getGroupFigs().size() == 0) {
        IViewImplVisualAnimation viewVisual = new IViewImplVisualAnimation(this.model, this.tempo);
        this.animationShapes.add(viewVisual.draw(i));
      } else {
        IViewImplVisualLayered viewVisual = new IViewImplVisualLayered(this.model, this.tempo);
        ArrayList<String> figs = new ArrayList<>();

        for (ArrayList<String> list : this.model.getGroupFigs().values()) {

          for (String s : list) {
            for (IFigure f : viewVisual.draw(i)) {
              if (s.equals(f.getName())) {
                figs.add(s);
              }
            }
          }
        }
        this.animationShapes.add(viewVisual.draw(figs, i));
      }
    }

    setTitle("Visual Animation");
    setSize(1200, 800);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    drawingPanel = new MyPanel();
    drawingPanel.setPreferredSize(this.getSize());
    this.add(drawingPanel);

    JScrollPane scrollPane = new JScrollPane(drawingPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scrollPane);

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new FlowLayout());
    this.add(inputPanel, BorderLayout.SOUTH);
    JPanel stateUpdates = new JPanel();
    stateUpdates.setLayout(new FlowLayout());
    this.add(stateUpdates, BorderLayout.NORTH);

    pauseButton = new JButton("Play/Pause");
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

    inputPanel.add(pauseButton);
    inputPanel.add(restartButton);
    inputPanel.add(fasterButton);
    inputPanel.add(slowerButton);
    inputPanel.add(svgButton);
    inputPanel.add(new JLabel("SVG output file name:"));
    inputPanel.add(fileName);
    inputPanel.add(loopBox);
    inputPanel.add(scrubber);
    stateUpdates.add(itemStateDisplay);
    this.resetFocus();
  }

  /**
   * Adds the listeners from the controller to be able to respond to user interactions with the
   * buttons.
   *
   * @param listener An ActionListener given by the controller.
   */
  public void setListeners(ActionListener listener) {
    pauseButton.addActionListener(listener);
    restartButton.addActionListener(listener);
    fasterButton.addActionListener(listener);
    slowerButton.addActionListener(listener);
    svgButton.addActionListener(listener);
    loopBox.addActionListener(listener);
  }

  public void setChangeListeners(ChangeListener chListener) {
    scrubber.addChangeListener(chListener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  /**
   * Immediately pauses and plays an animation in the visual view, upon user clicks.
   */
  public void pause() {
    if (this.timer.isRunning()) {
      this.timer.stop();
    } else {
      this.timer.start();
    }
    itemStateDisplay.setText("Play/Pause was selected.");
  }

  /**
   * Immediately resets an animation in the visual view, upon user clicks, to the beginning of the
   * animation.
   */
  public void restart() {
    this.timer.stop();
    this.time = 0;
    this.timer.start();
    itemStateDisplay.setText("Animation was restarted.");

  }

  /**
   * Immediately speeds up an animation in the visual view, upon user clicks, and will be reflected
   * in the SVG output, if enabled by the user.
   */
  public void speedUp() {
    if (this.timer.getDelay() != 1) {
      this.tempo *= 1.2;
      this.timer.setDelay((int) (1000 / this.tempo));
    }
    itemStateDisplay.setText("Animation was sped up.");
  }

  /**
   * Immediately slows down an animation in the visual view, upon user clicks, and will be reflected
   * in the SVG output, if enabled by the user.
   */
  public void speedDown() {
    this.tempo /= 1.2;
    this.timer.setDelay((int) (1000 / this.tempo));
    itemStateDisplay.setText("Animation was slowed down.");
  }

  /**
   * Creates a looping animation that resets to the beginning upon completion, and instantly replays
   * the animation forever.
   */
  public void loop() {
    this.isLooped = (!(this.isLooped));
    itemStateDisplay.setText("Looping was toggled.");

  }

  /**
   * Outputs an SVG file based on the file name provided from the text field. Can handle looping vs.
   * non-looping svg outputs.
   */
  public void outputSVG() {
    FileWriter writer = null;
    StringBuffer out = new StringBuffer();
    String name = fileName.getText();
    itemStateDisplay.setText("SVG output was written to file: " + fileName.getText() + ".svg");

    fileName.setText("");
    try {
      writer = new FileWriter(new File(name + ".svg"));
    } catch (IOException e) {
      System.err.println("Caught IOException: " + e.getMessage());
    }
    if (this.isLooped) {
      if (this.model.getGroupFigs().size() > 0) {
        IViewImplSVGLayeredLooped viewSVG = new IViewImplSVGLayeredLooped(this.model, out, this.tempo,
                1200, 900);
        ArrayList<String> allShapes = new ArrayList<>();
        for (ArrayList<String> list : this.model.getGroupFigs().values()) {
          allShapes.addAll(list);
        }
        viewSVG.play(allShapes);
      }
        else {
          IView viewSVG = new IViewImplSVGLooped(this.model, out, this.tempo,
                  1200, 900);
          viewSVG.play();
        }
      }
    else {
        if (this.model.getGroupFigs().size() > 0) {
          IViewImplSVGLayered viewSVG = new IViewImplSVGLayered(this.model, out, this.tempo,
                  1200, 900);
          ArrayList<String> allShapes = new ArrayList<>();
          for (ArrayList<String> list : this.model.getGroupFigs().values()) {
            allShapes.addAll(list);
          }
          viewSVG.play(allShapes);
        }
        else {
          IView viewSVG = new IViewImplSVGAnimation(model, out,
                  tempo, 800, 800);
          viewSVG.play();
        }
    }
    // closes writer to flush
    if (writer != null) {
      try {
        writer.write(out.toString());
        writer.close();
      } catch (IOException e) {
        throw new IllegalArgumentException();
      }
    }
  }

  @Override
  public void play() {
    setVisible(true);
    timer.restart();
  }
}