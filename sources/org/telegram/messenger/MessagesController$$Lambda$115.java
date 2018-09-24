package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$Updates;

final /* synthetic */ class MessagesController$$Lambda$115 implements Comparator {
    static final Comparator $instance = new MessagesController$$Lambda$115();

    private MessagesController$$Lambda$115() {
    }

    public int compare(Object obj, Object obj2) {
        return MessagesController.lambda$processUpdatesQueue$175$MessagesController((TLRPC$Updates) obj, (TLRPC$Updates) obj2);
    }
}
