package turbogram.Components.Fam;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import turbogram.Utilities.TurboConfig.Toolbar;

public class FloatingActionsMenu extends ViewGroup {
    private static final int ANIMATION_DURATION = 300;
    private static final float COLLAPSED_PLUS_ROTATION = 0.0f;
    private static final float EXPANDED_PLUS_ROTATION = 135.0f;
    public static final int EXPAND_DOWN = 1;
    public static final int EXPAND_LEFT = 2;
    public static final int EXPAND_RIGHT = 3;
    public static final int EXPAND_UP = 0;
    public static final int LABELS_ON_LEFT_SIDE = 0;
    public static final int LABELS_ON_RIGHT_SIDE = 1;
    private static Interpolator sAlphaExpandInterpolator = new DecelerateInterpolator();
    private static Interpolator sCollapseInterpolator = new DecelerateInterpolator(3.0f);
    private static Interpolator sExpandInterpolator = new OvershootInterpolator();
    private AddFloatingActionButton mAddButton;
    private int mAddButtonColorNormal;
    private int mAddButtonColorPressed;
    private int mAddButtonPlusColor;
    private int mAddButtonSize;
    private boolean mAddButtonStrokeVisible;
    private int mButtonSpacing;
    private int mButtonsCount;
    private AnimatorSet mCollapseAnimation;
    private AnimatorSet mExpandAnimation;
    private int mExpandDirection;
    private boolean mExpanded;
    private int mLabelsMargin;
    private int mLabelsPosition;
    private int mLabelsStyle;
    private int mLabelsVerticalOffset;
    private OnFloatingActionsMenuUpdateListener mListener;
    private int mMaxButtonHeight;
    private int mMaxButtonWidth;
    private RotatingDrawable mRotatingDrawable;
    private TouchDelegateGroup mTouchDelegateGroup;

    /* renamed from: turbogram.Components.Fam.FloatingActionsMenu$2 */
    class C22712 implements OnClickListener {
        C22712() {
        }

        public void onClick(View v) {
            FloatingActionsMenu.this.toggle();
        }
    }

    private class LayoutParams extends android.view.ViewGroup.LayoutParams {
        private boolean animationsSetToPlay;
        private ObjectAnimator mCollapseAlpha = new ObjectAnimator();
        private ObjectAnimator mCollapseDir = new ObjectAnimator();
        private ObjectAnimator mExpandAlpha = new ObjectAnimator();
        private ObjectAnimator mExpandDir = new ObjectAnimator();

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
            this.mExpandDir.setInterpolator(FloatingActionsMenu.sExpandInterpolator);
            this.mExpandAlpha.setInterpolator(FloatingActionsMenu.sAlphaExpandInterpolator);
            this.mCollapseDir.setInterpolator(FloatingActionsMenu.sCollapseInterpolator);
            this.mCollapseAlpha.setInterpolator(FloatingActionsMenu.sCollapseInterpolator);
            this.mCollapseAlpha.setProperty(View.ALPHA);
            this.mCollapseAlpha.setFloatValues(new float[]{1.0f, 0.0f});
            this.mExpandAlpha.setProperty(View.ALPHA);
            this.mExpandAlpha.setFloatValues(new float[]{0.0f, 1.0f});
            switch (FloatingActionsMenu.this.mExpandDirection) {
                case 0:
                case 1:
                    this.mCollapseDir.setProperty(View.TRANSLATION_Y);
                    this.mExpandDir.setProperty(View.TRANSLATION_Y);
                    return;
                case 2:
                case 3:
                    this.mCollapseDir.setProperty(View.TRANSLATION_X);
                    this.mExpandDir.setProperty(View.TRANSLATION_X);
                    return;
                default:
                    return;
            }
        }

        public void setAnimationsTarget(View view) {
            this.mCollapseAlpha.setTarget(view);
            this.mCollapseDir.setTarget(view);
            this.mExpandAlpha.setTarget(view);
            this.mExpandDir.setTarget(view);
            if (!this.animationsSetToPlay) {
                addLayerTypeListener(this.mExpandDir, view);
                addLayerTypeListener(this.mCollapseDir, view);
                FloatingActionsMenu.this.mCollapseAnimation.play(this.mCollapseAlpha);
                FloatingActionsMenu.this.mCollapseAnimation.play(this.mCollapseDir);
                FloatingActionsMenu.this.mExpandAnimation.play(this.mExpandAlpha);
                FloatingActionsMenu.this.mExpandAnimation.play(this.mExpandDir);
                this.animationsSetToPlay = true;
            }
        }

