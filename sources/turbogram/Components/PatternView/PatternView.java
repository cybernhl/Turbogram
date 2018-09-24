package turbogram.Components.PatternView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import com.baranak.turbogramf.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import turbogram.Components.PatternView.cells.Cell;
import turbogram.Components.PatternView.cells.CellManager;
import turbogram.Components.PatternView.utils.CellUtils;
import turbogram.Utilities.TurboUtils;

public class PatternView extends View {
    private long animatingPeriodStart;
    private Bitmap bitmapBtnDefault;
    private Bitmap bitmapBtnTouched;
    private Bitmap bitmapCircleDefault;
    private Bitmap bitmapCircleRed;
    private Bitmap bitmapCircleSelected;
    private int bitmapHeight;
    private int bitmapWidth;
    private CellManager cellManager;
    private int circleColor;
    private final Matrix circleMatrix;
    private final Paint circlePaint;
    private int circleSize;
    private final Path currentPath;
    private final float diameterFactor;
    private int dotColor;
    private final Paint dotPaint;
    private boolean drawingProfilingStarted;
    private boolean enableHapticFeedback;
    private int gridColumns;
    private int gridRows;
    private final float hitFactor;
    private boolean inErrorStealthMode;
    private float inProgressX;
    private float inProgressY;
    private boolean inStealthMode;
    private boolean inputEnabled;
    private final Rect invalidate;
    private ArrayList<Cell> mPattern;
    private final Runnable mPatternClearer;
    private OnPatternCellAddedListener onPatternCellAddedListener;
    private OnPatternClearedListener onPatternClearedListener;
    private OnPatternDetectedListener onPatternDetectedListener;
    private OnPatternStartListener onPatternStartListener;
    private final int padding;
    private final int paddingLeft;
    private final int paddingTop;
    private int pathColor;
    private final Paint pathPaint;
    private DisplayMode patternDisplayMode;
    private boolean patternInProgress;
    private float squareHeight;
    private float squareWidth;

    /* renamed from: turbogram.Components.PatternView.PatternView$1 */
    class C22741 implements Runnable {
        C22741() {
        }

        public void run() {
            PatternView.this.clearPattern();
        }
    }

    public enum DisplayMode {
        Correct,
        Animate,
        Wrong
    }

    public interface OnPatternCellAddedListener {
        void onPatternCellAdded();
    }

    public interface OnPatternClearedListener {
        void onPatternCleared();
    }

    public interface OnPatternDetectedListener {
        void onPatternDetected();
    }

    public interface OnPatternStartListener {
        void onPatternStart();
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C22751();
        private final int mDisplayMode;
        private final boolean mInStealthMode;
        private final boolean mInputEnabled;
        private final int[] mSerializedPattern;
        private final boolean mTactileFeedbackEnabled;

        /* renamed from: turbogram.Components.PatternView.PatternView$SavedState$1 */
        static class C22751 implements Creator<SavedState> {
            C22751() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        private SavedState(Parcelable superState, int[] serializedPattern, int displayMode, boolean inputEnabled, boolean inStealthMode, boolean tactileFeedbackEnabled) {
            super(superState);
            this.mSerializedPattern = serializedPattern;
            this.mDisplayMode = displayMode;
            this.mInputEnabled = inputEnabled;
            this.mInStealthMode = inStealthMode;
            this.mTactileFeedbackEnabled = tactileFeedbackEnabled;
        }

        private SavedState(Parcel in) {
            super(in);
            this.mSerializedPattern = in.createIntArray();
            this.mDisplayMode = in.readInt();
            this.mInputEnabled = ((Boolean) in.readValue(null)).booleanValue();
            this.mInStealthMode = ((Boolean) in.readValue(null)).booleanValue();
            this.mTactileFeedbackEnabled = ((Boolean) in.readValue(null)).booleanValue();
        }

        public int[] getSerializedPattern() {
            return this.mSerializedPattern;
        }

        public int getDisplayMode() {
            return this.mDisplayMode;
        }

        public boolean isInputEnabled() {
            return this.mInputEnabled;
        }

