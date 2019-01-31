//import org.junit.Test;
//
//import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
//import java.io.StringWriter;
//
//import cs3500.animator.model.IAnimation;
//import cs3500.animator.model.IAnimationImplColorChange;
//import cs3500.animator.model.IAnimationImplMove;
//import cs3500.animator.model.IAnimationImplScale;
//import cs3500.animator.model.IFigure;
//import cs3500.animator.model.IFigureImplDummy;
//import cs3500.animator.model.IModel;
//import cs3500.animator.model.IModelImpl;
//import cs3500.animator.util.AnimationFileReader;
//import cs3500.animator.util.TweenModelBuilder;
//import cs3500.animator.util.TweenModelBuilderImpl;
//import cs3500.animator.view.IView;
//import cs3500.animator.view.IViewImplSVGAnimation;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Class to test the SVG output IView.
// */
//public class IViewImplSVGAnimationTest {
//
//  // tests exception if the given tick is negative
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidTick() {
//    Appendable out = new StringBuffer();
//    IModel model = new IModelImpl();
//    IView view = new IViewImplSVGAnimation(model, out, -20, 1200, 900);
//  }
//
//  // tests exception if the given tick is zero
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidTick2() {
//    Appendable out = new StringBuffer();
//    IModel model = new IModelImpl();
//    IView view = new IViewImplSVGAnimation(model, out, 0, 1200, 900);
//  }
//
//  // tests exception if the given model is null
//  @Test(expected = IllegalArgumentException.class)
//  public void testNullInput2() throws Exception {
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(null, out, 20, 1200, 900);
//  }
//
//  // tests exception if the given output is null
//  @Test(expected = IllegalArgumentException.class)
//  public void testNullInput() throws Exception {
//    TweenModelBuilder<IModel> tween = new TweenModelBuilderImpl();
//    IModel model = new AnimationFileReader().readFile("resources/small-demo.txt", tween);
//    IView view = new IViewImplSVGAnimation(model, null, 20, 1200, 900);
//  }
//
//  // tests exception if the given output is closed
//  @Test(expected = IllegalArgumentException.class)
//  public void testClosedOutput() throws Exception {
//    TweenModelBuilder<IModel> tween = new TweenModelBuilderImpl();
//    IModel model = new AnimationFileReader().readFile("resources/small-demo.txt", tween);
//    Appendable out = new BufferedWriter(new StringWriter());
//    ((BufferedWriter) out).close();
//    IView view = new IViewImplSVGAnimation(model, out, 20, 1200, 900);
//    view.play();
//  }
//
//  // tests exception if the given file to be read in is not found
//  @Test(expected = FileNotFoundException.class)
//  public void testInputNotFound() throws Exception {
//    TweenModelBuilder<IModel> tween = new TweenModelBuilderImpl();
//    IModel model = new AnimationFileReader().readFile("test/small-demo.txt", tween);
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(model, out, 20, 1200, 900);
//  }
//
//  // tests exception if one of the screen dimensions is zero
//  @Test(expected = IllegalArgumentException.class)
//  public void testZeroScreenDim() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(model, out, 20, 0, 200);
//  }
//
//  // tests exception if one of the screen dimensions is zero
//  @Test(expected = IllegalArgumentException.class)
//  public void testZeroScreenDim2() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(model, out, 20, 200, 0);
//  }
//
//  // tests exception if one of the screen dimensions is negative
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidDim() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(model, out, 20, -200, 300);
//  }
//
//  // tests exception if both of the screen dimensions are invalid
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidDim2() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(model, out, 20, -200, 0);
//  }
//
//  // tests output of small-demo provided as input file
//  @Test
//  public void testSmallDemo() throws Exception {
//    TweenModelBuilder<IModel> tween = new TweenModelBuilderImpl();
//    IModel model = new AnimationFileReader().readFile("resources/small-demo.txt", tween);
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(model, out, 20, 1200, 900);
//    view.play();
//    assertEquals("<svg width=\"1200\" height=\"900\" version=\"1.1\"\n" +
//            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
//            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" \n" +
//            " fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"2550.0ms\" dur=\"950.0ms\" \n" +
//            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"3500.0ms\" dur=\"1500.0ms\" \n" +
//            "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"3500.0ms\" dur=\"1500.0ms\" \n" +
//            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" \n" +
//            "fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"300.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2500.0ms\" \n" +
//            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"2500.0ms\" dur=\"1500.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" \n" +
//            "to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</ellipse>\n" +
//            "</svg>", out.toString());
//  }
//
//  // tests SVG of the previously given example from HW5 adding the animations out of startTime order
//  @Test
//  public void testAnimationOrder() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(model, out, 3, 1200, 900);
//
//    IFigure r = new IFigureImplDummy(model, "rectangle", new double[]{1, 0, 0},
//            "R", 200,
//            200, 1, 100, 50, 100);
//    IFigure c = new IFigureImplDummy(model, "oval", new double[]{0, 0, 1},
//            "C", 500, 100, 6,
//            100, 60, 30);
//    model.add(r);
//    model.add(c);
//    IAnimation one = new IAnimationImplMove(model, "R", 100, 100, 10, 50);
//    model.animate(one, "R");
//    IAnimation two = new IAnimationImplMove(model, "C", 0, 300, 20, 70);
//    model.animate(two, "C");
//    IAnimation five = new IAnimationImplMove(model, "R", -100, -100, 70, 100);
//    model.animate(five, "R");
//    IAnimation four = new IAnimationImplScale(model, "R", 0.5, 1, 51, 70);
//    model.animate(four, "R");
//    IAnimation three = new IAnimationImplColorChange(model, "C", new double[]{0, 1, 0}, 50, 80);
//    model.animate(three, "C");
//    view.play();
//
//    assertEquals("<svg width=\"1200\" height=\"900\" version=\"1.1\"\n" +
//            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
//            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" \n" +
//            " fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"333.3ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"3333.3ms\" dur=\"13333.3ms\" \n" +
//            "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"3333.3ms\" dur=\"13333.3ms\" \n" +
//            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"17000.0ms\" dur=\"6333.3ms\" \n" +
//            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"23333.3ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"23333.3ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" \n" +
//            "fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"2000.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"6666.7ms\" dur=\"16666.7ms\" \n" +
//            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"16666.7ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" \n" +
//            "to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</ellipse>\n" +
//            "</svg>", out.toString());
//  }
//
//  // tests SVG of the previously given example from HW5 adding the rectangle first
//  @Test
//  public void testAddingFiguresOrder() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(model, out, 3, 1200, 900);
//
//    IFigure r = new IFigureImplDummy(model, "rectangle", new double[]{1, 0, 0},
//            "R", 200,
//            200, 1, 100, 50, 100);
//    IFigure c = new IFigureImplDummy(model, "oval", new double[]{0, 0, 1},
//            "C", 500, 100, 6,
//            100, 60, 30);
//    model.add(r);
//    model.add(c);
//    IAnimation one = new IAnimationImplMove(model, "R", 100, 100, 10, 50);
//    model.animate(one, "R");
//    IAnimation two = new IAnimationImplMove(model, "C", 0, 300, 20, 70);
//    model.animate(two, "C");
//    IAnimation three = new IAnimationImplColorChange(model, "C", new double[]{0, 1, 0}, 50, 80);
//    model.animate(three, "C");
//    IAnimation four = new IAnimationImplScale(model, "R", 0.5, 1, 51, 70);
//    model.animate(four, "R");
//    IAnimation five = new IAnimationImplMove(model, "R", -100, -100, 70, 100);
//    model.animate(five, "R");
//    view.play();
//
//    assertEquals("<svg width=\"1200\" height=\"900\" version=\"1.1\"\n" +
//            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
//            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" \n" +
//            " fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"333.3ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"3333.3ms\" dur=\"13333.3ms\" \n" +
//            "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"3333.3ms\" dur=\"13333.3ms\" \n" +
//            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"17000.0ms\" dur=\"6333.3ms\" \n" +
//            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"23333.3ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"23333.3ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" \n" +
//            "fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"2000.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"6666.7ms\" dur=\"16666.7ms\" \n" +
//            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"16666.7ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" \n" +
//            "to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</ellipse>\n" +
//            "</svg>", out.toString());
//  }
//
//  // tests SVG of the previously given example from HW5 adding the oval first
//  @Test
//  public void testAddingFiguresOrder2() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(model, out, 2, 700, 500);
//    IFigure r = new IFigureImplDummy(model, "rectangle", new double[]{1, 0, 0},
//            "R", 200,
//            200, 1, 100, 50, 100);
//    IFigure c = new IFigureImplDummy(model, "oval", new double[]{0, 0, 1},
//            "C", 500, 100, 6,
//            100, 60, 30);
//    model.add(c);
//    model.add(r);
//    IAnimation one = new IAnimationImplMove(model, "R", 100, 100, 10, 50);
//    model.animate(one, "R");
//    IAnimation two = new IAnimationImplMove(model, "C", 0, 300, 20, 70);
//    model.animate(two, "C");
//    IAnimation three = new IAnimationImplColorChange(model, "C", new double[]{0, 1, 0}, 50, 80);
//    model.animate(three, "C");
//    IAnimation four = new IAnimationImplScale(model, "R", 0.5, 1, 51, 70);
//    model.animate(four, "R");
//    IAnimation five = new IAnimationImplMove(model, "R", -100, -100, 70, 100);
//    model.animate(five, "R");
//    view.play();
//    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
//            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
//            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" \n" +
//            "fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"3000.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"25000.0ms\" \n" +
//            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"25000.0ms\" dur=\"15000.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" \n" +
//            "to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</ellipse>\n" +
//            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" \n" +
//            " fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"500.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"20000.0ms\" \n" +
//            "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"20000.0ms\" \n" +
//            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"25500.0ms\" dur=\"9500.0ms\" \n" +
//            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"35000.0ms\" dur=\"15000.0ms\" \n" +
//            "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"35000.0ms\" dur=\"15000.0ms\" \n" +
//            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "</svg>", out.toString());
//  }
//
//  // tests output of given buildings.txt as input file
//  @Test
//  public void testBuildingsFile() throws FileNotFoundException {
//    TweenModelBuilder<IModel> tween = new TweenModelBuilderImpl();
//    IModel model = new AnimationFileReader().readFile("resources/buildings.txt", tween);
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGAnimation(model, out, 20, 1200, 900);
//    view.play();
//    assertEquals("<svg width=\"1200\" height=\"900\" version=\"1.1\"\n" +
//            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
//            "<rect id=\"background\" x=\"0.0\" y=\"0.0\" width=\"800.0\" height=\"800.0\" \n" +
//            " fill=\"rgb(33.99150162935257,94.35000121593475,248.88000279664993" +
//            ")\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"2500.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(33.99150162935257,94.35000121593475" +
//            ",248.88000279664993)\" \n" +
//            "to=\"rgb(16.065000258386135,45.90000182390213,248.88000279664993)\"" +
//            " fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"B0\" x=\"80.0\" y=\"424.0\" width=\"100.0\" height=\"326.0\" \n" +
//            " fill=\"rgb(0.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"B1\" x=\"260.0\" y=\"365.0\" width=\"100.0\" height=\"385.0\" \n" +
//            " fill=\"rgb(0.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"B2\" x=\"440.0\" y=\"375.0\" width=\"100.0\" height=\"375.0\" \n" +
//            " fill=\"rgb(0.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"B3\" x=\"620.0\" y=\"445.0\" width=\"100.0\" height=\"305.0\" \n" +
//            " fill=\"rgb(0.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window000\" x=\"100.0\" y=\"850.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"850.0\" to=\"710.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window001\" x=\"140.0\" y=\"850.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"850.0\" to=\"710.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window010\" x=\"100.0\" y=\"890.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"890.0\" to=\"670.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window011\" x=\"140.0\" y=\"890.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"890.0\" to=\"670.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window020\" x=\"100.0\" y=\"930.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"930.0\" to=\"630.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window021\" x=\"140.0\" y=\"930.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"930.0\" to=\"630.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"4100.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window030\" x=\"100.0\" y=\"970.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"970.0\" to=\"590.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window031\" x=\"140.0\" y=\"970.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"970.0\" to=\"590.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window040\" x=\"100.0\" y=\"1010.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1010.0\" to=\"550.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window041\" x=\"140.0\" y=\"1010.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1010.0\" to=\"550.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"7350.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window050\" x=\"100.0\" y=\"1050.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1050.0\" to=\"510.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window051\" x=\"140.0\" y=\"1050.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1050.0\" to=\"510.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window060\" x=\"100.0\" y=\"1090.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1090.0\" to=\"470.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"7350.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window061\" x=\"140.0\" y=\"1090.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1090.0\" to=\"470.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"4650.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window100\" x=\"280.0\" y=\"-750.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-750.0\" to=\"710.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window101\" x=\"320.0\" y=\"-750.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-750.0\" to=\"710.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"5400.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window110\" x=\"280.0\" y=\"-710.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-710.0\" to=\"670.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"4750.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window111\" x=\"320.0\" y=\"-710.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-710.0\" to=\"670.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window120\" x=\"280.0\" y=\"-670.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-670.0\" to=\"630.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window121\" x=\"320.0\" y=\"-670.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-670.0\" to=\"630.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window130\" x=\"280.0\" y=\"-630.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-630.0\" to=\"590.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window131\" x=\"320.0\" y=\"-630.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-630.0\" to=\"590.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"7400.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window140\" x=\"280.0\" y=\"-590.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-590.0\" to=\"550.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window141\" x=\"320.0\" y=\"-590.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-590.0\" to=\"550.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window150\" x=\"280.0\" y=\"-550.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-550.0\" to=\"510.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window151\" x=\"320.0\" y=\"-550.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-550.0\" to=\"510.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"4950.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window160\" x=\"280.0\" y=\"-510.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-510.0\" to=\"470.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window161\" x=\"320.0\" y=\"-510.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-510.0\" to=\"470.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window170\" x=\"280.0\" y=\"-470.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-470.0\" to=\"430.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window171\" x=\"320.0\" y=\"-470.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-470.0\" to=\"430.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window180\" x=\"280.0\" y=\"-430.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-430.0\" to=\"390.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window181\" x=\"320.0\" y=\"-430.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-430.0\" to=\"390.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window200\" x=\"460.0\" y=\"850.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"850.0\" to=\"710.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"6350.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window201\" x=\"500.0\" y=\"850.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"850.0\" to=\"710.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window210\" x=\"460.0\" y=\"890.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"890.0\" to=\"670.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"4150.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window211\" x=\"500.0\" y=\"890.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"890.0\" to=\"670.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window220\" x=\"460.0\" y=\"930.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"930.0\" to=\"630.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window221\" x=\"500.0\" y=\"930.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"930.0\" to=\"630.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window230\" x=\"460.0\" y=\"970.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"970.0\" to=\"590.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window231\" x=\"500.0\" y=\"970.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"970.0\" to=\"590.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window240\" x=\"460.0\" y=\"1010.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1010.0\" to=\"550.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window241\" x=\"500.0\" y=\"1010.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1010.0\" to=\"550.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"5600.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window250\" x=\"460.0\" y=\"1050.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1050.0\" to=\"510.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"6400.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window251\" x=\"500.0\" y=\"1050.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1050.0\" to=\"510.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window260\" x=\"460.0\" y=\"1090.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1090.0\" to=\"470.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window261\" x=\"500.0\" y=\"1090.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1090.0\" to=\"470.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"4800.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window270\" x=\"460.0\" y=\"1130.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1130.0\" to=\"430.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window271\" x=\"500.0\" y=\"1130.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"1130.0\" to=\"430.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window300\" x=\"640.0\" y=\"-750.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-750.0\" to=\"710.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window301\" x=\"680.0\" y=\"-750.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-750.0\" to=\"710.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"6750.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window310\" x=\"640.0\" y=\"-710.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-710.0\" to=\"670.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"5150.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window311\" x=\"680.0\" y=\"-710.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-710.0\" to=\"670.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window320\" x=\"640.0\" y=\"-670.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-670.0\" to=\"630.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window321\" x=\"680.0\" y=\"-670.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-670.0\" to=\"630.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window330\" x=\"640.0\" y=\"-630.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-630.0\" to=\"590.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"4050.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window331\" x=\"680.0\" y=\"-630.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-630.0\" to=\"590.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"5950.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window340\" x=\"640.0\" y=\"-590.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-590.0\" to=\"550.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window341\" x=\"680.0\" y=\"-590.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-590.0\" to=\"550.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window350\" x=\"640.0\" y=\"-550.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-550.0\" to=\"510.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window351\" x=\"680.0\" y=\"-550.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-550.0\" to=\"510.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"5000.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window360\" x=\"640.0\" y=\"-510.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-510.0\" to=\"470.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"4950.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<rect id=\"window361\" x=\"680.0\" y=\"-510.0\" width=\"20.0\" height=\"20.0\" \n" +
//            " fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"-510.0\" to=\"470.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"4950.0ms\" dur=\"250.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" \n" +
//            "to=\"rgb(255.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "<ellipse id=\"moon\" cx=\"250.0\" cy=\"250.0\" rx=\"50.0\" ry=\"50.0\" \n" +
//            "fill=\"rgb(229.49999392032623,229.49999392032623,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"eclipse\" cx=\"450.0\" cy=\"50.0\" rx=\"50.0\" ry=\"50.0\" \n" +
//            "fill=\"rgb(33.99150162935257,94.35000121593475,248.88000279664993)\" visibility=\"" +
//            "hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"50.0ms\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"cx\" from=\"450.0\" to=\"280.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"cy\" from=\"50.0\" to=\"230.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"2500.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(33.99150162935257,94.35000121593475,248.88000" +
//            "279664993)\" \n" +
//            "to=\"rgb(16.065000258386135,45.90000182390213,248.88000279664993)\" fill=\"f" +
//            "reeze\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star0\" cx=\"226.0\" cy=\"69.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5400.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star1\" cx=\"588.0\" cy=\"214.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"6000.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star2\" cx=\"492.0\" cy=\"80.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5500.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star3\" cx=\"377.0\" cy=\"289.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4650.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star4\" cx=\"711.0\" cy=\"284.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4150.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star5\" cx=\"511.0\" cy=\"263.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4850.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star6\" cx=\"532.0\" cy=\"73.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4600.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star7\" cx=\"335.0\" cy=\"68.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5850.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star8\" cx=\"314.0\" cy=\"150.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4300.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star9\" cx=\"173.0\" cy=\"284.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4400.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star10\" cx=\"722.0\" cy=\"105.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4500.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star11\" cx=\"527.0\" cy=\"267.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"6450.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star12\" cx=\"771.0\" cy=\"197.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4300.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star13\" cx=\"769.0\" cy=\"182.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5650.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star14\" cx=\"513.0\" cy=\"81.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4900.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star15\" cx=\"624.0\" cy=\"152.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5700.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star16\" cx=\"494.0\" cy=\"255.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5300.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star17\" cx=\"408.0\" cy=\"66.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4450.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star18\" cx=\"553.0\" cy=\"270.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5650.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star19\" cx=\"111.0\" cy=\"200.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4700.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star20\" cx=\"740.0\" cy=\"81.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5150.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star21\" cx=\"798.0\" cy=\"140.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"6300.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star22\" cx=\"187.0\" cy=\"128.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4450.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star23\" cx=\"137.0\" cy=\"233.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4800.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star24\" cx=\"247.0\" cy=\"156.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"6000.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star25\" cx=\"262.0\" cy=\"122.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5600.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star26\" cx=\"325.0\" cy=\"272.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5300.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star27\" cx=\"415.0\" cy=\"185.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5850.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star28\" cx=\"677.0\" cy=\"140.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5950.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star29\" cx=\"49.0\" cy=\"249.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4800.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star30\" cx=\"391.0\" cy=\"318.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"6150.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star31\" cx=\"188.0\" cy=\"239.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"6050.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star32\" cx=\"553.0\" cy=\"235.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5450.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star33\" cx=\"659.0\" cy=\"104.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4700.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star34\" cx=\"286.0\" cy=\"114.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4200.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star35\" cx=\"652.0\" cy=\"329.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4600.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star36\" cx=\"694.0\" cy=\"270.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"5750.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star37\" cx=\"116.0\" cy=\"279.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4950.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star38\" cx=\"607.0\" cy=\"305.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"4950.0ms\" />\n" +
//            "</ellipse>\n" +
//            "<ellipse id=\"star39\" cx=\"465.0\" cy=\"165.0\" rx=\"3.0\" ry=\"3.0\" \n" +
//            "fill=\"rgb(255.0,255.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"6350.0ms\" />\n" +
//            "</ellipse>\n" +
//            "</svg>", out.toString());
//  }
//}
