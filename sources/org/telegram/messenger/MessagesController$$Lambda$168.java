package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC$updates_Difference;

final /* synthetic */ class MessagesController$$Lambda$168 implements Runnable {
    private final MessagesController arg$1;
    private final TLRPC$updates_Difference arg$2;
    private final ArrayList arg$3;
    private final SparseArray arg$4;
    private final SparseArray arg$5;

    MessagesController$$Lambda$168(MessagesController messagesController, TLRPC$updates_Difference tLRPC$updates_Difference, ArrayList arrayList, SparseArray sparseArray, SparseArray sparseArray2) {
        this.arg$1 = messagesController;
        this.arg$2 = tLRPC$updates_Difference;
        this.arg$3 = arrayList;
        this.arg$4 = sparseArray;
        this.arg$5 = sparseArray2;
    }

    public void run() {
        this.arg$1.lambda$null$195$MessagesController(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