        public boolean isInStealthMode() {
            return this.mInStealthMode;
        }

        public boolean isTactileFeedbackEnabled() {
            return this.mTactileFeedbackEnabled;
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeIntArray(this.mSerializedPattern);
            dest.writeInt(this.mDisplayMode);
            dest.writeValue(Boolean.valueOf(this.mInputEnabled));
            dest.writeValue(Boolean.valueOf(this.mInStealthMode));
            dest.writeValue(Boolean.valueOf(this.mTactileFeedbackEnabled));
        }
    }

    public PatternView(Context context) {
        this(context, null);
    }

    public PatternView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PatternView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.pathPaint = new Paint(1);
        this.circlePaint = new Paint(1);
        this.dotPaint = new Paint(1);
        this.drawingProfilingStarted = false;
        this.inProgressX = -1.0f;
        this.inProgressY = -1.0f;
        this.patternDisplayMode = DisplayMode.Correct;
        this.inputEnabled = true;
        this.inStealthMode = false;
        this.inErrorStealthMode = false;
        this.enableHapticFeedback = true;
        this.patternInProgress = false;
        this.diameterFactor = 0.1f;
        this.hitFactor = 0.6f;
        this.currentPath = new Path();
        this.invalidate = new Rect();
        this.circleMatrix = new Matrix();
        this.padding = 0;
        this.paddingLeft = 0;
        this.paddingTop = 0;
        this.mPatternClearer = new C22741();
        getFromAttributes(context, attrs);
        init();
        this.pathPaint.setDither(true);
        this.pathPaint.setStyle(Style.STROKE);
        this.pathPaint.setStrokeJoin(Join.ROUND);
        this.pathPaint.setStrokeCap(Cap.ROUND);
        loadBitmaps();
    }

    private void getFromAttributes(Context context, AttributeSet attrs) {
        this.circleSize = AndroidUtilities.dp(100.0f);
        this.circleColor = -1;
        this.dotColor = -1;
        this.pathColor = TurboUtils.getLighterColor(-1, 0.5f);
        this.gridColumns = 3;
        this.gridRows = 3;
    }

    private void init() {
        setPathColor(this.pathColor);
        setCircleColor(this.circleColor);
        setDotColor(this.dotColor);
        this.cellManager = new CellManager(this.gridRows, this.gridColumns);
        this.mPattern = new ArrayList(this.cellManager.getSize());
    }

    public void setSelectedBitmap(int resId) {
        this.bitmapCircleSelected = getBitmapFor(resId);
        computeBitmapSize();
    }

    public void setDefaultBitmap(int resId) {
        this.bitmapCircleDefault = getBitmapFor(resId);
        computeBitmapSize();
    }

    private void loadBitmaps() {
        this.bitmapBtnDefault = getBitmapFor(R.drawable.pattern_btn_touched);
        this.bitmapBtnTouched = this.bitmapBtnDefault;
        this.bitmapCircleDefault = getBitmapFor(R.drawable.pattern_button_untouched);
        this.bitmapCircleSelected = getBitmapFor(R.drawable.pattern_circle_white);
        this.bitmapCircleRed = getBitmapFor(R.drawable.pattern_circle_blue);
        computeBitmapSize();
    }

    private void computeBitmapSize() {
        int i = 0;
        Bitmap[] bitmaps = new Bitmap[]{this.bitmapBtnDefault, this.bitmapCircleSelected, this.bitmapCircleRed};
        if (isInEditMode()) {
            this.bitmapWidth = Math.max(this.bitmapWidth, 150);
            this.bitmapHeight = Math.max(this.bitmapHeight, 150);
            return;
        }
        int length = bitmaps.length;
        while (i < length) {
            Bitmap bitmap = bitmaps[i];
            this.bitmapWidth = Math.max(this.bitmapWidth, bitmap.getWidth());
            this.bitmapHeight = Math.max(this.bitmapHeight, bitmap.getHeight());
            i++;
        }
    }

    private Bitmap getBitmapFor(int resId) {
        return BitmapFactory.decodeResource(getContext().getResources(), resId);
    }

