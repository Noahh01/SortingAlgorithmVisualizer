import java.util.*;

/**
 * VisualizerModel class, used as the model for the MVC.
 * @author Noah Hammoud
 */
public class VisualizerModel {
    private List<Integer> array;
    private VisualizerObserver view;
    public static final String BUBBLE_SORT = "Bubble Sort";
    public static final String MERGE_SORT = "Merge Sort";
    public static final String INSERTION_SORT = "Insertion Sort";

    /**
     * The constructor for VisualizerModel.
     * @param view VisualizerView of the MVC
     */
    public VisualizerModel(VisualizerView view) {
        this.view = view;
    }

    /**
     * Sets the array instance variable of the model.
     * @param array List<Integer> the list of integers
     */
    public void setArray(List<Integer> array) {
        this.array = array;
    }

    /**
     * Runs the appropriate sorting algorithm based on the selected algorithm form the JComboBox.
     * @param selectedAlgorithm String that indicates which sorting algorithm will be used
     */
    public void sortArray(String selectedAlgorithm) {
        view.updateStateLabel("Sorting");
        if (selectedAlgorithm.equals(VisualizerModel.BUBBLE_SORT)) {
            Thread thread = new Thread(() -> {
                bubbleSort();
                view.updateStateLabel("Sorted");
            });
            thread.start();
        }
        else if (selectedAlgorithm.equals(VisualizerModel.MERGE_SORT)) {
            Thread thread = new Thread(() -> {
                mergeSortHelper(0, array.size() - 1);
                view.updateStateLabel("Sorted");
            });
            thread.start();
        }
        else if (selectedAlgorithm.equals(VisualizerModel.INSERTION_SORT)) {
            Thread thread = new Thread(() -> {
                insertionSort();
                view.updateStateLabel("Sorted");
            });
            thread.start();
        }
    }

    /**
     * Bubble sort algorithm.
     */
    private void bubbleSort() {
        boolean swapped = false;
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    Collections.swap(array, j, j + 1);
                    view.handleSwap();
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
        }
    }

    /**
     * Merge sort algorithm.
     * @param l int left index of array
     * @param m int middle index of array
     * @param r int right index of array
     */
    private void mergeSort(int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int left[] = new int[n1];
        int right[] = new int[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = array.get(l + i);
        }
        for (int j = 0; j < n2; j++) {
            right[j] = array.get(m + 1 + j);
        }

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                array.set(k, left[i]);
                i++;
            } else {
                array.set(k, right[j]);
                j++;
            }
            view.handleSwap();
            k++;
        }

        while (i < n1) {
            array.set(k, left[i]);
            view.handleSwap();
            i++;
            k++;
        }
        while (j < n2) {
            array.set(k, right[j]);
            view.handleSwap();
            k++;
            j++;
        }
    }


    /**
     * Merge sort helper which sorts array using mergeSort().
     * @param l int left index of array
     * @param r int right index of array
     */
    private void mergeSortHelper(int l, int r) {
        if (l < r) {
            int m = l + (r - l)/2;
            mergeSortHelper(l, m);
            mergeSortHelper(m + 1, r);
            mergeSort(l, m, r);
        }
    }

    /**
     * Insertion sort algorithm.
     */
    private void insertionSort() {
        int n = array.size();

        for (int i = 1; i < n; i++) {
            int key = array.get(i);
            int j = i - 1;
            while(j >= 0 && array.get(j) > key) {
                Collections.swap(array, j, j + 1);
                view.handleSwap();
                j--;
            }
            array.set(j + 1, key);
        }
    }

    /**
     * Creates new array and updates state label.
     */
    public void createNewArray() {
        view.handleNewArray();
        view.updateStateLabel("Unsorted");
    }

}
