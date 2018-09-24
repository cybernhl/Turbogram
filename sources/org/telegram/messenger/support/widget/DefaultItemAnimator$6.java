package org.telegram.messenger.support.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;

class DefaultItemAnimator$6 extends AnimatorListenerAdapter {
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ ViewPropertyAnimator val$animation;
    final /* synthetic */ int val$deltaX;
    final /* synthetic */ int val$deltaY;
    final /* synthetic */ ViewHolder val$holder;
    final /* synthetic */ View val$view;

    DefaultItemAnimator$6(DefaultItemAnimator this$0, ViewHolder viewHolder, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
        this.this$0 = this$0;
        this.val$holder = viewHolder;
        this.val$deltaX = i;
        this.val$view = view;
        this.val$deltaY = i2;
        this.val$animation = viewPropertyAnimator;
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.dispatchMoveStarting(this.val$holder);
    }

    public void onAnimationCancel(Animator animator) {
        if (this.val$deltaX != 0) {
            this.val$view.setTranslationX(0.0f);
        }
        if (this.val$deltaY != 0) {
            this.val$view.setTranslationY(0.0f);
        }
    }

    public void onAnimationEnd(Animator animator) {
        this.val$animation.setListener(null);
        this.this$0.dispatchMoveFinished(this.val$holder);
        this.this$0.mMoveAnimations.remove(this.val$holder);
        this.this$0.dispatchFinishedWhenDone();
    }
}
