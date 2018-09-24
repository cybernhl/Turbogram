package org.telegram.ui;

import android.webkit.JavascriptInterface;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

class PaymentFormActivity$TelegramWebviewProxy {
    final /* synthetic */ PaymentFormActivity this$0;

    private PaymentFormActivity$TelegramWebviewProxy(PaymentFormActivity paymentFormActivity) {
        this.this$0 = paymentFormActivity;
    }

    @JavascriptInterface
    public void postEvent(String eventName, String eventData) {
        AndroidUtilities.runOnUIThread(new PaymentFormActivity$TelegramWebviewProxy$$Lambda$0(this, eventName, eventData));
    }

    final /* synthetic */ void lambda$postEvent$0$PaymentFormActivity$TelegramWebviewProxy(String eventName, String eventData) {
        if (this.this$0.getParentActivity() != null && eventName.equals("payment_form_submit")) {
            try {
                JSONObject jsonObject = new JSONObject(eventData);
                PaymentFormActivity.access$002(this.this$0, jsonObject.getJSONObject("credentials").toString());
                PaymentFormActivity.access$102(this.this$0, jsonObject.getString("title"));
            } catch (Throwable e) {
                PaymentFormActivity.access$002(this.this$0, eventData);
                FileLog.m1224e(e);
            }
            PaymentFormActivity.access$200(this.this$0);
        }
    }
}
