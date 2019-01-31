import org.junit.Test;

import cs3500.animator.model.IModel;
import cs3500.animator.model.IModelImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.IViewCreator;
import cs3500.animator.view.IViewImplHybridAnimation;
import cs3500.animator.view.IViewImplSVGAnimation;
import cs3500.animator.view.IViewImplTextSummary;
import cs3500.animator.view.IViewImplVisualAnimation;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests that the factory for IViews works correctly.
 */
public class IViewCreatorTest {

  // tests that an IViewImplTextSummary is made when the IViewCreator is asked to create one
  @Test
  public void testTextSummaryIView() {
    IModel model = new IModelImpl();
    Appendable out = new StringBuffer();
    IModel model2 = new IModelImpl();
    Appendable out2 = new StringBuffer();
    IView view = new IViewCreator().createTextSummary(model, out, 1);
    view.play();
    assertEquals("Shapes:", out.toString());
    IView view2 = new IViewImplTextSummary(model2, out2, 1);
    view2.play();
    assertEquals(out2.toString(), out.toString());
  }

  // tests that an IViewImplSVGAnimation view is made when IViewCreator is asked to create it
  @Test
  public void testSVGIView() {
    IModel model = new IModelImpl();
    Appendable out = new StringBuffer();
    IModel model2 = new IModelImpl();
    Appendable out2 = new StringBuffer();
    IView view = new IViewCreator().createSVG(model, out, 1, 1200, 900);
    view.play();
    assertEquals("<svg width=\"1200\" height=\"900\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "</svg>", out.toString());
    IView view2 = new IViewImplSVGAnimation(model2, out2, 1, 1200, 900);
    view2.play();
    assertEquals(out2.toString(), out.toString());
  }

  // tests that an IViewImplHybridAnimation view is made when IViewCreator is asked to create it
  @Test
  public void testHybridIView() {
    IModel model = new IModelImpl();
    IView view = new IViewCreator().createHybrid(model, 1);
    assertTrue(view instanceof IViewImplHybridAnimation);
  }

  // tests that an IViewImplVisualAnimation view is made when IViewCreator is asked to create it
  @Test
  public void testVisualIView() {
    IModel model = new IModelImpl();
    IView view = new IViewCreator().createVisual(model, 1);
    assertTrue(view instanceof IViewImplVisualAnimation);
  }
}
