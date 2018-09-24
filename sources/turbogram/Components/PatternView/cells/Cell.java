package turbogram.Components.PatternView.cells;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Locale;
import turbogram.Components.PatternView.utils.CellUtils;

public class Cell implements Parcelable {
    public static final Creator<Cell> CREATOR = new C22761();
    private int column;
    private int row;

    /* renamed from: turbogram.Components.PatternView.cells.Cell$1 */
    static class C22761 implements Creator<Cell> {
        C22761() {
        }

        public Cell createFromParcel(Parcel in) {
            return new Cell(in);
        }

        public Cell[] newArray(int size) {
            return new Cell[size];
        }
    }

    public Cell(int row, int column) {
        CellUtils.checkRange(row, column);
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public String getId() {
        String formatRow = String.format(Locale.US, "%03d", new Object[]{Integer.valueOf(this.row)});
        return formatRow + "-" + String.format(Locale.US, "%03d", new Object[]{Integer.valueOf(this.column)});
    }

    public String toString() {
        return "(r=" + getRow() + ",c=" + getColumn() + ")";
    }

    public boolean equals(Object object) {
        if (object instanceof Cell) {
            return getColumn() == ((Cell) object).getColumn() && getRow() == ((Cell) object).getRow();
        } else {
            return super.equals(object);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getColumn());
        parcel.writeInt(getRow());
    }

    public void readFromParcel(Parcel in) {
        this.column = in.readInt();
        this.row = in.readInt();
    }

    private Cell(Parcel in) {
        readFromParcel(in);
    }
}
