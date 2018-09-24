package org.telegram.messenger.support.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class FastScroller$AnimatorListener extends AnimatorListenerAdapter {
    private boolean mCanceled;
    final /* synthetic */ FastScroller this$0;

    private FastScroller$AnimatorListener(FastScroller fastScroller) {
        this.this$0 = fastScroller;
        this.mCanceled = false;
    }

    public void onAnimationEnd(Animator animation) {
        if (this.mCanceled) {
            this.mCanceled = false;
        } else if (((Float) FastScroller.access$200(this.this$0).getAnimatedValue()).floatValue() == 0.0f) {
            FastScroller.access$302(this.this$0, 0);
            FastScroller.access$400(this.this$0, 0);
        } else {
            FastScroller.access$302(this.this$0, 2);
            FastScroller.access$500(this.this$0);
        }
    }

    public void onAnimationCancel(Animator animation) {
        this.mCanceled = true;
    }
}
