import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * BarsPanel class, used to display array elements.
 * @author Noah Hammoud
 */
public class BarsPanel extends JPanel {
    private final int panelHeight = 450;
    private final int barWidth = 20;
    private final int padding = 60;
    private List<Integer> array;

    /**
     * The constructor for BarsPanel.
     */
    public BarsPanel() {
        super();
        setBackground(Color.black);
        setPreferredSize(new Dimension(VisualizerView.frameWidth, panelHeight));
        setNewArray();
    }

    /**
     * Sets new array of integers.
     */
    public void setNewArray() {
        array = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < VisualizerView.frameWidth - padding; i += barWidth) {
            array.add(50 + random.nextInt(panelHeight - 50 + 1));
        }
    }

    /**
     * Draws the array elements as bars to visualize the sorting.
     * @param g Graphics object of the panel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        int i = 0;
        for (int xpos = 0; xpos < VisualizerView.frameWidth - padding; xpos += barWidth, i++) {
            int rectVal = array.get(i);
            g.fillRect(xpos + (2 * i), panelHeight - rectVal, barWidth, rectVal);
        }
    }

    /**
     * Gets the array of integers.
     * @return List<Integer> the array of integers
     */
    public List<Integer> getArray() {
        return array;
    }

}
