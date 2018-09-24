package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$Message;

final /* synthetic */ class SecretChatHelper$$Lambda$23 implements Comparator {
    static final Comparator $instance = new SecretChatHelper$$Lambda$23();

    private SecretChatHelper$$Lambda$23() {
    }

    public int compare(Object obj, Object obj2) {
        return AndroidUtilities.compare(((TLRPC$Message) obj).seq_out, ((TLRPC$Message) obj2).seq_out);
    }
}