    public void setInStealthMode(boolean inStealthMode) {
        this.inStealthMode = inStealthMode;
    }

    public void setInErrorStealthMode(boolean inErrorStealthMode) {
        this.inErrorStealthMode = inErrorStealthMode;
    }

    public void setTactileFeedbackEnabled(boolean tactileFeedbackEnabled) {
        this.enableHapticFeedback = tactileFeedbackEnabled;
    }

    public void setOnPatternStartListener(OnPatternStartListener onPatternStartListener) {
        this.onPatternStartListener = onPatternStartListener;
    }

    public void setOnPatternClearedListener(OnPatternClearedListener onPatternClearedListener) {
        this.onPatternClearedListener = onPatternClearedListener;
    }

    public void setOnPatternCellAddedListener(OnPatternCellAddedListener onPatternCellAddedListener) {
        this.onPatternCellAddedListener = onPatternCellAddedListener;
    }

    public void setOnPatternDetectedListener(OnPatternDetectedListener onPatternDetectedListener) {
        this.onPatternDetectedListener = onPatternDetectedListener;
    }

    public void setPattern(DisplayMode displayMode, List<Cell> pattern) {
        this.mPattern.clear();
        this.mPattern.addAll(pattern);
        clearPatternDrawLookup();
        for (Cell cell : pattern) {
            this.cellManager.draw(cell, true);
        }
        setDisplayMode(displayMode);
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.patternDisplayMode = displayMode;
        if (displayMode == DisplayMode.Animate) {
            if (this.mPattern.size() == 0) {
                throw new IllegalStateException("you must have a pattern to animate if you want to set the display mode to animate");
            }
            this.animatingPeriodStart = SystemClock.elapsedRealtime();
            Cell first = (Cell) this.mPattern.get(0);
            this.inProgressX = getCenterXForColumn(first.getColumn());
            this.inProgressY = getCenterYForRow(first.getRow());
            clearPatternDrawLookup();
        }
        invalidate();
    }

    public DisplayMode getDisplayMode() {
        return this.patternDisplayMode;
    }

    public List<Cell> getPattern() {
        return (List) this.mPattern.clone();
    }

    public String getPatternString() {
        return patternToString();
    }

    public String patternToString() {
        if (this.mPattern == null) {
            return "";
        }
        int patternSize = this.mPattern.size();
        StringBuilder res = new StringBuilder(patternSize);
        for (int i = 0; i < patternSize; i++) {
            res.append(((Cell) this.mPattern.get(i)).getId());
            if (i != patternSize - 1) {
                res.append("&");
            }
        }
        return res.toString();
    }

    public int[] patternToIntArray() {
        if (this.mPattern == null) {
            return new int[0];
        }
        int patternSize = this.mPattern.size();
        int[] array = new int[(patternSize * 2)];
        for (int i = 0; i < patternSize; i++) {
            array[i] = ((Cell) this.mPattern.get(i)).getRow();
            array[i + 1] = ((Cell) this.mPattern.get(i)).getColumn();
        }
        return array;
    }

    private void notifyCellAdded() {
        if (this.onPatternCellAddedListener != null) {
            this.onPatternCellAddedListener.onPatternCellAdded();
        }
    }

    private void notifyPatternStarted() {
        if (this.onPatternStartListener != null) {
            this.onPatternStartListener.onPatternStart();
        }
    }

    private void notifyPatternDetected() {
        if (this.onPatternDetectedListener != null) {
            this.onPatternDetectedListener.onPatternDetected();
        }
    }

    private void notifyPatternCleared() {
        if (this.onPatternClearedListener != null) {
            this.onPatternClearedListener.onPatternCleared();
        }
    }

    public void clearPattern() {
        cancelClearDelay();
        resetPattern();
        notifyPatternCleared();
    }

    private void resetPattern() {
        this.mPattern.clear();
        clearPatternDrawLookup();
        this.patternDisplayMode = DisplayMode.Correct;
        invalidate();
    }

    private void clearPatternDrawLookup() {
        for (int i = 0; i < this.gridRows; i++) {
            for (int j = 0; j < this.gridColumns; j++) {
                this.cellManager.clearDrawing();
            }
        }
    }

