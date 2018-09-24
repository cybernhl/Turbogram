package org.telegram.messenger.support.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;

class DefaultItemAnimator$5 extends AnimatorListenerAdapter {
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ ViewPropertyAnimator val$animation;
    final /* synthetic */ ViewHolder val$holder;
    final /* synthetic */ View val$view;

    DefaultItemAnimator$5(DefaultItemAnimator this$0, ViewHolder viewHolder, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.this$0 = this$0;
        this.val$holder = viewHolder;
        this.val$view = view;
        this.val$animation = viewPropertyAnimator;
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.dispatchAddStarting(this.val$holder);
    }

    public void onAnimationCancel(Animator animator) {
        this.val$view.setAlpha(1.0f);
    }

    public void onAnimationEnd(Animator animator) {
        this.val$animation.setListener(null);
        this.this$0.dispatchAddFinished(this.val$holder);
        this.this$0.mAddAnimations.remove(this.val$holder);
        this.this$0.dispatchFinishedWhenDone();
    }
}
