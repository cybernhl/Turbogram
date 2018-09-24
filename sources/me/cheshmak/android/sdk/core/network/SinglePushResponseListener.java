package me.cheshmak.android.sdk.core.network;

public interface SinglePushResponseListener {
    void onError(Throwable th);

    void onUniqueIdReceived(String str);
}
