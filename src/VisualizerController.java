import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * VisualizerController class, used as the controller for the MVC.
 * @author Noah Hammoud
 */
public class VisualizerController implements ActionListener {
    private VisualizerModel model;
    private String selectedAlgorithm = VisualizerModel.BUBBLE_SORT;

    /**
     * The constructor for VisualizerController.
     * @param model VisualizerModel, the model object used for the MVC
     */
    public VisualizerController(VisualizerModel model) {
        this.model = model;
    }

    /**
     * Handles the action events of the JButtons and JComboBox components.
     * @param e ActionEvent object which caused the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (e.getActionCommand().equals("Sort Array")) {
                model.sortArray(selectedAlgorithm);
            }
            else {
                model.createNewArray();
            }
        }
        else if (e.getSource() instanceof JComboBox) {
            selectedAlgorithm = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();
        }
    }

}
