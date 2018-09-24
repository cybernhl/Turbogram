package org.telegram.messenger.support.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;

class DefaultItemAnimator$4 extends AnimatorListenerAdapter {
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ ViewPropertyAnimator val$animation;
    final /* synthetic */ ViewHolder val$holder;
    final /* synthetic */ View val$view;

    DefaultItemAnimator$4(DefaultItemAnimator this$0, ViewHolder viewHolder, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.this$0 = this$0;
        this.val$holder = viewHolder;
        this.val$animation = viewPropertyAnimator;
        this.val$view = view;
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.dispatchRemoveStarting(this.val$holder);
    }

    public void onAnimationEnd(Animator animator) {
        this.val$animation.setListener(null);
        this.val$view.setAlpha(1.0f);
        this.this$0.dispatchRemoveFinished(this.val$holder);
        this.this$0.mRemoveAnimations.remove(this.val$holder);
        this.this$0.dispatchFinishedWhenDone();
    }
}
