package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$Updates;

final /* synthetic */ class MessagesController$$Lambda$116 implements Comparator {
    static final Comparator $instance = new MessagesController$$Lambda$116();

    private MessagesController$$Lambda$116() {
    }

    public int compare(Object obj, Object obj2) {
        return MessagesController.lambda$processUpdatesQueue$176$MessagesController((TLRPC$Updates) obj, (TLRPC$Updates) obj2);
    }
}
