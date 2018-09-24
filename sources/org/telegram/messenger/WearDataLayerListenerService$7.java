package org.telegram.messenger;

import com.google.android.gms.wearable.MessageEvent;
import org.json.JSONObject;

class WearDataLayerListenerService$7 implements Runnable {
    final /* synthetic */ WearDataLayerListenerService this$0;
    final /* synthetic */ MessageEvent val$messageEvent;

    WearDataLayerListenerService$7(WearDataLayerListenerService this$0, MessageEvent messageEvent) {
        this.this$0 = this$0;
        this.val$messageEvent = messageEvent;
    }

    public void run() {
        try {
            ApplicationLoader.postInitApplication();
            JSONObject jSONObject = new JSONObject(new String(this.val$messageEvent.getData(), "UTF-8"));
            CharSequence text = jSONObject.getString("text");
            if (text != null && text.length() != 0) {
                long dialog_id = jSONObject.getLong("chat_id");
                int max_id = jSONObject.getInt("max_id");
                int currentAccount = -1;
                int accountID = jSONObject.getInt("account_id");
                for (int i = 0; i < UserConfig.getActivatedAccountsCount(); i++) {
                    if (UserConfig.getInstance(i).getClientUserId() == accountID) {
                        currentAccount = i;
                        break;
                    }
                }
                if (dialog_id != 0 && max_id != 0 && currentAccount != -1) {
                    SendMessagesHelper.getInstance(currentAccount).sendMessage(text.toString(), dialog_id, null, null, true, null, null, null);
                    MessagesController.getInstance(currentAccount).markDialogAsRead(dialog_id, max_id, max_id, 0, false, 0, true);
                }
            }
        } catch (Exception x) {
            if (BuildVars.LOGS_ENABLED) {
                FileLog.e(x);
            }
        }
    }
}