    public int getPathColor() {
        return this.pathColor;
    }

    public void setPathColor(int pathColor) {
        this.pathColor = pathColor;
        this.pathPaint.setColor(pathColor);
        invalidate();
    }

    public int getCircleColor() {
        return this.circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        this.circlePaint.setColorFilter(new PorterDuffColorFilter(circleColor, Mode.MULTIPLY));
        invalidate();
    }

    public int getDotColor() {
        return this.dotColor;
    }

    public void setDotColor(int dotColor) {
        this.dotColor = dotColor;
        this.dotPaint.setColorFilter(new PorterDuffColorFilter(dotColor, Mode.MULTIPLY));
        invalidate();
    }

    protected int getSuggestedMinimumWidth() {
        return this.gridColumns * this.circleSize;
    }

    protected int getSuggestedMinimumHeight() {
        return this.gridRows * this.circleSize;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (MeasureSpec.getMode(widthMeasureSpec) == Integer.MIN_VALUE) {
            width = this.gridColumns * this.circleSize;
            this.squareWidth = (float) this.circleSize;
        } else {
            this.squareWidth = ((float) width) / ((float) this.gridColumns);
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == Integer.MIN_VALUE) {
            height = this.gridRows * this.circleSize;
            this.squareHeight = (float) this.circleSize;
        } else {
            this.squareHeight = ((float) height) / ((float) this.gridRows);
        }
        this.squareWidth = Math.min(this.squareWidth, this.squareHeight);
        this.squareHeight = Math.min(this.squareWidth, this.squareHeight);
        setMeasuredDimension(width, height);
    }

    private Cell detectAndAddHit(float x, float y) {
        Cell cell = checkForNewHit(x, y);
        if (cell == null) {
            return null;
        }
        ArrayList<Cell> newCells = new ArrayList();
        if (!this.mPattern.isEmpty()) {
            Cell lastCell = (Cell) this.mPattern.get(this.mPattern.size() - 1);
            int dRow = cell.getRow() - lastCell.getRow();
            int dCol = cell.getColumn() - lastCell.getColumn();
            int rsign = dRow > 0 ? 1 : -1;
            int csign = dCol > 0 ? 1 : -1;
            int i;
            if (dRow == 0) {
                for (i = 1; i < Math.abs(dCol); i++) {
                    newCells.add(new Cell(lastCell.getRow(), lastCell.getColumn() + (i * csign)));
                }
            } else if (dCol == 0) {
                for (i = 1; i < Math.abs(dRow); i++) {
                    newCells.add(new Cell(lastCell.getRow() + (i * rsign), lastCell.getColumn()));
                }
            } else if (Math.abs(dCol) == Math.abs(dRow)) {
                for (i = 1; i < Math.abs(dRow); i++) {
                    newCells.add(new Cell(lastCell.getRow() + (i * rsign), lastCell.getColumn() + (i * csign)));
                }
            }
        }
        Iterator it = newCells.iterator();
        while (it.hasNext()) {
            Cell fillInGapCell = (Cell) it.next();
            if (!(fillInGapCell == null || this.cellManager.isDrawn(fillInGapCell))) {
                addCellToPattern(fillInGapCell);
            }
        }
        addCellToPattern(cell);
        if (!this.enableHapticFeedback) {
            return cell;
        }
        performHapticFeedback(1, 3);
        return cell;
    }

    private void addCellToPattern(Cell newCell) {
        this.cellManager.draw(newCell, true);
        this.mPattern.add(newCell);
        notifyCellAdded();
    }

    private Cell checkForNewHit(float x, float y) {
        int rowHit = getRowHit(y);
        if (rowHit < 0) {
            return null;
        }
        int columnHit = getColumnHit(x);
        if (columnHit < 0 || this.cellManager.isDrawn(rowHit, columnHit)) {
            return null;
        }
        return this.cellManager.get(rowHit, columnHit);
    }

