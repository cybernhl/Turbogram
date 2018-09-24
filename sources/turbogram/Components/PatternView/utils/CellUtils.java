package turbogram.Components.PatternView.utils;

import java.util.ArrayList;
import java.util.List;
import turbogram.Components.PatternView.cells.Cell;
import turbogram.Components.PatternView.cells.CellManager;

public class CellUtils {
    public static void checkRange(int row, int column) {
        if (row < 0) {
            throw new IllegalArgumentException("row must be in range 0-" + (row - 1));
        } else if (column < 0) {
            throw new IllegalArgumentException("column must be in range 0-" + (row - 1));
        }
    }

    public static List<Cell> intArrayToPattern(int[] array, CellManager cellManager) {
        List<Cell> result = new ArrayList();
        if (array.length != 0) {
            int j = array.length;
            for (int i = 0; i < j; i += 2) {
                result.add(cellManager.get(array[i], array[i + 1]));
            }
        }
        return result;
    }
}
