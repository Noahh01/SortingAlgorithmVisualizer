import javax.swing.*;
import java.awt.*;

/**
 * VisualizerView class, used as the view for the MVC.
 * @author Noah Hammoud
 */
public class VisualizerView extends JFrame implements VisualizerObserver {
    public static final int frameWidth = 660;
    private static final int delay = 100;
    private ButtonsPanel buttonsPanel;
    private BarsPanel barsPanel;
    private VisualizerModel model;

    /**
     * The constructor for VisualizerView class
     */
    public VisualizerView() {
        super("Sorting Algorithms Visualizer");
        model = new VisualizerModel(this);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addPanelsToView();
        pack();
        setVisible(true);
    }

    /**
     * Adds the buttonsPanel and barsPanel to the View.
     */
    private void addPanelsToView() {
        VisualizerController controller = new VisualizerController(model);

        buttonsPanel = new ButtonsPanel(controller);
        add(buttonsPanel, BorderLayout.NORTH);

        barsPanel = new BarsPanel();
        add(barsPanel, BorderLayout.PAGE_END);

        model.setArray(barsPanel.getArray());
    }

    /**
     * Generates a new array to be sorted.
     */
    private void generateNewArray() {
        barsPanel.setNewArray();
        barsPanel.repaint();
        model.setArray(barsPanel.getArray());
    }

    /**
     * Handles the event when a user clicks on the "New Array" button.
     */
    @Override
    public void handleNewArray() {
        generateNewArray();
    }

    /**
     * Handles the event when array elements (or bars) have been swapped by a sorting algorithm.
     */
    @Override
    public void handleSwap() {
        barsPanel.repaint();
        try {
            Thread.sleep(VisualizerView.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the State label of the GUI.
     * @param currentState String text which represents the current state of array.
     */
    @Override
    public void updateStateLabel(String currentState) {
        buttonsPanel.setArrayState(currentState);
    }

}
