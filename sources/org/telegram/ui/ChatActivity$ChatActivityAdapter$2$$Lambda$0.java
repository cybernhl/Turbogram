package org.telegram.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.ui.ChatActivity.ChatActivityAdapter.C13212;

final /* synthetic */ class ChatActivity$ChatActivityAdapter$2$$Lambda$0 implements OnClickListener {
    private final C13212 arg$1;
    private final String arg$2;

    ChatActivity$ChatActivityAdapter$2$$Lambda$0(C13212 c13212, String str) {
        this.arg$1 = c13212;
        this.arg$2 = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$didPressedUrl$0$ChatActivity$ChatActivityAdapter$2(this.arg$2, dialogInterface, i);
    }
}
