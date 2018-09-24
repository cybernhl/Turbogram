package turbogram.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.baranak.turbogramf.R;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import turbogram.Components.PatternView.PatternView;
import turbogram.Components.PatternView.PatternView.OnPatternDetectedListener;
import turbogram.Utilities.TurboConfig;

public class TurboPatternView extends FrameLayout {
    private Drawable backgroundDrawable;
    private FrameLayout backgroundFrameLayout;
    private int checkType;
    private TurboPatternViewDelegate delegate;
    private TextView passcodeTextView;
    private FrameLayout passwordFrameLayout;
    private PatternView patternView;

    public interface TurboPatternViewDelegate {
        void didAcceptedPattern();
    }

    /* renamed from: turbogram.Components.TurboPatternView$1 */
    class C22971 implements OnPatternDetectedListener {
        C22971() {
        }

        public void onPatternDetected() {
            TurboPatternView.this.processDone();
        }
    }

    /* renamed from: turbogram.Components.TurboPatternView$2 */
    class C22982 extends AnimatorListenerAdapter {
        C22982() {
        }

        public void onAnimationEnd(Animator animation) {
            TurboPatternView.this.setVisibility(8);
        }
    }

    /* renamed from: turbogram.Components.TurboPatternView$4 */
    class C23004 implements OnTouchListener {
        C23004() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    /* renamed from: turbogram.Components.TurboPatternView$5 */
    class C23015 extends AnimatorListenerAdapter {
        C23015() {
        }

        public void onAnimationEnd(Animator animation) {
            TurboPatternView.this.setVisibility(8);
        }
    }

    public TurboPatternView(Context context, int checkType) {
        super(context);
        this.checkType = checkType;
        setWillNotDraw(false);
        setVisibility(8);
        this.backgroundFrameLayout = new FrameLayout(context);
        addView(this.backgroundFrameLayout);
        LayoutParams layoutParams = (LayoutParams) this.backgroundFrameLayout.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.backgroundFrameLayout.setLayoutParams(layoutParams);
        this.passwordFrameLayout = new FrameLayout(context);
        addView(this.passwordFrameLayout);
        layoutParams = (LayoutParams) this.passwordFrameLayout.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 51;
        this.passwordFrameLayout.setLayoutParams(layoutParams);
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setImageResource(R.drawable.passcode_logo);
        this.passwordFrameLayout.addView(imageView);
        layoutParams = (LayoutParams) imageView.getLayoutParams();
        if (AndroidUtilities.density < 1.0f) {
            layoutParams.width = AndroidUtilities.dp(30.0f);
            layoutParams.height = AndroidUtilities.dp(30.0f);
        } else {
            layoutParams.width = AndroidUtilities.dp(40.0f);
            layoutParams.height = AndroidUtilities.dp(40.0f);
        }
        layoutParams.gravity = 81;
        layoutParams.bottomMargin = AndroidUtilities.dp(100.0f);
        imageView.setLayoutParams(layoutParams);
        this.passcodeTextView = new TextView(context);
        this.passcodeTextView.setTextColor(-1);
        this.passcodeTextView.setTextSize(1, 14.0f);
        this.passcodeTextView.setGravity(1);
        this.passwordFrameLayout.addView(this.passcodeTextView);
        layoutParams = (LayoutParams) this.passcodeTextView.getLayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.bottomMargin = AndroidUtilities.dp(62.0f);
        layoutParams.gravity = 81;
        this.passcodeTextView.setLayoutParams(layoutParams);
        this.patternView = new PatternView(context);
        addView(this.patternView);
        this.patternView.setOnPatternDetectedListener(new C22971());
    }

    public int getCheckType() {
        return this.checkType;
    }

    public void setCheckType(int type) {
        this.checkType = type;
    }

    public void setDelegate(TurboPatternViewDelegate delegate) {
        this.delegate = delegate;
    }

    private void processDone() {
        String password = this.patternView.getPatternString().toString();
        if (password.length() == 0) {
            onPasscodeError();
            return;
        }
        String pass = TurboConfig.chatHidePattern;
        if (this.checkType == 1) {
            pass = TurboConfig.chatLockPattern;
        } else if (this.checkType == 2) {
            pass = TurboConfig.appLockPattern;
        }
        if (password.equals(pass)) {
            AnimatorSet AnimatorSet = new AnimatorSet();
            AnimatorSet.setDuration(200);
            r3 = new Animator[2];
            r3[0] = ObjectAnimator.ofFloat(this, "translationY", new float[]{(float) AndroidUtilities.dp(20.0f)});
            r3[1] = ObjectAnimator.ofFloat(this, "alpha", new float[]{(float) AndroidUtilities.dp(0.0f)});
            AnimatorSet.playTogether(r3);
            AnimatorSet.addListener(new C22982());
            AnimatorSet.start();
            setOnTouchListener(null);
            if (this.delegate != null) {
                this.delegate.didAcceptedPattern();
                return;
            }
            return;
        }
        this.patternView.clearPattern();
        onPasscodeError();
    }

