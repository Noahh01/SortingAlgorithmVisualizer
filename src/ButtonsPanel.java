import javax.swing.*;
import java.awt.*;

/**
 * ButtonsPanel class, used for users to interact with UI.
 * @author Noah Hammoud
 */
public class ButtonsPanel extends JPanel {
    private final int panelHeight = 50;
    private JLabel arrayState;
    private JButton newArray;
    private JButton sortArray;

    /**
     * The constructor for the ButtonsPanel class.
     * @param controller VisualizerController used as the action listener for the JButtons and JComboBox
     */
    public ButtonsPanel(VisualizerController controller) {
        super();
        setBackground(Color.black);
        setPreferredSize(new Dimension(VisualizerView.frameWidth, panelHeight));
        setLayout(new GridBagLayout());
        addComponents(controller);
    }

    /**
     * Adds all the components to Panel.
     * @param controller VisualizerController used as the action listener for the JButtons and JComboBox
     */
    private void addComponents(VisualizerController controller) {
        GridBagConstraints c = new GridBagConstraints();

        String newArrayText = "New Array";
        newArray = new JButton(newArrayText);
        newArray.setActionCommand(newArrayText);
        newArray.addActionListener(controller);
        newArray.setFocusPainted(false);
        this.add(newArray);

        String sortArrayText = "Sort Array";
        sortArray = new JButton(sortArrayText);
        sortArray.addActionListener(controller);
        sortArray.setFocusPainted(false);
        this.add(sortArray);

        arrayState = new JLabel();
        arrayState.setFont(new Font("Arial", Font.BOLD, 18));
        setArrayState("Unsorted");
        c.insets = new Insets(0, 55, 0, 90);
        this.add(arrayState, c);

        String[] sortingAlgorithms = {VisualizerModel.BUBBLE_SORT, VisualizerModel.MERGE_SORT, VisualizerModel.INSERTION_SORT};
        JComboBox jComboBox = new JComboBox(sortingAlgorithms);
        jComboBox.addActionListener(controller);
        this.add(jComboBox);
    }

    /**
     * Sets the text and colour of arrayState JLabel.
     * @param text String state of array
     */
    public void setArrayState(String text) {
        switch (text) {
            case "Unsorted":
                arrayState.setForeground(Color.red);
                break;
            case "Sorting":
                arrayState.setForeground(Color.YELLOW);
                this.toggleButtons();
                break;
            case "Sorted":
                arrayState.setForeground(Color.green);
                this.toggleButtons();
                break;
        }
        arrayState.setText("Array State: " + text);
    }

    /**
     * Toggles the JButtons enable (disabled when sorting, otherwise enabled).
     */
    private void toggleButtons() {
        boolean toggle = !(newArray.isEnabled());
        newArray.setEnabled(toggle);
        sortArray.setEnabled(toggle);
    }
}
