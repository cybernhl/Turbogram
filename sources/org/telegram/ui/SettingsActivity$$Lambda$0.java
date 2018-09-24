package org.telegram.ui;

import org.telegram.tgnet.TLRPC$InputFile;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_secureFile;
import org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate;

final /* synthetic */ class SettingsActivity$$Lambda$0 implements ImageUpdaterDelegate {
    private final SettingsActivity arg$1;

    SettingsActivity$$Lambda$0(SettingsActivity settingsActivity) {
        this.arg$1 = settingsActivity;
    }

    public void didUploadedPhoto(TLRPC$InputFile tLRPC$InputFile, TLRPC$PhotoSize tLRPC$PhotoSize, TLRPC$PhotoSize tLRPC$PhotoSize2, TLRPC$TL_secureFile tLRPC$TL_secureFile) {
        this.arg$1.lambda$onFragmentCreate$2$SettingsActivity(tLRPC$InputFile, tLRPC$PhotoSize, tLRPC$PhotoSize2, tLRPC$TL_secureFile);
    }
}
