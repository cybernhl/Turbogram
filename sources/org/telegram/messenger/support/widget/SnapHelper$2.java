package org.telegram.messenger.support.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import org.telegram.messenger.support.widget.RecyclerView.State;

class SnapHelper$2 extends LinearSmoothScroller {
    final /* synthetic */ SnapHelper this$0;

    SnapHelper$2(SnapHelper this$0, Context context) {
        this.this$0 = this$0;
        super(context);
    }

    protected void onTargetFound(View targetView, State state, RecyclerView$SmoothScroller$Action action) {
        if (this.this$0.mRecyclerView != null) {
            int[] snapDistances = this.this$0.calculateDistanceToFinalSnap(this.this$0.mRecyclerView.getLayoutManager(), targetView);
            int dx = snapDistances[0];
            int dy = snapDistances[1];
            int time = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)));
            if (time > 0) {
                action.update(dx, dy, time, this.mDecelerateInterpolator);
            }
        }
    }

    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 100.0f / ((float) displayMetrics.densityDpi);
    }
}