    private int getRowHit(float y) {
        float squareHeight = this.squareHeight;
        float hitSize = squareHeight * 0.6f;
        float offset = 0.0f + ((squareHeight - hitSize) / 2.0f);
        for (int i = 0; i < this.gridRows; i++) {
            float hitTop = offset + (((float) i) * squareHeight);
            if (y >= hitTop && y <= hitTop + hitSize) {
                return i;
            }
        }
        return -1;
    }

    private int getColumnHit(float x) {
        float squareWidth = this.squareWidth;
        float hitSize = squareWidth * 0.6f;
        float offset = 0.0f + ((squareWidth - hitSize) / 2.0f);
        for (int i = 0; i < this.gridColumns; i++) {
            float hitLeft = offset + (((float) i) * squareWidth);
            if (x >= hitLeft && x <= hitLeft + hitSize) {
                return i;
            }
        }
        return -1;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!this.inputEnabled || !isEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case 0:
                handleActionDown(event);
                return true;
            case 1:
                handleActionUp();
                return true;
            case 2:
                handleActionMove(event);
                return true;
            case 3:
                this.patternInProgress = false;
                resetPattern();
                notifyPatternCleared();
                return true;
            default:
                return false;
        }
    }

    private void handleActionMove(MotionEvent event) {
        int historySize = event.getHistorySize();
        for (int i = 0; i < historySize + 1; i++) {
            float x;
            float y;
            if (i < historySize) {
                x = event.getHistoricalX(i);
            } else {
                x = event.getX();
            }
            if (i < historySize) {
                y = event.getHistoricalY(i);
            } else {
                y = event.getY();
            }
            int patternSizePreHitDetect = this.mPattern.size();
            Cell hitCell = detectAndAddHit(x, y);
            int patternSize = this.mPattern.size();
            if (hitCell != null && patternSize == 1) {
                this.patternInProgress = true;
                notifyPatternStarted();
            }
            if (Math.abs(x - this.inProgressX) + Math.abs(y - this.inProgressY) > this.squareWidth * 0.01f) {
                float oldX = this.inProgressX;
                float oldY = this.inProgressY;
                this.inProgressX = x;
                this.inProgressY = y;
                if (!this.patternInProgress || patternSize <= 0) {
                    invalidate();
                } else {
                    float left;
                    float right;
                    float top;
                    float bottom;
                    ArrayList<Cell> pattern = this.mPattern;
                    float radius = (this.squareWidth * 0.1f) * 0.5f;
                    Cell lastCell = (Cell) pattern.get(patternSize - 1);
                    float startX = getCenterXForColumn(lastCell.getColumn());
                    float startY = getCenterYForRow(lastCell.getRow());
                    Rect invalidateRect = this.invalidate;
                    if (startX < x) {
                        left = startX;
                        right = x;
                    } else {
                        left = x;
                        right = startX;
                    }
                    if (startY < y) {
                        top = startY;
                        bottom = y;
                    } else {
                        top = y;
                        bottom = startY;
                    }
                    invalidateRect.set((int) (left - radius), (int) (top - radius), (int) (right + radius), (int) (bottom + radius));
                    if (startX < oldX) {
                        left = startX;
                        right = oldX;
                    } else {
                        left = oldX;
                        right = startX;
                    }
                    if (startY < oldY) {
                        top = startY;
                        bottom = oldY;
                    } else {
                        top = oldY;
                        bottom = startY;
                    }
                    invalidateRect.union((int) (left - radius), (int) (top - radius), (int) (right + radius), (int) (bottom + radius));
                    if (hitCell != null) {
                        startX = getCenterXForColumn(hitCell.getColumn());
                        startY = getCenterYForRow(hitCell.getRow());
                        if (patternSize >= 2) {
                            hitCell = (Cell) pattern.get((patternSize - 1) - (patternSize - patternSizePreHitDetect));
                            oldX = getCenterXForColumn(hitCell.getColumn());
                            oldY = getCenterYForRow(hitCell.getRow());
                            if (startX < oldX) {
                                left = startX;
                                right = oldX;
                            } else {
                                left = oldX;
                                right = startX;
                            }
                            if (startY < oldY) {
                                top = startY;
                                bottom = oldY;
                            } else {
                                top = oldY;
                                bottom = startY;
                            }
                        } else {
                            right = startX;
                            left = startX;
                            bottom = startY;
                            top = startY;
                        }
                        float widthOffset = this.squareWidth / 2.0f;
                        float heightOffset = this.squareHeight / 2.0f;
                        invalidateRect.set((int) (left - widthOffset), (int) (top - heightOffset), (int) (right + widthOffset), (int) (bottom + heightOffset));
                    }
                    invalidate(invalidateRect);
                }
            }
        }
        invalidate();
    }

    private void handleActionUp() {
        if (!this.mPattern.isEmpty()) {
            this.patternInProgress = false;
            notifyPatternDetected();
            invalidate();
        }
    }

    private void handleActionDown(MotionEvent event) {
        resetPattern();
        float x = event.getX();
        float y = event.getY();
        Cell hitCell = detectAndAddHit(x, y);
        if (hitCell != null) {
            this.patternInProgress = true;
            this.patternDisplayMode = DisplayMode.Correct;
            notifyPatternStarted();
        } else {
            this.patternInProgress = false;
            notifyPatternCleared();
        }
        if (hitCell != null) {
            float startX = getCenterXForColumn(hitCell.getColumn());
            float startY = getCenterYForRow(hitCell.getRow());
            float widthOffset = this.squareWidth / 2.0f;
            float heightOffset = this.squareHeight / 2.0f;
            invalidate((int) (startX - widthOffset), (int) (startY - heightOffset), (int) (startX + widthOffset), (int) (startY + heightOffset));
        }
        this.inProgressX = x;
        this.inProgressY = y;
    }

    private float getCenterXForColumn(int column) {
        return (0.0f + (((float) column) * this.squareWidth)) + (this.squareWidth / 2.0f);
    }

    private float getCenterYForRow(int row) {
        return (0.0f + (((float) row) * this.squareHeight)) + (this.squareHeight / 2.0f);
    }

    protected void onDraw(Canvas canvas) {
        int i;
        ArrayList<Cell> pattern = this.mPattern;
        int count = pattern.size();
        if (this.patternDisplayMode == DisplayMode.Animate) {
            int spotInCycle = ((int) (SystemClock.elapsedRealtime() - this.animatingPeriodStart)) % ((count + 1) * 700);
            int numCircles = spotInCycle / 700;
            clearPatternDrawLookup();
            for (i = 0; i < numCircles; i++) {
                this.cellManager.draw((Cell) pattern.get(i), true);
            }
            boolean needToUpdateInProgressPoint = numCircles > 0 && numCircles < count;
            if (needToUpdateInProgressPoint) {
                float percentageOfNextCircle = ((float) (spotInCycle % 700)) / 700.0f;
                Cell currentCell = (Cell) pattern.get(numCircles - 1);
                float centerX = getCenterXForColumn(currentCell.getColumn());
                float centerY = getCenterYForRow(currentCell.getRow());
                Cell nextCell = (Cell) pattern.get(numCircles);
                float dy = percentageOfNextCircle * (getCenterYForRow(nextCell.getRow()) - centerY);
                this.inProgressX = centerX + (percentageOfNextCircle * (getCenterXForColumn(nextCell.getColumn()) - centerX));
                this.inProgressY = centerY + dy;
            }
            invalidate();
        }
        float squareWidth = this.squareWidth;
        float squareHeight = this.squareHeight;
        this.pathPaint.setStrokeWidth((0.1f * squareWidth) * 0.5f);
        Path currentPath = this.currentPath;
        currentPath.rewind();
        getClass();
        getClass();
        for (i = 0; i < this.gridRows; i++) {
            float topY = ((float) 0) + (((float) i) * squareHeight);
            for (int j = 0; j < this.gridColumns; j++) {
                drawCircle(canvas, (int) (((float) 0) + (((float) j) * squareWidth)), (int) topY, this.cellManager.isDrawn(i, j));
            }
        }
        boolean drawPath = (!this.inStealthMode && this.patternDisplayMode == DisplayMode.Correct) || (!this.inErrorStealthMode && this.patternDisplayMode == DisplayMode.Wrong);
        boolean oldFlagCircle = (this.circlePaint.getFlags() & 2) != 0;
        boolean oldFlagDot = (this.dotPaint.getFlags() & 2) != 0;
        this.circlePaint.setFilterBitmap(true);
        this.dotPaint.setFilterBitmap(true);
        if (drawPath) {
            boolean anyCircles = false;
            for (i = 0; i < count; i++) {
                Cell cell = (Cell) pattern.get(i);
                if (!this.cellManager.isDrawn(cell)) {
                    break;
                }
                anyCircles = true;
                centerX = getCenterXForColumn(cell.getColumn());
                centerY = getCenterYForRow(cell.getRow());
                if (i == 0) {
                    currentPath.moveTo(centerX, centerY);
                } else {
                    currentPath.lineTo(centerX, centerY);
                }
            }
            if ((this.patternInProgress || this.patternDisplayMode == DisplayMode.Animate) && anyCircles && count > 1) {
                currentPath.lineTo(this.inProgressX, this.inProgressY);
            }
            canvas.drawPath(currentPath, this.pathPaint);
        }
        this.circlePaint.setFilterBitmap(oldFlagCircle);
        this.dotPaint.setFilterBitmap(oldFlagDot);
    }

    private void drawCircle(Canvas canvas, int leftX, int topY, boolean partOfPattern) {
        Bitmap outerCircle;
        Bitmap innerCircle;
        if (!partOfPattern || ((this.inStealthMode && this.patternDisplayMode == DisplayMode.Correct) || (this.inErrorStealthMode && this.patternDisplayMode == DisplayMode.Wrong))) {
            outerCircle = this.bitmapCircleDefault;
            innerCircle = this.bitmapBtnDefault;
        } else if (this.patternInProgress) {
            outerCircle = this.bitmapCircleSelected;
            innerCircle = this.bitmapBtnTouched;
        } else if (this.patternDisplayMode == DisplayMode.Wrong) {
            outerCircle = this.bitmapCircleRed;
            innerCircle = this.bitmapBtnDefault;
        } else if (this.patternDisplayMode == DisplayMode.Correct || this.patternDisplayMode == DisplayMode.Animate) {
            outerCircle = this.bitmapCircleSelected;
            innerCircle = this.bitmapBtnDefault;
        } else {
            throw new IllegalStateException("unknown display mode " + this.patternDisplayMode);
        }
        int width = this.bitmapWidth;
        int height = this.bitmapHeight;
        int offsetX = (int) ((this.squareWidth - ((float) width)) / 2.0f);
        int offsetY = (int) ((this.squareHeight - ((float) height)) / 2.0f);
        float sx = Math.min(this.squareWidth / ((float) this.bitmapWidth), 1.0f);
        float sy = Math.min(this.squareHeight / ((float) this.bitmapHeight), 1.0f);
        this.circleMatrix.setTranslate((float) (leftX + offsetX), (float) (topY + offsetY));
        this.circleMatrix.preTranslate((float) (this.bitmapWidth / 2), (float) (this.bitmapHeight / 2));
        this.circleMatrix.preScale(sx, sy);
        this.circleMatrix.preTranslate((float) ((-this.bitmapWidth) / 2), (float) ((-this.bitmapHeight) / 2));
        canvas.drawBitmap(outerCircle, this.circleMatrix, this.circlePaint);
        canvas.drawBitmap(innerCircle, this.circleMatrix, this.dotPaint);
    }

    protected Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), patternToIntArray(), this.patternDisplayMode.ordinal(), this.inputEnabled, this.inStealthMode, this.enableHapticFeedback);
    }

    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setPattern(DisplayMode.Correct, CellUtils.intArrayToPattern(ss.getSerializedPattern(), this.cellManager));
        this.patternDisplayMode = DisplayMode.values()[ss.getDisplayMode()];
        this.inputEnabled = ss.isInputEnabled();
        this.inStealthMode = ss.isInStealthMode();
        this.enableHapticFeedback = ss.isTactileFeedbackEnabled();
    }

    public void cancelClearDelay() {
        removeCallbacks(this.mPatternClearer);
    }
}
