package org.telegram.messenger.support.widget;

import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;

class SnapHelper$1 extends OnScrollListener {
    boolean mScrolled = false;
    final /* synthetic */ SnapHelper this$0;

    SnapHelper$1(SnapHelper this$0) {
        this.this$0 = this$0;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == 0 && this.mScrolled) {
            this.mScrolled = false;
            this.this$0.snapToTargetExistingView();
        }
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dx != 0 || dy != 0) {
            this.mScrolled = true;
        }
    }
}
