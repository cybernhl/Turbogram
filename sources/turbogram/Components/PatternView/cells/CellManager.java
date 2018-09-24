package turbogram.Components.PatternView.cells;

import java.lang.reflect.Array;
import java.util.Arrays;
import turbogram.Components.PatternView.utils.CellUtils;

public class CellManager {
    private Cell[][] cells;
    private int columns;
    private boolean[][] patternDrawLookup;
    private int rows;
    private int size;

    public CellManager(int rows, int columns) {
        CellUtils.checkRange(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.size = rows * columns;
        this.patternDrawLookup = (boolean[][]) Array.newInstance(Boolean.TYPE, new int[]{rows, columns});
        this.cells = (Cell[][]) Array.newInstance(Cell.class, new int[]{rows, columns});
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
            Arrays.fill(this.patternDrawLookup[i], false);
        }
    }

    public synchronized Cell get(int row, int column) {
        return this.cells[row][column];
    }

    public int getSize() {
        return this.size;
    }

    public void draw(Cell cell, boolean drawn) {
        int row = cell.getRow();
        this.patternDrawLookup[row][cell.getColumn()] = drawn;
    }

    public void clearDrawing() {
        for (int i = 0; i < this.rows; i++) {
            Arrays.fill(this.patternDrawLookup[i], false);
        }
    }

    public boolean isDrawn(int row, int column) {
        return this.patternDrawLookup[row][column];
    }

    public boolean isDrawn(Cell cell) {
        int row = cell.getRow();
        return this.patternDrawLookup[row][cell.getColumn()];
    }
}