        private void addLayerTypeListener(Animator animator, final View view) {
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    view.setLayerType(0, null);
                }

                public void onAnimationStart(Animator animation) {
                    view.setLayerType(2, null);
                }
            });
        }
    }

    public interface OnFloatingActionsMenuUpdateListener {
        void onMenuCollapsed();

        void onMenuExpanded();
    }

    private static class RotatingDrawable extends LayerDrawable {
        private float mRotation;

        public RotatingDrawable(Drawable drawable) {
            super(new Drawable[]{drawable});
        }

        public float getRotation() {
            return this.mRotation;
        }

        public void setRotation(float rotation) {
            this.mRotation = rotation;
            invalidateSelf();
        }

        public void draw(Canvas canvas) {
            canvas.save();
            canvas.rotate(this.mRotation, (float) getBounds().centerX(), (float) getBounds().centerY());
            super.draw(canvas);
            canvas.restore();
        }
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C22731();
        public boolean mExpanded;

        /* renamed from: turbogram.Components.Fam.FloatingActionsMenu$SavedState$1 */
        static class C22731 implements Creator<SavedState> {
            C22731() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        public SavedState(Parcelable parcel) {
            super(parcel);
        }

        private SavedState(Parcel in) {
            boolean z = true;
            super(in);
            if (in.readInt() != 1) {
                z = false;
            }
            this.mExpanded = z;
        }

        public void writeToParcel(@NonNull Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.mExpanded ? 1 : 0);
        }
    }

    public FloatingActionsMenu(Context context) {
        this(context, null);
    }

    public FloatingActionsMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mExpandAnimation = new AnimatorSet().setDuration(300);
        this.mCollapseAnimation = new AnimatorSet().setDuration(300);
        init(context, attrs);
    }

    public FloatingActionsMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mExpandAnimation = new AnimatorSet().setDuration(300);
        this.mCollapseAnimation = new AnimatorSet().setDuration(300);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        int i;
        this.mButtonSpacing = (AndroidUtilities.dp((float) Toolbar.buttonSpaccing) - AndroidUtilities.dp(9.0f)) - AndroidUtilities.dp(3.0f);
        this.mLabelsMargin = AndroidUtilities.dp(8.0f);
        this.mLabelsVerticalOffset = AndroidUtilities.dp(3.0f);
        this.mTouchDelegateGroup = new TouchDelegateGroup(this);
        setTouchDelegate(this.mTouchDelegateGroup);
        this.mAddButtonPlusColor = Theme.getColor(Theme.key_chats_actionIcon);
        this.mAddButtonColorNormal = Theme.getColor(Theme.key_chats_actionBackground);
        this.mAddButtonColorPressed = Theme.getColor(Theme.key_chats_actionPressedBackground);
        this.mAddButtonSize = 0;
        this.mAddButtonStrokeVisible = true;
        if (Toolbar.direction == 0) {
            this.mExpandDirection = 0;
        } else if (Toolbar.direction == 1) {
            this.mExpandDirection = LocaleController.isRTL ? 3 : 2;
        }
        this.mLabelsStyle = 0;
        if (LocaleController.isRTL) {
            i = 1;
        } else {
            i = 0;
        }
        this.mLabelsPosition = i;
        if (this.mLabelsStyle == 0 || !expandsHorizontally()) {
            createAddButton(context);
            return;
        }
        throw new IllegalStateException("Action labels in horizontal expand orientation is not supported.");
    }

    public void setMenuButtonSize() {
        this.mAddButtonSize = 0;
    }

    public void setOnFloatingActionsMenuUpdateListener(OnFloatingActionsMenuUpdateListener listener) {
        this.mListener = listener;
    }

    private boolean expandsHorizontally() {
        return this.mExpandDirection == 2 || this.mExpandDirection == 3;
    }

    private void createAddButton(Context context) {
        this.mAddButton = new AddFloatingActionButton(context) {
            void updateBackground() {
                this.mPlusColor = FloatingActionsMenu.this.mAddButtonPlusColor;
                this.mColorNormal = FloatingActionsMenu.this.mAddButtonColorNormal;
                this.mColorPressed = FloatingActionsMenu.this.mAddButtonColorPressed;
                this.mStrokeVisible = FloatingActionsMenu.this.mAddButtonStrokeVisible;
                super.updateBackground();
            }

            Drawable getIconDrawable() {
                RotatingDrawable rotatingDrawable = new RotatingDrawable(super.getIconDrawable());
                FloatingActionsMenu.this.mRotatingDrawable = rotatingDrawable;
                OvershootInterpolator interpolator = new OvershootInterpolator();
                ObjectAnimator collapseAnimator = ObjectAnimator.ofFloat(rotatingDrawable, "rotation", new float[]{FloatingActionsMenu.EXPANDED_PLUS_ROTATION, 0.0f});
                ObjectAnimator expandAnimator = ObjectAnimator.ofFloat(rotatingDrawable, "rotation", new float[]{0.0f, FloatingActionsMenu.EXPANDED_PLUS_ROTATION});
                collapseAnimator.setInterpolator(interpolator);
                expandAnimator.setInterpolator(interpolator);
                FloatingActionsMenu.this.mExpandAnimation.play(expandAnimator);
                FloatingActionsMenu.this.mCollapseAnimation.play(collapseAnimator);
                return rotatingDrawable;
            }
        };
        this.mAddButton.setSize(this.mAddButtonSize);
        this.mAddButton.setOnClickListener(new C22712());
        addView(this.mAddButton, super.generateDefaultLayoutParams());
        this.mButtonsCount++;
    }

    public FloatingActionButton getMenuButton() {
        return this.mAddButton;
    }

    public void addButton(FloatingActionButton button) {
        addView(button, this.mButtonsCount - 1);
        this.mButtonsCount++;
        if (this.mLabelsStyle != 0) {
            createLabels();
        }
    }

    public void removeButton(FloatingActionButton button) {
        removeView(button.getLabelView());
        removeView(button);
        this.mButtonsCount--;
    }

    private int getColor(@ColorRes int id) {
        return getResources().getColor(id);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = 0;
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;
        this.mMaxButtonWidth = 0;
        this.mMaxButtonHeight = 0;
        int maxLabelWidth = 0;
        for (int i2 = 0; i2 < this.mButtonsCount; i2++) {
            View child = getChildAt(i2);
            if (child.getVisibility() != 8) {
                switch (this.mExpandDirection) {
                    case 0:
                    case 1:
                        this.mMaxButtonWidth = Math.max(this.mMaxButtonWidth, child.getMeasuredWidth());
                        height += child.getMeasuredHeight();
                        break;
                    case 2:
                    case 3:
                        width += child.getMeasuredWidth();
                        this.mMaxButtonHeight = Math.max(this.mMaxButtonHeight, child.getMeasuredHeight());
                        break;
                }
                if (!expandsHorizontally()) {
                    TextView label = null;
                    if (label != null) {
                        maxLabelWidth = Math.max(maxLabelWidth, label.getMeasuredWidth());
                    }
                }
            }
        }
        if (expandsHorizontally()) {
            height = this.mMaxButtonHeight;
        } else {
            int i3 = this.mMaxButtonWidth;
            if (maxLabelWidth > 0) {
                i = this.mLabelsMargin + maxLabelWidth;
            }
            width = i3 + i;
        }
        switch (this.mExpandDirection) {
            case 0:
            case 1:
                height = adjustForOvershoot(height + (this.mButtonSpacing * (this.mButtonsCount - 1)));
                break;
            case 2:
            case 3:
                width = adjustForOvershoot(width + (this.mButtonSpacing * (this.mButtonsCount - 1)));
                break;
        }
        setMeasuredDimension(width, height);
    }

    private int adjustForOvershoot(int dimension) {
        return (dimension * 12) / 10;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int i;
        View child;
        int childX;
        int childY;
        float collapsedTranslation;
        float f;
        LayoutParams params;
        switch (this.mExpandDirection) {
            case 0:
            case 1:
                int nextY;
                boolean expandUp = this.mExpandDirection == 0;
                if (changed) {
                    this.mTouchDelegateGroup.clearTouchDelegates();
                }
                int addButtonY = expandUp ? (b - t) - this.mAddButton.getMeasuredHeight() : 0;
                int buttonsHorizontalCenter = this.mLabelsPosition == 0 ? (r - l) - (this.mMaxButtonWidth / 2) : this.mMaxButtonWidth / 2;
                int addButtonLeft = buttonsHorizontalCenter - (this.mAddButton.getMeasuredWidth() / 2);
                this.mAddButton.layout(addButtonLeft, addButtonY, this.mAddButton.getMeasuredWidth() + addButtonLeft, this.mAddButton.getMeasuredHeight() + addButtonY);
                int labelsOffset = (this.mMaxButtonWidth / 2) + this.mLabelsMargin;
                int labelsXNearButton = this.mLabelsPosition == 0 ? buttonsHorizontalCenter - labelsOffset : buttonsHorizontalCenter + labelsOffset;
                if (expandUp) {
                    nextY = addButtonY - this.mButtonSpacing;
                } else {
                    nextY = (this.mAddButton.getMeasuredHeight() + addButtonY) + this.mButtonSpacing;
                }
                for (i = this.mButtonsCount - 1; i >= 0; i--) {
                    child = getChildAt(i);
                    if (!(child == this.mAddButton || child.getVisibility() == 8)) {
                        childX = buttonsHorizontalCenter - (child.getMeasuredWidth() / 2);
                        if (expandUp) {
                            childY = nextY - child.getMeasuredHeight();
                        } else {
                            childY = nextY;
                        }
                        child.layout(childX, childY, child.getMeasuredWidth() + childX, child.getMeasuredHeight() + childY);
                        collapsedTranslation = (float) (addButtonY - childY);
                        if (this.mExpanded) {
                            f = 0.0f;
                        } else {
                            f = collapsedTranslation;
                        }
                        child.setTranslationY(f);
                        child.setAlpha(this.mExpanded ? 1.0f : 0.0f);
                        params = (LayoutParams) child.getLayoutParams();
                        params.mCollapseDir.setFloatValues(new float[]{0.0f, collapsedTranslation});
                        params.mExpandDir.setFloatValues(new float[]{collapsedTranslation, 0.0f});
                        params.setAnimationsTarget(child);
                        View label = null;
                        if (label != null) {
                            int labelXAwayFromButton;
                            int labelLeft;
                            int labelRight;
                            if (this.mLabelsPosition == 0) {
                                labelXAwayFromButton = labelsXNearButton - label.getMeasuredWidth();
                            } else {
                                labelXAwayFromButton = labelsXNearButton + label.getMeasuredWidth();
                            }
                            if (this.mLabelsPosition == 0) {
                                labelLeft = labelXAwayFromButton;
                            } else {
                                labelLeft = labelsXNearButton;
                            }
                            if (this.mLabelsPosition == 0) {
                                labelRight = labelsXNearButton;
                            } else {
                                labelRight = labelXAwayFromButton;
                            }
                            int labelTop = (childY - this.mLabelsVerticalOffset) + ((child.getMeasuredHeight() - label.getMeasuredHeight()) / 2);
                            label.layout(labelLeft, labelTop, labelRight, label.getMeasuredHeight() + labelTop);
                            this.mTouchDelegateGroup.addTouchDelegate(new TouchDelegate(new Rect(Math.min(childX, labelLeft), childY - (this.mButtonSpacing / 2), Math.max(child.getMeasuredWidth() + childX, labelRight), (child.getMeasuredHeight() + childY) + (this.mButtonSpacing / 2)), child));
                            if (this.mExpanded) {
                                f = 0.0f;
                            } else {
                                f = collapsedTranslation;
                            }
                            label.setTranslationY(f);
                            label.setAlpha(this.mExpanded ? 1.0f : 0.0f);
                            LayoutParams labelParams = (LayoutParams) label.getLayoutParams();
                            labelParams.mCollapseDir.setFloatValues(new float[]{0.0f, collapsedTranslation});
                            labelParams.mExpandDir.setFloatValues(new float[]{collapsedTranslation, 0.0f});
                            labelParams.setAnimationsTarget(label);
                        }
                        if (expandUp) {
                            nextY = childY - this.mButtonSpacing;
                        } else {
                            nextY = (child.getMeasuredHeight() + childY) + this.mButtonSpacing;
                        }
                    }
                }
                return;
            case 2:
            case 3:
                int nextX;
                boolean expandLeft = this.mExpandDirection == 2;
                int addButtonX = expandLeft ? (r - l) - this.mAddButton.getMeasuredWidth() : 0;
                int addButtonTop = ((b - t) - this.mMaxButtonHeight) + ((this.mMaxButtonHeight - this.mAddButton.getMeasuredHeight()) / 2);
                this.mAddButton.layout(addButtonX, addButtonTop, this.mAddButton.getMeasuredWidth() + addButtonX, this.mAddButton.getMeasuredHeight() + addButtonTop);
                if (expandLeft) {
                    nextX = addButtonX - this.mButtonSpacing;
                } else {
                    nextX = (this.mAddButton.getMeasuredWidth() + addButtonX) + this.mButtonSpacing;
                }
                for (i = this.mButtonsCount - 1; i >= 0; i--) {
                    child = getChildAt(i);
                    if (!(child == this.mAddButton || child.getVisibility() == 8)) {
                        if (expandLeft) {
                            childX = nextX - child.getMeasuredWidth();
                        } else {
                            childX = nextX;
                        }
                        childY = addButtonTop + ((this.mAddButton.getMeasuredHeight() - child.getMeasuredHeight()) / 2);
                        child.layout(childX, childY, child.getMeasuredWidth() + childX, child.getMeasuredHeight() + childY);
                        collapsedTranslation = (float) (addButtonX - childX);
                        if (this.mExpanded) {
                            f = 0.0f;
                        } else {
                            f = collapsedTranslation;
                        }
                        child.setTranslationX(f);
                        child.setAlpha(this.mExpanded ? 1.0f : 0.0f);
                        params = (LayoutParams) child.getLayoutParams();
                        params.mCollapseDir.setFloatValues(new float[]{0.0f, collapsedTranslation});
                        params.mExpandDir.setFloatValues(new float[]{collapsedTranslation, 0.0f});
                        params.setAnimationsTarget(child);
                        if (expandLeft) {
                            nextX = childX - this.mButtonSpacing;
                        } else {
                            nextX = (child.getMeasuredWidth() + childX) + this.mButtonSpacing;
                        }
                    }
                }
                return;
            default:
                return;
        }
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(super.generateDefaultLayoutParams());
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(super.generateLayoutParams(attrs));
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return new LayoutParams(super.generateLayoutParams(p));
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        bringChildToFront(this.mAddButton);
        this.mButtonsCount = getChildCount();
        if (this.mLabelsStyle != 0) {
            createLabels();
        }
    }

    private void createLabels() {
        Context context = new ContextThemeWrapper(getContext(), this.mLabelsStyle);
        for (int i = 0; i < this.mButtonsCount; i++) {
            FloatingActionButton button = (FloatingActionButton) getChildAt(i);
            String title = button.getTitle();
            TextView label = new TextView(context);
            label.setTextAppearance(getContext(), this.mLabelsStyle);
            label.setText(button.getTitle());
            addView(label);
        }
    }

    public void collapse() {
        collapse(false);
    }

    public void collapseImmediately() {
        collapse(true);
    }

    private void collapse(boolean immediately) {
        if (this.mExpanded) {
            this.mExpanded = false;
            this.mTouchDelegateGroup.setEnabled(false);
            this.mCollapseAnimation.setDuration(immediately ? 0 : 300);
            this.mCollapseAnimation.start();
            this.mExpandAnimation.cancel();
            if (this.mListener != null) {
                this.mListener.onMenuCollapsed();
            }
        }
    }

    public void toggle() {
        if (this.mExpanded) {
            collapse();
        } else {
            expand();
        }
    }

    public void expand() {
        if (!this.mExpanded) {
            this.mExpanded = true;
            this.mTouchDelegateGroup.setEnabled(true);
            this.mCollapseAnimation.cancel();
            this.mExpandAnimation.start();
            if (this.mListener != null) {
                this.mListener.onMenuExpanded();
            }
        }
    }

    public boolean isExpanded() {
        return this.mExpanded;
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mAddButton.setEnabled(enabled);
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mExpanded = this.mExpanded;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState savedState = (SavedState) state;
            this.mExpanded = savedState.mExpanded;
            this.mTouchDelegateGroup.setEnabled(this.mExpanded);
            if (this.mRotatingDrawable != null) {
                this.mRotatingDrawable.setRotation(this.mExpanded ? EXPANDED_PLUS_ROTATION : 0.0f);
            }
            super.onRestoreInstanceState(savedState.getSuperState());
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
