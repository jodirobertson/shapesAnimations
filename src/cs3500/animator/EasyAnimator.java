package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.controller.IControllerHybridImpl;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IModelImpl;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.util.TweenModelBuilderImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.IViewCreator;

/**
 * Runs the main program according to given arguments.
 */
public final class EasyAnimator {

  /**
   * Constructs a composition of animated IFigures in the desired view format according to
   * user-provided input, output, and tempo.
   *
   * @param args The input file name, type of view desired, output type desired, and tempo.
   * @throws Exception if any of the given arguments are invalid.
   */
  public static void main(String[] args) throws Exception {
    String fileName = "resources/layered-shapes-3.txt";
    String viewType = "interactive";
    String outputType = "out";
    double tempo = 20;

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-if":
          fileName = args[i + 1];
          i++;
          break;
        case "-iv":
          viewType = args[i + 1];
          i++;
          break;
        case "-o":
          outputType = args[i + 1];
          i++;
          break;
        case "-speed":
          tempo = Double.valueOf(args[i + 1]);
          i++;
          break;
        default:
          JOptionPane.showMessageDialog(new JFrame(), "Invalid arguments",
                  "Error message", JOptionPane.ERROR_MESSAGE);
      }
    }

    // checks valid tempo
    if (tempo <= 0) {
      throw new IllegalArgumentException("Tempo must be greater than zero.");
    }

    IView view;
    IModel model = new IModelImpl();
    Appendable output = System.out;

    FileWriter writer = null;

    if (outputType.equals("out")) {
      output = System.out;
    } else if (outputType.length() > 4 &&
            (outputType.substring(outputType.length() - 4).equals(".svg") ||
                    outputType.substring(outputType.length() - 4).equals(".txt"))) {
      try {
        writer = new FileWriter(outputType);
      } catch (IOException e) {
        System.err.println("Caught IOException: " + e.getMessage());
      }
      output = writer;
    } else if (!outputType.equals("")) {
      JOptionPane.showMessageDialog(new JFrame(), "Invalid arguments",
              "Error message", JOptionPane.ERROR_MESSAGE);
    }

    try {
      model = new AnimationFileReader().readFile(fileName, new TweenModelBuilderImpl());
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Input file not found.");
    }

    //UPDATE: implemented factory pattern here with IViewCreator
    switch (viewType) {
      case "text":
        view = new IViewCreator().createTextSummary(model, output, tempo);
        view.play();
        break;
      case "visual":
        view = new IViewCreator().createVisual(model, tempo);
        view.play();
        break;
      case "svg":
        view = new IViewCreator().createSVG(model, output, tempo, 800, 800);
        view.play();
        break;
      case "interactive":
        new IControllerHybridImpl(new IViewCreator().createHybrid(model, tempo));
        break;
      default:
        JOptionPane.showMessageDialog(new JFrame(), "Invalid arguments",
                "Error message", JOptionPane.ERROR_MESSAGE);
    }

    if (writer != null) {
      try {
        writer.close();
      } catch (IOException e) {
        throw new IllegalArgumentException("Could not close output.");
      }
    }
  }
}