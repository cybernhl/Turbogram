package org.telegram.ui.Components;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import org.telegram.tgnet.TLRPC$EncryptedChat;

final /* synthetic */ class AlertsCreator$$Lambda$29 implements OnClickListener {
    private final TLRPC$EncryptedChat arg$1;
    private final NumberPicker arg$2;

    AlertsCreator$$Lambda$29(TLRPC$EncryptedChat tLRPC$EncryptedChat, NumberPicker numberPicker) {
        this.arg$1 = tLRPC$EncryptedChat;
        this.arg$2 = numberPicker;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AlertsCreator.lambda$createTTLAlert$30$AlertsCreator(this.arg$1, this.arg$2, dialogInterface, i);
    }
}
