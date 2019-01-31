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
//import cs3500.animator.view.IViewImplSVGLooped;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Class to test SVGLooped animations.
// */
//public class IViewImplSVGLoopedTest {
//
//  // tests exception if the given tick is negative
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidTick() {
//    Appendable out = new StringBuffer();
//    IModel model = new IModelImpl();
//    IView view = new IViewImplSVGLooped(model, out, -20, 1200, 900);
//  }
//
//  // tests exception if the given tick is zero
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidTick2() {
//    Appendable out = new StringBuffer();
//    IModel model = new IModelImpl();
//    IView view = new IViewImplSVGLooped(model, out, 0, 1200, 900);
//  }
//
//  // tests exception if the given model is null
//  @Test(expected = IllegalArgumentException.class)
//  public void testNullInput2() throws Exception {
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGLooped(null, out, 20, 1200, 900);
//  }
//
//  // tests exception if the given output is null
//  @Test(expected = IllegalArgumentException.class)
//  public void testNullInput() throws Exception {
//    TweenModelBuilder<IModel> tween = new TweenModelBuilderImpl();
//    IModel model = new AnimationFileReader().readFile("resources/small-demo.txt", tween);
//    IView view = new IViewImplSVGLooped(model, null, 20, 1200, 900);
//  }
//
//  // tests exception if the given output is closed
//  @Test(expected = IllegalArgumentException.class)
//  public void testClosedOutput() throws Exception {
//    TweenModelBuilder<IModel> tween = new TweenModelBuilderImpl();
//    IModel model = new AnimationFileReader().readFile("resources/small-demo.txt", tween);
//    Appendable out = new BufferedWriter(new StringWriter());
//    ((BufferedWriter) out).close();
//    IView view = new IViewImplSVGLooped(model, out, 20, 1200, 900);
//    view.play();
//  }
//
//  // tests exception if the given file to be read in is not found
//  @Test(expected = FileNotFoundException.class)
//  public void testInputNotFound() throws Exception {
//    TweenModelBuilder<IModel> tween = new TweenModelBuilderImpl();
//    IModel model = new AnimationFileReader().readFile("test/small-demo.txt", tween);
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGLooped(model, out, 20, 1200, 900);
//  }
//
//  // tests exception if one of the screen dimensions is zero
//  @Test(expected = IllegalArgumentException.class)
//  public void testZeroScreenDim() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGLooped(model, out, 20, 0, 200);
//  }
//
//  // tests exception if one of the screen dimensions is zero
//  @Test(expected = IllegalArgumentException.class)
//  public void testZeroScreenDim2() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGLooped(model, out, 20, 200, 0);
//  }
//
//  // tests exception if one of the screen dimensions is negative
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidDim() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGLooped(model, out, 20, -200, 300);
//  }
//
//  // tests exception if both of the screen dimensions are invalid
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidDim2() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGLooped(model, out, 20, -200, 0);
//  }
//
//  // tests output of small-demo provided as input file
//  @Test
//  public void testSmallDemo() throws Exception {
//    TweenModelBuilder<IModel> tween = new TweenModelBuilderImpl();
//    IModel model = new AnimationFileReader().readFile("resources/small-demo.txt", tween);
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGLooped(model, out, 20, 1200, 900);
//    view.play();
//    assertEquals("<svg width=\"1200\" height=\"900\" version=\"1.1\"\n" +
//            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
//            "<rect> \n" +
//            " <animate id=\"base\" begin=\"0;base.end\" dur=\"5000.0ms\" attri" +
//            "buteName=\"visibility\" from=\"hide\" to=\"hide\"/> </rect><rect id=\"R" +
//            "\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" \n" +
//            " fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"base.begin+50.0ms\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"fill\" to=\"rgb(255.0,0.0,0.0)\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"x\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"y\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"width\" to=\"50.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"height\" to=\"100.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+500.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+500.0ms\" dur=\"2000.0ms\" \n" +
//            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+2550.0ms\" dur=\"950.0ms\" \n" +
//            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+3500.0ms\" dur=\"1500.0ms\" \n" +
//            "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+3500.0ms\" dur=\"1500.0ms\" \n" +
//            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"visible\"\n" +
//            "  to=\"hidden\" begin=\"base.end\" />\n" +
//            "</rect>\n" +
//            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" \n" +
//            "fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"base.begin+300.0ms\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"fill\" to=\"rgb(0.0,0.0,255.0)\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"cx\" to=\"500.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"cy\" to=\"100.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"rx\" to=\"60.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"ry\" to=\"30.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+1000.0ms\" dur=\"2500.0ms\" \n" +
//            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.begin+2500.0ms\" dur=\"1500.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" \n" +
//            "to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"visible\"\n" +
//            " to=\"hidden\" begin=\"base.end\" />\n" +
//            "</ellipse>\n" +
//            "</svg>", out.toString());
//  }
//
//  // tests SVG of the previously given example from HW5 adding the animations out of startTime order
//  @Test
//  public void testAnimationOrder() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGLooped(model, out, 3, 1200, 900);
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
//            "<rect> \n" +
//            " <animate id=\"base\" begin=\"0;base.end\" dur=\"33333.3ms\"" +
//            " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/> </rect><rec" +
//            "t id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" \n" +
//            " fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"base.begin+333.3ms\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"fill\" to=\"rgb(255.0,0.0,0.0)\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"x\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"y\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"width\" to=\"50.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"height\" to=\"100.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+3333.3ms\" dur=\"13333.3ms\" \n" +
//            "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+3333.3ms\" dur=\"13333.3ms\" \n" +
//            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+17000.0ms\" dur=\"6333.3ms\" \n" +
//            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+23333.3ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+23333.3ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"visible\"\n" +
//            "  to=\"hidden\" begin=\"base.end\" />\n" +
//            "</rect>\n" +
//            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" \n" +
//            "fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"base.begin+2000.0ms\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"fill\" to=\"rgb(0.0,0.0,255.0)\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"cx\" to=\"500.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"cy\" to=\"100.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"rx\" to=\"60.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"ry\" to=\"30.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+6666.7ms\" dur=\"16666.7ms\" \n" +
//            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.begin+16666.7ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" \n" +
//            "to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"visible\"\n" +
//            " to=\"hidden\" begin=\"base.end\" />\n" +
//            "</ellipse>\n" +
//            "</svg>", out.toString());
//  }
//
//  // tests SVG of the previously given example from HW5 adding the rectangle first
//  @Test
//  public void testAddingFiguresOrder() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGLooped(model, out, 3, 1200, 900);
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
//            "<rect> \n" +
//            " <animate id=\"base\" begin=\"0;base.end\" dur=\"33333.3ms\" attrib" +
//            "uteName=\"visibility\" from=\"hide\" to=\"hide\"/> </rect><rect " +
//            "id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" \n" +
//            " fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"base.begin+333.3ms\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"fill\" to=\"rgb(255.0,0.0,0.0)\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"x\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"y\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"width\" to=\"50.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"height\" to=\"100.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+3333.3ms\" dur=\"13333.3ms\" \n" +
//            "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+3333.3ms\" dur=\"13333.3ms\" \n" +
//            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+17000.0ms\" dur=\"6333.3ms\" \n" +
//            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+23333.3ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+23333.3ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"visible\"\n" +
//            "  to=\"hidden\" begin=\"base.end\" />\n" +
//            "</rect>\n" +
//            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" \n" +
//            "fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"base.begin+2000.0ms\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"fill\" to=\"rgb(0.0,0.0,255.0)\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"cx\" to=\"500.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"cy\" to=\"100.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"rx\" to=\"60.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"ry\" to=\"30.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+6666.7ms\" dur=\"16666.7ms\" \n" +
//            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.begin+16666.7ms\" dur=\"10000.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" \n" +
//            "to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"visible\"\n" +
//            " to=\"hidden\" begin=\"base.end\" />\n" +
//            "</ellipse>\n" +
//            "</svg>", out.toString());
//  }
//
//  // tests SVG of the previously given example from HW5 adding the oval first
//  @Test
//  public void testAddingFiguresOrder2() {
//    IModel model = new IModelImpl();
//    Appendable out = new StringBuffer();
//    IView view = new IViewImplSVGLooped(model, out, 2, 700, 500);
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
//            "<rect> \n" +
//            " <animate id=\"base\" begin=\"0;base.end\" dur=\"50000.0ms\" attr" +
//            "ibuteName=\"visibility\" from=\"hide\" to=\"hide\"/> </rect><ellipse id=" +
//            "\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" \n" +
//            "fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"base.begin+3000.0ms\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"fill\" to=\"rgb(0.0,0.0,255.0)\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"cx\" to=\"500.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"cy\" to=\"100.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"rx\" to=\"60.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"ry\" to=\"30.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+10000.0ms\" dur=\"25000.0ms\" \n" +
//            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.begin+25000.0ms\" dur=\"15000.0ms\" \n" +
//            "attributeName=\"fill\" from=\"rgb(0.0,0.0,255.0)\" \n" +
//            "to=\"rgb(0.0,255.0,0.0)\" fill=\"freeze\" />\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"visible\"\n" +
//            " to=\"hidden\" begin=\"base.end\" />\n" +
//            "</ellipse>\n" +
//            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" \n" +
//            " fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"hidden\" \n" +
//            "to=\"visible\" begin=\"base.begin+500.0ms\" />\n" +
//            "<animate attributeType=\"CSS\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"fill\" to=\"rgb(255.0,0.0,0.0)\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"x\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"y\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"width\" to=\"50.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1.0ms\" \n" +
//            "attributeName=\"height\" to=\"100.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+5000.0ms\" dur=\"20000.0ms\" \n" +
//            "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+5000.0ms\" dur=\"20000.0ms\" \n" +
//            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+25500.0ms\" dur=\"9500.0ms\" \n" +
//            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+35000.0ms\" dur=\"15000.0ms\" \n" +
//            "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" begin=\"base.begin+35000.0ms\" dur=\"15000.0ms\" \n" +
//            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "<set attributeType=\"xml\" attributeName=\"visibility\" from=\"visible\"\n" +
//            "  to=\"hidden\" begin=\"base.end\" />\n" +
//            "</rect>\n" +
//            "</svg>", out.toString());
//  }
//}