    private void shakeTextView(final float x, final int num) {
        if (num != 6) {
            AnimatorSet AnimatorSet = new AnimatorSet();
            Animator[] animatorArr = new Animator[1];
            animatorArr[0] = ObjectAnimator.ofFloat(this.passcodeTextView, "translationX", new float[]{(float) AndroidUtilities.dp(x)});
            AnimatorSet.playTogether(animatorArr);
            AnimatorSet.setDuration(50);
            AnimatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    TurboPatternView.this.shakeTextView(num == 5 ? 0.0f : -x, num + 1);
                }
            });
            AnimatorSet.start();
        }
    }

    private void onPasscodeError() {
        Vibrator v = (Vibrator) getContext().getSystemService("vibrator");
        if (v != null) {
            v.vibrate(200);
        }
        shakeTextView(2.0f, 0);
    }

    public void onShow() {
        if (getVisibility() != 0) {
            setAlpha(1.0f);
            setTranslationY(0.0f);
            if (ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).getInt("selectedBackground", 1000001) == 1000001) {
                this.backgroundFrameLayout.setBackgroundColor(-11436898);
            } else {
                this.backgroundDrawable = Theme.getCachedWallpaper();
                if (this.backgroundDrawable != null) {
                    this.backgroundFrameLayout.setBackgroundColor(-1090519040);
                } else {
                    this.backgroundFrameLayout.setBackgroundColor(-11436898);
                }
            }
            this.passcodeTextView.setText(LocaleController.getString("DrawYourPattern", R.string.DrawYourPattern));
            this.passcodeTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            setVisibility(0);
            this.patternView.clearPattern();
            setOnTouchListener(new C23004());
        }
    }

    public void hide() {
        if (getVisibility() == 0) {
            AnimatorSet AnimatorSet = new AnimatorSet();
            AnimatorSet.setDuration(200);
            r1 = new Animator[2];
            r1[0] = ObjectAnimator.ofFloat(this, "translationY", new float[]{(float) AndroidUtilities.dp(20.0f)});
            r1[1] = ObjectAnimator.ofFloat(this, "alpha", new float[]{(float) AndroidUtilities.dp(0.0f)});
            AnimatorSet.playTogether(r1);
            AnimatorSet.addListener(new C23015());
            AnimatorSet.start();
        }
    }

    public boolean isVisible() {
        if (getVisibility() == 0) {
            return true;
        }
        return false;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = AndroidUtilities.displaySize.y - (VERSION.SDK_INT >= 21 ? 0 : AndroidUtilities.statusBarHeight);
        LayoutParams layoutParams;
        if (AndroidUtilities.isTablet() || getContext().getResources().getConfiguration().orientation != 2) {
            int top = 0;
            int left = 0;
            if (AndroidUtilities.isTablet()) {
                if (width > AndroidUtilities.dp(498.0f)) {
                    left = (width - AndroidUtilities.dp(498.0f)) / 2;
                    width = AndroidUtilities.dp(498.0f);
                }
                if (height > AndroidUtilities.dp(528.0f)) {
                    top = (height - AndroidUtilities.dp(528.0f)) / 2;
                    height = AndroidUtilities.dp(528.0f);
                }
            }
            layoutParams = (LayoutParams) this.passwordFrameLayout.getLayoutParams();
            layoutParams.height = height / 3;
            layoutParams.width = width;
            layoutParams.topMargin = top;
            layoutParams.leftMargin = left;
            this.passwordFrameLayout.setTag(Integer.valueOf(top));
            this.passwordFrameLayout.setLayoutParams(layoutParams);
            layoutParams = (LayoutParams) this.patternView.getLayoutParams();
            layoutParams.height = (height / 3) * 2;
            layoutParams.leftMargin = left;
            layoutParams.topMargin = (height - layoutParams.height) + top;
            layoutParams.width = width;
            this.patternView.setLayoutParams(layoutParams);
        } else {
            layoutParams = (LayoutParams) this.passwordFrameLayout.getLayoutParams();
            layoutParams.width = width / 2;
            layoutParams.height = AndroidUtilities.dp(140.0f);
            layoutParams.topMargin = (height - AndroidUtilities.dp(140.0f)) / 2;
            this.passwordFrameLayout.setLayoutParams(layoutParams);
            layoutParams = (LayoutParams) this.patternView.getLayoutParams();
            layoutParams.height = height;
            layoutParams.leftMargin = width / 2;
            layoutParams.topMargin = height - layoutParams.height;
            layoutParams.width = width / 2;
            this.patternView.setLayoutParams(layoutParams);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        if (getVisibility() == 0) {
            if (this.backgroundDrawable == null) {
                super.onDraw(canvas);
            } else if (this.backgroundDrawable instanceof ColorDrawable) {
                this.backgroundDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
                this.backgroundDrawable.draw(canvas);
            } else {
                float scale;
                float scaleX = ((float) getMeasuredWidth()) / ((float) this.backgroundDrawable.getIntrinsicWidth());
                float scaleY = ((float) getMeasuredHeight()) / ((float) this.backgroundDrawable.getIntrinsicHeight());
                if (scaleX < scaleY) {
                    scale = scaleY;
                } else {
                    scale = scaleX;
                }
                int width = (int) Math.ceil((double) (((float) this.backgroundDrawable.getIntrinsicWidth()) * scale));
                int height = (int) Math.ceil((double) (((float) this.backgroundDrawable.getIntrinsicHeight()) * scale));
                int x = (getMeasuredWidth() - width) / 2;
                int y = (getMeasuredHeight() - height) / 2;
                this.backgroundDrawable.setBounds(x, y, x + width, y + height);
                this.backgroundDrawable.draw(canvas);
            }
        }
    }
}
