package org.telegram.messenger.support.widget;

import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;

class FastScroller$2 extends OnScrollListener {
    final /* synthetic */ FastScroller this$0;

    FastScroller$2(FastScroller this$0) {
        this.this$0 = this$0;
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        this.this$0.updateScrollPosition(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
    }
}
